# JAVA Web Application

This project was the biggest task of the final exam of the course "Internet and Applications" of NTUA - 2023. We were asked to develop part of a web application that allows registered users to change the "status" of a task assigned to them.

## Description

You are given the Database (**exam0623db.sql**) which contains the following tables and fields:
**USER** ( ID, USERNAME, USERPASS, <ins>ROLE_ID</ins> ), **ROLE** ( ID, NAME ), **TASK** ( ID, <ins>USER_ID</ins>, TITLE, DESCRIPTION, <ins>STATUS_ID</ins>, DATE_UPDATED ), **STATUS** ( ID, NAME ) and some sample data. The IDs are the primary keys of the corresponding tables and the underlined fields are the Foreign Keys.

<br>

In the Login HTML page (**Login.html**) fill in the HTML elements that
are missing to send the user's username and password to the predefined address in the main
part of the message and (b) format this page through the use of **CSS** so that:

- The headers ("h1" and "h3") are placed in the center of the page and are red in color.
- The table ("table") containing the form elements should be located in the center of the page.
- The table should have a green background colour.
- The table should have a border 4 pixels thick, black in color and be dashed.
- The "reset" button of the panel should have a blue font color while the "log-in" button should have a green font color.

<br>

Develop **JavaScript** code that does the following:

- Prevents the user from logging in (by making disabled = true | false the submit button) if the username Sis empty.
- Examines how "strong" the user's existing password is, displaying an appropriate message in the field next to the password. For the purposes of this exercise, assume that if the password has only alphanumeric characters is considered EASY otherwise, if it has less than 8 characters it is MODERATE otherwise DIFFICULT.

<br>

Create a **Servlet** named "ViewTasks" that takes as input the username and password of the user and works as follows:

- Servlet URL: http://localhost:8080/PROJECTNAME/ViewTasks?username=u2&password=p2.
- It first checks if the user's username and password are correct and then displays the role that user has in the system, on the page returned to that user.
- If the user is NOT a system administrator, display the Tasks assigned them and additional information about each of the tasks. In particular, the task ID, the title, the first 50 characters of the description, the state it is in and the date it was last updated in the following format "29/06/2023".

<br>

Create a **JSP page** named "UpdateTask.jsp" that allows the user to change the status of a
task assigned to them. Specifically:

- JSP URL: http://localhost:8080/PROJECTNAME/UpdateTask.jsp?task_id=3&status_id=1.
- The JSP page should allow the user to select the new status from a DropDown list that will be built based on the available statuses in the database, having the current state selected by default (html option, selected).
- When the user changes the status and presses the submit button, update the database.

<br>

Modify the above code to allow the above functionality only to registered users who will have access through the Login page to the rest of the site, while giving the ability to logout independently on the page they are on.

## Dependencies

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)
- [Apache Tomcat (version 8.5)](https://tomcat.apache.org/download-80.cgi#8.5.63)
- [XAMPP](https://www.apachefriends.org/download.html)
- [Eclipse IDE Enterprise Edition](https://www.eclipse.org/downloads/packages/)
- [MySQL DB Connector](https://dev.mysql.com/downloads/connector/j/)

## Starting the Project

Follow these steps to run the application:

1. Start Apache Server and MySQL Database from XAMPP Control panel.
2. Create a database named `exam0623db` in the Server.
3. Run the queries found in `/1stWebApp/SQL/exam0623db.sql` to import the tables and the sample data in the database.
4. Import the project (directory `/1stWebApp`) to Eclipse, and configure any outdated dependencies.
5. Start the project by right clicking it --> Run As --> Run on Server
