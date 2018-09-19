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
	<title>Внутренняя корреспонденция</title>
</head>
<body>
	<form method="post" action="internalMail?action=submit">
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<p>Тип документа: </p> 
					<input type="text" name="docType" style="width: 250px; height: 40px;" value="" list="docTypeList" placeholder="Тип документа" />    
					<br><br><br>
					<p>№ НПК-1/: </p> 
					<input type="text" name="numNPK" style="width: 250px; height: 40px;" value="" placeholder="№ НПК-1/" />    
					<br><br><br>
					<p>Адресат (Кому): </p> 
					<input type="text" name="destination" style="width: 250px; height: 40px;" value="" list="names" placeholder="Адресат" />    
					<br><br><br>
					<p>Дополнительный адресат: </p> 
					<input type="text" name="additionalDestination" style="width: 250px; height: 40px;" value="" list="names" placeholder="Доп. адресат" />    
					<br><br><br>
				</div>
						
				<div class="col-4">
					<p>Тема документа: </p>
					<input type="text" name="docTheme" style="width: 250px; height: 40px;" value="" placeholder="Тема документа" />
					<br><br><br>
					<p>Исполнитель: </p> 
					<input type="text" name="executor" style="width: 250px; height: 40px;" value="" list="names" placeholder="Исполнитель" />    
					<br><br><br>
					<p>Примечание: </p>
					<input type="text" name="note" style="width: 250px; height: 40px;" value="" placeholder="Примечание" />
					<br><br><br>
					<button type="submit">Добавить</button>
				</div>
						
				<div class="col-4">

				</div>
						<datalist id="docTypeList">
								<%@ page import="IncomingMail.*"%>
								<%@ page import="java.util.ArrayList"%>
								<% 
								ArrayList<String> docTypeList = (ArrayList<String>) request.getAttribute("docTypeList");
									for (String values : docTypeList) { 
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