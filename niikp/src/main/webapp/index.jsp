<%@page import="UserProfile.UserProfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<!--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->



<!-- Подключение js и css только таким образом! -->
<!-- Если добавить новый css js, то сначала сделать рефреш проекта,иначе не будет видеть файл! -->
<script type="text/javascript">
  <%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
	<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<style>
<script type="text/javascript">
  <%@include file="/resources/js/login.js"%>
</script>
	<%@include file="/resources/css/login.css"%>
	<%@include file="/resources/css/index.css"%>
</style>

<%@ include file = "head.jsp" %> <!-- Шапка страницы -->




<body>

<style>
.five {
  background: -webkit-linear-gradient(right, #76b852);
  padding: 50px 20px 50px 170px;
  text-align: center;
} 
.five h1 {
  font-family: 'Open Sans', sans-serif;
  font-weight: 400;
  position: relative;
  color: #587493;
  font-size: 2.5em;
  font-weight: normal;
  display: inline-block;
  margin: 0;
  line-height: 1;
  padding: 8px 20px 8px 2px;
  border-top: 4px solid;
}
.five h1:before {
  content: "НИИ";
  position: absolute;
  top: -10px;
  left: -160px;
  font-size: 1.5em;
  transform: rotate(-25deg);
  font-family: 'Marck Script', cursive;
}
.five h1:after {
  content: ""; 
  position: absolute;
  width: 120%;
  height: 4px;
  right: 0;
  bottom: -4px;
  background: #587493;
}
@media (max-width: 580px) {
  .five {padding-left: 130px;}
  .five h1 {font-size: 2em;}
  .five h1:before {left: -130px;}
}
@media (max-width: 480px) {
  .five {padding-left: 100px;}
  .five h1 {
    font-size: 1.5em;
    padding-right: 10px;
}
  .five h1:before {left: -100px;}
}
@media (max-width: 380px) {
  .five {padding-left: 90px;}
  .five h1 {font-size: 1.3em;}
  .five h1:before {left: -88px;}
}
</style>

<div class="container-fluid">
  <div class="row">
    <div class="col-9">
      <div class="five"><h1>Космического приборостроения</h1></div>
    </div>
    <div class="col-3" align="right" style="padding-right: 0px;">
      <%@ include file = "header.jsp" %>
    </div>
  </div>



  <section id="info--1"></section>
  <section id="info--2"></section>
  <section id="featured-on"></section>
  <section id="feature-rundown"></section>
  <section id="pricing"></section>
  <section id="footer"></section>

</body>

</html>