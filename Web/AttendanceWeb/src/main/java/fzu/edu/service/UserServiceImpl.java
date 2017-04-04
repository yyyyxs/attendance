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
	

}
