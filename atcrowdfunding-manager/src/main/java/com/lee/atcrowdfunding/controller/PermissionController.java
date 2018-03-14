package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.BaseController;
import com.atguigu.atcrowdfunding.common.bean.Permission;
import com.atguigu.atcrowdfunding.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		
		return "permission/edit";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Permission permission ) {
		start();
		
		try {
			int count = permissionService.deletePermission(permission);
			success(count == 1);
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission permission ) {
		start();
		
		try {
			int count = permissionService.updatePermission(permission);
			success(count == 1);
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission permission ) {
		start();
		
		try {
			permissionService.insertPermission(permission);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/loadAssignAsyncData")
	public Object loadAssignAsyncData(Integer roleid) {

		List<Permission> permissions = new ArrayList<Permission>();
		List<Permission> dbPermissions =
				permissionService.queryAll();
		// 查询当前角色已经分配过的许可数据
		List<Integer> permissionids = permissionService.queryPermissionidsByRoleid(roleid);
		
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		for ( Permission permission : dbPermissions ) {
			if ( permissionids.contains(permission.getId()) ) {
				permission.setChecked(true);
			}
			permissionMap.put(permission.getId(), permission);
		}
		
		for ( Permission permission : dbPermissions ) {
			// 子菜单
			Permission child = permission;
			if ( child.getPid() == 0 ) {
				permissions.add(permission);
			} else {
				// 父菜单
				Permission parent = permissionMap.get(child.getPid());
				// 组合父子菜单的关系
				parent.getChildren().add(child);
			}
		}
		
		return permissions;
	}
	
	@ResponseBody
	@RequestMapping("/loadAsyncData")
	public Object loadAsyncData() {

			List<Permission> permissions = new ArrayList<Permission>();
			List<Permission> dbPermissions =
					permissionService.queryAll();
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			for ( Permission permission : dbPermissions ) {
				permissionMap.put(permission.getId(), permission);
			}
			
			for ( Permission permission : dbPermissions ) {
				// 子菜单
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					permissions.add(permission);
				} else {
					// 父菜单
					Permission parent = permissionMap.get(child.getPid());
					// 组合父子菜单的关系
					parent.getChildren().add(child);
				}
			}
			
			return permissions;
	}
	
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		
		start();
		
		try {
			
			// List ==> []
			// Map ==> {}
			// User ==> {}
			
			List<Permission> permissions = new ArrayList<Permission>();
			/* 临时数据
			Permission p1 = new Permission();
			p1.setName("Test1");
			p1.setOpen(true);
			Permission p2 = new Permission();
			p2.setName("Test2");
			Permission p3 = new Permission();
			p3.setName("Test3");
			
			p1.getChildren().add(p2);
			p1.getChildren().add(p3);
			
			permissions.add(p1);
			*/
			// ******************************************
			/*
			// 父菜单
			Permission root = permissionService.queryRootPermission();
			permissions.add(root);
			
			// 查询子菜单
			List<Permission> childPermissions =
				permissionService.queryChildPermissions(root.getId());
			
			for ( Permission childPermission : childPermissions ) {
				// 查询子菜单的子菜单
				List<Permission> childChildPermissions =
						permissionService.queryChildPermissions(childPermission.getId());
				
				for ( Permission childChildPermission : childChildPermissions ) {
					List<Permission> childChildChildChildChildChildPermissions =
				}
				
				childPermission.setChildren(childChildPermissions);
			}
			
			// 整合父子菜单的关系
			root.setChildren(childPermissions);
			*/
			// ***************************************
			// 递归查询菜单数据
			/*
			Permission temp = new Permission();
			temp.setId(0);
			
			queryChildPermission(temp);
			
			*/
			// ***************************************
			// 查询一次数据库，准备好树形结构的数据
			// 使用for循环的嵌套查询数据
			List<Permission> dbPermissions =
				permissionService.queryAll();

			/*
			for ( Permission permission : dbPermissions ) {
				// 子菜单
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					permissions.add(permission);
				} else {
					for ( Permission innerPermission : dbPermissions ) {
						if ( innerPermission.getId() == child.getPid() ) {
							// 父菜单
							Permission parent = innerPermission;
							// 组合父子菜单的关系
							parent.getChildren().add(child);
							break;
						}
					}
				}
			}
			*/
			// *********************************************
			// 使用Map集合整合菜单之间的关系
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			for ( Permission permission : dbPermissions ) {
				permissionMap.put(permission.getId(), permission);
			}
			
			for ( Permission permission : dbPermissions ) {
				// 子菜单
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					permissions.add(permission);
				} else {
					// 父菜单
					Permission parent = permissionMap.get(child.getPid());
					// 组合父子菜单的关系
					parent.getChildren().add(child);
				}
			}
			
			data(permissions);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	/**
	 * 递归的要素：
	 * 1）逻辑相同，方法自己调用自己
	 * 2）方法调用时，数据之间应该有规律
	 * 3）递归方法一定要有跳出的逻辑
	 * @param parent
	 */
	private void queryChildPermission( Permission parent ) {
		// 查询子菜单
		List<Permission> childPermissions =
			permissionService.queryChildPermissions(parent.getId());
		
		for ( Permission childPermission : childPermissions ) {
			queryChildPermission(childPermission);
		}
		
		// 组合父子菜单的关系
		parent.setChildren(childPermissions);
	}
}
