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
	<h2>Входящая корреспонденция</h2>
	<p><a href="/niikp/incomingMailList?pageNumber=1"><button>Назад</button></a>
	<a href="/niikp/exportSearchListIntoExcel"><button>Экспорт в Excel</button></a></p>
	
	<table border="1">	
	<!-- <form method="post" action="/niikp/incomingMailList?action=sort">
	<button type="submit">Сортировать</button>
	<br>	
		<tr>
		<th>		
			<select name="sortDateReg" style="width: 170px; height: 35px;">
					<option value="noSort">Не сортировать</option>
					<option value="increase">Возрастанию</option>
					<option value="decrease">Убыванию</option>
			</select></p> 
			Дата регистрации
			<br>
		</th>
		<th>
			<select name="sortNumReg" style="width: 170px; height: 35px;">
					<option value="noSort">Не сортировать</option>
					<option value="increase">Возрастанию</option>
					<option value="decrease">Убыванию</option>
			</select></p> 
			Рег. номер письма
			<br>
		</th>
		</form> -->
		
	<tr>
		<th>Дата регистрации</th>
		<th>Рег. номер письмa</th>
		<th>Тип письма</th>
		<th>		
			<!-- <select name="sortSender" style="width: 170px; height: 35px;">
					<option value="noSort">Не сортировать</option>
					<option value="increase">От А до Я</option>
					<option value="decrease">От Я до А</option>
			</select></p>  -->
			Отправитель
			<!-- <br> -->
		</th>
		<th>		
		<!--	<select name="sortSendDate" style="width: 170px; height: 35px;">
					<option value="noSort">Не сортировать</option>
					<option value="increase">Возрастанию</option>
					<option value="decrease">Убыванию</option>
			</select></p>  -->
			Дата отпр. письма
		<!--	<br> -->
			
		</form>
		</th>
			
			<th>Номер письма</th>
			<th>Тема письма</th>
			<th>Дата 2 этажа</th>
			<th>Номер 2 этажа</th>
			<th>Документ</th>
		</tr>
		<%@ page import="java.util.ArrayList"%>
		<%@ page import="java.util.HashMap"%>
		<%@ page import="IncomingMail.*"%>
		<%@ page import="Work.*"%>
		<%@ page import="UserProfile.*"%>
		<style>
		<%@include file="/resources/css/showHideForIncomingMailList.css"%> 
	</style>
	<%
	ArrayList<IncomingMail> list = (ArrayList<IncomingMail>) request.getSession().getAttribute("searchList");
	HashMap<Integer, ArrayList<Work>> resolutionMapForSearch = (HashMap<Integer, ArrayList<Work>>) request.getAttribute("resolutionMapForSearchIncomingMail");
	
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

if (list.get(i).getSecondFloorDate() != null && !list.get(i).getSecondFloorDate().equals("null")) {
out.println("<td>" + list.get(i).getSecondFloorDate() + "</td>");
} else {
out.println("<td>-</td>");
}

if (list.get(i).getSecondFloorNum() != null && !list.get(i).getSecondFloorNum().equals("null")) {
out.println("<td>" + list.get(i).getSecondFloorNum() + "</td>");
} else {
out.println("<td>-</td>");
}

if (list.get(i).getFilePathAndName() != null && !list.get(i).getFilePathAndName().contains("null") && list.get(i).getFilePathAndName().length() > 28) {
out.println("<td><a href=\"/niikp/DownloadServlet?" + list.get(i).getFilePathAndName().substring(28) + "\">" + list.get(i).getFilePathAndName().substring(28) + "</a></td>"); //was 28
} else if (list.get(i).getFilePathAndName() != null && !list.get(i).getFilePathAndName().contains("null")) {
	out.println("<td>" + list.get(i).getFilePathAndName() + "</td>");
} else {
out.println("<td>-</td>");
}








//out.println("<a href=\"" + list.get(i).getFilePathAndName() + "\"><td>" + "Документ" + "</td></a>");
//Продумать путь до файла, чтобы не было мусорки, и задать в web.xml

out.println("<td width=\"50\"><a href=\"/niikp/incomingMail/" + list.get(i).getIdMail() + 
	"?action=update\"><button type=\"submit\"><img src=\"resources/images/edit.png\" weight=20 height=20></button></a>");
	
	out.println("<a href=\"/niikp/incomingMail/" + list.get(i).getIdMail() + 
	"?action=delete\"><button type=\"submit\"><img src=\"resources/images/delete.jpg\" weight=20 height=20></button></a></td>");
	
	out.println("<td><a href=\"/niikp/workAdd?id=" + list.get(i).getIdMail() + 
		"&type=incomingMail\"><button type=\"submit\">Добавить резолюцию</button></a></td>");
	
	out.println("</tr>");
		
	out.println("<tr>");
	out.println("<td>");
		out.println("<div class=\"demo\">");			
		out.println("<input class=\"hide\" id=\"hd-" + i + "\" type=\"checkbox\">");
		out.println("<label for=\"hd-" + i + "\">Резолюция</label>");
		out.println("<div>");
			
			if (!resolutionMapForSearch.isEmpty()) {
			String isComplete = null;
			ArrayList<Work> resolutionList = (ArrayList<Work>) resolutionMapForSearch.get(list.get(i).getIdMail());
				for (Work resolution: resolutionList) {
				
				if (resolution.isComplete() == false) {
				isComplete = "Не выполнено";
			} else if (resolution.isComplete() == true) {
			isComplete = "Выполнено";
		}
		
				
		out.println("Кому: " + resolution.getUserNameFromId(resolution.getToUserId()) + "<br>");
		out.println("Поручение: " + resolution.getAssignment() + "<br>");
		out.println("Ход выполнения: " + isComplete + "<br>");
		out.println("Отчет: " + resolution.getReport() + "<br>");
		if (!(resolution.getReportFilePathAndNameToWork() == null) && resolution.getReportFilePathAndNameToWork().length()>27) {
		out.println("Отчетный файл: ");
		out.println("<a href=\"/niikp/DownloadServlet?" + resolution.getReportFilePathAndNameToWork().substring(27) + "\">"
		+ resolution.getReportFilePathAndNameToWork().substring(27) + "</a><br>");
	} else {
	out.println("Отчетный файл: Не прикреплен<br>");
}
		out.println("*******************<br>");
}

}
out.println("</div>");
out.println("</div>");
out.println("</td>");
out.println("</tr>"); 


	
	
}
out.println("</table>");
%>
	

</body>
</html>