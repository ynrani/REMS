<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>REMS | Booking</title>
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


			<h2 style="color: #0098cc; font-size: 14px;">Environment Booking
				- Intake</h2>
			<hr>

			<div class="two-col">
				<form:form id="remsEnvBookingForm" name="remsEnvBookingForm"
					action="${pageContext.request.contextPath}/remsEnvBooking"
					modelAttribute="remsEnvBookingsDTO">

					<form:hidden path="id" />
					<form:hidden path="createdDate" />
					<form:hidden path="userId" />

					<table
						style="width: 100%; border: 0; font-size: 75%; cellpadding: 4;">
						<tbody>
							<tr>
								<td class="lable-title" width="25%" align="left" valign="middle">Application
									Name <span>*</span>
								</td>
								<td class="flied-title" width="25%" align="left" valign="middle">
									<form:select path="appName" id="appName" required="required"
										class="down-control">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${remsEnvBookingsDTO.appNames}"
											var="appNames">
											<form:option value="${appNames}">${appNames}</form:option>
										</c:forEach>
									</form:select>
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Environment
									Name<span>*</span>
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle"
									scope="col"><form:select path="envName" id="envName"
										required="required" class="down-control">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${remsEnvBookingsDTO.envNames}"
											var="envNames">
											<form:option value="${envNames}">${envNames}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">Environment
									User</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="envUser" id="envUser" required="required"
										class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Environment
									Booked to</td>
								<td class="lable-title" align="left" valign="middle" scope="col">
									<form:input path="bookedTo" id="bookedTo" class="form-control" />
								</td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle">Booking
									Start Date</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="startDt" id="startDt"
										class="date-control datepicker from" /></td>
								<td class="lable-title" align="left" valign="middle">Booking
									End Date</td>
								<td class="lable-title" align="left" valign="middle" scope="col">
									<form:input path="endDt" id="endDt"
										class="date-control datepicker to" />
								</td>
							</tr>

							<tr>
								<td class="lable-title" align="left" valign="middle">Environment
									is connected to</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="envConn" id="envConn" class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Environment
									booked out to</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:select path="envBookedOut" id="envBookedOut"
										required="required" class="down-control">
										<form:option value="">--Select--</form:option>
										<form:option value="demo1">demo1</form:option>
										<form:option value="demo2">demo2</form:option>
										<form:option value="demo3">demo3</form:option>
										<form:option value="demo3">demo5</form:option>
									</form:select>
								</td>
							</tr>
							<tr>
								<td class="lable-title" align="left" valign="middle">Software
									Loaded Information</td>
								<td class="flied-title" align="left" valign="middle"><form:input
										path="swLoadInfo" id="swLoadInfo" class="form-control" /></td>
								<td class="lable-title" align="left" valign="middle">Notes
									Section</td>
								<td class="flied-title" align="left" valign="middle" scope="col">
									<form:textarea path="note" id="note" class="form-control"
										placeholder="Enter Text here..." />
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
										test="${remsEnvBookingsDTO.id eq null}">
										<input type="submit" id="submit" name="submit"
											class="btn-primary btn-cell" value="Book">
										<input type="reset" id="reset" name="reset"
											class="btn-primary btn-cell" value="Reset">
									</c:if> <c:if test="${remsEnvBookingsDTO.id ne null}">
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
menu_highlight('services_env_book');
menu_highlight('services_env_book_add');

 
$(function() {
	var dateToday = new Date();
	
    $( ".from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      minDate: dateToday,
      changeYear: true,
      onClose: function( selectedDate ) {
        $( ".to" ).datepicker( "option", "minDate", selectedDate );
      }
    });
    $( ".to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      numberOfMonths: 1,
      minDate: dateToday,
      changeYear: true,
      onClose: function( selectedDate ) {
        $( ".from" ).datepicker( "option", "maxDate", selectedDate );
      }
    });
  }); 

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
