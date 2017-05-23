package com.jmhz.device.sys.service;

import com.jmhz.device.sys.entity.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IResourceService {

    public String createResource(Resource resource);

    public void updateResource(Resource resource);

    public void deleteResource(Long resourceId, String parentIds);

    Resource findOne(Long resourceId);

    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     *
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     *
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

    String getResourceNames(Collection<Long> resourceIds);

}
