package com.atguigu.atcrowdfunding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.atguigu.atcrowdfunding.common.bean.Permission;

public interface PermissionDao {

	Permission queryRootPermission();

	List<Permission> queryChildPermissions(Integer id);

	@Select("select * from t_permission")
	List<Permission> queryAll();

	void insertPermission(Permission permission);

	@Select("select * from t_permission where id = #{id}")
	Permission queryById(Integer id);

	int updatePermission(Permission permission);

	@Delete("delete from t_permission where id = #{id}")
	int deletePermission(Permission permission);

	@Select("select permissionid from t_role_permission where roleid = #{roleid}")
	List<Integer> queryPermissionidsByRoleid(Integer roleid);

}
