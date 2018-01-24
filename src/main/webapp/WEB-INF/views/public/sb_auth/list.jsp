<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<div xmlns:jsp="http://java.sun.com/JSP/Page" 
     xmlns:c="http://java.sun.com/jsp/jstl/core" 
     xmlns:spring="http://www.springframework.org/tags"
     xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
     version="2.0">
    <jsp:directive.page contentType="text/html;charset=UTF-8"/>
    <jsp:output omit-xml-declaration="yes"/>

	<spring:message code="label_role_name" var="labelRoleName" />
	<spring:message code="label_role_new" var="labelRoleNew" />
	<spring:message code="label_role_update" var="labelRoleUpdate" />
	<spring:message code="label_role_list" var="labelRoleList" />
	<spring:url value="/public/sb_authitem/" var="showRoleUrl" />

<div class="divide">
	<h2>${labelRoleList}</h2>
	<c:if test="${not empty roles}">
		<table class="table table-bordered" width="100%">
			<thead>
				<tr>
					<th>Role</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles}" var="role">
					<tr>
						<td>${role.roleName}</td>
						<td>&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<p>
		<span><a href="/public/sb_auth?form">New Role</a></span>
	</p>
</div>
</div>


