#Kasean-travel.agency
This is simple 'Travel agency' web application.

##Requirements
1. JDK 11

2. Apache Maven

##Build application:

mvn clean install

cd web-app

mvn clean install

##Select profiles for db

in application.properties file:

spring.profiles.active=dev(for use H2 in memory database)
spring.profiles.active=prod(for use MySQL database)

##Local tests
From the same directory as your root pom.xml, type: for start web-app...

cd web-app/target

java -jar web-app.jar

In order to start working with the application, you need to follow the links:

http://localhost:8080/create-users - to create test users

http://localhost:8080/create-tours - to create test tours

http://localhost:8080/ - authorization page 

###(["user@user.com", "user"], ["admin@admin.com", "admin"]) - logins and passwords for the user and administrator.