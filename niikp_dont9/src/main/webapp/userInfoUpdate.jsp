<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Подключение js и css только таким образом! -->
<!-- Если добавить новый css js, то сначала сделать рефреш проекта,иначе не будет видеть файл! -->
<script type="text/javascript">
  <%@include file="/resources/js/login.js"%>
</script>

<style>
<%@include file="/resources/css/login.css"%>
<%@include file="/resources/css/index.css"%>
</style>


<!-- Подключение bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<head>
    <title>Update</title>
</head>
<body>
<section>
    <jsp:useBean id="userProfileUpdate" scope="request" type="UserProfile.UserProfile"/>
    <form method="post" action="userProfile?action=submit">
        			<input type="text" name="name" value="${userProfileUpdate.name}" placeholder="Имя" />
					<input type="text" name="secondName" value="${userProfileUpdate.secondName}" placeholder="Фамилия" />
					<input type="date" name="birthday" value="${userProfileUpdate.birthday}" placeholder="Дата Рождения" />
					<input type="text" name="email" value="${userProfileUpdate.email}" placeholder="email" />
					<input type="password" name="password" value="${userProfileUpdate.password}" placeholder="Пароль" />
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>