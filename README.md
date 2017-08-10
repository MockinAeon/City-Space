<div align=center><img width="120" height="120" src="https://github.com/MockinAeon/CitySpace/blob/master/src/main/webapp/resources/pic/logo.png"/></div>

# City Space Management

## A web application for users to check status, subscribe and reserve any space in a city
This is a Maven project with Java and using Spring MVC.<br>
•	Used Hibernate to map all the POJOs to the local MySQL database useing annotations.<br>
•	Created Java servlets with RESTful APIs to handle HTTP requests and responses.<br>
•	Provided dynamic support using AJAX and manipulated as well as validated the forms using jQuery.<br>
•	Designed and created a login page with a video and added great effects using HTML5, CSS and jQuery.<br>

## Developed environment:
java-version: 1.6 <br>
hibernate: 5.1.0.Final <br>
maven-compiler-plugin: 2.5.1 <br>
server: Tomcat v8.0 <br>

## Demo & Work Flow:

### System Admin manage space & accounts 
The system admin can add space according to the level:<br>
city --> enterprise --> building --> floor --> space <br>
<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/create%20space.gif"/></div>
<div align=center>Add Space</div>

<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/createaccount.gif"/></div>
<div align=center>Add Accounts</div>

### Login Page & User account created<br>
Here is the login page, used a video as background, when you move your mouse to the login part, the video will blur.<br>
And you can create user account, after creating, it will login automatically.<br>
<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/login.gif"/></div>

### User Account

For normal users, they can check all the space, subscribe it if they like. <br>
They can reserve a space(a request will be sent to credit role and reserve role for processing). <br>
<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/user%20account.gif"/></div>

### Credit Role
Processing all requests by sending advice according to user's behavior and credit.<br>
Manage user's credit<br>
<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/credit%20role.gif"/></div>

### Reserve Role
Processing all requests, if credit role has processed this request, there will be a message.<br>
<div align=center><img src="https://github.com/MockinAeon/CitySpace/blob/master/gif/reserve%20role.gif"/></div>
