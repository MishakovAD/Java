<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Дела/Поручения</title>

<!-- Подключение bootstrap -->
<script type="text/javascript">
<%@include file="/resources/bootstrap/js/bootstrap.min.js"%>  
</script>
<style>
<%@include file="/resources/bootstrap/css/bootstrap.min.css"%> 
</style>

<%@ include file = "head.jsp" %>

<body>
<h1>Дела/Поручения</h1>
<table border="1">

<tr>
<th>№</th>
<th>Источник поручения</th>
<th>Наименование</th>
<th>Ответсвенный исполнитель</th>
<th>Доп. исполнители</th>
<th>Поручение выдано</th>			
<th>Кем выдано</th>			
<th>Выполнить до</th>
<th>Номер письма</th>
<th>Документ</th>
<th>Статус выполнения</th>
<th>Отчетность</th>
<th>Отчетный документ</th>
<th>+</th>
<th>-</th>
</tr>


<%@ page import="java.util.ArrayList"%>
<%@ page import="Work.*"%>
<%@ page import="UserProfile.*"%>
<%@ page import="DAO.GetterDB"%>
<%
ArrayList<Work> list = (ArrayList<Work>) request.getAttribute("workList");
UserProfile userToWork = (UserProfile) request.getSession().getAttribute("userSignIn");

