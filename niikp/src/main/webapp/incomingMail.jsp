<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Входящая корреспонденция</title>
</head>
<body>
		<form method="post" action="incomingMail?action=submit" enctype="multipart/form-data">
		<p>Тип письма: </>
		<select name="typeMail">
		<%@ page import="IncomingMail.*"%>
		<%@ page import="java.util.ArrayList"%>
		<% 
			ArrayList<String> typeMail = (ArrayList<String>) request.getAttribute("typeMailList");
			for (String values : typeMail) { %>
				<option value="<%= values %>"><%= values %></option>
			<% } %>
    	</select></p> 
   		<br><br><br>
   		<p>Отправитель: </>
		<input type="text" name="sender" value="" list="senderList" placeholder="Отправитель" />	
		<br><br><br>
		<p>Дата отправления письма: </>
		<input type="date" required name="sendDate" value="" placeholder="Дата отправления письма" />
		<br><br><br>
		<p>Номер письма: </>
		<input type="text" name="mailNum" value="" placeholder="Номер письма" />
		<br><br><br>
		<p>Тема письма: </>
		<input type="text" name="mailTheme" value="" placeholder="Тема письма" />
		<br><br><br>
		<p>Дата, присваевыемая при первичной рег. документа: </>
		<input type="date" required name="secondFloorDate" value="" placeholder="Дата, присваевыемая при первичной рег. документа" />
		<p>Номер письма, присваевыемый при первичной рег. документа: </>
		<input type="text" required name="secondFloorNum" value="" placeholder="Номер письма, присваевыемый при первичной рег. документа" />
		<br><br><br>
		<p>Письмо: </>
		<input name="file" type="file"><br>
		<br><br><br>
       	<button type="submit">Save</button>
       	
       	<datalist id="senderList">
						<%@ page import="IncomingMail.*"%>
						<%@ page import="java.util.ArrayList"%>
						<% 
							ArrayList<String> senderList = (ArrayList<String>) request.getAttribute("senderMailList");
							for (String values : senderList) { 
							out.println("<option>");
            				out.println(values); 
            				out.println("</option>");	
            			} %>
						</datalist>
       	
	</form>
</body>
</html>