package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.service.ExcelDataService;
import com.jmhz.device.sys.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * <p> .
 * <p> Created at 2014/4/20 11:48
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/excel")
public class ExcelDataController extends BaseController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ExcelDataService excelDataService;

    /**
     * 导出excel存储的目录
     */
    private String contextRootPath;

    @PostConstruct
    public void afterPropertiesSet() {
        contextRootPath = servletContext.getRealPath("/");
    }

    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String exportExcel(
            @CurrentUser User user,
            RedirectAttributes redirectAttributes) throws IOException {
        excelDataService.exportExcel2007(contextRootPath);
        return "done";
    }

}
