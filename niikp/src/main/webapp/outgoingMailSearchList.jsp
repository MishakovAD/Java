<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Исходящая корреспонденция</title>

<!-- Подключение bootstrap -->
<script type="text/javascript">
<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "head.jsp" %>


<body>
<p><h2>Исходящая корреспонденция</h2>
	<p><a href="/niikp/incomingMailList?pageNumber=1"><button>Назад</button></a>
	<a href="/niikp/exportOutgoingMailSearchListIntoExcel"><button>Экспорт в Excel</button></a></p>
	
	<table border="1">	
	<tr>
	<th>Дата регистрации</th>
	<th>№ Ф-ВА-01/</th>
	
	<th>Адресат</th>
	
	<th>ФИО кому</th>
	
	<th>Дата отпр. письма</th>
	
	<th>Тема письма</th>
	
	<th>Исполнитель письма</th>
	
	<th>Исполнитель реальный</th>

	<th>Номер вх. письма</th>
			
	<th>Кому расписано</th>
	
	<th>Ответ на вх. №</th>
	</form>	
	<th>Примечание</th>
	<th>Примечание по списку рассылки</th>
	<th>Документ</th>
	</tr>
		<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.HashMap"%>
	<%@ page import="OutgoingMail.*"%>
	<%@ page import="IncomingMail.*"%>
	<%@ page import="Work.*"%>
	<%@ page import="UserProfile.*"%>
	<% 
	ArrayList<OutgoingMail> list = (ArrayList<OutgoingMail>) request.getSession().getAttribute("searchListOutgoingMail");
	
	for (int i = 0; i < list.size(); i++) {				
		out.println("<tr>");

		if (list.get(i).getRegDate() != null) {
			out.println("<td>" + list.get(i).getRegDate().substring(0, 10) + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getMailNum() != null) {
			out.println("<td>" + list.get(i).getMailNum() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getDestination() != null && !list.get(i).getDestination().isEmpty()) {
			out.println("<td>" + list.get(i).getDestination() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getForWhom() != null) {
			out.println("<td>" + list.get(i).getMailNum() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getSendDate() != null) {
			out.println("<td>" + list.get(i).getSendDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getMailTheme() != null && !list.get(i).getMailTheme().isEmpty()) {
			out.println("<td>" + list.get(i).getMailTheme() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getExecutor() != null) {
			out.println("<td>" + list.get(i).getExecutor() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getRealExecutor() != null) {
			out.println("<td>" + list.get(i).getRealExecutor() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getIncomingMailNum() != null && !list.get(i).getIncomingMailNum().isEmpty()) {
			out.println("<td>" + list.get(i).getIncomingMailNum() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getToWhomItIsPainted() != null && !list.get(i).getToWhomItIsPainted().equals("null")) {
			out.println("<td>" + list.get(i).getToWhomItIsPainted() + "</td>");
		} else {
			out.println("<td>-</td>");
		}
		
		if (list.get(i).getIncomingMailId() != 0) {
			out.println("<td>" + list.get(i).getIncomingMailId() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getNote() != null && !list.get(i).getNote().equals("null")) {
			out.println("<td>" + list.get(i).getNote() + "</td>");
		} else {
			out.println("<td>-</td>");
		}
		
		if (list.get(i).getMailingNote() != null && !list.get(i).getMailingNote().equals("null")) {
			out.println("<td>" + list.get(i).getMailingNote() + "</td>");
		} else {
			out.println("<td>-</td>");
		}


out.println("<td width=\"50\"><a href=\"/niikp/outgoingMail/" + list.get(i).getIdMail() + 
	"?action=update\"><button type=\"submit\"><img src=\"resources/images/edit.png\" weight=20 height=20></button></a>");
	
	out.println("<a href=\"/niikp/outgoingMail/" + list.get(i).getIdMail() + 
	"?action=delete\"><button type=\"submit\"><img src=\"resources/images/delete.jpg\" weight=20 height=20></button></a></td>");
	
	out.println("</tr>");
	
	
}
out.println("</table>");
%>
	

</body>
</html>