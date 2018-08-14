<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">

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
            ArrayList<Work> workListToUser = (ArrayList<Work>) request.getAttribute("workListToUser");
			System.out.println(workListToUser.size());
            %>

          	<c:set var="id" value="${userSignIn.getUserId()}"/>    
          	<c:set var="email" value="${userSignIn.getEmail()}"/>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
            <a href="/niikp/users/${id}"  style="color:black">
            	<p align="right">Привет, <%= userSignIn.getName() %></p>
            </a>            
            <a href="/niikp/logOut"  style="color:black">
            <p align="right">Выйти</p>
            </a>
          <a href="/niikp/workList">
          	<p align="right">Невыполненных дел: <%= workListToUser.size() %></p>
          </a>
          <br>
           
          <li class="btn btn-link btn-lg" style="color:black">         
           </li>

	
	<% } %>

		
		
</header>

</html>