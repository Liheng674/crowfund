package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.BaseController;
import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Page;
import com.atguigu.atcrowdfunding.common.bean.Role;
import com.atguigu.atcrowdfunding.common.util.StringUtil;
import com.atguigu.atcrowdfunding.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}
	
	@RequestMapping("/assign")
	public String assign( Integer id, Model model ) {
		
		Role role = roleService.queryById(id);
		model.addAttribute("role", role);
		return "role/assign";
	}
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer roleid, Datas ds ) {
		start();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("roleid", roleid);
			paramMap.put("permissionids", ds.getIds());
			roleService.insertRolePermissions(paramMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryContent, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		try {
	    	int start = (pageno-1)*pagesize;
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	    	paramMap.put("start", start);
	    	paramMap.put("size", pagesize);
	    	if ( !StringUtil.isEmpty(queryContent) ) {
	    		if ( queryContent.indexOf("\\") != -1 ) {
	    			queryContent = queryContent.replaceAll("\\\\", "\\\\\\\\");
	    		}
	    		if ( queryContent.indexOf("%") != -1 ) {
	    			queryContent = queryContent.replaceAll("%", "\\\\%");
	    		}
	    		if ( queryContent.indexOf("_") != -1 ) {
	    			queryContent = queryContent.replaceAll("_", "\\\\_");
	    		}
	    	}
	    	paramMap.put("queryContent", queryContent);
	    	List<Role> roles = roleService.queryPageRoles(paramMap);
	    	int totalsize = roleService.queryPageCount(paramMap);
	    	
	    	Page<Role> rolePage = new Page<Role>();
	    	rolePage.setPageno(pageno);
	    	rolePage.setPagesize(pagesize);
	    	rolePage.setDatas(roles);
	    	rolePage.setTotalsize(totalsize);
			
	    	result.setData(rolePage);
			result.setSuccess(true);
		} catch( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
