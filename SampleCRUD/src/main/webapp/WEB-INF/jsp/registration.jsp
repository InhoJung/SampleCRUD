<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page session="true" %>
<%@ page contentType = "text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Hello World with Spring 3 MVC</title>
		<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> -->
 		<script type="text/javascript" src='<c:url value="/resources/common.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/resources/registration.js"/>'></script>
		
		<script type="text/javascript">
			var projectUrl = '<c:url value="/"/>';
			if(projectUrl.indexOf(";", 0) != -1){
				projectUrl = projectUrl.substring(0, projectUrl.indexOf(";", 0));
			}
		</script>		
	</head>
	<body>
		<fieldset>
		<legend>Registration Form</legend>
		<center>
		<form:form commandName="customer" action="/SampleCRUD/registration/add" name="userForm">
		
		<form:hidden path="customer_id"/>
		<table>
			<tr><td colspan="2" align="left"><form:errors path="*" ssStyle="color : red;"/></td></tr>
			<tr><td>Name : </td><td><form:input path="name" /></td></tr>
			<tr><td>Standard : </td><td><form:input path="standard" /></td></tr>
			<tr><td>Age : </td><td><form:input path="age" /></td></tr>
			<tr><td>Sex : </td><td><form:select path="sex">
					<form:option value="Male"/>
					<form:option value="Female"/>
			</form:select></td></tr>
			<tr><td colspan="2"><input type="submit" value="Save Changes"/>
			&nbsp;<input type="reset" name="newUser" value="New User" onclick="setAddForm();" disabled="disabled"/>
			&nbsp;<input type="submit" name="deleteUser" value="Delete User" onclick="setDeleteForm();" disabled="disabled"/></td></tr>
		</table>
		</form:form>
		</center>
		</fieldset>
		<c:if test="${!empty customers}">
		
			<br />
			<center>
				<table width="90%">
					<tr style="background-color: gray;">
						<th>Name</th>
						<th>Standard</th>
						<th>Age</th>
						<th>Sex</th>
					</tr>
					<c:forEach items="${customers}" var="customer">
						<tr style="background-color: silver;" id="${customer.customer_id}" onclick="setUpdateForm('${customer.customer_id}');">
							<td><c:out value="${customer.name}"/></td>
							<td><c:out value="${customer.standard}"/></td>
							<td><c:out value="${customer.age}"/></td>
							<td><c:out value="${customer.sex}"/></td>
						</tr>
					</c:forEach>
				</table>
				</center>
			<br />
		
		</c:if>
	</body>
</html>