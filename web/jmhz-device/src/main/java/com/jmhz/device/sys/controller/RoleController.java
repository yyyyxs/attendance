package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.entity.Role;
import com.jmhz.device.sys.service.IResourceService;
import com.jmhz.device.sys.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> .
 * <p> Created at 2014/4/20 10:14
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Role> roleList = roleService.findAll();
        Map<Long, String> resourceNames = new HashMap<Long, String>();
        for (Role role : roleList) {
            resourceNames.put(role.getId(),resourceService.getResourceNames(roleService.getResourceIds(role)));
        }
        model.addAttribute("roleList", roleList);
        model.addAttribute("resourceNames", resourceNames);
        model.addAttribute("resourceList", resourceService.findAll());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "system");
        model.addAttribute("subMenu", "role");
        return "/jsp/system/role/index.jsp";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonModel create(Role role) {
        String id = roleService.createRole(role);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setMsg("添加成功");
        jsonModel.setOrder(id);
        return jsonModel;
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findOne(id);
        model.addAttribute("resourceList", resourceService.findAll());
        model.addAttribute("role", role);
        model.addAttribute("id", id);
        String[] ids = role.getResourceIds().split(",");
        List<Long> longIds = new ArrayList<Long>();
        int i = 0;
        for (String s : ids) {
            longIds.add(i, Long.valueOf(s));
        }
        model.addAttribute("resourceNames", resourceService.getResourceNames(longIds));
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "system");
        model.addAttribute("subMenu", "role");
        return "/jsp/system/role/edit.jsp";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role";
    }


    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("delRoleId") Long delRoleId, RedirectAttributes redirectAttributes) {
        roleService.deleteRole(delRoleId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/role";
    }

}
