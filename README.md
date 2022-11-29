# Cinema app ![](Icon.png)

# Project purpose
This is simple simulator of cinema service for reservation tickets, that supports registration, authentication and CRUD operations.
___

## Features:
* register or login as user
* create and find movies
* create and find available movie sessions
* creating shopping cart
* add tickets to shopping cart
* complete an order
---

## Used technologies
- Java 11
- Apache Maven
- Apache Tomcat
- MySQL
- Hibernate
- Spring

## Project architecture:
The Cinema-Service is based on 3-layer architecture:
1. Controllers process requests, call services and send responses
2. Services - there are all business logic
3. DAO - there are CRUD operations to database

## How to run this project?
- Clone the project
- You must have installed and configured MySQL and Apache Tomcat
- Edit database parameters in resources
- Run the application with Tomcat

### Model structure
![](Cinema_Uml.png)
---
