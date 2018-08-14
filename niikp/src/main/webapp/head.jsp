<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<!--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<!-- Подключение bootstrap -->
<script type="text/javascript">
  <%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
	<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<head>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  	<a class="navbar-brand" href="#">НИИ-КП</a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar1" aria-controls="navbar1" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>

  	<div class="collapse navbar-collapse" id="navbar1">
    	<ul class="navbar-nav mr-auto">
      		<li class="nav-item active">
        		<a class="nav-link" href="/niikp/">Главная страница <span class="sr-only">(current)</span></a>
      		</li>
      		<li class="nav-item">
		    	<a class="nav-link" href="/niikp/users">Пользователи</a>
		    </li>
		    <li class="nav-item">
		    	<a class="nav-link" href="/niikp/workList">Дела/Поручения</a>
		    </li>
		    <li class="nav-item">
		    	<a class="nav-link" href="/niikp/incomingMailList">Входящая корреспонденция</a>
		    </li>
    	</ul>
    	<!-- <form class="form-inline my-2 my-lg-0">
      		<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      		<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
    	</form> -->
  	</div>
</nav>
</head>

</html>