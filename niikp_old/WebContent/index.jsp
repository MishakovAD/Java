<%@page import="UserProfile.UserProfile"%>
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

</head>

<header>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<section id="head">
<% 	if ((request.getSession().getAttribute("userSignIn") == null)) { %>		

    <div class="row" style="background-color:#76b852;box-shadow: 0 3px 20px #d4aeae;">
      <div class="col-md-4"></div>
      <div class="col-md-4 col-xs-4 col-lg-4 col-sm-4"  style="font-size:72px;box-shadow: -13px 3px black;text-shadow: 6px 0px black;">
        <a href="http://127.0.0.1:8000" style="color: #5d4747;"> Название
        </a>
      </div>
      <div class="col-md-4"> 
        <div class="row" >
          <div class="col-md-2"></div>
          <div class="col-md-2"></div>
          <div class="col-md-8"style="text-align:end;">
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
          </div>

        </div>
      </div>
    </div>
	<% } else  { %>
	
	<div class="row" style="background-color:#76b852;box-shadow: 0 3px 20px #d4aeae;">
    <div class="col-md-4"></div>
    <div class="col-md-4 col-xs-4 col-lg-4 col-sm-4"  style="font-size:72px;box-shadow: -13px 3px black;text-shadow: 6px 0px black;">
      <a href="http://127.0.0.1:8000" style="color: #5d4747;"> Название
      </a>
    </div>
    <div class="col-md-4"> 
      <div class="row" >
        <div class="col-md-2"></div>
        <div class="col-md-2"></div>
        <div class="col-md-8"style="text-align:end;">
            
            <%
            UserProfile userSignIn = (UserProfile) request.getSession().getAttribute("userSignIn");
            System.out.println("id = " + userSignIn.getUserId() + " email: " + userSignIn.getEmail());
            %>

          	<c:set var="id" value="${userSignIn.getUserId()}"/>    
          	<c:set var="email" value="${userSignIn.getEmail()}"/>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
            <a href="/niikp/users/${id}"  style="color:black">Привет, ${email}</a>            
          </li>
          <li class="btn btn-link btn-lg">
            <a href="/niikp/logOut"  style="color:black">Выйти</a>
          </li>
          <li class="btn btn-link btn-lg" style="color:black">         
           </li>
         </div>

       </div>
     </div>
   </div>
	
	<% } %>

		
		

  </section>

</header>

<body>



  <section id="info--1">
    <div class="container">
      <div class="row" >
        <div class="col-md-8">
          <h1 class="text-white">Coding on steroids</h1>
          <p class="lead text-white-70">Stop hiring engineers to write your code. 
          Just use the 1kb script that magically solves all your problems and algorithms.</p>
          <br>
          <a class="btn btn-primary" href="#" role="button">Try it yesterday</a>
        </div>
        <div class="col-md-4"></div>
      </div>
    </div>
  </section>
  <section id="info--2"></section>
  <section id="info--3"></section>
  <section id="featured-on"></section>
  <section id="feature-rundown"></section>
  <section id="pricing"></section>
  <section id="footer"></section>

</body>
</html>