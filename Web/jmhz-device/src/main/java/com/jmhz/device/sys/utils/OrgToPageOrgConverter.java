package com.jmhz.device.sys.utils;

import com.jmhz.device.sys.entity.Organization;
import com.jmhz.device.sys.pageEntity.ZTreeNode;

import java.util.ArrayList;
import java.util.List;

public class OrgToPageOrgConverter {

    public static final List<ZTreeNode> converter(List<Organization> source) {
        List<ZTreeNode> zTreeNodeList = new ArrayList<ZTreeNode>();
        for (Organization org : source) {
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setId(org.getId());
            zTreeNode.setpId(org.getParentId());
            zTreeNode.setName(org.getName());
            zTreeNode.setOpen(org.getParentId() == 0);
            zTreeNode.setParentId(org.getParentId());
            zTreeNode.setParentIds(org.getParentIds());
            zTreeNode.setAvailable(org.getAvailable());
            zTreeNodeList.add(zTreeNode);
        }
        return zTreeNodeList;
    }

}
