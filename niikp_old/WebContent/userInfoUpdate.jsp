<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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