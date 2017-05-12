package com.jmhz.device.controller;

import com.jmhz.device.backModel.UserBack;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.service.IUserService;
import com.jmhz.device.sys.service.impl.PasswordService;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by fjmjuhqc on 2016-01-06.
 */
@Controller
@RequestMapping("/mobileuser")
public class UserMobileController extends BaseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordService passwordService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserBack userLogin(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {
        UserBack userBack = new UserBack();
        User user = userService.findByUsername(username);
        if (user != null) {
            if (passwordService.matchePassword(user, password)) {//匹对正确
                userBack.setUser(user);
                userBack.setSuccess(true);
                userBack.setErrorReason("帐号密码正确");
                userBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
            } else {
                userBack.setSuccess(false);
                userBack.setErrorReason("密码错误");//密码不正确
                userBack.setErrcode(ErrorCodeUtils.EC_UNKNOWN);
            }
        } else {
            userBack.setSuccess(false);
            userBack.setErrorReason("用户名不存在");//用户名不正确
            userBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return userBack;
    }

    @ResponseBody
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public UserBack changePassword(@RequestParam("id") Long id,
                                   @RequestParam("newPassword") String newPassword) {
        //String uuid = UUID.randomUUID();
        User user = userService.findOne(id);
        UserBack userBack = new UserBack();
        if (user != null) {
            userService.changePassword(id, newPassword);
            userBack.setSuccess(true);
            userBack.setErrorReason("密码修改成功");
            userBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            userBack.setSuccess(false);
            userBack.setErrorReason("密码修改失败");
            userBack.setErrcode(ErrorCodeUtils.EC_UNKNOWN);
        }

        return userBack;
    }

}
