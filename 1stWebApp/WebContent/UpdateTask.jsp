<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="lab4.db.model.User"%>
<%@page import="lab4.db.model.Status"%>
<%@page import="lab4.db.DbConnector"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	final User sessionUser = (User) session.getAttribute("user");
	final Integer taskid=Integer.parseInt(request.getParameter("taskid"));
	if (taskid != null) {
		session.setAttribute("taskid", taskid);
	}
	if (sessionUser == null || request.getParameter("stat") == null) {
		// Redirect User to Login Page
		response.sendRedirect( "Login.html"); 
	}
	final List<Status> Slist;
	try {
		// Get ALL vehicles
		DbConnector.getInstance().openDbConnection();
		Slist = DbConnector.getInstance().getStatus();
		DbConnector.getInstance().closeDbConnection();
	} catch (Throwable t) {
		// Inform the user in case of an Error
		final String errMsg = "Error ... " + t.getMessage() + " Ask system administrators for details !";
		response.getWriter().append(errMsg);
		t.printStackTrace();
		return;
	}
	final List<Integer> nl=new ArrayList<>();
	for (Status S :Slist) {
		final int id= S.getId();
		nl.add(id);
	}
	
	pageContext.setAttribute("nl", nl);
	pageContext.setAttribute("stat", request.getParameter("stat"));
	%>
	<p> <a href="LogoutServletex">Logout</a></p>
	<form action="UpdateTaskServlet" method="POST">
		Select Status:
		<select name="my-select">
		  <c:forEach items="${nl}" var="item">
			<option value="${item}" ${item == stat ? 'selected="selected"' : ''}>${item}</option>
		  </c:forEach>
		</select> 
		<input type="submit" value="Update">
	</form>

</body>
</html>