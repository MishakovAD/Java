<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Входящая корреспонденция</title>
</head>
<body>
		<form method="post" action="incomingMail?action=submit" enctype="multipart/form-data">
		<p>Тип письма: </>
		<select name="typeMail">
    		<option value="Письмо1">Письмо</option>
    		<option value="Письмо2">Письмо</option>
    		<option value="Письмо3">Письмо</option>
    		<option value="Письмо4">Письмо</option>
   		</select></p> 
   		<br><br><br>
   		<p>Отправитель: </>
		<input type="text" name="sender" value="" list="senderList" placeholder="Отправитель" />	
		<br><br><br>
		<p>Дата отправления письма: </>
		<input type="date" required name="sendDate" value="" placeholder="Дата отправления письма" />
		<br><br><br>
		<p>Номер письма: </>
		<input type="text" name="mailNum" value="" placeholder="Номер письма" />
		<br><br><br>
		<p>Тема письма: </>
		<input type="text" name="mailTheme" value="" placeholder="Тема письма" />
		<br><br><br>
		<p>Дата, присваевыемая при первичной рег. документа: </>
		<input type="date" required name="secondFloorDate" value="" placeholder="Дата, присваевыемая при первичной рег. документа" />
		<p>Номер письма, присваевыемый при первичной рег. документа: </>
		<input type="text" required name="secondFloorNum" value="" placeholder="Номер письма, присваевыемый при первичной рег. документа" />
		<br><br><br>
		<p>Резолюция: </>
		<input type="text" name="resolution" value="" placeholder="Резолюция" /> <!-- Добавить отдельную таблицу -->
		<br><br><br>
		<p>Письмо: </>
		<input name="file" type="file"><br>
		<br><br><br>
       	<button type="submit">Save</button>
	</form>
</body>
</html>