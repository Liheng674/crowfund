package com.atguigu.atcrowdfunding.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.BaseController;
import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Datas;
import com.atguigu.atcrowdfunding.common.bean.Page;
import com.atguigu.atcrowdfunding.common.bean.Role;
import com.atguigu.atcrowdfunding.common.bean.User;
import com.atguigu.atcrowdfunding.common.util.ConfigUtil;
import com.atguigu.atcrowdfunding.common.util.MD5Util;
import com.atguigu.atcrowdfunding.common.util.StringUtil;
import com.atguigu.atcrowdfunding.service.RoleService;
import com.atguigu.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	@RequestMapping("/assign")
	public String assign(Integer id, Model model) {
		
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		// 查询所有的角色数据
		List<Role> roles = roleService.queryAll();
		// 查询当前用户已经分配的角色数据
		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		
		List<Role> assingList = new ArrayList<Role>();
		List<Role> unassingList = new ArrayList<Role>();
		
		for ( Role role : roles ) {
			if ( roleids.contains(role.getId()) ) {
				assingList.add(role);
			} else {
				unassingList.add(role);
			}
		}
		
		model.addAttribute("assingList", assingList);
		model.addAttribute("unassingList", unassingList);
		
		return "user/assign";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	@RequestMapping("/addBatch")
	public String addBatch() {
		return "user/batch";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("/unassignRole")
	public Object unassignRole(Integer userid, Datas ds) {
		start();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", userid);
			paramMap.put("roleids", ds.getIds());
			userService.deleteUserRoles(paramMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/assignRole")
	public Object assignRole(Integer userid, Datas ds) {
		start();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", userid);
			paramMap.put("roleids", ds.getIds());
			userService.insertUserRoles(paramMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
		   	
			// 删除
			userService.deleteUsers(ds);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer id) {
		start();
		
		try {
			int count = userService.deleteUserById(id);
			success(count == 1);
		} catch (Exception e) {
			fail();
		}
		
		return end();
		
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(User user) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 修改用户信息
			int count = userService.updateUser(user);
			result.setSuccess(count == 1);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * SpringMVC不支持引用数组类型数据接收数据
	 * SpringMVC默认不支持多数据的封装操作
	 * 如果想要让SpringMVC可以支持封装多数据对象，需要特殊操作：
	 * 1） 增加封装集合对象的包装类(不支持泛型), 包装类中增加集合属性并提供set/get方法
	 * 2）修改表单数据提交的方式
	 *    2-1） id=1&id=2&name=zhangsan&name=lisi
	 *    2-2）users[0].id=1&users[1].id=2&users[0].name=zhangsan
	 * 3）在方法的参数中增加包装类即可
	 * 4）封装数据时，可能会有一些无用数据，需要删除
	 */
	@RequestMapping("/batchInsert")
	public String batchInsert( Datas ds ) {
		/*
		String[] usernames = req.getParameterValues("username");
		req.getParameter("username");
		Map<String, String[]> map = req.getParameterMap();
		map.get("username");
		*/
		
		/*
		for ( User user : ds.getUsers() ) {
			if ( user.getLoginacct() == null ) {
				ds.getUsers().remove(user);
			}
		}
		*/
		/* 集合删除
		Iterator<User> userIter = ds.getUsers().iterator();
		while ( userIter.hasNext() ) {
			User user = userIter.next();
			if ( user.getLoginacct() == null ) {
				userIter.remove();
			}
		}
		*/
		
		return "user/xxxxxx";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( User user ) {
		AJAXResult result = new AJAXResult();
		try {
			// 保存用户信息
			user.setUserpswd(MD5Util.digest(ConfigUtil.getValueByKey("DEFAULT_PASSWORD")));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			user.setCreatetime(time);
			userService.insertUser(user);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryContent, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		// map(obj) ==> json ==> {} ==> js = obj
		// list     ==> json ==> [] ==> js = array
		try {
			
			// 分页查询用户信息
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
	    	List<User> users = userService.queryPageUsers(paramMap);
	    	int totalsize = userService.queryPageCount(paramMap);
	    	
	    	Page<User> userPage = new Page<User>();
	    	userPage.setPageno(pageno);
	    	userPage.setPagesize(pagesize);
	    	userPage.setDatas(users);
	    	userPage.setTotalsize(totalsize);
			
	    	result.setData(userPage);
			result.setSuccess(true);
		} catch( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 *  // 1) 点击菜单，跳转页面
    	// 2) 调用服务，查询数据
    	// 3) Service调用DAO，查询数据（limit start, size）
    	// 4) 返回查询结果 DAO==>Service==>Controller
    	// 5) 保存查询结果（request,）
    	// 6) 跳转页面
	 * @param query
	 * @param pageno
	 * @param pagesize
	 * @param model
	 * @return
	 */
    @RequestMapping("/index1")
    public String index1( @RequestParam(value="pageno", required=false, defaultValue="1")Integer pageno, @RequestParam(value="pagesize", required=false, defaultValue="2")Integer pagesize, Model model ) {
    	int start = (pageno-1)*pagesize;
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("start", start);
    	paramMap.put("size", pagesize);
    	List<User> users = userService.queryPageUsers(paramMap);
    	int totalsize = userService.queryPageCount(paramMap);
    	
    	Page<User> userPage = new Page<User>();
    	userPage.setPageno(pageno);
    	userPage.setPagesize(pagesize);
    	userPage.setDatas(users);
    	userPage.setTotalsize(totalsize);
    	
    	model.addAttribute("userPage", userPage);
    	
        return "user/index";
    }

}
