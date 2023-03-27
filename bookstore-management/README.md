#Follow the steps to run book-store management project

Pre-requisite: 
1) Install mysql server locally (Follow this guide - https://flaviocopes.com/mysql-how-to-install/)
2) Create a database/schema in mysql with the name "bookstore_management" (Use Sequal Pro or TablePlus for sql queries)
3) Start mysql "mysql.server start"
4) Ensure java version is java8

How to run: 
1) Change mysql credentials in application.properties (spring.datasource.username & spring.datasource.password)
2) Run "mvn clean install -U" on the project root folder (i.e, bookstore-management)
3) Run BookStoreManagementApplication class from IDE
4) Use the postman collection (BookStore.postman_collection.json) to do any CRUD operation
5) First call authenticate endpoint to get JWT token (http://localhost:8081/authenticate) (username, password can be found in the postman collection)
6) The JWT token is valid for 10 hours   
7) For every CRUD operation, copy the token and pass in the Authorization header with the format "Bearer {{jwt}}"


