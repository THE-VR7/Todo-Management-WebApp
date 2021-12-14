package com.todos.models;


public class UserDTO {

	public Long userid;
	public String username;
	public String mobile;
	public String token;
	public UserDTO(Long userid, String username, String mobile, String token) {
		super();
		this.userid = userid;
		this.username = username;
		this.mobile = mobile;
		this.token = token;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", username=" + username + ", mobile=" + mobile
				+ ", token=" + token + "]";
	}
	
}

