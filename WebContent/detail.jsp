<%@page import="models.dao.studentsDAO"%>
<%@page import="models.bean.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<title>Trang chi tiết sinh viên</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<style>
			<%@include file="/WEB-INF/lib/ctsv.css" %>
		</style>
	</head>

	<body>
		<header>
			<% if(request.getParameter("idUpdate")==null) {%>
			<h2>Thêm Mới Sinh Viên</h2>
			<%}else{ %>
			<h2>Chi tiết sinh viên</h2>
			<%} %>
		</header>
		<main>
			<% if(request.getParameter("idUpdate")==null){ %>
			<form action="studentController" method="post">
				<div class="formtop">
					Họ và tên: <input id="name" type="text" name="name" ><br><br>
					Mã số sinh viên: <input id="id" type="number" name="id"><br><br>
					Khoa:<input id="falculty" name="falculty" type="text"><br><br>
					Lớp:<input id="classOf" name="classOf" type="text"><br><br>
					Ngày sinh: <input id="birthDay" type="date" name="birthDay"><br><br>
					Giới tính: <label class="radio-inline"><input type="radio" name="gentle" value="1" checked>Nam</label>
							   <label class="radio-inline"><input type="radio" name="gentle" Value="0">Nữ</label>
							   <br><br>
					Điểm: <input id="score" name="score" type="number" step="any" min="0" max="10"><br><br>
					<input type="submit" name="submit" value="Add">

				</div>
			</form>
			<%}else{
				int id = Integer.parseInt(request.getParameter("idUpdate"));
				Student student = studentsDAO.getStudentById(id);
				out.println(student.getName());%>
			<form action="studentController" method="post">
				<div class="formtop">
					Họ và tên: <input id="name" type="text" name="name" value=<%=student.getName() %>><br><br>
					Mã số sinh viên: <input id="id" type="number" name="id" value=<%=student.getId() %>><br><br>
					Khoa:<input id="falculty" name="falculty" type="text" value=<%=student.getIdFalcuty() %>><br><br>
					Lớp:<input id="classOf" name="classOf" type="text" value=<%=student.getClassof() %>><br><br>
					Ngày sinh: <input id="birthDay" type="date" name="birthDay" value=<%=student.getBirthday() %>><br><br>
					Giới tính: <% if(student.Gentle()==true){ %>
					<label class="radio-inline"><input type="radio" name="gentle" value="1" checked>Nam</label>
							   <label class="radio-inline"><input type="radio" name="gentle" Value="0">Nữ</label>
							   <%}else{ %>
							   <label class="radio-inline"><input type="radio" name="gentle" value="1" >Nam</label>
							   <label class="radio-inline"><input type="radio" name="gentle" Value="0" checked>Nữ</label>
							   <%} %>
							   <br><br>
					Điểm: <input id="score" name="score" type="number" step="any" min="0" max="10" value=<%=student.getScore()%>><br><br>
					<input type="submit" name="submit" value="Update">

				</div>
			</form>
			<%} %>
		</main>
		<!--script src="detailPage.js"></script-->
	</body>

</html>
