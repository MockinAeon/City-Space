<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>myCity</title>
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
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/indexresponsive.css">
</head>

<body>
	<video id="video" muted autoplay loop>
		<source src="resources/pic/city.mp4">
	</video>

	<form method="post" id="loginform" action="welcome.htm">
		<div id="login">
			<div id="first">
				<div id="img">
					<img src="resources/pic/logo.png" alt="logo">
				</div>
			</div>
			<div id="second">
				<div id="redtxt">City Space Manage</div>
				<div id="myneutxt">myCity</div>

			</div>

			<div id="enterpart">
				<div class="user">
					<input id="username" type="text" name="username"
						placeholder="UserName" tabindex="1">
				</div>
				<div class="user">
					<input id="password" type="password" name="password"
						placeholder="Password" tabindex="1">
				</div>
				<div class="user">
					<div class="errormsg">Wrong password or username does not exist</div>
				</div>
			</div>
			<div id="third">
				<div class="smalltxt">
					<a data-toggle="modal" data-target="#create" href="" tabindex="4">No
						account? Create one right now!</a>
				</div>
				<input type="hidden" value="login" name="action" />
				<input id="loginbtn" type="submit" value="login" name="action"
					tabindex="3" />
			</div>

		</div>
	</form>
	<div class="modal fade" tabindex="-1" role="dialog" id="create">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Create Your Account</h3>
				</div>
				<div class="modal-body">

					<form action="createaccount.htm" method="post" id="createguestform" >
						<div class="form-group">
							<div class="form-group">
								<label for="fullname">Full Name</label> <input type="text"
									name="fullname" class="form-control input-text" required />
							</div>
							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									name="username" class="form-control input-text" required />
							</div>
							<div class="form-group">
								<label for="password">Create the password</label> <input
									type="password" name="password" class="form-control input-text" required />
							</div>
							<div class="form-group">
								<label for="password2">Confirm your password </label> <input
									type="password" name="password2" class="form-control input-text" required />
							</div>
							<div class="errormsg1">This account has been added</div>
							<div class="errormsg2">Password does not match</div>
							<button type="submit" id="addguestbtn" class="btn btn-default">Create</button>
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
	<div class="modal fade" tabindex="-1" role="dialog" id="add-success">
		<div class="modal-dialog">
			<div class="modal-content action-success">


				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">Create Account Succefully!</h3>
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
	
	<script src="resources/js/script.js">
		
	</script>

</body>

</html>