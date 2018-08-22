<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Updating</title>
</head>
<body>
	<%@ page import="IncomingMail.*" %>
	<%@ page import="java.text.SimpleDateFormat"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.Date"%>
	<%@ page import="java.util.Locale"%>
	<%
	IncomingMail incomingMail = (IncomingMail) request.getAttribute("incomingMail");
	
	Date resultSendDate = null;
	Date resultSecondFloorDate = null;
	String sendDate = null;
	String secondFloorDate = null;
	SimpleDateFormat oldDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
	SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
	if (!incomingMail.getSendDate().equals("null")) {
	resultSendDate = oldDateFormat.parse(incomingMail.getSendDate());
	sendDate = newDateFormat.format(resultSendDate);
} else {
resultSendDate = oldDateFormat.parse("01-01-0001");
sendDate = newDateFormat.format(resultSendDate);
}
if (!incomingMail.getSecondFloorDate().equals("null")) {
resultSecondFloorDate = oldDateFormat.parse(incomingMail.getSecondFloorDate());				
secondFloorDate = newDateFormat.format(resultSecondFloorDate);
} else {
resultSecondFloorDate = oldDateFormat.parse("01-01-0001");				
secondFloorDate = newDateFormat.format(resultSecondFloorDate);
}




%>




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
			<input type="text" name="sender" value="<%= incomingMail.getSender() %>" list="senderList" placeholder="Отправитель" />	
			<br><br><br>
			<p>Дата отправления письма: </>
				<input type="text" required name="sendDate" value="<%= sendDate %>" placeholder="Дата отправления письма" />
				<br><br><br>
				<p>Номер письма: </>
					<input type="text" name="mailNum" value="<%= incomingMail.getMailNum() %>" placeholder="Номер письма" />
					<br><br><br>
					<p>Тема письма: </>
						<input type="text" name="mailTheme" value="<%= incomingMail.getMailTheme() %>" placeholder="Тема письма" />
						<br><br><br>
						<p>Дата, присваевыемая при первичной рег. документа: </>
							<input type="text" name="secondFloorDate" value="<%= secondFloorDate %>" placeholder="Дата, присваевыемая при первичной рег. документа" />
							<p>Номер письма, присваевыемый при первичной рег. документа: </>
								<input type="text" name="secondFloorNum" value="<%= incomingMail.getSecondFloorNum() %>" placeholder="Номер письма, присваевыемый при первичной рег. документа" />
								<br><br><br>
								<p>Документ: </>
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