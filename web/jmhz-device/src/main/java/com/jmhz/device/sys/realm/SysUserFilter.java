package com.jmhz.device.sys.realm;

import com.jmhz.device.Constants;
import com.jmhz.device.sys.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private IUserService userService;
    @Autowired
    private ServletContext servletContext;
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        servletContext.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
