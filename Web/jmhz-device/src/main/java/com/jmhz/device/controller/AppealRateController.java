package com.jmhz.device.controller;

import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.pageModel.RatedInfo;
import com.jmhz.device.service.AppealDataExportServiceI;
import com.jmhz.device.service.AppealRateServiceI;
import com.jmhz.device.service.AppealServiceI;
import com.jmhz.device.pageModel.JsonModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/appealRate")
public class AppealRateController extends BaseController {

    @Autowired
    private AppealDataExportServiceI appealDataExportService;

    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private AppealRateServiceI appealRateService;

    @RequestMapping(value = "/getRateList/{id}", method = RequestMethod.GET)
    public String getRatedAppeal(@PathVariable("id") int id, Model model) {
        model.addAttribute("appealId", id);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealView");
        return "/jsp/appeal/rateList.jsp";// 跳转到个人诉求评论的页面
    }

    @ResponseBody
    @RequestMapping(value = "/getAllRatedList/{id}", method = RequestMethod.GET)
    public JsonModel getAllRatedList(@PathVariable("id") int id, int page, int rows, String _search, String filters, Model model) {
        Tappeal tappeal = appealService.getAppealById(id);
        if (tappeal==null){
            return null;
        }
        List<Tappealrate> tappealrateList = appealRateService.getAllAppealRate(id, page, rows);
        List<RatedInfo> ratedInfoList = new ArrayList<RatedInfo>();
        for (Tappealrate tappealrate : tappealrateList) {
            RatedInfo ratedInfo = new RatedInfo();
            BeanUtils.copyProperties(tappeal, ratedInfo);
            BeanUtils.copyProperties(tappealrate, ratedInfo);
            ratedInfo.setId(tappealrate.getId());
            ratedInfoList.add(ratedInfo);
        }
        JsonModel jsonModel = new JsonModel();
        Long count = appealRateService.getCountById(id);
        int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("success");
        jsonModel.setDataObj(ratedInfoList);
        jsonModel.setTotalpages(pages);
        jsonModel.setCurrentpage(page);
        jsonModel.setTotalrecords(count.intValue());
        jsonModel.setPagerows(rows);
        jsonModel.setSort("");
        jsonModel.setOrder("");
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/updateRate", method = RequestMethod.POST)
    public JsonModel updateRate(@RequestParam("id") int id,
                             @RequestParam("rateLevel") int rateLevel,
                             @RequestParam("rateContent") String rateContent,
                             @RequestParam("rateDate") String rateDate) {
        appealRateService.updateAppealRate(id, rateLevel, rateContent, rateDate);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteRate", method = RequestMethod.POST)
    public JsonModel deleteRate(@RequestParam("id") int id) {
        appealRateService.deleteAppealRate(id);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }


}
