package com.atguigu.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Role;

public interface RoleService {

	List<Role> queryPageRoles(Map<String, Object> paramMap);

	int queryPageCount(Map<String, Object> paramMap);

	List<Role> queryAll();

	Role queryById(Integer id);

	void insertRolePermissions(Map<String, Object> paramMap);

}
