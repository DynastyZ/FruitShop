package com.study.po;

import java.io.Serializable;

public class Manage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String passWord;
	private String realName;
	
	
	
	public Manage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manage(Integer id, String userName, String passWord, String realName) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.realName = realName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@Override
	public String toString() {
		return "Manage [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", realName=" + realName
				+ "]";
	}
	
	
	
}
