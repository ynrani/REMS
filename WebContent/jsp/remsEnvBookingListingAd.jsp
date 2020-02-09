<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>REMS | Booking Listing</title>
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
<body>
	<div class="wrapper mainAll">
		<jsp:include page="indexHeader.jsp"></jsp:include>

		<div class="container">

			<c:if test="${error ne null}">
				<table class="my-error-class">
					<tbody>
						<tr>
							<td class="lable-title" align="left" valign="middle">${error}</td>
						</tr>
					</tbody>
				</table>
			</c:if>

			<br /> <br /> <br />


			<h2 style="color: #0098cc; font-size: 14px;">Environment
				Booking(s) - Showcase</h2>
			<hr>

			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<script src="include/bookingMenu.js"></script>
			</security:authorize>
			<div class="two-col">
				<form:form id="remsEnvBookingListingAdForm"
					name="remsEnvBookingListingAdForm"
					action="${pageContext.request.contextPath}/remsEnvBookingListingAd"
					modelAttribute="remsEnvBookingsDTO">

					<table style="width: 100%; border: 0; font-size: 75%; padding: 1%;">
						<tbody>
							<tr>
								<td class="lable-title" width="25%" align="left" valign="middle">Application
									Name</td>
								<td class="flied-title" width="25%" align="left" valign="middle">
									<form:select path="appName" id="appName" class="down-control">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${remsEnvBookingsDTO.appNames}"
											var="appNames">
											<form:option value="${appNames}">${appNames}</form:option>
										</c:forEach>
									</form:select>
								</td>
								<td class="lable-title" width="25%" align="left" valign="middle">Environment
									Name</td>
								<td class="lable-title" width="25%" align="left" valign="middle"
									scope="col"><form:select path="envName" id="envName"
										class="down-control">
										<form:option value="">--Select--</form:option>
										<c:forEach items="${remsEnvBookingsDTO.envNames}"
											var="envNames">
											<form:option value="${envNames}">${envNames}</form:option>
										</c:forEach>
									</form:select></td>
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
						</tbody>
					</table>

					<table
						style="width: 100%; border: 0; font-size: 13px; color: #0C5473; align: center;">
						<tbody>
							<tr>
								<th scope="col" class="buttonsAll8"><input type="submit"
									id="submit1" name="submit1" class="btn-primary btn-cell"
									value="Search"> <input type="submit" id="submit2"
									name="submit2" class="btn-primary btn-cell" value="Show All">
								</th>
							</tr>
						</tbody>
					</table>

					<c:choose>
						<c:when test="${remsEnvBookingsDTOs ne null}">
							<%
				int currentPage = (Integer) request.getAttribute("currentPage");
				int count1 = currentPage - 1;
				count1 = count1 * 10;
 			%>
							<div class="nav" id="myid">
								<table id="search_output_table" class="hoverTable"
									style="width: 100%; font-size: 13px; border: 0; cellpadding: 0; cellspacing: 1;">
									<thead>
										<tr>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Environment Name</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Application Name</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Environment User</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Environment Booked to</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Environment Connectivity</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">IsActive</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Created On</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Created By</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Delete ?</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${remsEnvBookingsDTOs}"
											var="remsEnvBookingsDTOs" varStatus="status">
											<tr>
												<td align="left"><a class="hrefVisited"
													href="./remsEnvBooking?id=${remsEnvBookingsDTOs.id}">${remsEnvBookingsDTOs.envName}</a></td>
												<td align="left">${remsEnvBookingsDTOs.appName}</td>
												<td align="left">${remsEnvBookingsDTOs.envUser}</td>
												<td align="left">${remsEnvBookingsDTOs.bookedTo}</td>
												<td align="left">${remsEnvBookingsDTOs.envConn}</td>
												<td align="left">${remsEnvBookingsDTOs.active}</td>
												<td align="left">${remsEnvBookingsDTOs.userId}</td>
												<td align="left">${remsEnvBookingsDTOs.createdDate}</td>
												<td align="left"><a class="hrefVisited" href="#"
													onClick="deleteBookingAd(${remsEnvBookingsDTOs.id});">Delete</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<ul class="grdPagination">
								<%
			                  				int noOfPages = (Integer) request.getAttribute("noOfPages");
			                  				int startPage = (Integer) request.getAttribute("startPage");
			                  				int lastPage = (Integer) request.getAttribute("lastPage");
			                  		  
											if (currentPage != 1) {
			   							%>
								<li><a href="remsEnvBookingListingAd?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li><a
									href="remsEnvBookingListingAd?page=<%= currentPage-1 %>">&lt;
										Prev</a>
									<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
								<li class="disable"><a
									href="remsEnvBookingListingAd?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li class="disable"><a
									href="remsEnvBookingListingAd?page=<%= currentPage-1 %>">&lt;
										Prev</a>
									<div>&lt; Prev</div> <%
			   								 	}
			   								}
											if(noOfPages > 1) {
			    								for (int i=startPage; i<=lastPage; i++) {
			    									if(currentPage == i) {
			   			 				%>
								<li class="active"><a href="#"><%= i %></a>
									<div><%= i %></div></li>
								<%
			    									} else {
			    						%>
								<li><a href="remsEnvBookingListingAd?page=<%= i %>"
									id="employeeLink"><%= i %></a>
									<div><%= i %></div></li>
								<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
								<li><a
									href="remsEnvBookingListingAd?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li><a href="remsEnvBookingListingAd?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											} else {
											    if(noOfPages > 1) {
										%>
								<li class="disable"><a
									href="remsEnvBookingListingAd?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li class="disable"><a
									href="remsEnvBookingListingAd?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											    }
											}
										%>
							</ul>


							<table style="width: 100%; border: 0">
								<tbody>
									<tr>
										<th scope="col" class="buttonsAll15"><input type="submit"
											name="export" id="export" value="ExportAll to Excel">
										</th>
									</tr>
								</tbody>
							</table>

						</c:when>
						<c:otherwise>
							<h3
								style="float: left; width: 40%; border: 0; font-size: 14px; color: black; padding-top: 15px">
								<div>No Environment Booking(s) are available</div>
							</h3>
							<br />
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
		<script src="include/footer.js"></script>
	</div>

	<script>
menu_highlight('services');
menu_highlight('services_env_book');
menu_highlight('services_env_book_modify');
 
menu_highlight('All');


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

window.location.hash = "myid";
   function getHostDtls(id){
	   document.location.href="./remsEnvBooking?id="+id;
   }
   
   function deleteBookingAd(reqId)
   {
	   confirmation_dialogYNCancelBookingAd(reqId,'Submit','Are you sure do you want to cancel the request ?',400,'YesNo','Yes','No');
   }
   
   $("#search_output_table").tablesorter({
	    widgets: ['zebra']
	  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
</script>
</body>
</html>