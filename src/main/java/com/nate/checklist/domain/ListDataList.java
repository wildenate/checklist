package com.nate.checklist.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

@XmlRootElement(name="Lists")
public class ListDataList extends ResourceSupport implements Serializable {
	private static final long serialVersionUID = -5408494313409170077L;
	
	private List<ListData> listDataList;
	
	@XmlElement(name="List")
	public List<ListData> getListDataList() {
		return listDataList;
	}
	public void setListDataList(List<ListData> listDataList) {
		this.listDataList = listDataList;
	}

}
