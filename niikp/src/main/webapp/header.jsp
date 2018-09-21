<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">

<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	width: 220px;
	height: 150px;
	align: right;
	text-align: center;
	font-family: arial;
	background-color: gainsboro;
}


button {
	border: none;
	outline: 0;
	display: inline-block;
	padding: 8px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 14px;
}

a {
	text-decoration: none;
	font-size: 16px;
	color: black;
}

h1 {
	font-size: 20px;
	color: black;
}

button:hover, a:hover {
	opacity: 0.7;
}
</style>

<header>
	<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

	<% 	if ((request.getSession().getAttribute("userSignIn") == null)) { %>		

	
	<div class="login-page">
		<div class="form">
			<form method="post" action="/niikp/signIn" class="login-form">
				<input type="text" name="email" placeholder="Введите email"/>
				<input type="password" name="password" placeholder="Пароль"/>
				<button type="submit" formaction="/niikp/signIn">Войти</button>
				<p class="message">Не зарегестрированы? <a href="/niikp/registration">Создать аккаунт</a></p>
			</form>
		</div>
	</div>

	<% } else  { %>
	
	
	
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="Work.*"%>
	<%
	UserProfile userSignIn = (UserProfile) request.getSession().getAttribute("userSignIn");
            //System.out.println("id = " + userSignIn.getUserId() + " email: " + userSignIn.getEmail());
            ArrayList<Work> workListToUser = (ArrayList<Work>) request.getSession().getAttribute("workListToUser");
			//System.out.println(workListToUser.size());
			%>

			<c:set var="id" value="${userSignIn.getUserId()}"/>    
			<div class="card" align="right">
				<a href="/niikp/users/${id}"  style="color:black">
					<h1><%= userSignIn.getName() %> <%= userSignIn.getSecondName() %> </h1>
				</a>
				<a href="/niikp/logOut"  style="color:black">
					<p><button>Выйти</button></p>
				</a>
				<!-- <a href="/niikp/workList?parameter=toMe">
					<p class="title">Невыполненных дел: <%= workListToUser.size() %></p>
				</a> -->
			</div>
			
			










			
			<% } %>
			
		</header>

		</html>