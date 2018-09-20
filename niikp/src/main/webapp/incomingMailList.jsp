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
	<p><h2>Входящая корреспонденция</h2>
	
	<a href="/niikp/incomingMail">Добавить Входящую корреспонденцию</button></a>
	<a href="/niikp/downloadIncomingMailExcel">Скачать Excel файл</a> </p>
	
	<form method="get" action="/niikp/incomingMailList?pageNumber=num">
Перейти к странице: <input type="text" name="pageNumber" size="20">
<button type="submit">Перейти</button>
</form>

<style>
.pagination {padding: 20px; clear:both;}
.pagination ul {text-align: center;}
.pagination ul li {display: inline-block; margin: 0 1px; vertical-align: middle; line-height: 12px; padding: 2px 6px;}
.pagination ul li a, .pagination ul li span {color: #666f80;} 
.pagination ul li a:hover, .pagination ul li span:hover {background: #f5f5f5; -webkit-border-radius: 1px; -moz-border-radius: 1px; border-radius: 1px;}
.pagination ul li a.prev, .pagination ul li a.next {font-weight: bold;}
.pagination ul li.active a, .pagination ul li.active span {color: #2d4c80; font-size: 16px; font-weight: bold;}
.pagination ul li.active a:hover, .pagination ul li.active span:hover {background: none;} 
}


</style>

<%@ page import="Pagination.*"%>
<%@ page import="java.util.Map"%>
<%
Map<Integer, Boolean> paginationPages = (Map<Integer, Boolean>) request.getAttribute("paginationPages");
int lastValueKey = paginationPages.keySet().size();

%>


<div class="pagination">
	<ul>
		<li><a href="#" class="prev"><</a></li>
		<% for (Integer key : paginationPages.keySet()) { %>
		<li><a href="/niikp/incomingMailList?pageNumber=<%=key%>"><%=key%></a></li>
		<% } %>
		<span>&hellip;</span>
		<li><a href="/niikp/incomingMailList?pageNumber=1">Вернуться в начало</a></li>
		<li><a href="#" class="next">></a></li>
	</ul>
</div>
	
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
		<th>№</th>
		<th>Дата регистрации
		<form method="post" action="/niikp/search?search=searchIncomingMail" class="form-inline my-2 my-lg-0">
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchRegDate" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>

		</th>
		<th>Рег. номер письма
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchIdMail" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
		</th>
		
		<th>Тип письма
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchMailType" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
		</th>
		<th>Отправитель
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchSender" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
			
		</th>
		<th>Дата отпр. письма
<input class="form-control mr-sm-2" type="search" name="searchSendDate" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
		</th>
			
			<th>Номер письма
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchMailNum" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
			</th>
			<th>Тема письма
			<form method="post" action="/niikp/search?search=searchMailTheme" class="form-inline my-2 my-lg-0">
<input class="form-control mr-sm-2" type="search" name="searchMailTheme" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
			</th>
			<th>Дата 2 этажа
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchSecondFloorDate" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
			</th>
			<th>Номер 2 этажа
<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchSecondFloorNum" placeholder="Search" aria-label="Search">
<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
</form>
			</th>
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
	ArrayList<IncomingMail> list = (ArrayList<IncomingMail>) request.getAttribute("incomingMailList");
	HashMap<Integer, ArrayList<Work>> resolutionMap = (HashMap<Integer, ArrayList<Work>>) request.getAttribute("resolutionListForIncomingMail");
	
	for (int i = 0; i < list.size(); i++) {				
	out.println("<tr>");
	
	out.println("<td>" + (i + 1) + "</td>");
		

		if (list.get(i).getRegDate() != null) {
			if (list.get(i).isOnControl()) {
				out.println("<td bgcolor=\"#FFFB00\">" + list.get(i).getRegDate() + "</td>");
			} else {
				out.println("<td>" + list.get(i).getRegDate() + "</td>");
			}		
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
out.println("<td><a href=\"/niikp/DownloadServlet?" + list.get(i).getFilePathAndName().substring(28) + "\">" + list.get(i).getFilePathAndName().substring(43) + "</a></td>"); //was 28
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
	out.println("<td><a href=\"/niikp/outgoingMail?id=" + list.get(i).getIdMail() + 
			"&type=incomingMail\"><button type=\"submit\">Добавить исходящее письмо</button></a></td>");
	
	out.println("</tr>");
		
	out.println("<tr>");
	out.println("<td> &#8593; </td>");
	out.println("<td colspan=\"10\">");
		out.println("<div class=\"demo\">");			
		out.println("<input class=\"hide\" id=\"hd-" + i + "\" type=\"checkbox\">");
		out.println("<label for=\"hd-" + i + "\">Резолюция к письму под №" + (i+1) +"</label>");
		out.println("<div>");
				
			
			if (!resolutionMap.isEmpty()) {
			String isComplete = null;
			ArrayList<Work> resolutionList = (ArrayList<Work>) resolutionMap.get(list.get(i).getIdMail());
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