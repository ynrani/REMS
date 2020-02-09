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

			<div class="logo">
				<img src="images/logo-cap.jpg" width="157" height="27" class="logo"
					alt="" />
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

			<div id="cssmenu">
				<ul>
					<li id="env_avb"><a href="#">Environment Availability</a></li>
					<li id="search_book"><a href="./searchApp">Search and Book</a></li>
				</ul>
			</div>
		</section>
	</header>
</section>
