package com.jmhz.device.controller;

import com.jmhz.device.model.Tsmstemplate;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.SMSTemplateServiceI;
import com.jmhz.device.sys.bind.annotation.CurrentUser;
import com.jmhz.device.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/smstpl")
public class SMSTemplateController extends BaseController {

    @Autowired
    private SMSTemplateServiceI smsTemplateService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSMSTemplateList(@CurrentUser User user, Model model) {
        List<Tsmstemplate> tsmstemplateList = smsTemplateService.findAllSMSTplForCurUser(user.getUsername());
        model.addAttribute("tsmstemplateList", tsmstemplateList);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smstpl");
        model.addAttribute("subMenu", "smstpls");
        return "/jsp/smstpl/tplList.jsp";
    }

    @RequestMapping(value = "/update/{tplId}", method = RequestMethod.GET)
    public String showSMSTemplateUpdate(@PathVariable int tplId, Model model) {
        model.addAttribute("tsmstemplate", smsTemplateService.findSMSTplById(tplId));
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smstpl");
        model.addAttribute("subMenu", "smstpls");
        return "/jsp/smstpl/tplEdit.jsp";
    }

    @RequestMapping(value = "/update/{tplId}", method = RequestMethod.POST)
    public String updateSMSTemplate(@CurrentUser User user, @PathVariable int tplId,
                                    @RequestParam String tplname, @RequestParam String tplcontent,
                                    @RequestParam(value = "isvalid", required = false) String isvalid,
                                    RedirectAttributes redirectAttributes) {
        Tsmstemplate tsmstemplateOrigin = smsTemplateService.findSMSTplById(tplId);
        if ("on".equals(isvalid) && tsmstemplateOrigin.getIsvalid() != 1) {
            List<Tsmstemplate> tsmstemplateList = smsTemplateService.findAllValidSMSTplForCurUserAndName(user.getUsername(), tplname);
            // 已存在配置为生效的模板，不能再将新增模板配置为生效模板
            if (tsmstemplateList.size() > 0) {
                redirectAttributes.addFlashAttribute("addrs", 0);
                redirectAttributes.addFlashAttribute("addmsg", "已存在配置为生效的短信模板，此新增短信模板不能配置为生效状态！");
                return "redirect:/smstpl/show";
            }
        }

        tsmstemplateOrigin.setTplname(tplname);
        tsmstemplateOrigin.setTplcontent(tplcontent);
        if ("on".equals(isvalid)) {
            tsmstemplateOrigin.setIsvalid(1);
        } else {
            tsmstemplateOrigin.setIsvalid(0);
        }
        tsmstemplateOrigin.setTplcreator(user.getUsername());
        smsTemplateService.updateSMSTpl(tsmstemplateOrigin);
        return "redirect:/smstpl/show";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showSMSTemplateAddForm(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smstpl");
        model.addAttribute("subMenu", "smstpls");
        return "/jsp/smstpl/tplAdd.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSMSTemplate(@RequestParam String tplname, @RequestParam String tplcontent,
                                 @RequestParam(value = "isvalid", required = false) String isvalid,
                                 @CurrentUser User user, RedirectAttributes redirectAttributes) {
        if ("on".equals(isvalid)) {
            List<Tsmstemplate> tsmstemplateList = smsTemplateService.findAllValidSMSTplForCurUserAndName(user.getUsername(), tplname);
            // 已存在配置为生效的模板，不能再将新增模板配置为生效模板
            if (tsmstemplateList.size() > 0) {
                redirectAttributes.addFlashAttribute("addrs", 0);
                redirectAttributes.addFlashAttribute("addmsg", "已存在配置为生效的短信模板，此新增短信模板不能配置为生效状态！");
                return "redirect:/smstpl/show";
            }
        }
        Tsmstemplate tsmstemplate = new Tsmstemplate();
        tsmstemplate.setTplname(tplname);
        tsmstemplate.setTplcontent(tplcontent);
        if ("on".equals(isvalid)) {
            tsmstemplate.setIsvalid(1);
        } else {
            tsmstemplate.setIsvalid(0);
        }
        tsmstemplate.setTplcreator(user.getUsername());
        int aid = smsTemplateService.addSMSTpl(tsmstemplate);
        if (aid >= 0) {
            redirectAttributes.addFlashAttribute("addrs", 1);//1 成功
            redirectAttributes.addFlashAttribute("addmsg", "短信模板添加成功");
        } else {
            redirectAttributes.addFlashAttribute("addrs", 0);
            redirectAttributes.addFlashAttribute("addmsg", "短信模板添加失败");
        }
        return "redirect:/smstpl/show";
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public JsonModel deleteSMSTemplate(@PathVariable int id, Model model) {
        Tsmstemplate tsmstemplate = new Tsmstemplate();
        tsmstemplate.setId(id);
        smsTemplateService.deleteSMSTpl(tsmstemplate);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setDataObj("true");
        jsonModel.setSuccess(true);
        jsonModel.setMsg("true");
        return jsonModel;
    }

}
