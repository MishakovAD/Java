<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

			<%@ page import="java.util.HashMap" %>
			<%@ page import="java.util.Map" %> 
            <%@ page import="Users.*" %>
            <%@ page import="UserProfile.*" %>
            <%
            HashMap<Integer, UserProfile> list = (HashMap<Integer, UserProfile>) request.getAttribute("usersList");
            
            for (Map.Entry entry : list.entrySet()) {
            	UserProfile user = (UserProfile) entry.getValue();
            	int id =user.getUserId();
            	out.println("<a href=\"/niikp/users/" + id + "\">");
            	out.println("<h1>" + user.getName() + " " + user.getSecondName() + "</h1>"); 
            	out.println("</a>");
                //System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
            }
             
             %>

</body>
</html>