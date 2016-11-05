package com.nate.checklist.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="Items")
public class ItemDataList extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = 5115049950494151321L;
	
	List<ItemData> itemDataList;
	
	@XmlElement(name="Item")
	public List<ItemData> getItemDataList() {
		return itemDataList;
	}
	public void setItemDataList(List<ItemData> itemDataList) {
		this.itemDataList = itemDataList;
	}

}
