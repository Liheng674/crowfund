package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Permission;
import com.atguigu.atcrowdfunding.common.bean.User;
import com.atguigu.atcrowdfunding.dao.UserDao;
import com.atguigu.atcrowdfunding.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User queryUser(Integer id) {
		return userDao.queryUser(id);
	}

	public User queryUser4Login(User user) {
		return userDao.queryUser4Login(user);
	}

	public List<User> queryPageUsers(Map<String, Object> paramMap) {
		return userDao.queryPageUsers(paramMap);
	}

	public int queryPageCount(Map<String, Object> paramMap) {
		return userDao.queryPageCount(paramMap);
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	public User queryById(Integer id) {
		return userDao.queryUser(id);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUserById(Integer id) {
		return userDao.deleteUserById(id);
	}

	public void deleteUsers(Datas ds) {
		userDao.deleteUsers(ds);
	}

	public void insertUserRoles(Map<String, Object> paramMap) {
		userDao.insertUserRoles(paramMap);
	}

	public void deleteUserRoles(Map<String, Object> paramMap) {
		userDao.deleteUserRoles(paramMap);
	}

	public List<Integer> queryRoleidsByUserid(Integer id) {
		return userDao.queryRoleidsByUserid(id);
	}

	public List<Permission> queryPermissionsByUser(User dbUser) {
		return userDao.queryPermissionsByUser(dbUser);
	}

}
