package com.atguigu.atcrowdfunding.common.bean;

import java.util.List;

/**
 * 数据包装类
 * @author 18801
 *
 */
public class Datas {

	private List<User> users;
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
