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
				<nav class="four">
					<ul class="topmenu">
						<li><a href="#">Дела/Поручения<i class="fa fa-angle-down"></i></a>
							<ul class="submenu">
								<li><a href="/niikp/workList?parameter=toMe">Поручено мне</a></li>
								<li><a href="/niikp/workList?parameter=fromMe">Поручено мной</a></li>
							</ul>
						</li>
					</ul>
				</nav>
				<nav class="four">
					<ul class="topmenu">
						<li><a href="#">Корреспонденция<i class="fa fa-angle-down"></i></a>
							<ul class="submenu">
								<li><a href="/niikp/incomingMailList?pageNumber=1">Входящая корреспонденция</a></li>
								<!-- <li><a href="/niikp">Исходящая корреспонденция</a></li>
									<li><a href="/niikp">Внутренняя корреспонденция</a></li>  -->
								</ul>
							</li>
						</ul>
					</nav>
				</ul>
<form method="post" action="/niikp/search" class="form-inline my-2 my-lg-0">
<input class="form-control mr-sm-2" type="search" name="searchAll" placeholder="Search" aria-label="Search">
<button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
</form>
</div>
</nav>
</head>

<style>
body {margin:0;}
h2 {
	font-family: monospace;
	color: #606060;
}
ul {
	list-style: none; 
	margin: 0;
	padding: 0;
}
.topmenu > li {
	display: inline-block;
	margin-right: 20px;
	position: relative;
}
.topmenu > li:last-child {
	margin-right: 0;
}
a {
	display: block;
	padding: 10px 15px;
	text-decoration: none;
	outline: none;
	transition: .5s linear;
}
.fa {
	font-family: "FontAwesome";
	color: inherit;
	padding-left: 10px;
}
.submenu {
	position: absolute;
	top: 100%;
	left: 0;
	width: 100%;
	z-index: 10;
	-webkit-transition: 0.5s ease-in-out;
	-moz-transition: 0.5s ease-in-out;
	-o-transition: 0.5s ease-in-out;
	transition: 0.5s ease-in-out;
}
.four ul {
	background: #343a40!important;
}
.four .submenu {
	-webkit-transform: scaleY(0);
	-ms-transform: scaleY(0);
	transform: scaleY(0);
	-webkit-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	transform-origin: 0 0;
	background: #FFAB51;
}
.four ul li:hover .submenu {
	-webkit-transform: scaleY(1);
	-ms-transform: scaleY(1);
	transform: scaleY(1);
}
.four ul a {
	color: rgba(255,255,255,.5);
}
.submenu li a {
	border-bottom: 1px solid rgba(255,255,255,.5);
	color: white;
}
</style>



</html>