package com.nate.checklist.jdbc;

import java.util.List;

import com.nate.checklist.domain.ItemData;
import com.nate.checklist.domain.ListData;
import com.nate.checklist.domain.UserData;

public interface ChecklistManager {
	
	public List<UserData> getAllUsers();
	
	public UserData getUserByUserId(Integer userId);
	
	public void updateUser(UserData userData);
	
	public void insertUser(UserData userData);
	
	public List<ListData> getListsByUserId(Integer userId);
	
	public ListData getListByListId(Integer listId);
	
	public void updateList(ListData listData);
	
	public void insertList(ListData listData);
	
	public void deleteListByListId(Integer listId);
	
	public List<ItemData> getItemsByListId(Integer itemId);
	
	public ItemData getItemByItemId(Integer itemId);
	
	public void updateItem(ItemData itemData);
	
	public void insertItem(ItemData itemData);
	
	public void deleteItemByItemId(Integer itemId);
	
	public void deleteItemsByListId(Integer listId);

}
