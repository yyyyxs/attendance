package com.jmhz.device.sys.service;

import com.jmhz.device.sys.entity.Organization;

import java.util.List;

public interface IOrganizationService {

    public String createOrganization(Organization organization);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);

}
