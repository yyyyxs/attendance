package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.dao.IResourceDao;
import com.jmhz.device.sys.service.IResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Resource
    private CacheManager shiroCacheManager;

    @Autowired
    private IResourceDao resourceDao;

    @Override
    public String createResource(com.jmhz.device.sys.entity.Resource resource) {
        return resourceDao.save(resource).toString();
    }

    @Override
    public void updateResource(com.jmhz.device.sys.entity.Resource resource) {
        resourceDao.merge(resource);
    }

    @Override
    public void deleteResource(Long resourceId, String parentIds) {
        String hqlToDel = "delete from Resource r where r.id=:resourceId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("resourceId", resourceId);
        resourceDao.executeHql(hqlToDel, params);
        final String deleteDescendantsSql = "delete from Resource where parent_ids like :parentids";
        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("parentids", parentIds + "%");
        resourceDao.executeHql(deleteDescendantsSql, params2);
    }

    @Override
    public com.jmhz.device.sys.entity.Resource findOne(Long resourceId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", resourceId);
        String hql = "select r from Resource r where id=:id";
        return resourceDao.get(hql, params);
    }

    @Override
    public List<com.jmhz.device.sys.entity.Resource> findAll() {
        String hql = "from Resource";
        return resourceDao.find(hql);
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for (Long resourceId : resourceIds) {
            com.jmhz.device.sys.entity.Resource resource = findOne(resourceId);
            if (resource != null && !org.springframework.util.StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<com.jmhz.device.sys.entity.Resource> findMenus(Set<String> permissions) {
        List<com.jmhz.device.sys.entity.Resource> allResources = findAll();
        List<com.jmhz.device.sys.entity.Resource> menus = new ArrayList<com.jmhz.device.sys.entity.Resource>();
        for (com.jmhz.device.sys.entity.Resource resource : allResources) {
            if (resource.getParentId() == 0) {
                continue;
            }
            if (resource.getType() != com.jmhz.device.sys.entity.Resource.ResourceType.menu) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, com.jmhz.device.sys.entity.Resource resource) {
        if (org.springframework.util.StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    public String getResourceNames(Collection<Long> resourceIds) {
        if (org.springframework.util.CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for (Long resourceId : resourceIds) {
            com.jmhz.device.sys.entity.Resource resource = findOne(resourceId);
            if (resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if (s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
}
