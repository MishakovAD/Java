<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<!-- Подключение js и css только таким образом! -->
<script type="text/javascript">
    <%@include file="/resources/js/login.js"%>
</script>

<style>
    <%@include file="/resources/css/login.css"%>
</style>
<head>
<title>Update</title>
</head>
<body>

<div class="login-page">
<div class="form">
		<form method="post" action="registration?action=submit" class="login-form">
					<input type="text" name="name" value="${userProfile.name}" placeholder="Имя" />
					<input type="text" name="secondName" value="${userProfile.secondName}" placeholder="Фамилия" />
					<input type="date" name="birthday" value="${userProfile.birthday}" placeholder="Дата Рождения" />
					<input type="text" name="email" value="${userProfile.email}" placeholder="email" />
					<input type="password" name="password" value="${userProfile.password}" placeholder="Пароль" />
			<button type="submit">Регистрация</button>
		</form>
	</div>
</div>
</body>
</html>