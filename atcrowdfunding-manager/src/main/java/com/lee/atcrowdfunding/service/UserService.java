package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Permission;
import com.atguigu.atcrowdfunding.common.bean.User;

public interface UserService {

	User queryUser(Integer id);

	User queryUser4Login(User user);

	List<User> queryPageUsers(Map<String, Object> paramMap);

	int queryPageCount(Map<String, Object> paramMap);

	void insertUser(User user);

	User queryById(Integer id);

	int updateUser(User user);

	int deleteUserById(Integer id);

	void deleteUsers(Datas ds);

	void insertUserRoles(Map<String, Object> paramMap);

	void deleteUserRoles(Map<String, Object> paramMap);

	List<Integer> queryRoleidsByUserid(Integer id);

	List<Permission> queryPermissionsByUser(User dbUser);

}
