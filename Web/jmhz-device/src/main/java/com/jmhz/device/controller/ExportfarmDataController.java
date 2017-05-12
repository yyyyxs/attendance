package com.jmhz.device.controller;

import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.entity.ExportData;
import com.jmhz.device.sys.service.IExportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by ChenXin on 2014/6/16.
 * 导出农户信息
 */
@Controller
@RequestMapping("/exportfarmerData")
public class ExportfarmDataController extends BaseController{
    @Autowired
    private IExportDataService exportDataService;

    @RequestMapping("/list/{type}")
    public String list(Model model, @PathVariable("type") String type) {
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerExportList");
        List<ExportData> exportDataList = exportDataService.findAllByType(type);
        model.addAttribute("exportDataList", exportDataList);
        return "/jsp/farmer/exportList.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonModel exportExcel(@RequestParam("id") int id,
                                 RedirectAttributes redirectAttributes, Model model) {
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(exportDataService.delete(id));
        return jsonModel;
    }
}
