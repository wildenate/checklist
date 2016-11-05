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

import com.nate.checklist.domain.ItemData;
import com.nate.checklist.domain.ItemDataList;
import com.nate.checklist.domain.ListData;
import com.nate.checklist.jdbc.ChecklistManager;

@Controller
@RequestMapping("/lists/{listId}/items")
public class ItemController {
	
	private static final String ITEMS_URI_PREFIX = "/checklist/lists/";
	private static final String ITEMS_URI_SUFFIX = "/items/";
	private static final String COMPLETED_YES = "Y";
	private static final String COMPLETED_NO = "N";
	
	@Autowired
	private ChecklistManager checklistManager;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody HttpEntity<ItemDataList> getItemsForList(@PathVariable Integer listId) {
		ItemDataList itemDataListObj = new ItemDataList();
		
		List<ItemData> itemDataList = checklistManager.getItemsByListId(listId);
		
		for (ItemData itemData : itemDataList) {
			itemData.add(linkTo(methodOn(ItemController.class).getItem(listId, itemData.getItemId())).withSelfRel());
		}
		
		itemDataListObj.setItemDataList(itemDataList);
		
		return new ResponseEntity<ItemDataList>(itemDataListObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody ResponseEntity<ItemData> getItem(@PathVariable Integer listId, @PathVariable Integer itemId) {
		ItemData itemData = checklistManager.getItemByItemId(itemId);
		
		if (itemData != null) {
			return new ResponseEntity<ItemData>(itemData, HttpStatus.OK);
		}
		
		return new ResponseEntity<ItemData>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<ItemData> createItem(@PathVariable Integer listId, @RequestBody ItemData itemData) {
		//Item Text is required on create
		if (StringUtils.isBlank(itemData.getItemText())) {
			return new ResponseEntity<ItemData>(HttpStatus.BAD_REQUEST);
		}
		
		//Check if List exists and refuse request if it does not
		ListData existingListData = checklistManager.getListByListId(listId);
		if (existingListData == null) {
			return new ResponseEntity<ItemData>(HttpStatus.FORBIDDEN);
		}
		
		//Set listID from URL
		itemData.setListId(listId);
		
		checklistManager.insertItem(itemData);
		
		//Return the location of the created Item in the header response
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", ITEMS_URI_PREFIX + listId + ITEMS_URI_SUFFIX + itemData.getItemId());
		return new ResponseEntity<ItemData>(itemData, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{itemId}", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<ItemData> updateItem(@PathVariable Integer itemId, @RequestBody ItemData itemData) {
		//Item Text or Completed is required on update.  Also if Completed is not 'Y' or 'N', refuse the request.
		if ((StringUtils.isBlank(itemData.getItemText()) && StringUtils.isBlank(itemData.getCompleted()))
				|| (StringUtils.isNotBlank(itemData.getCompleted()) && !(itemData.getCompleted().equals(COMPLETED_YES) || itemData.getCompleted().equals(COMPLETED_NO)))) {
			return new ResponseEntity<ItemData>(HttpStatus.BAD_REQUEST);
		}
		
		//Check if item exists and refuse request if it does not
		ItemData existingItemData = checklistManager.getItemByItemId(itemId);
		if (existingItemData == null) {
			return new ResponseEntity<ItemData>(HttpStatus.NOT_FOUND);
		}
		
		//Set itemId from URL
		itemData.setItemId(itemId);
		
		checklistManager.updateItem(itemData);
		
		return new ResponseEntity<ItemData>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE, produces = "application/xml")
	public @ResponseBody ResponseEntity<ItemData> deleteItem( @PathVariable Integer itemId) {
		//Check if item exists and refuse request if it does not
		ItemData existingItemData = checklistManager.getItemByItemId(itemId);
		if (existingItemData == null) {
			return new ResponseEntity<ItemData>(HttpStatus.NOT_FOUND);
		}
		
		checklistManager.deleteItemByItemId(itemId);
		
		return new ResponseEntity<ItemData>(HttpStatus.OK);
	}

}
