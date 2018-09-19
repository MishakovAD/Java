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
<p><h2>Внутренняя корреспонденция</h2>

<a href="/niikp/internalMail">Добавить Внутреннюю корреспонденцию</button></a>
<a href="/niikp/downloadInternalMailExcel">Скачать Excel файл</a> </p>

<form method="get" action="/niikp/internalMailList?pageNumber=num">
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
	<li><a href="/niikp/internalMailList?pageNumber=<%=key%>"><%=key%></a></li>
	<% } %>
	<span>&hellip;</span>
	<li><a href="/niikp/internalMailList?pageNumber=1">Вернуться в начало</a></li>
	<li><a href="#" class="next">></a></li>
	</ul>
	</div>

	<table border="1">	
	<tr>
	<th>Номер документa</th>
	<th>Дата регистрации
	<form method="post" action="/niikp/search?search=searchInternalMail" class="form-inline my-2 my-lg-0">
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchRegDate" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>

	</th>
	<th>Тип документa</th>
	
	<th>№ НПК-1/</th>
	<th>Адресат (Кому)</th>
	<th>Дополнительный адресат</th>
	
	<th>Тема документа
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchDocTheme" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	<th>Исполнитель</th>
	<th>Примечание
	<input class="form-control mr-sm-2" style="width: 150px; height: 40px;" type="search" name="searchNote" placeholder="Search" aria-label="Search">
	<button hidden class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
	</th>
	</form>
	</tr>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.HashMap"%>
	<%@ page import="InternalMail.*"%>
	<%@ page import="Work.*"%>
	<%@ page import="UserProfile.*"%>
	<%
	ArrayList<InternalMail> list = (ArrayList<InternalMail>) request.getAttribute("internalMailList");
	
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