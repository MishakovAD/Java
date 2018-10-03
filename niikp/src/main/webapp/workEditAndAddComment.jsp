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

<%@ include file = "head.jsp" %>

<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<meta charset="UTF-8">
	<title>Дела/Поручения</title>
</head>
<body>
	

	
	
	<form method="post" action="workDone?action=comment" enctype="multipart/form-data">
		
		<% Work work = (Work) request.getAttribute("workForComment"); %>
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<h3>Комментарий:
						<textarea rows="6" cols="35" name="comment"><%=work.getComment() %></textarea>
						<!-- <input type="text" name="assignment" value="" placeholder="Поручение" /> -->
					</h3>
				</div>
				<div class="col-4">
					<h3>Поручение:
						<textarea rows="6" cols="35" name="assignment"><%=work.getAssignment() %></textarea>
						<!-- <input type="text" name="assignment" value="<%=work.getAssignment() %>" placeholder="Поручение" /> -->
					</h3>
					<br><br><br>

					<button type="submit">Save</button>
				</div>
				<div class="col-4">
					<h3>Источник поручения: 
						<input type="text" name="assigmentSource" value="<%=work.getAssigmentSourse() %>" placeholder="Источник поручения" />	
					</h3>
				</div>
			</div>
			
			
			
		</form>
	</body>
	</html>