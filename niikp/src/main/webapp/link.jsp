<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Ссылка, которую вы нажали</title>

<!-- Подключение bootstrap -->
<script type="text/javascript">
	<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "head.jsp" %>
</head>
<body>
	
	<input type="button" onclick="history.back();" value="Назад"/>
	
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="java.util.HashMap"%>
		<%@ page import="IncomingMail.*"%>
		<%@ page import="Work.*"%>
		<%@ page import="UserProfile.*"%>
		
		<% 
		IncomingMail incMail = (IncomingMail) request.getAttribute("incomingMailForLink");
		if (incMail != null) { %>
			<table border="1">	
			<tr>
				<th>Дата регистрации</th>
				<th>Рег. номер письмa</th>
				<th>Тип письма</th>
				<th>Отправитель</th>
				<th>Дата отпр. письма</th>					
				<th>Номер письма</th>
				<th>Тема письма</th>
				<th>Дата 2 этажа</th>
				<th>Номер 2 этажа</th>
				<th>Документ</th>
			</tr>
		<%
		out.println("<tr>");


		if (incMail.getRegDate() != null) {
			out.println("<td>" + incMail.getRegDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		out.println("<td>" + incMail.getIdMail() + "</td>");

		if (incMail.getTypeMail() != null) {
			out.println("<td>" + incMail.getTypeMail() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (incMail.getSender() != null && !incMail.getSender().isEmpty()) {
			out.println("<td>" + incMail.getSender() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (incMail.getSendDate() != null) {
			out.println("<td>" + incMail.getSendDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (incMail.getMailNum() != null && !incMail.getMailNum().isEmpty()) {
			out.println("<td>" + incMail.getMailNum() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (incMail.getMailTheme() != null && !incMail.getMailTheme().isEmpty()) {
			out.println("<td>" + incMail.getMailTheme() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (incMail.getSecondFloorDate() != null && !incMail.getSecondFloorDate().equals("null")) {
			out.println("<td>" + incMail.getSecondFloorDate() + "</td>");
		} else {
			out.println("<td>-</td>");
		}

		if (incMail.getSecondFloorNum() != null && !incMail.getSecondFloorNum().equals("null")) {
			out.println("<td>" + incMail.getSecondFloorNum() + "</td>");
		} else {
			out.println("<td>-</td>");
		}

		if (incMail.getFilePathAndName() != null && !incMail.getFilePathAndName().contains("null") && incMail.getFilePathAndName().length() > 28) {
		out.println("<td><a href=\"/niikp/DownloadServlet?" + incMail.getFilePathAndName().substring(28) + "\">" + incMail.getFilePathAndName().substring(28) + "</a></td>"); //was 28
		} else if (incMail.getFilePathAndName() != null && !incMail.getFilePathAndName().contains("null")) {
			out.println("<td>" + incMail.getFilePathAndName() + "</td>");
		} else {
			out.println("<td>-</td>");
		}
		}
		
		%>
</body>
</html>