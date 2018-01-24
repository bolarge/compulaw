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
<spring:url value="/public/sb_auth/role/" var="showRoleUrl" />

<spring:eval
	expression="role.roleName == null ? labelRoleNew:labelRoleUpdate"
	var="formTitle" />


<div class="divide">
	<form class="form-horizontal">
		<c:if test="${not empty message}">
			<div id="message" class="${message.type}">${message.message}</div>
		</c:if>
		<fieldset>
			<legend>${formTitle}</legend>
			<div class="control-group">
				<label class="control-label" for="roleName">${labelRoleName}</label>
				<div class="controls">
					<input type="text" id="roleName" name="roleName"
						value="${role.roleName}" readonly>
				</div>
			</div>
			<!--
   <div class="control-group">
   	<label class="control-label" for="duration">${labelItemDuration}</label>
   	<div class="controls">
      		<input type="text" id="duration" name="duration" value="${item.duration }" readonly>
    	</div>
   </div>
    
   <div class="form-actions">
  	<button type="submit" class="btn">Add Subscription Item</button>
  	<button type="button" class="btn">Cancel</button>
   </div> 
  -->

		</fieldset>
		<div>
			<a href="${showRoleUrl}/${role.id}?form">${labelRoleUpdate}</a>
		</div>
	</form>
</div>
</div>