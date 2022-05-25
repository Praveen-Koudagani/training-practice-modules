<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="createAccount.css">
<title>Account Operations</title>

</head>
<body>
	<center>
		<div class="mainblock">

			<form name="master" method="post" action="/createAccount2">
				<div align="center">
					<table>
						<tr>
							<td>Group:</td>
							<td colspan="3"><input id="group" type="text"
								name="groupname" required="required" /></td>
						</tr>

						<tr>
							<td>URL:</td>
							<td colspan="3"><input id="url" type="text" name="url"
								required="required" /></td>
						</tr>


						<tr>
							<td>UserId:</td>
							<td colspan="3"><input id="username" type="text"
								name="username" required="required" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td colspan="3"><input id="pwd" type="password"
								name="password" required="required" /></td>
						</tr>

					</table>
					<br> <br>
					<p>
						<input type="submit" value="Add Account" /> <br> <br> <a
							href="/taskmenu">Go to TaskMenu</a>
					</p>
				</div>
			</form>
		</div>
		<div id="message">
			<label id="letter" class="invalid"> A <b>lowercase</b> letter
			</label> <label id="capital" class="invalid"> A <b>capital
					(uppercase)</b> letter
			</label> <label id="number" class="invalid"> A <b>number</b>
			</label> <label id="length" class="invalid"> Minimum <b>8
					characters</b>
			</label>
		</div>
		<div id="urlmessage"></div>

	</center>
	<script>
		var myInput = document.getElementById("pwd");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");
		var urlinput = document.getElementById("url");

		// When the user clicks on the password field, show the message box
		myInput.onfocus = function() {
			document.getElementById("message").style.display = "block";
		}

		// When the user clicks outside of the password field, hide the message box
		myInput.onblur = function() {
			document.getElementById("message").style.display = "none";
		}

		// When the user starts to type something inside the password field
		myInput.onkeyup = function() {
			// Validate lowercase letters
			var lowerCaseLetters = /[a-z]/g;
			if (myInput.value.match(lowerCaseLetters)) {
				letter.classList.remove("invalid");
				letter.classList.add("valid");
			} else {
				letter.classList.remove("valid");
				letter.classList.add("invalid");
			}

			// Validate capital letters
			var upperCaseLetters = /[A-Z]/g;
			if (myInput.value.match(upperCaseLetters)) {
				capital.classList.remove("invalid");
				capital.classList.add("valid");
			} else {
				capital.classList.remove("valid");
				capital.classList.add("invalid");
			}

			// Validate numbers
			var numbers = /[0-9]/g;
			if (myInput.value.match(numbers)) {
				number.classList.remove("invalid");
				number.classList.add("valid");
			} else {
				number.classList.remove("valid");
				number.classList.add("invalid");
			}

			// Validate length
			if (myInput.value.length >= 8) {
				length.classList.remove("invalid");
				length.classList.add("valid");
			} else {
				length.classList.remove("valid");
				length.classList.add("invalid");
			}
		}

		urlinput.onblur = function() {
			document.getElementById("urlmessage").style.display = "none";
		}
		urlinput.onkeypress= function() {
			var urltext = urlinput.value;
			if (urltext.length <8 || urltext.substring(0,8)!="https://") {
				document.getElementById("urlmessage").innerHTML ="Inavlid URL";
				document.getElementById("urlmessage").style.display = "block";
			}else{
				document.getElementById("urlmessage").style.display = "none";
			}

		}
	</script>
</body>
</html>