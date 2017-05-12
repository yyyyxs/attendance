package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.dao.IRoleDao;
import com.jmhz.device.sys.entity.Role;
import com.jmhz.device.sys.service.IResourceService;
import com.jmhz.device.sys.service.IRoleService;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private CacheManager shiroCacheManager;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IResourceService resourceService;


    @Override
    public String createRole(Role role) {
        return roleDao.save(role).toString();
    }

    @Override
    public void updateRole(Role role) {
        roleDao.merge(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        String sql = "delete from sys_role where id="+ roleId;
        roleDao.executeSql(sql);
    }

    @Override
    public Role findOne(Long roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        String hql = "select r from Role r where r.id=:roleId";
        return roleDao.get(hql, params);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.find("from Role");
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(List<Long> roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for (Long roleId : roleIds) {
            Role role = findOne(roleId);
            if (role != null) {
                resourceIds.addAll(getResourceIds(role));
            }
        }
        return resourceService.findPermissions(resourceIds);
    }

    public List<Long> getResourceIds(Role role) {
        if (role.getResourceIds() == null || role.getResourceIds().equals("")) {
            return new ArrayList<Long>();
        }
        List<Long> resourceIds = new ArrayList<Long>();
        String[] resourceIdsList = role.getResourceIds().split(",");
        for (String s : resourceIdsList) {
            resourceIds.add(Long.valueOf(s));
        }
        return resourceIds;
    }

    public String getResourceIdsStr(Role role) {
        if (role.getResourceIds().equals("") || role.getResourceIds() == null) {
            return "";
        }
        return role.getResourceIds();
    }



}
