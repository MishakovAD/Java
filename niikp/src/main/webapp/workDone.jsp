<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Отчет</title>

<!-- <style type="text/css">
   .report {
    width: 308px; /* Ширина поля с учетом padding */
    height: 200px; /* Высота */
    /*background: #dad7c5 url(images/input.png) no-repeat; /* Фон */
    padding: 0 10px; /* Поля */
    /*border: none; /* Убираем рамку */
    font-size: 1em; /* Размер текста */
    line-height: 0px; /* Выравниваем по  центру в IE */
   }
</style> -->

</head>
<body>
	<form method="post" action="workDone?action=submit" enctype="multipart/form-data">
		<p>Отчет: </>
			<textarea name="report" cols="40" rows="3"></textarea></p>
			<p>Файл: </>
				<input name="file" type="file"><br>
				<br><br><br>
				<button type="submit">Save</button>
			</form>
		</body>
		</html>