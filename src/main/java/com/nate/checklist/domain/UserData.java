package com.nate.checklist.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="User")
public class UserData extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 5260263973137552610L;
	
	private Integer userId;
	private String userName;
	
	
	@XmlElement(name="UserId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@XmlElement(name="UserName")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
