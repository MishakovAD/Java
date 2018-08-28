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
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<script>
		var n = 2;
		function add_input(){
			var inputs = $('.inputs input[type="text"]');
			$('.inputs').append('<input id="id-'+n+'" list="names" name="toWhomItIsPainted'+n+'"  style="height: 25px; width: 200px;" /><br><br>');
			n++
		}
	</script>
	<meta charset="UTF-8">
	<title>Исходящая корреспонденция</title>
</head>
<body>
	<form method="post" action="outgoingMail?action=submit" enctype="multipart/form-data">
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<p>№ Ф-ВА-01/: </p>
					<input type="text" name="mailNum" style="width: 250px; height: 40px;" value="" placeholder="Номер письма" />
					<br><br><br>
					<p>Адресат: </p> 
					<input type="text" name="destination" style="width: 250px; height: 40px;" value="" list="destinationList" placeholder="Адресат" />    
					<br><br><br>
					<p>Кому: </p> 
					<input type="text" name="forWhom" style="width: 250px; height: 40px;" value="" list="names" placeholder="Адресат" />    
					<br><br><br>
					<p>Дата отправления письма: </p>
					<input type="date" required class="form-control" style="width: 250px;" name="sendDate" value="" placeholder="Дата отправления письма" />
					<br><br><br>
				</div>
						
				<div class="col-4">
					<p>Тема письма: </p>
					<input type="text" name="mailTheme" style="width: 250px; height: 40px;" value="" placeholder="Тема письма" />
					<br><br><br>
					<p>Исполнитель: </p> 
					<input type="text" name="executor" style="width: 250px; height: 40px;" value="" list="names" placeholder="Адресат" />    
					<br><br><br>
					<p>Реальный исполнитель: </p> 
					<input type="text" name="realExecutor" style="width: 250px; height: 40px;" value="" list="names" placeholder="Адресат" />    
					<br><br><br>
					<p>№ входящего: </p>
					<input type="text" name="incomingMailNum" style="width: 250px; height: 40px;" value="" placeholder="Номер письма" />
					<br><br><br>
					<form id="Form">
						<input type="button" onclick="add_input()" value="Добавить #Кому расписано#" />
						<br><br>
						<label>Кому расписано: </label>
						<div class="inputs">
							<input id="id-1" name="toWhomItIsPainted1" list="names" style="height: 25px; width: 200px;" />
							<br><br>
						</div> 
					</form>
				</div>
						
				<div class="col-4">
					<p>Примечание: </p>
					<input type="text" name="note" style="width: 250px; height: 40px;" value="" placeholder="Номер письма" />
					<br><br><br>
					<p>Примечание рассылки: </p>
					<input type="text" name="mailingNote" style="width: 250px; height: 40px;" value="" placeholder="Номер письма" />
					<br><br><br>
					<p>Письмо: </p>
					<input name="file" type="file"><br><br>
					<p><input type="checkbox" name="onControl" value="true" unchecked>На контроле?<Br></p>							
					<button type="submit">Добавить</button>
				</div>
						<datalist id="destinationList">
								<%@ page import="IncomingMail.*"%>
								<%@ page import="java.util.ArrayList"%>
								<% 
								ArrayList<String> destinationList = (ArrayList<String>) request.getAttribute("destinationMailList");
									for (String values : destinationList) { 
									out.println("<option>");
										out.println(values); 
									out.println("</option>");   
								} %>
						</datalist> 
						
						<datalist id="names">
							<%@ page import="java.util.HashMap" %>
							<%@ page import="java.util.Map" %> 
							<%@ page import="Users.*" %>
							<%@ page import="UserProfile.*" %>
							
							<%
							HashMap<Integer, UserProfile> list = (HashMap<Integer, UserProfile>) request.getAttribute("usersList");
							
							for (Map.Entry entry : list.entrySet()) {
							UserProfile user = (UserProfile) entry.getValue(); 
							out.println("<option>");
								out.println(user.getName() + " " + user.getSecondName()); 
							out.println("</option>");	
						} %>
						</datalist> 
							
					</div>
				</div>
			</form>
		</body>
		</html>