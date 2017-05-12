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
 * <p> .
 * <p> Created at 2014/5/18 21:18
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/exportData")
public class ExportDataController extends BaseController {

    @Autowired
    private IExportDataService exportDataService;

    /**
     * 显示导出列表
     * @param model
     * @param type - 导出excel所属的类型：0--诉求信息；1--农户信息
     * @return
     */
    @RequestMapping("/list/{type}")
    public String list(Model model, @PathVariable("type") String type) {
        //根据类型不同显示不同的菜单选中
        model.addAttribute("navMenu", type);
        model.addAttribute("subMenu", type + "ExportList");
        List<ExportData> exportDataList = exportDataService.findAllByType(type);
        model.addAttribute("exportDataList", exportDataList);
        if(type.equals("village")){
            return "/jsp/group/exportList.jsp";
        }else{
            return "/jsp/"+ type +"/exportList.jsp";
        }

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
