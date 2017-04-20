/**
 * 
 */
package fzu.edu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.sql.ast.statement.SQLShowTablesStatement;
import com.alibaba.fastjson.JSONObject;

import fzu.edu.model.User;
import fzu.edu.service.UserServiceI;

/**
 * @author xiangtao
 *
 *         2017年3月30日
 */
@Controller
@RequestMapping("/UserController")
public class UserController {
	private UserServiceI UserServiceI;

	public UserServiceI getUserServiceI() {
		return UserServiceI;
	}

	@Autowired
	public void setUserServiceI(UserServiceI userServiceI) {
		UserServiceI = userServiceI;
	}

	/*
	 * @RequestMapping("/{id}/showUser") public String showUser(@PathVariable
	 * int id,HttpServletRequest request){ User user =
	 * UserServiceI.getUserById(id); request.setAttribute("user", user); return
	 * "showUser"; }
	 */
	@RequestMapping(value = "/checkUserLogin")
	@ResponseBody
	public int checkUserLogin(@RequestBody JSONObject requestJson) {
		String number = requestJson.getString("userNum");
		String passwd = requestJson.getString("userPasswd");
		int result = UserServiceI.CheckLogin(number, passwd);
		return result;
	}
	
	@RequestMapping(value = "/registerStu")
	@ResponseBody
	public int register(@RequestBody JSONObject requestJson) {
		String num = requestJson.getString("userNum");
		String name = requestJson.getString("userName");
		String tel = requestJson.getString("userTel");
		String passwd = requestJson.getString("userPasswd");
		int result = UserServiceI.StudentAdd(num, name, tel, passwd);
		return result;
	}
}