out.println("Я поручил: <br>");
int counter = 1;
for (int i = 0; i < list.size(); i++) {				
	if (list.get(i).getFromUserId() == userToWork.getUserId() && (list.get(i).getIsAccept().equals("null") || list.get(i).getIsAccept().equals("refuse") || list.get(i).getIsAccept().equals("done"))) {
		
		out.println("<tr>");
		if (list.get(i).getIsAccept().equals("accept")) {
			out.println("<td style=\"color: darkcyan\">" + (i+1) + ") </td>");
		} else {
			out.println("<td style=\"color: crimson\">" + (i+1) + ") </td>");
		}
		
		
		if (list.get(i).getAssigmentSourse() != null && !list.get(i).getAssigmentSourse().isEmpty()) {
			out.println("<td>" + list.get(i).getAssigmentSourse() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}
		
		if (list.get(i).getAssignment() != null && !list.get(i).getAssignment().isEmpty()) {
			out.println("<td>" + list.get(i).getAssignment() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getObserverId() != 0) {
			UserProfile observer = GetterDB.getUserFromId(list.get(i).getObserverId());
			out.println("<td>" + observer.getName() + " " + observer.getSecondName() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getCo_executor() != null) {
			//UserProfile toUser = GetterDB.getUserFromId(list.get(i).getToUserId());
			//out.println("<td>" + toUser.getName() + " " + toUser.getSecondName() + "</td>");
			out.println("<td>" + list.get(i).getCo_executor() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getStartDate() != null) {
			out.println("<td>" + list.get(i).getStartDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getFromUserId() != 0) {
			UserProfile fromUser = GetterDB.getUserFromId(list.get(i).getFromUserId());
			out.println("<td>" + fromUser.getName() + " " + fromUser.getSecondName() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}					

		if (list.get(i).getEndDate() != null) {
			out.println("<td>" + list.get(i).getEndDate() + "</td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}


		if (!list.get(i).getMailId().contains("null")) {
			String mailName = null;
			if (list.get(i).getMailId().contains("incomingMail")) {
				mailName = list.get(i).getMailId().replaceAll("incomingMail", "VhodKorr");
			}
			out.println("<td><a href=\"/niikp/link?type=work&mailName=" + mailName + "\">" + mailName + "</a></td>");
		} else {
			out.println("<td>Не заполнено</td>");
		}

		if (list.get(i).getFilePathAndNameToWork() != null
			&& !list.get(i).getFilePathAndNameToWork().contains("null")  && list.get(i).getFilePathAndNameToWork().length()>28) {
//System.out.println("list.get(i).getFilePathAndNameToWork() = " + list.get(i).getFilePathAndNameToWork());
			out.println("<td><a href=\"/niikp/DownloadServlet?"
				+ list.get(i).getFilePathAndNameToWork().substring(28) + "\">"
				+ list.get(i).getFilePathAndNameToWork().substring(28) + "</a></td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).isComplete() == false) {
		out.println("<td>Не выполнено</td>");
	} else {
		out.println("<td>Выполнено " + list.get(i).getFinishDate() + "</td>");
	}

	if (!list.get(i).getReport().contains("null")) {
		out.println("<td>" + list.get(i).getReport() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (!list.get(i).getReportFilePathAndNameToWork().contains("null") && list.get(i).getReportFilePathAndNameToWork().length()>48) {
		out.println("<td><a href=\"/niikp/DownloadServlet?"
			+ list.get(i).getReportFilePathAndNameToWork().substring(27) + "\">"
			+ list.get(i).getReportFilePathAndNameToWork().substring(27) + "</a></td>"); //was 28
	} else {
		out.println("<td>Не заполнено</td>");
	}
	out.println("<td><a href=\"/niikp/workDone?workId=" + list.get(i).getWorkId() + "&action=accept\"><button type=\"submit\">Принять</button></a></td>");
	out.println("<td><a href=\"/niikp/workDone?workId="	+ list.get(i).getWorkId() + "&action=refuse\"><button type=\"submit\">Отказаться</button></a></td>");
	out.println("</tr>");
} else if (list.get(i).getFromUserId() == userToWork.getUserId()) {

	out.println("<tr>");
	out.println("<td>" + (i+1) + ") </td>");
	
	if (list.get(i).getAssigmentSourse() != null && !list.get(i).getAssigmentSourse().isEmpty()) {
		out.println("<td>" + list.get(i).getAssigmentSourse() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getAssignment() != null && !list.get(i).getAssignment().isEmpty()) {
		out.println("<td>" + list.get(i).getAssignment() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getObserverId() != 0) {
		UserProfile observer = GetterDB.getUserFromId(list.get(i).getObserverId());
		out.println("<td>" + observer.getName() + " " + observer.getSecondName() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getToUserId() != 0) {
		UserProfile toUser = GetterDB.getUserFromId(list.get(i).getToUserId());
		out.println("<td>" + toUser.getName() + " " + toUser.getSecondName() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getStartDate() != null) {
		out.println("<td>" + list.get(i).getStartDate() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getFromUserId() != 0) {
		UserProfile fromUser = GetterDB.getUserFromId(list.get(i).getFromUserId());
		out.println("<td>" + fromUser.getName() + " " + fromUser.getSecondName() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}					

	if (list.get(i).getEndDate() != null) {
		out.println("<td>" + list.get(i).getEndDate() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}


	if (!list.get(i).getMailId().contains("null")) {
		String mailName = null;
		if (list.get(i).getMailId().contains("incomingMail")) {
			mailName = list.get(i).getMailId().replaceAll("incomingMail", "VhodKorr");
		}
		out.println("<td><a href=\"/niikp/link?type=work&mailName=" + mailName + "\">" + mailName + "</a></td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).getFilePathAndNameToWork() != null
		&& !list.get(i).getFilePathAndNameToWork().contains("null")  && list.get(i).getFilePathAndNameToWork().length()>28) {
//System.out.println("list.get(i).getFilePathAndNameToWork() = " + list.get(i).getFilePathAndNameToWork());
		out.println("<td><a href=\"/niikp/DownloadServlet?"
			+ list.get(i).getFilePathAndNameToWork().substring(28) + "\">"
			+ list.get(i).getFilePathAndNameToWork().substring(28) + "</a></td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (list.get(i).isComplete() == false) {
		out.println("<td>Не выполнено</td>");
	} else {
		out.println("<td>Выполнено</td>");
	}

	if (!list.get(i).getReport().contains("null")) {
		out.println("<td>" + list.get(i).getReport() + "</td>");
	} else {
		out.println("<td>Не заполнено</td>");
	}

	if (!list.get(i).getReportFilePathAndNameToWork().contains("null") && list.get(i).getReportFilePathAndNameToWork().length()>48) {
		out.println("<td><a href=\"/niikp/DownloadServlet?"
			+ list.get(i).getReportFilePathAndNameToWork().substring(27) + "\">"
	+ list.get(i).getReportFilePathAndNameToWork().substring(27) + "</a></td>"); //was 28
	} else {
		out.println("<td>Не заполнено</td>");
	}
	out.println("<td><a href=\"/niikp/workDone?workId=" + list.get(i).getWorkId() + "&action=accept\"><button type=\"submit\">Принять</button></a></td>");
	out.println("<td><a href=\"/niikp/workDone?workId="	+ list.get(i).getWorkId() + "&action=refuse\"><button type=\"submit\">Отказаться</button></a></td>");
	counter++;
	out.println("</tr>");
	}

	
}



out.println("</table>");
%>
</body>
</html>