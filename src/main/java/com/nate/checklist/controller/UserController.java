package com.nate.checklist.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

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

import com.nate.checklist.domain.UserData;
import com.nate.checklist.domain.UserDataList;
import com.nate.checklist.jdbc.ChecklistManager;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private static final String USERS_URI = "/checklist/users/";
	
	@Autowired
	private ChecklistManager checklistManager;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody HttpEntity<UserDataList> getAllUsers() {
		UserDataList userDataListObj = new UserDataList();
		
		List<UserData> userDataList = checklistManager.getAllUsers();
		
		//Add links
		for (UserData userData : userDataList) {
			userData.add(linkTo(methodOn(UserController.class).getUser(userData.getUserId())).withSelfRel());
		}
		
		userDataListObj.setUserDataList(userDataList);
		
		return new ResponseEntity<UserDataList>(userDataListObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody ResponseEntity<UserData> getUser(@PathVariable Integer userId) {
		UserData userData = checklistManager.getUserByUserId(userId);
		
		if (userData != null) {
			return new ResponseEntity<UserData>(userData, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<UserData> createUser(@RequestBody UserData userData) {
		//User Name is required on create
		if (StringUtils.isBlank(userData.getUserName())) {
			return new ResponseEntity<UserData>(HttpStatus.BAD_REQUEST);
		}
		
		checklistManager.insertUser(userData);
		
		//Return the location of the created User in the header response
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", USERS_URI + userData.getUserId());
		return new ResponseEntity<UserData>(userData, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces = "application/xml")
	public @ResponseBody ResponseEntity<UserData> updateUser(@PathVariable Integer userId, @RequestBody UserData userData) {
		//User Name is required on update
		if (StringUtils.isBlank(userData.getUserName())) {
			return new ResponseEntity<UserData>(HttpStatus.BAD_REQUEST);
		}
		
		//Check if user exists and refuse request if it does not
		UserData existingUserData = checklistManager.getUserByUserId(userId);
		if (existingUserData == null) {
			return new ResponseEntity<UserData>(HttpStatus.NOT_FOUND);
		}
		
		//Set userId from URL
		userData.setUserId(userId);
		
		checklistManager.updateUser(userData);
		
		return new ResponseEntity<UserData>(HttpStatus.OK);
	}

}
