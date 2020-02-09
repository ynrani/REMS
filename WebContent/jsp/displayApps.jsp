<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

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
<link rel="stylesheet" type="text/css"
	href="jquery/jquery.autocomplete.css" />

<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.tablesorter.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="jquery/lib/jquery.js"></script>
<script type="text/javascript" src="jquery/jquery.autocomplete.js"></script>
<body>
	<div class="wrapper mainAll">
		<%--  <jsp:include page="index.jsp"></jsp:include> --%>

		<div class="container">

			<br /> <br /> <br />
			<hr>
			<div class="two-col">
				<form:form id="remsAppSearch" name="remsAppSearch"
					action="${pageContext.request.contextPath}/displayApps"
					modelAttribute="remsEnvBookingsDTO" method="get">
					<c:choose>
						<c:when test="${remsEnvBookingsDTOs ne null}">

							<h1>
								From:
								<%=  request.getAttribute("startDt")%>
								To:
								<%=  request.getAttribute("endDt")%>
							</h1>
							<%
				int currentPage = (Integer) request.getAttribute("currentPage");
				int count1 = currentPage - 1;
				count1 = count1 * 10;
 			%>
							<div class="nav" id="myid">
								<table id="search_output_table" class="table table-bordered">
									<thead>
										<tr>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Application Name</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Environment Name</th>
											<%-- <% String strdt = (String) request.getAttribute("startDt"); %> --%>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont"><%= request.getAttribute("startDt") %>
											</th>
											<% @SuppressWarnings("unchecked")
				       List<String> dateList = (ArrayList<String>) request.getAttribute("dateList");
				       
				       for(int i=0; i<dateList.size();) {
				   	       %>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont"><%= (String) dateList.get(i)%></th>
											<%
			  	 		i++; } %>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont"><%= request.getAttribute("endDt") %></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${remsEnvBookingsDTOs}"
											var="remsEnvBookingsDTOs" varStatus="status">
											<tr>
												<td align="left">${remsEnvBookingsDTOs.appName}</td>
												<td align="left"><a class="hrefVisited"
													href="./remsEnvBooking?id=${remsEnvBookingsDTOs.id}">${remsEnvBookingsDTOs.envName}</a></td>




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
								<li><a href="remsEnvBookingListing?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li><a
									href="remsEnvBookingListing?page=<%= currentPage-1 %>">&lt;
										Prev</a>
									<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
								<li class="disable"><a
									href="remsEnvBookingListing?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li class="disable"><a
									href="remsEnvBookingListing?page=<%= currentPage-1 %>">&lt;
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
								<li><a href="remsEnvBookingListing?page=<%= i %>"
									id="employeeLink"><%= i %></a>
									<div><%= i %></div></li>
								<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
								<li><a
									href="remsEnvBookingListing?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li><a href="remsEnvBookingListing?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											} else {
											    if(noOfPages > 1) {
										%>
								<li class="disable"><a
									href="remsEnvBookingListing?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li class="disable"><a
									href="remsEnvBookingListing?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											    }
											}
										%>
							</ul>
						</c:when>
						<c:otherwise>
							<h3
								style="float: left; width: 40%; border: 0; font-size: 14px; color: black; padding-top: 15px">
								No apps found</h3>
							<br />
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
		</div>
		<script src="include/footer.js"></script>
	</div>
</head>
</body>
</html>