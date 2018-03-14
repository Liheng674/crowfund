package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Permission;
import com.atguigu.atcrowdfunding.common.bean.User;

public interface UserDao {

	@Select("select * from t_user where id = #{id}")
	User queryUser(Integer id);

	@Select("select * from t_user where loginacct = #{loginacct} and userpswd = #{userpswd}")
	User queryUser4Login(User user);

	List<User> queryPageUsers(Map<String, Object> paramMap);

	int queryPageCount(Map<String, Object> paramMap);

	void insertUser(User user);

	int updateUser(User user);

	int deleteUserById(Integer id);

	void deleteUsers(Datas ds);

	void insertUserRoles(Map<String, Object> paramMap);

	void deleteUserRoles(Map<String, Object> paramMap);

	@Select("select roleid from t_user_role where userid = #{userid}")
	List<Integer> queryRoleidsByUserid(Integer id);

	List<Permission> queryPermissionsByUser(User dbUser);


}
