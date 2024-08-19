<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<div class="row">
			<div class="col-6">
				<c:url var="post" value="/post"></c:url>
				<form class="p-3 bg-white shadow rounded" action="${post}"
					method="post">

					<c:if test="${null ne msg}">
						<div class="alert alert-info">
							<c:out value="${msg}"></c:out>
						</div>
						<c:remove var="msg" scope="session" />
					</c:if>

					<label class="mb-2">Post Title</label> 
						<input name="title"
						type="text" class="form-control mb-3" placeholder="Title" required="required"/> 
					
					<label
						class="mb-2">Description</label>
					<textarea name="description" class="form-control mb-3" rows=""
						cols="" required="required"></textarea>
					
					<input name="author" type="text" class="form-control mb-3"
						placeholder="Author" required="required"/> 
					
					<label class="mb-2">Category</label> 
					<select
						name="category" class="form-select mb-3" required="required">
						<option value="Health">Health</option>
						<option value="Beauty">Beauty</option>
						<option value="Technology">Technology</option>
						<option value="Sport">Sport</option>
					</select>
					<button type="submit" class="btn btn-primary w-50 d-block mx-auto">Post</button>
				</form>

			</div>
			<div class="col-6">
				<div class="shadow h-100 rounded p-4">
					<form action="${post}" method="get" class="mb-3 d-flex">
						<input type="text" class="form-control w-50 me-3" name="author"
							placeholder="Search By Author" />
						<button type="submit" class="btn btn-primary">Search</button>
					</form>
					<c:if test="${not empty posts}">
						<div class="row">
							<c:forEach var="post" items="${posts}">
								<div class="card mb-3">
									<div class="card-body">
										<div class="card-title border-bottom pb-2">
											<span class="btn btn-sm btn-success float-end">${post.category}</span>
											<h5>${post.title}</h5>
											<small class="text-muted">${post.author} | </small>

											 <small class="text-muted">
											 	${post.postedDate.format(formatter)}
											 </small>
										</div>
										<div class="card-text">${post.description}</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>