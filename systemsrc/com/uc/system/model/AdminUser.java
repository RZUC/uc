package com.uc.system.model;

/**
 * @ClassName: AdminUser
 * @Description: TODO(简单的管理员用户)
 * @author Administrator
 * @date 2016年6月26日 下午3:36:51
 */
public class AdminUser {
	private int id;
	private String name;

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", name=" + name + ", password="
				+ password + ", description=" + description + "]";
	}

	private String password;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
