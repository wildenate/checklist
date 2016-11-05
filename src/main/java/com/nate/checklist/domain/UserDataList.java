package com.nate.checklist.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="Users")
public class UserDataList extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 6745506032738966459L;
	
	private List<UserData> userDataList;
	
	
	@XmlElement(name="User")
	public List<UserData> getUserDataList() {
		return userDataList;
	}
	public void setUserDataList(List<UserData> userDataList) {
		this.userDataList = userDataList;
	}

}
