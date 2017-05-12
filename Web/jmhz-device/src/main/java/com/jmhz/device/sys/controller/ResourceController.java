package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.entity.Resource;
import com.jmhz.device.sys.service.IResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p> .
 * <p> Created at 2014/4/20 10:14
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @ModelAttribute("types")
    public Resource.ResourceType[] resourceTypes() {
        return Resource.ResourceType.values();
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "system");
        model.addAttribute("subMenu", "resource");
        return "/jsp/system/resource/index.jsp";
    }

    @ResponseBody
    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/appendChild", method = RequestMethod.POST)
    public JsonModel appendChild(Resource resource) {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加成功");
        resourceService.createResource(resource);
        return jsonModel;
    }

    @ResponseBody
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonModel update(Resource resource) {
        JsonModel jsonModel = new JsonModel();
        if (resource.getParentId()==null) {
            resource.setParentId(Long.parseLong("0"));
        }
        resourceService.updateResource(resource);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("修改成功");
        return jsonModel;
    }

    @ResponseBody
    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonModel delete(@RequestParam("id") Long id, @RequestParam("parentIds") String parentIds) {
        JsonModel jsonModel = new JsonModel();
        resourceService.deleteResource(id, parentIds);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("删除成功");
        return jsonModel;
    }

}
