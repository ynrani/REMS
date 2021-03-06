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
<title>REMS | Listing</title>
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


			<h2 style="color: #0098cc; font-size: 14px;">HostServer(s) -
				Showcase</h2>
			<hr>
			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<script src="include/hostServermenu.js"></script>
			</security:authorize>

			<div class="two-col">

				<form:form id="remsCreateHostServerListingAdForm"
					name="remsCreateHostServerListingAdForm"
					action="${pageContext.request.contextPath}/remsCreateHostServerListingAd"
					modelAttribute="remsCreateHostServerDTO">

					<table style="width: 100%; border: 0; font-size: 75%; padding: 1%;">
						<tbody>
							<tr>
								<td class="lable-title" width="35%" align="left" valign="middle">Host
									Server Name</td>
								<td class="flied-title" width="20%" align="left" valign="middle">
									<form:input path="hostServerName" id="hostServerName"
										class="form-control" />
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
										class="form-control" />
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
						<c:when test="${remsCreateHostServerDTOs ne null}">
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
												class="whitefont">Host Server Name</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Host Server Physical Location</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Host Server IP Address</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Allocated CPU</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">Allocated Disk Space</th>
											<th align="center" bgcolor="#E3EFFB" scope="col"
												class="whitefont">SME</th>
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
										<c:forEach items="${remsCreateHostServerDTOs}"
											var="remsCreateHostServerDTOs" varStatus="status">
											<tr>
												<td align="left"><a class="hrefVisited"
													href="./remsCreateHostServer?id=${remsCreateHostServerDTOs.id}">${remsCreateHostServerDTOs.hostServerName}</a></td>
												<td align="left">${remsCreateHostServerDTOs.serverPhyLoc}</td>
												<td align="left">${remsCreateHostServerDTOs.ip}</td>
												<td align="left">${remsCreateHostServerDTOs.alloCPU}</td>
												<td align="left">${remsCreateHostServerDTOs.alloDiskSpace}</td>
												<td align="left">${remsCreateHostServerDTOs.sme}</td>
												<td align="left">${remsCreateHostServerDTOs.active}</td>
												<td align="left">${remsCreateHostServerDTOs.createdDate}</td>
												<td align="left">${remsCreateHostServerDTOs.userId}</td>
												<td align="left"><a class="hrefVisited" href="#"
													onClick="deleteServerAd(${remsCreateHostServerDTOs.id});">Delete</a>
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
								<li><a href="remsCreateHostServerListingAd?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li><a
									href="remsCreateHostServerListingAd?page=<%= currentPage-1 %>">&lt;
										Prev</a>
									<div>&lt; Prev</div> <%
			   								} else {
			   								 	if(noOfPages > 1) {
			   							%>
								<li class="disable"><a
									href="remsCreateHostServerListingAd?page=<%= 1 %>">First</a>
									<div>First</div></li>
								<li class="disable"><a
									href="remsCreateHostServerListingAd?page=<%= currentPage-1 %>">&lt;
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
								<li><a href="remsCreateHostServerListingAd?page=<%= i %>"
									id="employeeLink"><%= i %></a>
									<div><%= i %></div></li>
								<%
			    									}
			    								}
			    							}
											if(currentPage < noOfPages) {
										%>
								<li><a
									href="remsCreateHostServerListingAd?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li><a
									href="remsCreateHostServerListingAd?page=<%= noOfPages %>">Last</a>
									<div>Last</div></li>
								<%
											} else {
											    if(noOfPages > 1) {
										%>
								<li class="disable"><a
									href="remsCreateHostServerListingAd?page=<%= currentPage+1 %>">Next
										&gt;</a>
									<div>Next &gt;</div></li>
								<li class="disable"><a
									href="remsCreateHostServerListingAd?page=<%= noOfPages %>">Last</a>
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
								<div>No Host Servers are available</div>
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
menu_highlight('services_mg_host');
menu_highlight('services_mg_host_modify');

menu_highlight('All');

window.location.hash = "myid";
   function getHostDtls(id){
	   document.location.href="./remsCreateHostServer?id="+id;
   }
   
   function deleteServerAd(reqId)
   {
	   confirmation_dialogYNCancelHostServerAd(reqId,'Delete','Are you sure do you want to delete the Server ?',400,'YesNo','Yes','No');
   }
   
   $("#search_output_table").tablesorter({
	    widgets: ['zebra']
	  });
   $(".table tr:odd").css('background-color', '#ffffff');
   $(".table tr:even").addClass('even'); 
   
    
</script>
</body>
</html>