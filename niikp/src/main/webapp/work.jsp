<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- Подключение bootstrap -->
<script type="text/javascript">
	<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<head>
	<meta charset="UTF-8">
	<title>Дела/Поручения</title>
	 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>
        var n = 2;
        function add_input(){
            var inputs = $('.inputs input[type="text"]');
            $('.inputs').append('<input id="id-'+n+'" list="names" name="user'+n+'"  style="height: 25px; width: 200px;" /><br><br>');
            n++
        }
        </script>
</head>
<body>
	

	
	
	<form method="post" action="workAdd?action=submit" enctype="multipart/form-data">
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<form id="Form">
        <input type="button" onclick="add_input()" value="Добавить исполнителя" />
        <br><br>
        	<label> Исполнитель: </label>
            <div class="inputs">
                <input id="id-1" name="user1" list="names" style="height: 25px; width: 200px;" />
                <br><br>
            </div>
					<%@ page import="java.util.HashMap" %>
					<%@ page import="java.util.Map" %> 
					<%@ page import="Users.*" %>
					<%@ page import="UserProfile.*" %>
					
					
					<!--  <h3>Исполнители: </h3>
					<select size="15" multiple name="user" style="width: 269px;">
						<%@ page import="java.util.ArrayList"%>
						<%
						/*HashMap<Integer, UserProfile> list1 = (HashMap<Integer, UserProfile>) request.getAttribute("usersList");
						
						for (Map.Entry entry : list1.entrySet()) {
						UserProfile user = (UserProfile) entry.getValue(); 
						out.println("<option>");
							out.println(user.getName() + " " + user.getSecondName()); 
						out.println("</option>");	
					} */ %>
				</select></p> -->
			</div>
			<div class="col-4">
				
				
				<select name="userGroup" style="width: 269px; height: 35px;">
					<%@ page import="java.util.ArrayList"%>
					<% 
					ArrayList<String> typeMail = (ArrayList<String>) request.getAttribute("groupList");
						for (String values : typeMail) { %>
						<option value="<%= values %>"><%= values %></option>
						<% } %>
					</select></p> 
					<p><input type="checkbox" name="isGroup" value="true" unchecked>Выбрать группу<Br></p> <!-- либо null либо true -->
					
					<!-- <input type="text" name="template" value="" list="templates" placeholder="Шаблон" />
						<br><br><br> -->
						<h3>Дата начала:
							<input type="date" name="startDate" value="" placeholder="Дата начала" />
						</h3>
						<br><br><br>
						<h3>Срок исполнения:
							<input type="date" name="endDate" value="" placeholder="Срок исполнения" />
						</h3>
						<br><br><br>
						<h3>Поручение:
							<input type="text" name="assignment" value="" placeholder="Поручение" />
						</h3>
						<br><br><br>
						<input name="file" type="file"><br>
						<br><br><br>
						<button type="submit">Save</button>
						
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
				<div class="col-4">
					<h3>Ответсвенный:
						<input type="text" name="observer" value="" list="names" placeholder="Ответственный" />	
					</h3>
					<br><br><br>
				</div>
			</div>
			
			
			
		</form>
	</body>
	</html>