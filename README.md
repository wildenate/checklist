Database Setup:
Import chklst.sql in to MySQL.  Import Structure and Data.  Schema name should default to 'chklst'.

Project setup:
Import maven project.
Update datasource password in applicationContext.xml for deploy or applicationContext-test.xml for integration tests.
Deploy to app server (ie: Tomcat 9)

API:
GET	/checklist/users/				Returns all users
GET	/checklist/users/*USERID*			Returns specific user
POST	/checklist/users/				Creates a new user - returns new location in header
POST	/checklist/users/*USERID*			Updates an existing user
GET	/checklist/users/*USERID*/lists/		Returns all lists for a user
GET	/checklist/users/*USERID*/lists/*LISTID*	Returns a specific list
POST	/checklist/users/*USERID*/lists/		Creates a new list - returns new location in header
POST	/checklist/users/*USERID*/lists/*LISTID*	Updates an existing list
DELETE	/checklist/users/*USERID*/lists/*LISTID*	Deletes a list and its child items
GET	/checklist/lists/*LISTID*/items/		Returns all items for a list
GET	/checklist/lists/*LISTID*/items/*ITEMID*	Returns a specific item
POST	/checklist/lists/*LISTID*/items/		Creates a new item - returns new location in header - 'Completed' flag defaults to N
POST	/checklist/lists/*LISTID*/items/*ITEMID*	Updates an existing item
DELETE	/checklist/lists/*LISTID*/items/*ITEMID*	Deletes an item