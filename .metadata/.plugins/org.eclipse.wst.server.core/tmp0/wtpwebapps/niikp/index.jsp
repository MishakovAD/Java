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
<!-- ГИСТОГРАММА  -->
<script src="https://www.google.com/jsapi"></script>
  <script>
   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(drawChart);
   function drawChart() {
    var data = google.visualization.arrayToDataTable([
     ['Число', 'Выполнено', 'Не выполнено'],
     ['1860', 1.3, 70],
     ['1885', 2000, 3120],
     ['1901', 12170, 9920]
    ]);
    var options = {
     title: 'График выполнения работы',
     hAxis: {title: 'Число'},
     vAxis: {title: 'Стадия'}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('oil'));
    chart.draw(data, options);
   }
  </script>
<!-- <div id="oil" style="width: 500px; height: 400px;"></div> -->
<!-- КОНЕЦ ГИСТОГРАММЫ  -->



<div class="container-fluid">
  <div class="row">
    <div class="col-9">
      1
    </div>
    <div class="col-3">
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
<!-- Подключение bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

</html>