package com.nate.checklist.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="Item")
public class ItemData extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 9180549494697469620L;
	
	private Integer itemId;
	private Integer listId;
	private String itemText;
	private String completed;
	
	@XmlElement(name="ItemId")
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	@XmlElement(name="ListId")
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	@XmlElement(name="ItemText")
	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	@XmlElement(name="Completed")
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}

}
