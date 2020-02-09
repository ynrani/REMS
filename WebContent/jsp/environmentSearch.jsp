<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Environment Search Page</title>
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

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<body>
	<div class="wrapper mainAll">
		<jsp:include page="environmentOwner.jsp"></jsp:include>
		<br /> <br /> <br />

		<h2 style="color: #0098cc; font-size: 14px;">Search Environment
			Availability for Booking</h2>

		<security:authorize ifAnyGranted="ROLE_ENVOWNER">
			<script src="include/environmentSearchMenu.js"></script>


			<form:form name="SelectApplication"
				action="${pageContext.request.contextPath}/displayApps"
				method="post" modelAttribute="remsEnvBookingsDTO">

				<table
					style="width: 100%; border: 0; font-size: 13px; padding-left: 90px; padding-right: 350px; padding-top: 35px; cellpadding: 2;">
					<tr>
						<td class="lable-title">Application ID 1:</td>
						<td class="flied-title" align="left" valign="middle"><form:select
								path="appName" id="secondAppName" class="form-control">
								<form:option value="">--Select--</form:option>
								<form:option value="TCOE-REMS">TCOE-REMS</form:option>
								<form:option value="APP4">APP4</form:option>
							</form:select></td>
					</tr>
					<tr>
						<td class="lable-title">Application ID 2:</td>
						<td class="flied-title" align="left" valign="middle"><form:select
								path="appName" id="secondAppName" class="form-control">
								<form:option value="">--Select--</form:option>
								<form:option value="TCOE-REMS">TCOE-REMS</form:option>
								<form:option value="APP4">APP4</form:option>
							</form:select></td>
					</tr>

					<tr>
						<td class="lable-title">Application ID 3:</td>
						<td class="flied-title" align="left" valign="middle"><form:select
								path="appName" id="thirdAppName" class="form-control">
								<form:option value="">--Select--</form:option>
								<form:option value="TCOE-REMS">TCOE-REMS</form:option>
								<form:option value="APP4">APP4</form:option>
							</form:select></td>
					</tr>
					<tr>

						<td class="lable-title">Start Date:</td>
						<td><form:input name="inputStartDt" path="startDt"
								id="inputStartDt" class="date-control datepicker from" /></td>
					</tr>
					<tr>
						<td class="lable-title">End Date:</td>
						<td><form:input name="inputEndDt" path="endDt"
								id="inputEndDt" class="date-control datepicker to" /></td>
					</tr>
				</table>

				<table
					style="width: 100%; border: 0; font-size: 13px; padding-left: 90px; padding-right: 350px; padding-top: 35px; cellpadding: 2;">
					<tbody>
						<tr>
							<td colspan="4" align="center" valign="middle"
								class="buttonsAll15"><input type="submit" value="SUBMIT"
								id="submit"></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</security:authorize>
		<script src="include/footer.js"></script>
	</div>

	<script>
menu_highlight('services');
menu_highlight('services_env_book');
menu_highlight('services_env_book_modify');

$(function() {
	var dateToday = new Date();
	
    $( ".from" ).datepicker({
      defaultDate: "",
      changeMonth: true,
      numberOfMonths: 1,
      minDate: dateToday,
      changeYear: true,
      onClose: function( selectedDate ) {
        $( ".to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( ".to" ).datepicker({
      defaultDate: "",
      changeMonth: true,
      numberOfMonths: 1,
      minDate: dateToday,
      changeYear: true,
      onClose: function( selectedDate ) {
        $( ".from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  }); 
</script>
</body>
</html>


