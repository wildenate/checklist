package com.nate.checklist.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nate.checklist.domain.ListData;
import com.nate.checklist.domain.ListDataList;
import com.nate.checklist.domain.UserData;
import com.nate.checklist.jdbc.ChecklistManager;

@Controller
@RequestMapping("/users/{userId}/lists")
public class ListController {
	
	private static final String LISTS_URI_PREFIX = "/checklist/users/";
	private static final String LISTS_URI_SUFFIX = "/lists/";
	
	@Autowired
	private ChecklistManager checklistManager;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody HttpEntity<ListDataList> getListsForUser(@PathVariable Integer userId) {
		ListDataList listDataListObj = new ListDataList();
		
		List<ListData> listDataList = checklistManager.getListsByUserId(userId);
		
		//Add links
		for (ListData listData : listDataList) {
			listData.add(linkTo(methodOn(ListController.class).getList(userId, listData.getListId())).withSelfRel());
		}
		
		listDataListObj.setListDataList(listDataList);
		
		return new ResponseEntity<ListDataList>(listDataListObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{listId}", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody ResponseEntity<ListData> getList(@PathVariable Integer userId, @PathVariable Integer listId) {
		ListData listData = checklistManager.getListByListId(listId);
		
		if (listData != null) {
			return new ResponseEntity<ListData>(listData, HttpStatus.OK);
		}
		
		return new ResponseEntity<ListData>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<ListData> createList(@PathVariable Integer userId, @RequestBody ListData listData) {
		//List Name is required on create
		if (StringUtils.isBlank(listData.getListName())) {
			return new ResponseEntity<ListData>(HttpStatus.BAD_REQUEST);
		}
		
		//Check if user exists and refuse request if it does not
		UserData existingUserData = checklistManager.getUserByUserId(userId);
		if (existingUserData == null) {
			return new ResponseEntity<ListData>(HttpStatus.FORBIDDEN);
		}
		
		//Set userID from URL
		listData.setUserId(userId);
		
		checklistManager.insertList(listData);
		
		//Return the location of the created List in the header response
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", LISTS_URI_PREFIX + userId + LISTS_URI_SUFFIX + listData.getListId());
		return new ResponseEntity<ListData>(listData, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{listId}", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<ListData> updateList(@PathVariable Integer listId, @RequestBody ListData listData) {
		//List Name is required on update
		if (StringUtils.isBlank(listData.getListName())) {
			return new ResponseEntity<ListData>(HttpStatus.BAD_REQUEST);
		}
		
		//Check if list exists and refuse request if it does not
		ListData existingListData = checklistManager.getListByListId(listId);
		if (existingListData == null) {
			return new ResponseEntity<ListData>(HttpStatus.NOT_FOUND);
		}
		
		//Set listId from URL
		listData.setListId(listId);
		
		checklistManager.updateList(listData);
		
		return new ResponseEntity<ListData>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{listId}", method = RequestMethod.DELETE, produces = "application/xml")
	public @ResponseBody ResponseEntity<ListData> deleteList( @PathVariable Integer listId) {
		//Check if list exists and refuse request if it does not
		ListData existingListData = checklistManager.getListByListId(listId);
		if (existingListData == null) {
			return new ResponseEntity<ListData>(HttpStatus.NOT_FOUND);
		}
		
		checklistManager.deleteListByListId(listId);
		
		return new ResponseEntity<ListData>(HttpStatus.OK);
	}

}
