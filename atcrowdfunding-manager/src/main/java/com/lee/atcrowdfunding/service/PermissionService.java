package com.atguigu.atcrowdfunding.service;

import java.util.List;

import com.atguigu.atcrowdfunding.common.bean.Permission;

public interface PermissionService {

	Permission queryRootPermission();

	List<Permission> queryChildPermissions(Integer id);

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryById(Integer id);

	int updatePermission(Permission permission);

	int deletePermission(Permission permission);

	List<Integer> queryPermissionidsByRoleid(Integer roleid);

}
