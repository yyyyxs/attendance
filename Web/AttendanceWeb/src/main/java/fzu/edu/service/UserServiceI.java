package fzu.edu.service;


import java.util.List;

import fzu.edu.model.User;
/**
 * @author xiangtao
 *
 * 2017年3月30日
 */
public interface UserServiceI {
	
	public User getUserById(int id);
	
	public List<User> getAll();

	public List<User> getListByRole(String roleId);
}

