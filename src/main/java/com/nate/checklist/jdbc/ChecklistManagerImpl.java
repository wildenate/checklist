package com.nate.checklist.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.nate.checklist.domain.ItemData;
import com.nate.checklist.domain.ListData;
import com.nate.checklist.domain.UserData;

public class ChecklistManagerImpl extends SqlSessionDaoSupport implements ChecklistManager {
	
	//Users
	public List<UserData> getAllUsers() {
		List<UserData> userDataList = getSqlSession().selectList("com.nate.checklist.getAllUsers");
		
		return userDataList;
	}
	
	public UserData getUserByUserId(Integer userId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userId", userId);
		
		UserData userData = getSqlSession().selectOne("com.nate.checklist.getUserByUserId", parameterMap);
		
		return userData;
	}
	
	public void updateUser(UserData userData) {
		this.getSqlSession().update("com.nate.checklist.updateUser", userData);
	}
	
	public void insertUser(UserData userData) {
		this.getSqlSession().insert("com.nate.checklist.insertUser", userData);
	}
	
	//Lists
	public List<ListData> getListsByUserId(Integer userId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("userId", userId);
		
		List<ListData> listDataList = getSqlSession().selectList("com.nate.checklist.getListsByUserId", parameterMap);
		
		return listDataList;
	}
	
	public ListData getListByListId(Integer listId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("listId", listId);
		
		ListData listData = getSqlSession().selectOne("com.nate.checklist.getListByListId", parameterMap);
		
		return listData;
	}
	
	public void updateList(ListData listData) {
		this.getSqlSession().update("com.nate.checklist.updateList", listData);
	}
	
	public void insertList(ListData listData) {
		this.getSqlSession().insert("com.nate.checklist.insertList", listData);
	}
	
	public void deleteListByListId(Integer listId) {
		//Delete child items before deleting the parent list
		deleteItemsByListId(listId);
		
		this.getSqlSession().delete("com.nate.checklist.deleteListByListId", listId);
	}
	
	
	//Items
	public List<ItemData> getItemsByListId(Integer listId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("listId", listId);
		
		List<ItemData> itemDataList = getSqlSession().selectList("com.nate.checklist.getItemsByListId", parameterMap);
		
		return itemDataList;
	}
	
	public ItemData getItemByItemId(Integer itemId) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("itemId", itemId);
		
		ItemData itemData = getSqlSession().selectOne("com.nate.checklist.getItemByItemId", parameterMap);
		
		return itemData;
	}
	
	public void updateItem(ItemData itemData) {
		this.getSqlSession().update("com.nate.checklist.updateItem", itemData);
	}
	
	public void insertItem(ItemData itemData) {
		this.getSqlSession().insert("com.nate.checklist.insertItem", itemData);
	}
	
	public void deleteItemByItemId(Integer itemId) {
		this.getSqlSession().delete("com.nate.checklist.deleteItemByItemId", itemId);
	}
	
	public void deleteItemsByListId(Integer listId) {
		this.getSqlSession().delete("com.nate.checklist.deleteItemsByListId", listId);
	}

}
