<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>REMS | Instance Request</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/custom.css">
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<link rel="stylesheet" type="text/css" href="css/theme.default.css">
<link rel="stylesheet" type="text/css" href="css/stylesNew.css">
<link rel="stylesheet" type="text/css" href="css/steps.css">

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<body>
	<div class="mainAll">
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div class="container">
			<c:if test="${error ne null}">
				<table class="my-error-class">
					<tbody>
						<tr>
							<td class="lable-title" align="left" valign="middle">
								${error}</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<br /> <br /> <br />


			<h2 style="color: #0098cc; font-size: 14px;">Create Environment
				Instance</h2>
			<hr>

			<div class="two-col">
				<form:form id="remsCreateEnvInstanceForm"
					name="remsCreateEnvInstanceForm"
					action="${pageContext.request.contextPath}/remsCreateEnvInstance"
					modelAttribute="remsCreateEnvInstanceDTO">

					<form:hidden path="id" />
					<form:hidden path="createdDate" />

					<form:hidden path="userId" />

					<table
						style="width: 100%; border: 0; font-size: 75%; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Project
									Name<span>*</span>
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="projName" id="projName" required="required"
										class="form-control" />
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Environment
									Name<span>*</span>
								</td>
								<td class="lable-title" width="20%" align="left" valign="middle"
									scope="col"><form:input path="envName" id="envName"
										class="form-control" /></td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">Application
									Name<span>*</span>
								</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="appName" id="appName" required="required"
										class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Software
									Loaded Info/Version<span>*</span>
								</td>
								<td class="lable-title" align="left" valign="middle" scope="col">
									<form:input path="swLoadInfo" id="swLoadInfo"
										class="form-control" />
								</td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle">Host
									Server Name</td>
								<td class="flied-title" align="left" valign="middle"><form:select
										path="hostServerName" id="hostServerName" class="down-control">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${remsCreateEnvInstanceDTO.hostServerNames}"
											var="hostServerNames">
											<form:option value="${hostServerNames}">${hostServerNames}</form:option>
										</c:forEach>

									</form:select></td>
								<td class="lable-title" align="left" valign="middle">Operating
									System Version</td>
								<td class="lable-title" align="left" valign="middle" scope="col">
									<form:input path="osVer" id="osVer" class="form-control" />
								</td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">DataBase
									Name</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="dbName" id="dbName" class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Network
									Connectivity Info</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="nwConnInfo" id="nwConnInfo"
										class="down-control">
										<form:option value="">--Select--</form:option>
										<form:option value="LAN">LAN</form:option>
										<form:option value="WAN">WAN</form:option>
										<form:option value="VPN">VPN</form:option>
									</form:select>
								</td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle">Allocated
									Memory</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="alloMemory" id="alloMemory" class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Type
									Of Environment</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:input path="envType" id="envType" class="form-control" />
								</td>
							</tr>



							<tr>
								<td class="lable-title" align="left" valign="middle">Support
									Person/Support Department</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="sme" id="sme" class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Connectivity
									to Another Environment</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:input path="anotherEnv" id="anotherEnv"
										class="form-control" />
								</td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">Active?</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<label class="radio-inline"> <form:radiobutton
											path="active" id="active" value="Yes" />Yes
								</label> <label class="radio-inline"> <form:radiobutton
											path="active" id="active" value="No" />No
								</label>
								</td>
							</tr>

						</tbody>
					</table>




					<table
						style="width: 100%; border: 0; font-size: 13px; color: #0C5473; align: center;">
						<tbody>
							<tr>
								<th scope="col" class="buttonsAll8"><c:if
										test="${remsCreateEnvInstanceDTO.id eq null}">
										<input type="submit" id="submit" name="submit"
											class="btn-primary btn-cell" value="Create">
										<input type="reset" id="reset" name="reset"
											class="btn-primary btn-cell" value="Reset">
									</c:if> <c:if test="${remsCreateEnvInstanceDTO.id ne null}">
										<input type="submit" id="submit" name="submit"
											class="btn-primary btn-cell" value="Update">
									</c:if></th>
							</tr>
						</tbody>
					</table>

				</form:form>
			</div>
		</div>
		<script src="include/footer.js"></script>
	</div>

	<script>

menu_highlight('services');
menu_highlight('services_env_inst');
menu_highlight('services_env_inst_add');

 

//remsCreateHost();

$(function() {
	$( document ).tooltip({
		position: {
			my: "center bottom-20",
			at: "center top",
			using: function( position, feedback ) {
				$( this ).css( position );
				$( "<div>" )
					.addClass( "arrow" )
					.addClass( feedback.vertical )
					.addClass( feedback.horizontal )
					.appendTo( this );
			}
		}
	});
});

$(document).ready(function() {
	$(document).ready(function() {
		var readOnly = '${readOnly}';
		if(readOnly=='true'){
			
			disablePage();
		}
		  
      
  });	
});
</script>
</body>
</html>
