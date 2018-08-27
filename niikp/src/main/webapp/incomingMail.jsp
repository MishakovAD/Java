<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<!-- Подключение bootstrap -->
<script type="text/javascript">
	<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "head.jsp" %>
<head>
	<meta charset="UTF-8">
	<title>Входящая корреспонденция</title>
</head>
<body>
	<form method="post" action="incomingMail?action=submit" enctype="multipart/form-data">
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<p>Тип письма: </> </p>
					<select name="typeMail">
						<%@ page import="IncomingMail.*"%>
						<%@ page import="java.util.ArrayList"%>
						<% 
						ArrayList<String> typeMail = (ArrayList<String>) request.getAttribute("typeMailList");
							for (String values : typeMail) { %>
							<option value="<%= values %>"><%= values %></option>
							<% } %>
						</select> 
						<br><br><br>
						<p>Отправитель: </p> 
						<input type="text" name="sender" style="width: 250px; height: 40px;" value="" list="senderList" placeholder="Отправитель" />    
						<br><br><br>
						<p>Дата отправления письма: </p>
						<input type="date" required class="form-control" style="width: 250px;" name="sendDate" value="" placeholder="Дата отправления письма" />
						<br><br><br>
						</div>
						
						<div class="col-4">
						<p>Номер письма: </p>
						<input type="text" name="mailNum" style="width: 250px; height: 40px;" value="" placeholder="Номер письма" />
						<br><br><br>
						<p>Тема письма: </p>
						<input type="text" name="mailTheme" style="width: 250px; height: 40px;" value="" placeholder="Тема письма" />
						<br><br><br>
						<p>Дата, присваевыемая при первичной рег. документа: </p>
						<input type="date" name="secondFloorDate" class="form-control" style="width: 250px;" value="" placeholder="Дата, присваевыемая при первичной рег. документа" />
						<p>Номер письма, присваевыемый при первичной рег. документа: </p>
						<input type="text" name="secondFloorNum" style="width: 250px; height: 40px;" value="" placeholder="Номер письма, присваевыемый при первичной рег. документа" />
						<br><br><br>
						</div>
						
						<div class="col-4">
						<p>Письмо: </p>
							<input name="file" type="file"><br><br>
							<p><input type="checkbox" name="onControl" value="true" unchecked>На контроле?<Br></p>							
							<button type="submit">Добавить</button>
						</div>
						<datalist id="senderList">
								<%@ page import="IncomingMail.*"%>
								<%@ page import="java.util.ArrayList"%>
								<% 
								ArrayList<String> senderList = (ArrayList<String>) request.getAttribute("senderMailList");
									for (String values : senderList) { 
									out.println("<option>");
										out.println(values); 
									out.println("</option>");   
								} %>
							</datalist>
					</div>
				</div>
			</form>
		</body>
		</html>