<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Поиск: Входящая корреспонденция</title>

<!-- Подключение bootstrap -->
<script type="text/javascript">
	<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "/head.jsp" %>


<body>
	<h2>Внутренняя корреспонденция</h2>
	<p><a href="/niikp/internalMailList?pageNumber=1"><button>Назад</button></a>
	<a href="/niikp/exportInternalMailSearchListIntoExcel"><button>Экспорт в Excel</button></a></p>
	
	<table border="1">	
		
	<tr>
	<th>Номер документa</th>
	<th>Дата регистрации</th>
	<th>Тип документa</th>	
	<th>№ НПК-1/</th>
	<th>Адресат (Кому)</th>
	<th>Дополнительный адресат</th>	
	<th>Тема документа</th>
	<th>Исполнитель</th>
	<th>Примечание</th>
	</tr>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="java.util.HashMap"%>
		<%@ page import="InternalMail.*"%>
		<%@ page import="Work.*"%>
		<%@ page import="UserProfile.*"%>
	<%
	ArrayList<InternalMail> list = (ArrayList<InternalMail>) request.getSession().getAttribute("searchListInternalMail");
	
	for (int i = 0; i < list.size(); i++) {				
		out.println("<tr>");
		out.println("<td>" + list.get(i).getIdMail() + "</td>");
		
		if (list.get(i).getRegDate() != null) {
			out.println("<td>" + list.get(i).getRegDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}


		if (list.get(i).getDocType() != null && !list.get(i).getDocType().isEmpty()) {
			out.println("<td>" + list.get(i).getDocType() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getNumNPK() != null && !list.get(i).getNumNPK().isEmpty()) {
			out.println("<td>" + list.get(i).getNumNPK() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getDestination() != null && !list.get(i).getDestination().isEmpty()) {
			out.println("<td>" + list.get(i).getDestination() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getAdditionalDestination() != null && !list.get(i).getAdditionalDestination().isEmpty()) {
			out.println("<td>" + list.get(i).getAdditionalDestination() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getDocTheme() != null && !list.get(i).getDocTheme().isEmpty()) {
			out.println("<td>" + list.get(i).getDocTheme() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getExecutor() != null && !list.get(i).getExecutor().isEmpty()) {
			out.println("<td>" + list.get(i).getExecutor() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getNote() != null && !list.get(i).getNote().isEmpty()) {
			out.println("<td>" + list.get(i).getNote() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		
		out.println("<td width=\"50\"><a href=\"/niikp/internalMail/" + list.get(i).getIdMail() + 
				"?action=update\"><button type=\"submit\"><img src=\"resources/images/edit.png\" weight=20 height=20></button></a>");
		out.println("<a href=\"/niikp/internalMail/" + list.get(i).getIdMail() + 
				"?action=delete\"><button type=\"submit\"><img src=\"resources/images/delete.jpg\" weight=20 height=20></button></a></td>");

out.println("</tr>");	

}
out.println("</table>");
%>
	

</body>
</html>