<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome</title>
<!-- Latest compiled and minified JavaScript -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<link rel="stylesheet" href="resources/css/user-home.css">
<link rel="stylesheet" href="resources/css/guest.css">
</head>

<body>
	<div class="wrapper">
		<div class="container-fluid">
			<div class="row top-bar">
				<div class="col-sm-6 col-xs-6">
					<div class="col-xs-offset-2 col-sm-3">
						<div id="logoimg">
							<img src="resources/pic/logo.png" alt="logoimg" />
						</div>
					</div>
				</div>

				<div class="col-sm-6 col-xs-6">
					<div class="row icon-part">
						<div id="logout-part">
							<form method="post" action="welcome-logout.htm">
								<a href="GuestRoleAccount">Check My Account</a>
								<button id="logout-btn" type="submit" name="action"
									value="logout" class="btn btn-default">Logout</button>
							</form>
						</div>

						<div class="col-sm-2 col-xs-2"></div>
						<div class="col-sm-2 col-xs-2"></div>
						<div class="col-xs-offset-6 col-sm-3 col-xs-3">
							<div class="icon-part-icon">
								<a id="login"><i class="fa fa-user fa-2x"></i> <span>
										Hello <c:out value="${sessionScope.name}"></c:out>
								</span></a>
							</div>

						</div>

					</div>

				</div>
			</div>
			<div class="row">
				<nav id="nav-bar" class="navbar navbar-default">
					<div class="container-fluid">

						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">


							<ul class="nav navbar-nav navbar-right">
								<li><a
									href="Home">Home</a></li>
								<li><a href="CheckSpace">Check All
										Space</a></li>
								<li><a href="Subscription">Manage
										Subscription</a></li>
								<li id="active" class="active"><a href="Reservation">Manage
										Reservation</a></li>

							</ul>
						</div>
						<div id="mouseout"></div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
		</div>

		<div class="container">
			<div class="row current-space">
				<c:choose>
					<c:when test="${!empty sessionScope.name.usingSpace}">
						<h4>Now you are using:
							${sessionScope.name.usingSpace.floorname.buildingname.enterprisename.cityname}
							>
							${sessionScope.name.usingSpace.floorname.buildingname.enterprisename}
							> ${sessionScope.name.usingSpace.floorname.buildingname} >
							${sessionScope.name.usingSpace.floorname} >
							${sessionScope.name.usingSpace}</h4>
						<button id="leavespace-btn" class="btn btn-default">Leave
							It</button>
					</c:when>
					<c:otherwise>
						<h4>You are not using any space now.</h4>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="row">
				
				<div class="button-part">
					<h2>Your Reservation</h2>
					<button class="btn btn-default" id="update-btn"
						data-toggle="modal" data-target="">Update</button>
						<button class="btn btn-default" id="update-modal-btn"
						data-toggle="modal" data-target="#update"></button>
					<button class="btn btn-default" id="cancel-btn"
						data-toggle="modal" data-target="">Cancel</button>
						<button class="btn btn-default" id="cancel-modal-btn"
						data-toggle="modal" data-target="#cancel"></button>
				</div>
				<span class="tips tips1">Please select a space.</span>
				<span class="tips tips2">You cannot cancel this reservation.</span>
				<span class="tips tips3">You cannot update this reservation.</span>
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th>City</th>
							<th>Enterprise</th>
							<th>Building</th>
							<th>Floor</th>
							<th>Space</th>
							<th>Date</th>
							<th>Start Time</th>
							<th>End Time</th>
							<th>Process By</th>
							<th>Reject/Cancel Reason</th>
							<th>Reservation Status</th>
							<th></th>
						</tr>
					</thead>
					<c:forEach var="reservation"
						items="${requestScope.reservationlist}">
						<tr>
							<td>${reservation.spacename.floorname.buildingname.enterprisename.cityname}</td>
							<td>${reservation.spacename.floorname.buildingname.enterprisename}</td>
							<td>${reservation.spacename.floorname.buildingname}</td>
							<td>${reservation.spacename.floorname}</td>
							<td>${reservation.spacename}</td>
							<td>${reservation.reserveDay}</td>
							<td>${reservation.startTime}</td>
							<td>${reservation.endTime}</td>
							<td>${reservation.processPerson}</td>
							<td>${reservation.rejectedReason}${reservation.cancelReason}</td>
							<td>${reservation.reservationStatus}</td>
							<td><input type="radio" name="selectreservation"
								value="${reservation.id}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			

		</div>
		<!--end container-->
		<div class="row foot-part"></div>
		<!--			end foot-part-->
	</div>
	<!--end wrapper-->
	
	<div class="modal fade" tabindex="-1" role="dialog" id="cancel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Cancel Reason</h3>
				</div>
				<div class="modal-body">

					<form action="reject.htm" method="post" id="cancelform">
						<div class="form-group">

							<h5 class="show-reserve-info"></h5>
							<div class="form-group">
								<label for="name">Write reason here</label> <input type="text"
									class="form-control input-text" name="reason" />
							</div>
							
							<input type="hidden" name="reservationid" id="reservationid" />
							<button type="submit" id="cancelbtn" class="btn btn-default">Cancel</button>
						</div>
					</form>
					<button id="add-success-btn" type="button"
						class="btn btn-md btn-green" data-dismiss="modal"
						data-toggle="modal" data-target="#add-success"></button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	
	
	<div class="modal fade" tabindex="-1" role="dialog" id="update">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Update a Reservation</h3>
				</div>
				<div class="modal-body">

					<form action="update.htm" method="post" id="updateform">
						<div class="form-group">

							<h5 class="show-reserve-info"></h5>
							<div class="form-group">
								<label for="name">Reserve Date</label> <input type="date"
									class="form-control input-text" name="reserveday" />
							</div>
							<div class="form-group">
								<label for="name">Start Time</label> <input type="time"
									class="form-control input-text" name="starttime" />
							</div>
							<div class="form-group">
								<label for="name">End Time</label> <input type="time"
									class="form-control input-text" name="endtime" />
							</div>
							<div class="errormsg time-wrong">You should enter right
								time.</div>
							<input type="hidden" name="reservationid" id="reservationid2" />

							<button type="submit" id="updatebtn" class="btn btn-default">Update</button>
						</div>
					</form>
					<button id="update-success-btn" type="button"
						class="btn btn-md btn-green" data-dismiss="modal"
						data-toggle="modal" data-target="#add-success"></button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
		
	</div>
	<div class="modal fade" tabindex="-1" role="dialog" id="add-success">
		<div class="modal-dialog">
			<div class="modal-content action-success">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Cancel successfully.</h3>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-md btn-default"
						data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script src="resources/js/user-home.js"></script>
	<script src="resources/js/guest-reservation.js"></script>
</body>

</html>