/**
 * 
 */
package fzu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fzu.edu.dao.UserMapper;
import fzu.edu.model.User;
import java.util.List;
/**
 * @author xiangtao
 *
 * 2017年3月30日
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {

	private UserMapper Usermapper;
	
	public UserMapper getUsermapper() {
		return Usermapper;
	}
	@Autowired
	public void setUsermapper(UserMapper usermapper) {
		Usermapper = usermapper;
	}
	
	@Override
	public User getUserById(int id) {
		return Usermapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> getAll() {
		return Usermapper.getAll();
	}

	@Override
	public List<User> getListByRole(String roleId) {
		return Usermapper.getListByRole(roleId);
	}

	@Override
	public int CheckLogin(String num,String password) {
		User user = Usermapper.getUserByNum(num);
		if(user != null && user.getUserPasswd().equals(password) && user.getDelFlag() == 0) // 用户正确登录
		{
			if(user.getUserRoleid().equals("1"))
				return 1;//管理员
			else if (user.getUserRoleid().equals("2")) {
				return 11;//教师
			}
			else {
				return 111;//学生
			}
		}	
		else if(user != null && user.getUserPasswd().equals(password) == false)
			return 2; //用户密码错误
		else 
			return 0; //没有该用户
	}
	
	@Override
	public User findByNum(String num) {
		return Usermapper.getUserByNum(num);
	}
	@Override
	public int StudentAdd(String num, String name, String tel, String passwd) {
		// TODO Auto-generated method stub
		String roleId = "3";//学生
		int del_flag = 0;//是否存在
		User user = new User();
		user.setUserNum(num);
		user.setUserName(name);
		user.setUserTel(tel);
		user.setUserPasswd(passwd);
		user.setUserRoleid(roleId);
		user.setDelFlag(del_flag);
		Usermapper.insert(user);
		return 0;
	}
	

}
