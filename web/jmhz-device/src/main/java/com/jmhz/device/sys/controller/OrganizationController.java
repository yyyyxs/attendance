package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import com.jmhz.device.sys.pageEntity.ZTreeNode;
import com.jmhz.device.sys.service.IOrganizationService;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.entity.Organization;
import com.jmhz.device.sys.utils.OrgToPageOrgConverter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> .
 * <p> Created at 2014/4/21 11:13
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    @Autowired
    private IOrganizationService organizationService;

    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "system");
        model.addAttribute("subMenu", "organization");
        return "/jsp/system/organization/index.jsp";
    }

    @ResponseBody
    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/appendChild", method = RequestMethod.POST)
    public JsonModel create(Organization organization) {
        String id = organizationService.createOrganization(organization);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加成功");
        jsonModel.setDataObj(id);
        return jsonModel;
    }

    @ResponseBody
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/modifyName", method = RequestMethod.POST)
    public JsonModel modifyName(Organization organization) {
        organizationService.updateOrganization(organization);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("修改成功");
        return jsonModel;
    }

    @ResponseBody
    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public JsonModel delete(@PathVariable("id") Long id) {
        organizationService.deleteOrganization(id);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("删除成功");
        return jsonModel;
    }

    @ResponseBody
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/targetList/{sourceId}", method = RequestMethod.GET)
    public List<ZTreeNode> showMoveForm(@PathVariable("sourceId") Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        List<Organization> organizationList = organizationService.findAllWithExclude(source);
        List<ZTreeNode> zTreeNodeList = OrgToPageOrgConverter.converter(organizationList);
        return zTreeNodeList;
    }

    @ResponseBody
    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/move/{sourceId}/{targetId}", method = RequestMethod.POST)
    public JsonModel move(
            @PathVariable("sourceId") Long sourceId,
            @PathVariable("targetId") Long targetId) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.move(source, target);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("移动成功");
        return jsonModel;
    }

}
