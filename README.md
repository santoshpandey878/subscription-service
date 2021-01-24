# subscription-service

Implemented **"Subscription Service"** using **Spring Boot with Java** (back-end).

**Source code:**

Source code : /subscription-service-master/subscriptionservice



**Steps to run the application:**

You can run the application by docker compose.

Created docker-compose.yml file and it is integrated with all modules: back-end and database postgresql. When you run this script, it will fetch the database image, build the backend module, create images and start both containers. File **schema.sql (present inside: /subscription-service-master/subscriptionservice/SQL)** is also integrated with the database using this yml file. It will create the database schema and populate the databse for predefined data for tables users, product, subscription_plan and voucher.

Below are the steps to run the application:

**1.** Go inside directory:     /subscription-service-master/subscriptionservice

**2.** Execute the command:     docker-compose up

Application will start on port 9082 **(http://localhost:9082/subscriptionservice/)**.


**API Documentation**

Integrated the swagger for detailed API documentation. You can check with these URLs:

http://localhost:9082/subscriptionservice/swagger-ui.html

http://localhost:9082/subscriptionservice/v2/api-docs


**Tools & Technologies Used**

Java 8

Spring Boot

Spring Data JPA

PostgreSQL  

Swagger      (Used for REST API documentation)

Docker Compose

**Testing Guide**

You can test the service either by using Postman or Swagger UI integrated with application. Link is mentioned above.

Please have a look on file **API_Request_Response_Sample.docx** present inside /subscription-service-master/subscriptionservice directory. This file have request response sample to test the application.

**Note:** I have pouplated users table with one user having email as "user1@gmail.com" that you can use to test the application. Although, I have also implemented the API to create a new user. You can use that as well. Existing user's email is required to subscribe a product.
