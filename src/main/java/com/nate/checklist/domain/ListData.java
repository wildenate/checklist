package com.nate.checklist.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="List")
public class ListData extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = -8837999588479333646L;
	
	private Integer listId;
	private Integer userId;
	private String listName;
	
	
	@XmlElement(name="ListId")
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	@XmlElement(name="UserId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@XmlElement(name="ListName")
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}

}
