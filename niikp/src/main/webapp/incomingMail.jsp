<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Входящая корреспонденция</title>
</head>
<body>
	
	<%@ page import="IncomingMail.*" %>
	<%
		//Эти все выражения для случая, когда мы возвращаем объект. Если объекта нет, то возвращается нулл и вылетает ошибка
		//Нужно продумать вместе ифами, чтобы при обычном добавлении- одно, а при редактирование елсе.
		IncomingMail incomingMail = (IncomingMail) request.getAttribute("incomingMail");
		System.out.println(incomingMail.getRegDate());
	%>

	<c:set var="regDate" value="${incMail.getRegDate()}"/>
	<form method="post" action="incomingMail?action=submit" enctype="multipart/form-data">
		<input type="date" name="regDate" value="<%= incomingMail.getRegDate() %>" placeholder="Дата регистрации" />
		<br><br><br>
		<input type="number" name="regNum" value="<%= incomingMail.getIdMail() %>" placeholder="Порядковый номер" /> <!-- Скрыть, будет обычный айди записи -->
		<br><br><br>
		<p><select name="typeMail">
    		<option value="Письмо1">Письмо</option>
    		<option value="Письмо2">Письмо</option>
    		<option value="Письмо3">Письмо</option>
    		<option value="Письмо4">Письмо</option>
   		</select></p> 
   		<br><br><br>
		<input type="text" name="sender" value="" list="senderList" placeholder="Отправитель" />	
		<br><br><br>
		<input type="date" name="sendDate" value="" placeholder="Дата отправления письма" />
		<br><br><br>
		<input type="text" name="mailNum" value="" placeholder="Номер письма" />
		<br><br><br>
		<input type="text" name="mailTheme" value="" placeholder="Тема письма" />
		<br><br><br>
		<input type="date" name="secondFloorDate" value="" placeholder="Дата и номер письма, присваевыемые при первичной рег. документа" />
		<br><br><br>
		<input type="text" name="resolution" value="" placeholder="Резолюция" /> <!-- Добавить отдельную таблицу -->
		<br><br><br>
		<input name="file" type="file"><br>
		<br><br><br>
       	<button type="submit">Save</button>
	</form>
</body>
</html>