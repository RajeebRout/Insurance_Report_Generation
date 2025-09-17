<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="frm" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Bootstrap demo</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>

	<div class="container">
				<h3 class="mb-5 pt-3">Insurance Report Application</h3>
	
	<frm:form action="searchPost" modelAttribute="search" method="POST">
		<table>
			<tr>
				<td>Plan Name :</td>
				<td>
					<frm:select path="planName">
						<frm:option value="">--SELECT--</frm:option>
						<frm:options items="${names}"/>
					</frm:select>
				</td>
			
				<td>Plan Status :</td>
				<td>
					<frm:select path="planStatus">
						<frm:option value="">--SELECT--</frm:option>
						<frm:options items="${status}"/>
					</frm:select>
				</td>
				
				<td>Gender :</td>
				<td>
					<frm:select path="gender">
						<frm:option value="">--SELECT--</frm:option>
						<frm:option value="Male">Male</frm:option>
						<frm:option value="Female">Female</frm:option>
					</frm:select>
				</td>
			</tr>
			
			<tr>
				<td>Plan Start Date :</td>
				<td><frm:input type="date" path="startDate"/></td>
			
				<td>Plan End Date :</td>
				<td><frm:input type="date" path="endDate"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Find Record" class="btn btn-primary"/></td>
				<td><input type="reset" value="Reset" class="btn btn-secondary"/></td>
			</tr>
		</table>
	</frm:form>
	
	<hr><hr>
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Gender</th>
				<th>Insurance Plan Name</th>
				<th>Plan Status</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Benefit Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recordList}" var="recordList">
					<tr>
						<td>${recordList.citizenId}</td>
						<td>${recordList.citizenName}</td>
						<td>${recordList.gender}</td>
						<td>${recordList.planName}</td>
						<td>${recordList.planStatus}</td>
						<td>${recordList.planStartDate}</td>
						<td>${recordList.planEndDate}</td>
						<td>${recordList.benefitAmount}</td>
					</tr>
			</c:forEach>
			<tr>
				<c:if test="${empty recordList}">
						<td colspan="8" style="text-align:center">No Record Available with this combination</td>
				</c:if>
			</tr>
		</tbody>
	</table>
	
	Export :<a href="">Pdf</a>  <a href="">Excel</a>
</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>