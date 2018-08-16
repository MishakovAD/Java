<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Входящая корреспонденция</title>

<!-- Подключение bootstrap -->
<script type="text/javascript">
  <%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
	<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "head.jsp" %>


<body>
	<table border="1">
		<caption>Входящая корреспонденция</caption>
		<tr>
			<th>Дата регистрации</th>
			<th>Рег. номер письма</th>
			<th>Тип письма</th>
			<th>Отправитель</th>
			<th>Дата отправления письма</th>
			<th>Номер письма</th>
			<th>Тема письма</th>
			<th>Дата 2 этажа</th>
			<th>Номер 2 этажа</th>
			<th>Документ</th>
			<th><a href="/niikp/downloadExcel">Excel файл</a></th>
		</tr>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="IncomingMail.*"%>
		<%@ page import="UserProfile.*"%>
		<%
			ArrayList<IncomingMail> list = (ArrayList<IncomingMail>) request.getAttribute("incomingMailList");
			for (int i = 0; i < list.size(); i++) {
				out.println("<tr>");
				if (list.get(i).getRegDate() != null) {
					out.println("<td>" + list.get(i).getRegDate() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				out.println("<td>" + list.get(i).getIdMail() + "</td>");
				
				if (list.get(i).getTypeMail() != null) {
					out.println("<td>" + list.get(i).getTypeMail() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getSender() != null && !list.get(i).getSender().isEmpty()) {
					out.println("<td>" + list.get(i).getSender() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getSendDate() != null) {
					out.println("<td>" + list.get(i).getSendDate() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getMailNum() != null && !list.get(i).getMailNum().isEmpty()) {
					out.println("<td>" + list.get(i).getMailNum() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getMailTheme() != null && !list.get(i).getMailTheme().isEmpty()) {
					out.println("<td>" + list.get(i).getMailTheme() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getSecondFloorDate() != null) {
					out.println("<td>" + list.get(i).getSecondFloorDate() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getSecondFloorNum() != null) {
					out.println("<td>" + list.get(i).getSecondFloorNum() + "</td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				if (list.get(i).getFilePathAndName() != null && !list.get(i).getFilePathAndName().contains("null")) {
					out.println("<td><a href=\"/niikp/DownloadServlet?" + list.get(i).getFilePathAndName().substring(28) + "\">" + list.get(i).getFilePathAndName().substring(28) + "</a></td>"); //was 28
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				
				
				
				
				
				
				
				//out.println("<a href=\"" + list.get(i).getFilePathAndName() + "\"><td>" + "Документ" + "</td></a>");
				//Продумать путь до файла, чтобы не было мусорки, и задать в web.xml
				
				out.println("<td><button type=\"submit\"><a href=\"/niikp/incomingMail/" + list.get(i).getIdMail() + "?action=update\">Редактировать</a></button></td>");
				out.println("<td><button type=\"submit\"><a href=\"/niikp/incomingMail/" + list.get(i).getIdMail() + "?action=delete\">Удалить</a></button></td>");
				out.println("<td><button type=\"submit\"><a href=\"/niikp/workAdd?id=" + list.get(i).getIdMail() + "&type=incomingMail\">Добавить резолюцию</a></button></td>");
				out.println("</tr>");	
				//Для добавления резолюции к определенному письму можно создать кнопку с адресом и параметрами, которые будут передаваться, типа вход, айди. И красота
			}
			out.println("</table>");
		%>
	<a href="/niikp/incomingMail"><button type="submit">Добавить</button></a>
	
</body>
</html>