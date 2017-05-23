package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.dao.IOrganizationDao;
import com.jmhz.device.sys.entity.Organization;
import com.jmhz.device.sys.service.IOrganizationService;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Resource
    private CacheManager shiroCacheManager;

    @Autowired
    private IOrganizationDao organizationDao;


    @Override
    public String createOrganization(Organization organization) {
        return organizationDao.save(organization).toString();
    }

    @Override
    public void updateOrganization(Organization organization) {
        organizationDao.update(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        Organization organization = findOne(organizationId);
        final String deleteDescendantsSql = "delete from sys_organization where parent_ids like '"+organization.makeSelfAsParentIds()+"%'";
        organizationDao.delete(organization);
        organizationDao.executeSql(deleteDescendantsSql);
    }

    @Override
    public Organization findOne(Long organizationId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", organizationId);
        String hql = "select o from Organization o where id=:id";
        return organizationDao.get(hql, params);
    }

    @Override
    public List<Organization> findAll() {
        //Map<String, Object> params = new HashMap<String, Object>();
        //params.put("available", 1);
        //String hql = "select o from Organization o where o.available=:available";
        String hql = "from Organization";
        return organizationDao.find(hql);
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        StringBuilder hql = new StringBuilder();
        hql.append("select o from Organization o where o.id!=:id and parent_ids not like :parentIds and parent_id!=:parent_id ");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", excludeOraganization.getId());
        params.put("parentIds", excludeOraganization.makeSelfAsParentIds() + "%");
        params.put("parent_id", excludeOraganization.getId());

        String hqlForLevel2 = "select o from Organization o where parent_id=:parent_id";
        Map<String, Object> paramsForLevel2 = new HashMap<String, Object>();
        paramsForLevel2.put("parent_id", excludeOraganization.getId());
        List<Organization> organizationList = organizationDao.find(hqlForLevel2, paramsForLevel2);

        int i = 0;
        for (Organization organization : organizationList) {
            String tempParam = "parent_id_"+i;
            hql.append("and parent_id!=:"+ tempParam +" ");
            params.put(tempParam, organization.getId());
            i++;
        }
        return organizationDao.find(hql.toString().trim(), params);
    }

    @Override
    public void move(Organization source, Organization target) {
        Organization orgForUpdate = new Organization();
        orgForUpdate.setId(source.getId());
        orgForUpdate.setParentId(target.getId());
        orgForUpdate.setParentIds(target.getParentIds());
        orgForUpdate.setName(source.getName());
        orgForUpdate.setAvailable(source.getAvailable());
        organizationDao.merge(orgForUpdate);
        String moveSourceDescendantsSql =
                "update sys_organization set parent_ids=concat('"+target.makeSelfAsParentIds()+"', substring(parent_ids, " +
                        "length('"+source.makeSelfAsParentIds()+"'))) where parent_ids like '"+ source.makeSelfAsParentIds()+"%'";
        organizationDao.executeSql(moveSourceDescendantsSql);
    }

}
