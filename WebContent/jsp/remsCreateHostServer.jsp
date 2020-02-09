<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>REMS | Request</title>
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


			<h2 style="color: #0098cc; font-size: 14px;">Create HostServer</h2>
			<hr>

			<div class="two-col">
				<form:form id="remsCreateHostServerForm"
					name="remsCreateHostServerForm"
					action="${pageContext.request.contextPath}/remsCreateHostServer"
					modelAttribute="remsCreateHostServerDTO">

					<form:hidden path="id" />
					<form:hidden path="createdDate" />
					<form:hidden path="userId" />

					<table
						style="width: 100%; border: 0; font-size: 75%; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Host
									Server Name <span>*</span>
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="hostServerName" id="hostServerName"
										required="required" class="form-control" />
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Host
									Server Physical Location</td>
								<td class="lable-title" width="20%" align="left" valign="middle"
									scope="col"><form:input path="serverPhyLoc"
										id="serverPhyLoc" class="form-control" /></td>
							</tr>

							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Host
									Server IP Address <span>*</span>
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="ip" id="ip" required="required"
										class="form-control"
										pattern="((^|\.)((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]?\d))){4}$" />
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Allocated
									CPU</td>
								<td class="lable-title" width="20%" align="left" valign="middle"
									scope="col"><form:input path="alloCPU" id="alloCPU"
										class="form-control" /></td>
							</tr>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Allocated
									Disk Space</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="alloDiskSpace" id="alloDiskSpace"
										class="form-control" />
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Allocated
									Memory</td>
								<td class="lable-title" width="20%" align="left" valign="middle"
									scope="col"><form:input path="alloMemory" id="alloMemory"
										class="form-control" /></td>
							</tr>

							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">SME
								</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="sme" id="sme" class="form-control" />
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Active?</td>
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
										test="${remsCreateHostServerDTO.id eq null}">
										<input type="submit" id="submit" name="submit"
											class="btn-primary btn-cell" value="Create">
										<input type="reset" id="reset" name="reset"
											class="btn-primary btn-cell" value="Reset">
									</c:if> <c:if test="${remsCreateHostServerDTO.id ne null}">
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
menu_highlight('services_mg_host');
menu_highlight('services_mg_host_add');

 

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
