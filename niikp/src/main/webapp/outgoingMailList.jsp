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

<a href="/niikp/outgoingMail">Добавить Исходящую корреспонденцию</button></a>
<a href="/niikp/downloadOutgoingMailExcel">Скачать Excel файл</a> </p>

<form method="get" action="/niikp/outgoingMailList?pageNumber=num">
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
	<li><a href="/niikp/outgoingMailList?pageNumber=<%=key%>"><%=key%></a></li>
	<% } %>
	<span>&hellip;</span>
	<li><a href="/niikp/outgoingMailList?pageNumber=1">Вернуться в начало</a></li>
	<li><a href="#" class="next">></a></li>
	</ul>
	</div>
	
	<table border="1">	
	<tr>
	<th>Дата регистрации
	<form method="post" action="/niikp/search?search=searchOutgoingMail" class="form-inline my-2 my-lg-0">
	<input class="form-control mr-sm-2" style="width: 100px; height: 40px;" type="search" name="searchRegDate" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>

	</th>
	<th>№ Ф-ВА-01/
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchMailNum" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>Адресат
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchDestination" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>ФИО кому
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchForWhom" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>	
	</th>
	
	<th>Дата отпр. письма
	<input class="form-control mr-sm-2" style="width: 100px; height: 40px;" type="search" name="searchSendDate" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>Тема письма
	<input class="form-control mr-sm-2" style="width: 100px; height: 40px;" type="search" name="searchMailTheme" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>Исполнитель письма
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchExecutor" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>Исполнитель реальный
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchRealExecutor" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>

	<th>Номер вх. письма
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchIncomingMailNum" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
			
	<th>Кому расписано
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchToWhomItIsPainted" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	
	<th>Ответ на вх. №
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchIncomingMailId" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
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
	ArrayList<OutgoingMail> list = (ArrayList<OutgoingMail>) request.getAttribute("outgoingMailList");
	HashMap<Integer, ArrayList<Work>> resolutionMap = (HashMap<Integer, ArrayList<Work>>) request.getAttribute("resolutionListForOutgoingMail");
	
	for (int i = 0; i < list.size(); i++) {				
		out.println("<tr>");
		
		//out.println("<td>" + list.get(i).getIdMail() + "</td>");

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


		if (list.get(i).getFilePathAndName() != null && !list.get(i).getFilePathAndName().contains("null") && list.get(i).getFilePathAndName().length() > 28) {
out.println("<td><a href=\"/niikp/DownloadServlet?" + list.get(i).getFilePathAndName().substring(28) + "\">" + list.get(i).getFilePathAndName().substring(44) + "</a></td>"); //was 28
} else if (list.get(i).getFilePathAndName() != null && !list.get(i).getFilePathAndName().contains("null")) {
	out.println("<td>" + list.get(i).getFilePathAndName() + "</td>");
} else {
	out.println("<td>-</td>");
}








//out.println("<a href=\"" + list.get(i).getFilePathAndName() + "\"><td>" + "Документ" + "</td></a>");
//Продумать путь до файла, чтобы не было мусорки, и задать в web.xml

out.println("<td width=\"50\"><a href=\"/niikp/outgoingMail/" + list.get(i).getIdMail() + 
	"?action=update\"><button type=\"submit\"><img src=\"resources/images/edit.png\" weight=20 height=20></button></a>");

out.println("<a href=\"/niikp/outgoingMail/" + list.get(i).getIdMail() + 
	"?action=delete\"><button type=\"submit\"><img src=\"resources/images/delete.jpg\" weight=20 height=20></button></a></td>");

//out.println("<td><a href=\"/niikp/incomingMail?id=" + list.get(i).getIdMail() + 
//	"&type=outgoingMail\"><button type=\"submit\">Добавить входящее письмо</button></a></td>");

out.println("</tr>");

/*out.println("<tr>");
out.println("<td colspan=\"10\">");
out.println("<div class=\"demo\">");			
out.println("<input class=\"hide\" id=\"hd-" + i + "\" type=\"checkbox\">");
out.println("<label for=\"hd-" + i + "\">Резолюция</label>");
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

out.println("</tr>");*/




}
out.println("</table>");
%>

</body>
</html>