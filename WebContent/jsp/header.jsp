<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@page import="com.rems.constant.AppConstant"%>
<section class="headerDiv">
	<header>
		<section class="top-blue-nav">
			<div class="primary-nav">
				<nav>
					<ul>
						<li><a href="./index"><img src="images/home-icon.png"
								width="20" height="20" alt="" /> Home</a></li>
						<li><a href="http://www.capgemini.com/about-capgemini"
							TARGET="_NEW"><img src="images/about-icon.png" width="20"
								height="20" alt="" /> About Us</a></li>
						<li><a href="http://www.in.capgemini.com/contact-capgemini"
							TARGET="_NEW"><img src="images/contact-icon.png" width="20"
								height="20" alt="" /> Contact Us</a></li>
						<li><a href="./logout?logout=true"><img
								src="images/logout-icon.png" width="20" height="20" alt="" />Logout</a></li>
					</ul>
				</nav>
			</div>
			<div class="welcome">
				<h5>
					Welcome
					<%
					out.println((String) session.getAttribute("UserName"));
				%>
				</h5>
			</div>
		</section>
		<section class="navigation">
			<div class="logo">
				<img src="images/logo-cap.jpg" width="197" height="47" class="logo"
					alt="" />
			</div>
			<div class="main-nav">
				<div id="cssmenu">
					<ul>
						<security:authorize ifAnyGranted="ROLE_ADMIN">
							<li id="admin"><a href="#">Admin</a>
								<ul>

									<li id="admin_user_mgmt"><a href="#">User Management</a>
										<ul>
											<li id="admin_user_mgmt_modify"><a href="./testdaAdmin">Modify/Remove
													Users</a></li>
											<li id="admin_user_mgmt_add"><a
												href="./tesdaCreateNewUser">Add Users</a></li>
										</ul></li>

									<li id="admin_cfg_mgmt"><a href="#">Application
											Configuration Management</a>
										<ul>
											<li id="admin_cfg_mgmt_dash"><a href="#">Dash
													boarding</a></li>
										</ul></li>

								</ul></li>
						</security:authorize>



						<li id="services"><a href="#">Manage</a>
							<ul>
								<li id="services_mg_host"><a href="#">HostServer</a>
									<ul>
										<li id="services_mg_host_modify"><a
											href="./remsCreateHostServerListing">View/Modify/Remove </a></li>
										<li id="services_mg_host_add"><a
											href="./remsCreateHostServer">Create</a></li>

									</ul></li>
								<li id="services_env_inst"><a href="#">Environment
										Instance</a>
									<ul>
										<li id="services_env_inst_modify"><a
											href="./remsCreateEnvInstanceListing">View/Modify/Remove</a></li>
										<li id="services_env_inst_add"><a
											href="./remsCreateEnvInstance">Create</a></li>

									</ul></li>
								<li id="services_env_book"><a href="#">Environment
										Booking</a>
									<ul>
										<li id="services_env_book_modify"><a
											href="./remsEnvBookingListing">View/Modify/Remove</a></li>
										<li id="services_env_book_add"><a href="./remsEnvBooking">Create</a></li>

									</ul></li>
								<security:authorize ifAnyGranted="ROLE_ADMIN">
									<li id="services_request"><a href="./remsBookingRequests">Booking
											Requests</a></li>
									<li id="services_reports"><a href="./remsReport">Reports</a></li>
								</security:authorize>
								<li id="services_calen_view"><a href="./remsCalendarView">Booking
										Calendar view</a></li>

							</ul></li>

					</ul>
				</div>
			</div>
		</section>
		<section class="title-band">
			<div class="title">
				<h3 class="h3Tdm">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3>
			</div>
		</section>
	</header>
</section>