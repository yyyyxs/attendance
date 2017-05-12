package com.jmhz.device.sys.service;

import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.sys.entity.User;

import java.util.List;
import java.util.Set;

public interface IUserService {

    /**
     * 创建用户
     *
     * @param user
     */
    public String createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long id);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);


    User findOne(Long userId);

    List<User> findAll();

    List<User> findTeacher();

    List<User> findAll(int page, int rows);

    List<User> findAll(int page, int rows,int userId);

    List<User> findAll(int page, int rows, int userId, Filters filtersClass);

    Long getCount(Filters filtersClass, int userId);

    Long getCount();

    Long getCount(int userId);
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    public User login(String username, String password);

}
