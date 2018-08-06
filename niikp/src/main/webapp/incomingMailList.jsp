<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Входящая корреспонденция</title>
</head>
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
			<th>Дата и номер 2 этажа</th>
			<th>Документ</th>
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
				
				if (list.get(i).getFilePathAndName() != null) {
					out.println("<td><a href=\"/niikp/DownloadServlet?" + list.get(i).getFilePathAndName().substring(29) + "\">Документ</a></td>");
				} else {
					out.println("<td>Не заполнено</td>");
				}
				
				
				
				
				
				
				
				
				//out.println("<a href=\"" + list.get(i).getFilePathAndName() + "\"><td>" + "Документ" + "</td></a>");
				//Продумать путь до файла, чтобы не было мусорки, и задать в web.xml
				
				out.println("<td><button type=\"submit\"><a href=\"/niikp/incomingMail/" + i + "?action=update\">Редактировать</a></button></td>");
				out.println("<td><button type=\"submit\"><a href=\"/niikp/incomingMail/" + i + "?action=delete\">Удалить</a></button></td>");
				out.println("</tr>");				
			}
			out.println("</table>");
		%>
	<button type="submit"><a href="/niikp/incomingMail">Добавить</a></button>
	
</body>
</html>