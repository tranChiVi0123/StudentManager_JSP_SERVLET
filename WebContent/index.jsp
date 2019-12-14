<%@page import="models.dao.studentsDAO"%>
<%@page import="org.apache.tomcat.jni.Stdlib"%>
<%@page import="models.bean.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Trang quản lí sinh viên</title>
<style>
<%@include file="/WEB-INF/lib/qlsv.css" %>
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0-11/css/all.min.css">
</head>

<body>
	<div class="container-fluid">
	
		<div class="row">
			<div class="col-1 bg-dark"></div>
			<div class="col-11">
				<%
					if (session.getAttribute("status") != null) {
						if (session.getAttribute("status").equals(0)) {
							out.println(
									" <div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>Failed!</strong></div> ");
							session.removeAttribute("status");
						} else {
							out.println(
									" <div class=\"alert alert-success alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button><strong>Success!</strong></div> ");
							session.removeAttribute("status");
						}
					}
				%>
				<header>
					<div class="row">
						<div class="col-10 mt-3">
							<h2>Quản lí sinh viên</h2>
						</div>
						<div class="col-2 mr-0">
							<!--Modal: Login / Register Form-->

							<div class="text-center">
								<%
									if (session.getAttribute("userCurrent") == null) {
								%>
								<button class="btn btn-outline-info btn-rounded my-3"
									data-toggle="modal" data-target="#modalLRForm">LogIn/Register</button>
								<%
									} else {
								%>
								<strong> <%out.println(session.getAttribute("userCurrent"));%>
								</strong>
								<form action="authController" method="GET">
									<input class="btn btn-outline-info btn-rounded my-3"
										name="logout" type="submit" value="Logout">
								</form>
								<%
									}
								%>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-8">
							<input type="text" id="myInput" onkeyup="mySearchFunc()"
								placeholder="Search for names..." title="Type in a name">
						</div>
						<div class="col-4 ">
							<% if(session.getAttribute("role")=="1"){ %>
							<button id="btnAdd" class="button" style="vertical-align: middle">
								<span><a href="<%=request.getContextPath()+"/detail.jsp"%>">Thêm</a> </span>
							</button>
							<%} %>
						</div>


					</div>
				</header>
				<main>
					<table id="table">
						<tr>
							<th>Stt</th>
							<th>Mã số sinh viên</th>
							<th>Họ và tên</th>
							<th>Khoa</th>
							<th>Lớp</th>
							<th>Ngày sinh</th>
							<th>Giới tính</th>
							<th>Điểm</th>
							<% if(session.getAttribute("role")=="1"){ %>
							<th>Chỉnh sửa</th>
							<%} %>
						</tr>
						<%
							if(session.getAttribute("userCurrent")!=null){
								ArrayList<Student> students = studentsDAO.getStudents();
								int i = 0;
								for (Student s : students) {
						%>
						<tr>
							<td><%=++i%>
							<td><%=s.getId()%></td>
							<td><%=s.getName()%></td>
							<td><%=s.getIdFalcuty()%></td>
							<td><%=s.getClassof()%></td>
							<td><%=s.getBirthday()%></td>
							<td><%=s.Gentle() ? "Nam" : "Nữ"%></td>
							<td><%=s.getScore()%></td>
							<% if(session.getAttribute("role")=="1"){ %>
							<td><a
								href="<%=request.getContextPath() + "/studentController?idUpdate=" + s.getId()%>"
								class="text-primary mr-3"><i class="fas fa-edit"></i></a> <a
								href="<%=request.getContextPath() + "/studentController?idRemove=" + s.getId()%>"
								class="text-primary"><i class="far fa-trash-alt"></i></a></td>
							<%} %>
						</tr>
						<%
							 }
							}
						%>
					</table>
				</main>
				<footer>
					<div class="row">
						<div class="col-sm-6">
							<p>Posted by: Mai Văn Hà</p>
						</div>
						<div class="col-sm-6">
							<p>
								Contact information: <a href="mailto:someone@example.com">
									someone@example.com</a>.
							</p>
						</div>
					</div>
				</footer>
			</div>
		</div>
	</div>
	<!--Modal: Login / Register Form-->
	<div class="modal fade" id="modalLRForm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog cascading-modal" role="document">
			<!--Content-->
			<div class="modal-content">

				<!--Modal cascading tabs-->
				<div class="modal-c-tabs">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3"
						role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#panel7" role="tab"><i
								class="fas fa-user mr-1"></i> Login</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#panel8" role="tab"><i class="fas fa-user-plus mr-1"></i>
								Register</a></li>
					</ul>
					<!-- Tab panels -->
					<div class="tab-content">
						<!--Panel 7-->
						<div class="tab-pane fade in show active" id="panel7"
							role="tabpanel">

							<!--Body-->

							<form action="authController" method="post">
								<div class="modal-body mb-1">
									<div class="md-form form-sm mb-5">
										<i class="fas fa-envelope prefix"></i> <input type="email"
											id="modalLRInput10" name="email"
											class="form-control form-control-sm validate"> <label
											data-error="wrong" data-success="right" for="modalLRInput10">Your
											email</label>
									</div>

									<div class="md-form form-sm mb-4">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="modalLRInput11" name="password"
											class="form-control form-control-sm validate"> <label
											data-error="wrong" data-success="right" for="modalLRInput11">Your
											password</label>
									</div>
									<div class="text-center mt-2">
										<input class="btn btn-info" type="submit" value="Log In" />
									</div>
								</div>
								<!--Footer-->
								<div class="modal-footer">
									<div class="options text-center text-md-right mt-1">
										<p>
											Not a member? <a href="#" class="blue-text">Sign Up</a>
										</p>
										<p>
											Forgot <a href="#" class="blue-text">Password?</a>
										</p>
									</div>
									<button type="button"
										class="btn btn-outline-info waves-effect ml-auto"
										data-dismiss="modal">Close</button>
								</div>
							</form>

						</div>
						<!--/.Panel 7-->

						<!--Panel 8-->
						<div class="tab-pane fade" id="panel8" role="tabpanel">
							<form action="authController" method="POST">
								<!--Body-->
								<div class="modal-body">
									<div class="md-form form-sm mb-5">
										<i class="fas fa-envelope prefix"></i> <input type="email"
											id="modalLRInput12" name="email"
											class="form-control form-control-sm validate"> <label
											data-error="wrong" data-success="right" for="modalLRInput12">Your
											email</label>
									</div>

									<div class="md-form form-sm mb-5">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="modalLRInput13" name="password"
											class="form-control form-control-sm validate"> <label
											data-error="wrong" data-success="right" for="modalLRInput13">Your
											password</label>
									</div>

									<div class="md-form form-sm mb-4">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="modalLRInput14" name="passwordrp"
											class="form-control form-control-sm validate"> <label
											data-error="wrong" data-success="right" for="modalLRInput14">Repeat
											password</label>
									</div>

									<div class="text-center form-sm mt-2">
										<button class="btn btn-info">
											Sign up <i class="fas fa-sign-in ml-1"></i>
										</button>
									</div>

								</div>
								<!--Footer-->
								<div class="modal-footer">
									<div class="options text-right">
										<p class="pt-1">
											Already have an account? <a href="#" class="blue-text">Log
												In</a>
										</p>
									</div>
									<button type="button"
										class="btn btn-outline-info waves-effect ml-auto"
										data-dismiss="modal">Close</button>
								</div>
							</form>

						</div>
						<!--/.Panel 8-->
					</div>

				</div>
			</div>
			<!--/.Content-->
		</div>
	</div>
	  <script>
	  <%@include file="/WEB-INF/lib/qlsv.js" %>
	  </script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.15.0/umd/popper.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>
