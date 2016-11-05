package com.nate.checklist.integration;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nate.checklist.domain.ItemData;
import com.nate.checklist.domain.ListData;
import com.nate.checklist.domain.UserData;
import com.nate.checklist.jdbc.ChecklistManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/com/nate/checklist/spring/applicationContext-test.xml")
public class ChecklistManagerTest {
	
	@Autowired
	private ChecklistManager checklistManager;
	
	@Test
	public void testGetAllUserIds() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<UserData> userDataList = checklistManager.getAllUsers();
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(userDataList));
	}
	
	@Test
	public void testGetUserByUserId() {
		Integer userId = 1;
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UserData userData = checklistManager.getUserByUserId(userId);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(userData != null);
	}
	
	@Test
	public void testUpdateUser() {
		boolean failed = false;
		try {
			UserData userData = new UserData();
			userData.setUserId(12);
			userData.setUserName("name55");
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.updateUser(userData);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}
	
	@Test
	public void testInsertUser() {
		UserData userData = new UserData();
		userData.setUserName("namename1");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		checklistManager.insertUser(userData);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(userData.getUserId() != null);
	}
	
	@Test
	public void testGetListsByUserId() {
		Integer userId = 1;
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<ListData> listDataList = checklistManager.getListsByUserId(userId);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(listDataList));
	}
	
	@Test
	public void testGetListByListId() {
		Integer listId = 1;
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ListData listData = checklistManager.getListByListId(listId);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(listData != null);
	}
	
	@Test
	public void testUpdateList() {
		boolean failed = false;
		try {
			ListData listData = new ListData();
			listData.setListId(4);
			listData.setListName("list66");
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.updateList(listData);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}
	
	@Test
	public void testInsertList() {
		ListData listData = new ListData();
		listData.setListName("list22");
		listData.setUserId(2);
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		checklistManager.insertList(listData);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(listData.getListId() != null);
	}
	
	@Test
	public void testDeleteListByListId() {
		boolean failed = false;
		try {
			Integer listId = 2;
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.deleteListByListId(listId);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}
	
	@Test
	public void testGetItemsByListId() {
		Integer listId = 1;
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<ItemData> itemDataList = checklistManager.getItemsByListId(listId);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(CollectionUtils.isNotEmpty(itemDataList));
	}
	
	@Test
	public void testGetItemByItemId() {
		Integer itemId = 1;
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ItemData itemData = checklistManager.getItemByItemId(itemId);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(itemData != null);
	}
	
	@Test
	public void testUpdateItem() {
		boolean failed = false;
		try {
			ItemData itemData = new ItemData();
			itemData.setItemId(2);
			itemData.setItemText("item11");
			itemData.setCompleted("Y");
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.updateItem(itemData);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}
	
	@Test
	public void testInsertItem() {
		ItemData itemData = new ItemData();
		itemData.setItemText("item22");
		itemData.setListId(1);
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		checklistManager.insertItem(itemData);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		Assert.assertTrue(itemData.getItemId() != null);
	}
	
	@Test
	public void testDeleteItemByItemId() {
		boolean failed = false;
		try {
			Integer itemId = 8;
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.deleteItemByItemId(itemId);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}
	
	@Test
	public void testDeleteItemsByListId() {
		boolean failed = false;
		try {
			Integer listId = 1;
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			checklistManager.deleteItemsByListId(listId);
			stopWatch.stop();
			System.out.println(stopWatch.toString());
		} catch (Exception e) {
			System.out.println(e);
			failed = true;
		}
		
		Assert.assertTrue(!failed);
	}

}
