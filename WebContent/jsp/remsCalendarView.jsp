<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>REMS | Calendar</title>
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

<script type="text/javascript" src="js/fullcalendar.js"></script>
<script type="text/javascript" src="js/fullcalendar.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/fullcalendar.css">
<link type="text/css" rel="stylesheet"
	href="css/fullcalendar.print_.css">



<style>
#calendar {
	width: 900px;
	margin: 0 auto;
}
</style>
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


			<h2 style="color: #0098cc; font-size: 14px;">Calendar View</h2>
			<hr>

			<div class="two-col">
				<form:form id="remsCalendarViewForm" name="remsCalendarViewForm"
					action="${pageContext.request.contextPath}/remsCalendarView">
					<div class="container-fluid">
						<div style="clear: both;"></div>
						<div id="loading" style="display: none;">loading</div>
						<div id="calendar" style="width: 80%; margin: 0 auto;"
							class="fc fc-ltr"></div>
						<!--end of calender-->
					</div>
				</form:form>
			</div>
		</div>
	</div>

	<script>

menu_highlight('services');
menu_highlight('services_calen_view');


$(document).ready(function() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
  $.getJSON('./remsCalView', function (data) {
    var calendar = $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            var title = prompt('Event Title:');
            if (title) {
                calendar.fullCalendar('renderEvent',
                {
                    title: title,
                    start: start,
                    end: end,
                    allDay: allDay
                },
                        true // make the event "stick"
                        );
            }
            calendar.fullCalendar('unselect');
        },
        editable: true,
        events:data
    });
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
