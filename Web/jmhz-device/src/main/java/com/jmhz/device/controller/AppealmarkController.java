package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.model.Tappealmark;
import com.jmhz.device.service.SmhlAppealServiceI;
import com.jmhz.device.service.TappealmarkServiceI;
import com.jmhz.device.service.TsmhlappealmarkServiceI;
import com.jmhz.device.model.Tsmhlappealmark;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.AppealServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ChenXin on 2014/4/22.
 */
@Controller
@RequestMapping("/appealmark")
public class AppealmarkController extends BaseController {

    @Autowired
    private TappealmarkServiceI tappealmarkService;
    @Autowired
    private AppealServiceI appealService;
    @Autowired
    private SmhlAppealServiceI smhlAppealService;
    @Autowired
    private TsmhlappealmarkServiceI tsmhlappealmarkService;

    @RequestMapping(value = "/getmarklist/{id}")
    public String getProcessUpdateRemark(@PathVariable("id") int appealid, Model model){
        model.addAttribute("appealid",appealid);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealView");
        return "/jsp/appealmark/marklist.jsp";
    }

    @RequestMapping(value = "/getsmhlmarklist/{id}")
    public String getProcessUpdateSmhlremark(@PathVariable("id")int appealid,  Model model){
        model.addAttribute("appealid",appealid);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealView");
        return "/jsp/appealmark/smhlmarklist.jsp";
    }

    //获得备注管理列表
    @RequestMapping(value = "/getAllsmhlmarks/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel getAllSmhlmanager(@PathVariable("id") int id, int page, int rows,String _search, String filters,HttpSession session)
    {
        String uuid = smhlAppealService.getAppealById(id).getUuid();
       session.setAttribute("uuid",uuid);
         JsonModel jsonModel = new JsonModel();
        if (_search.equals("false"))
        {
            List<Tsmhlappealmark> tsmhlappealmarks;
           // tappealmarks = tappealmarkService.getAllAppealmark(appealid,page, rows);
            tsmhlappealmarks = tsmhlappealmarkService.getSmhlappealmarkByUuid(uuid, page, rows);
           // Long count = tappealmarkService.getCount(appealid);
            Long count = tsmhlappealmarkService.getCountByUuid(uuid);
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmhlappealmarks);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }else
        {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            List<Tsmhlappealmark> tsmhlappealmarks;
            tsmhlappealmarks = tsmhlappealmarkService.getAllSmhlappealmark(page, rows, filtersClass,session.getAttribute("uuid").toString());
            Long count = tsmhlappealmarkService.getCount(filtersClass,session.getAttribute("uuid").toString());
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmhlappealmarks);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @RequestMapping(value = "/getAllmarks/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel getAllManager(@PathVariable("id") int id, int page, int rows,String _search, String filters,HttpSession session)
    {
        String uuid = appealService.getAppealById(id).getUuid();
        session.setAttribute("uuid",uuid);
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false"))
        {
            List<Tappealmark> tappealmarks;
            // tappealmarks = tappealmarkService.getAllAppealmark(appealid,page, rows);
            tappealmarks = tappealmarkService.getAppealmarkByUuid(uuid, page, rows);
            // Long count = tappealmarkService.getCount(appealid);
            Long count = tappealmarkService.getCountByUuid(uuid);
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tappealmarks);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }else
        {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            List<Tappealmark> tappealmarks;
            tappealmarks = tappealmarkService.getAllAppealmark(page, rows, filtersClass,session.getAttribute("uuid").toString());
            Long count = tappealmarkService.getCount(filtersClass,session.getAttribute("uuid").toString());
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tappealmarks);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }
    //编辑管理
    @ResponseBody
    @RequestMapping("/modify")
    public String modify(Tappealmark tappealmark, @RequestParam("oper") String oper)
    {
        if (oper.equals("edit")){
            //编辑
            tappealmarkService.updateRemark(tappealmark.getId(),
                    tappealmark.getProcessupdateremark(), tappealmark.getProcessupdatetime());
            return "/jsp/appealmark/marklist.jsp";
        }else if (oper.equals("del")){
            //删除
            tappealmarkService.delAppealmark(tappealmark);
            return "/jsp/appealmark/marklist.jsp";
        }else if (oper.equals("add")){
            tappealmarkService.add(tappealmark);
            return "/jsp/appealmark/marklist.jsp";
        }
        return "/jsp/appealmark/marklist.jsp";
    }

    @ResponseBody
    @RequestMapping("/smhlmodify")
    public String smhlmodify(Tsmhlappealmark tsmhlappealmark, @RequestParam("oper") String oper)
    {
        if (oper.equals("edit")){
            //编辑
           tsmhlappealmarkService.updateRemark(tsmhlappealmark.getId(),
                   tsmhlappealmark.getProcessupdateremark(), tsmhlappealmark.getProcessupdatetime());
            return "/jsp/appealmark/smhlmarklist.jsp";
        }else {
            //删除
            tsmhlappealmarkService.delAppealmark(tsmhlappealmark);
            return "/jsp/appealmark/smhlmarklist.jsp";
        }
    }
//2014.05.12  0:57增加的 begin
    @RequestMapping(value = "/addAppealmark", method = RequestMethod.POST)
    @ResponseBody
    public String addAppeal(@RequestParam(value = "appealid") String appealid,
                            @RequestParam(value = "uuid") String uuid,
                            @RequestParam(value = "householdername") String householdername,
                            @RequestParam(value = "processupdatetime") String processupdatetime,
                            @RequestParam(value = "processupdateremark") String processupdateremark){
        Tappealmark tappealmark = new Tappealmark();
        tappealmark.setUuid(uuid);
        tappealmark.setAppealid(Integer.parseInt(appealid));
        tappealmark.setHouseholdername(householdername);
        tappealmark.setProcessupdatetime(processupdatetime);
        tappealmark.setProcessupdateremark(processupdateremark);


        int aid = tappealmarkService.add(tappealmark);
        if (aid >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }

    }
    //2014.05.12  0:57增加的 end
}
