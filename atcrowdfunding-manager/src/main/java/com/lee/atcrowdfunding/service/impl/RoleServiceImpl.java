package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Role;
import com.atguigu.atcrowdfunding.dao.RoleDao;
import com.atguigu.atcrowdfunding.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> queryPageRoles(Map<String, Object> paramMap) {
		return roleDao.queryPageRoles(paramMap);
	}

	public int queryPageCount(Map<String, Object> paramMap) {
		return roleDao.queryPageCount(paramMap);
	}

	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}

	public void insertRolePermissions(Map<String, Object> paramMap) {
		roleDao.deleteRolePermissions(paramMap);
		roleDao.insertRolePermissions(paramMap);
	}
}
