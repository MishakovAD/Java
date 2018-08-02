<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Дела/Поручения</title>
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
                %>

                   
                
             
 
	<form method="post" action="workAdd?action=submit" enctype="multipart/form-data">
					<input type="text" name="user" value="" list="names" placeholder="Имя и Фамилия" />	
					<br><br><br>
					<input type="date" name="endDate" value="" placeholder="Срок исполнения" />
					<br><br><br>
					<input type="text" name="assignment" value="" placeholder="Поручение" />
					<br><br><br>
					<input name="file" type="file"><br>
					<br><br><br>
       				<button type="submit">Save</button>
       				
       				<datalist id="names">
						<%
							out.println("<option>");
            				out.println(user.getName() + " " + user.getSecondName()); 
            				out.println("</option>");	
            			} %>
						</datalist>
    </form>
</body>
</html>