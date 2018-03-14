package com.atguigu.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.common.bean.Role;

public interface RoleDao {

	List<Role> queryPageRoles(Map<String, Object> paramMap);

	int queryPageCount(Map<String, Object> paramMap);

	@Select("select * from t_role")
	List<Role> queryAll();

	@Select("select * from t_role where id = #{id}")
	Role queryById(Integer id);

	void insertRolePermissions(Map<String, Object> paramMap);

	void deleteRolePermissions(Map<String, Object> paramMap);

}
