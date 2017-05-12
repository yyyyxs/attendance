package com.jmhz.device.sys.service.impl;

import com.jmhz.device.Constants;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.sys.dao.IUserDao;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.exception.UserNotExistsException;
import com.jmhz.device.sys.exception.UserPasswordNotMatchException;
import com.jmhz.device.sys.service.IRoleService;
import com.jmhz.device.sys.service.IUserService;
import com.jmhz.device.sys.utils.UserLogUtils;
import com.jmhz.device.util.SearchUtil;
import org.apache.shiro.cache.CacheManager;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private IRoleService roleService;

    @Resource
    private CacheManager shiroCacheManager;


    private boolean maybeUsername(String username) {
        if (!username.matches(Constants.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < Constants.USERNAME_MIN_LENGTH || username.length() > Constants.USERNAME_MAX_LENGTH) {
            return false;
        }
        return true;
    }

    @Override
    public String createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.save(user).toString();
    }

    @Override
    public void updateUser(User user) {
        userDao.merge(user);
    }

    @Override
    public void deleteUser(Long id) {
        String hql = "delete from User u where u.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        userDao.executeHql(hql, params);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        updateUser(user);
    }

    @Override
    public User findOne(Long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "select u from User u where u.id=:userId";
        return userDao.get(hql, params);
    }

    @Override
    public List<User> findAll() {
        return userDao.find("from User");
    }

    @Override
    public List<User> findTeacher() {
        return userDao.find("from User u where u.roleIds=4 and u.teacherId=0");
    }

    @Override
    public List<User> findAll(int page, int rows) {
        String hql = "from User t order by t.id desc";
        return userDao.find(hql, page, rows);
    }

    @Override //Create by fjmjuhqc on 2015-10-29
    public List<User> findAll(int page, int rows, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "from User t where t.teacherId =:userId order by t.id desc";
        return userDao.find(hql,params, page, rows);
    }

    @Override
    public List<User> findAll(int page, int rows, int userId, Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        System.out.println("userId=" + userId);
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from User t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " and t.teacherId= :userId order by t.id desc";
        // System.out.print("deviimpl0000"+hql);
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        // System.out.println("page=" + Integer.parseInt(page)+" rows="+Integer.parseInt(rows));

        //List<User> users = userDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        List<User> users = userDao.find(hql, params, page, rows);
        return users;
    }

    @Override
    public Long getCount(Filters filtersClass, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from User t where t.userId= :userId and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        //List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = userDao.count(hql, params);
        return count;
    }

    @Override
    public Long getCount() {
        return userDao.count("select count(*) from User");
    }

    @Override //Create by fjmjuhqc on 2015-10-29
    public Long getCount(int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql="select count(*) from User t where t.teacherId=:userId";
        return userDao.count(hql,params);
    }

    @Override
    public User findByUsername(String username) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        String hql = "select u from User u where u.username=:username";
        return userDao.get(hql, params);
    }

    @Override
    public Set<String> findRoles(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(getRoleIds(user).toArray(new Long[0]));
    }

    @Override
    public Set<String> findPermissions(String username) {
        User user = findByUsername(username);
        if (user == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(getRoleIds(user));
    }

    @Override
    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(username, "loginError", "username is empty");
            throw new UserNotExistsException();
        }
        //密码如果不在指定范围内[5-50] 肯定错误
        if (password.length() < Constants.PASSWORD_MIN_LENGTH || password.length() > Constants.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(username, "loginError", "password length error! password is between {} and {}",
                    Constants.PASSWORD_MIN_LENGTH, Constants.PASSWORD_MAX_LENGTH);
            throw new UserPasswordNotMatchException();
        }
        User user = null;
        //此处需要走代理对象，目的是能走缓存切面
        IUserService proxyUserService = (IUserService) AopContext.currentProxy();
        if (maybeUsername(username)) {
            user = proxyUserService.findByUsername(username);
        }
        if (user == null || Boolean.TRUE.equals(user.getLocked())) {
            UserLogUtils.log(username, "loginError", "user is locked!");
            throw new UserNotExistsException();
        }
        passwordService.validate(user, password);
        UserLogUtils.log(username, "loginSuccess", "");
        return user;
    }

    public List<Long> getRoleIds(User user) {
        if (user.getRoleIds() == null) {
            user.setRoleIds("");
        }
        List<Long> roleIds = new ArrayList<Long>();
        String[] roleIdsList = user.getRoleIds().split(",");
        for (String s : roleIdsList) {
            roleIds.add(Long.valueOf(s));
        }
        return roleIds;
    }

    public String getRoleIdsStr(User user) {
        if (user.getRoleIds().equals("") || user.getRoleIds() == null) {
            return "";
        }
        return user.getRoleIds();
    }
}
