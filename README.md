# Bookstore-Management

#Follow the steps to run book-store management project

Pre-requisite:

Install mysql server locally (Follow this guide - https://flaviocopes.com/mysql-how-to-install/)
Create a database/schema in mysql with the name "bookstore_management" (Use Sequal Pro or TablePlus for sql queries)
Start mysql "mysql.server start"
Ensure java version is java8
How to run:

Change mysql credentials in application.properties (spring.datasource.username & spring.datasource.password)
Run "mvn clean install -U" on the project root folder (i.e, bookstore-management)
Run BookStoreManagementApplication class from IDE
Use the postman collection (BookStore.postman_collection.json) to do any CRUD operation
First call authenticate endpoint to get JWT token (http://localhost:8081/authenticate) (username, password can be found in the postman collection)
The JWT token is valid for 10 hours
For every CRUD operation, copy the token and pass in the Authorization header with the format "Bearer {{jwt}}"
