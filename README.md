# MenagerPilkarski
Football manager (teams, football players, matches, stadiums). Adding, deleting, editing.  
Project for database subject on Poznań University of Technology

## Techonology
Backend in Java. Spring boot, Hibernate, MySql.  
  
Frontend in separate project(propably in AngularJS)

## Requirements
- JDK 8
- GIT
- Maven
- MySQL Database
- MySQL Workbench (optional but recommended for managing)
- NodeJS required to run frontpage
*******

## Setting up database
1. create schema **meneger_pilkarski**
2. create user **meneger** with password: **mpilkarski123#** 
3. grant him all privs in schema **meneger_pilkarski**
********

## To Run
1. clone from github
2. cd $clone_dir
3. <code>mvn flyway:clean & mvn flyway:migrate</code>
4. <code>mvn clean package && java -jar target/MenegerPilkarski-${version}.jar</code>
*******

## To Run frontpage
1. cd Webpage
2. npm start
*******

### Updating local database:
1. mvn flyway:clean
2. mvn flyway:migrate
