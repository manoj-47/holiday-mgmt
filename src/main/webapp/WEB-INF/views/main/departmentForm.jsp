<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<layout:layout>
	<jsp:attribute name="header">
      <h1>Department Master</h1>
    </jsp:attribute>
	<jsp:body>
	    <center>
            <h1>Department Master</h1>
            <h2>
                <a href="/new">Add New Department</a>
                &nbsp;&nbsp;&nbsp;
                <a href="/list">List All Departments</a>

            </h2>
        </center>
	<div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>List of Departments</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="department" items="${listDepartment}">
                    <tr>
                        <td><c:out value="${department.id}" /></td>
                        <td><c:out value="${department.departmentName}" /></td>
                        <td>
                            <a href="/edit?id=<c:out value='${department.id}' />">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/delete?id=<c:out value='${department.id}' />">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
	</jsp:body>
</layout:layout>