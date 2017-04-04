/**
 * 
 */
package fzu.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.sql.ast.statement.SQLShowTablesStatement;

import fzu.edu.model.User;
import fzu.edu.service.UserServiceI;

/**
 * @author xiangtao
 *
 * 2017年3月30日
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
	/*@RequestMapping("/showUser")
	public String showALL(HttpServletRequest request){
		List<User> user = UserServiceI.getAll();
		request.setAttribute("user", user);
		return "showUser";
	}*/
	@RequestMapping("/{id}/showUser")  
	public String showUser(@PathVariable int id,HttpServletRequest request){
		User user = UserServiceI.getUserById(id);
		request.setAttribute("user", user);
		return "showUser";
	}
	
}
