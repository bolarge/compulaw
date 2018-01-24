<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<div xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:joda="http://www.joda.org/joda/time/tags"
	version="2.0">
	<jsp:directive.page contentType="text/html;charset=UTF-8" />
	<jsp:output omit-xml-declaration="yes" />

	<spring:message code="label_person_list" var="labelPersonList" />
	<spring:message code="label_person_first_name" var="labelPersonFirstName" />
	<spring:message code="label_person_last_name" var="labelPersonLastName" />
	<spring:message code="label_person_birth_date" var="labelPersonBirthDate" />
	<spring:url value="/public/sb_pson/" var="showPersonUrl"/>

	<div>
		<script type="text/javascript">
			$(function() {
				$("#list").jqGrid(
					{
						url : '${showPersonUrl}/listgrid',
						datatype : 'json',
						mtype : 'GET',
						colNames : [ '${labelPersonFirstName}','${labelPersonLastName}','${labelPersonBirthDate}' ],
						colModel : [ {name : 'firstName',index : 'firstName',width : 150}, 
									 {name : 'lastName',index : 'lastName',width : 150}, 
								     {name : 'birthDateString',index : 'birthDate',width : 100} ],
						jsonReader : {
							root : "personData",
							page : "currentPage",
							total : "totalPages",
							records : "totalRecords",
							repeatitems : false,
							id : "id"},
						pager : '#pager',
						rowNum : 10,
						rowList : [10,20,30],
						sortname : 'firstName',
						sortorder : 'asc',
						viewrecords : true,
						gridview : true,
						height : 250,
						width : 860,
						caption : '${labelPersonList}',
						onSelectRow : function(id) {
							document.location.href = "${showPersonUrl}/" + id;
						}
					});
		});
	</script>

	<!--  -->
	<div class="row-fluid" id="">
		<div class="span9 text-left divide">
			<h4>USERS</h4>
		</div>
	</div>
		
	<c:if test="${not empty message}">
		<div id="message" class="${message.type}">${message.message}</div>
	</c:if>
	<div>
		<table id="list" class="table">
			<tr>
				<td />
			</tr>
		</table>
	</div>
	<div id="pager"></div>
</div>
<br />
	<p>
		<span><a href="/compulaw/public/sb_pson?form">New User</a></span>
	</p>
</div>



