package com.jmhz.device.controller;

import com.jmhz.device.model.Tfarmervisit;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.AppealRateServiceI;
import com.jmhz.device.service.AppealServiceI;
import com.jmhz.device.service.FarmerVisitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/farmerVisit")
public class FarmerVisitController extends BaseController {

    @Autowired
    private FarmerVisitServiceI farmerVisitService;

    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private AppealRateServiceI appealRateService;

    @RequestMapping(value = "/getVisitList/{id}", method = RequestMethod.GET)
    public String getVisitList(@PathVariable("id") int id, Model model) {
        model.addAttribute("farmerId", id);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/farmer/visitList.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllVisitList/{id}", method = RequestMethod.GET)
    public JsonModel getAllVisitList(@PathVariable("id") int id, int page, int rows, String _search, String filters, Model model) {
        List<Tfarmervisit> tfarmervisitList = farmerVisitService.getAllVisitList(id, page, rows);
        JsonModel jsonModel = new JsonModel();
        Long count = farmerVisitService.getCountById(id);
        int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("success");
        jsonModel.setDataObj(tfarmervisitList);
        jsonModel.setTotalpages(pages);
        jsonModel.setCurrentpage(page);
        jsonModel.setTotalrecords(count.intValue());
        jsonModel.setPagerows(rows);
        jsonModel.setSort("");
        jsonModel.setOrder("");
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/updateVisit", method = RequestMethod.POST)
    public JsonModel updateRate(@RequestParam("id") int id,
                             @RequestParam("visittime") String visittime,
                             @RequestParam("visitremark") String visitremark) {
        farmerVisitService.updateFarmerVisit(id, visittime, visitremark);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteVisit", method = RequestMethod.POST)
    public JsonModel deleteRate(@RequestParam("id") int id) {
        farmerVisitService.deleteFarmerVisit(id);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public JsonModel addVisit(@RequestParam("farmerid") int farmerid,
                          @RequestParam("visittime") String visittime,
                          @RequestParam("visitremark") String visitremark,
                          Model model) {
        Tfarmervisit tfarmervisit = new Tfarmervisit();
        tfarmervisit.setFarmerid(farmerid);
        tfarmervisit.setVisittime(visittime);
        tfarmervisit.setVisitremark(visitremark);
        tfarmervisit.setInserttime(new Date());
        int fvid = farmerVisitService.add(tfarmervisit);
        JsonModel jsonModel = new JsonModel();
        if (fvid == -1) {
            jsonModel.setSuccess(false);
        } else {
            jsonModel.setSuccess(true);
        }
        return jsonModel;
    }
}
