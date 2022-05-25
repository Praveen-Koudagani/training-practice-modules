<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="index.css">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<div class="login">
			<form name="master" method="post" action="/addMaster2">
				<label for="usrname">Username</label> <input type="text"
					id="usrname" name="username" required> <br> <label
					for="psw">Password</label> <input type="password" id="psw"
					name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
					title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
					required><br><center>
					<p id="message">
						<label id="letter" class="invalid"> A <b>lowercase</b>
							letter
						</label> <label id="capital" class="invalid"> A <b>capital
								(uppercase)</b> letter
						</label> <label id="number" class="invalid"> A <b>number</b>
						</label> <label id="length" class="invalid"> Minimum <b>8
								characters</b>
						</label>
					</p></center>
				
				<input type="submit" value="register"><br> <a
					href="/">login</a>

			</form>
		</div>


	</div>
	<script type="text/javascript">
		var myInput = document.getElementById("psw");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");

		myInput.onfocus = function() {
			document.getElementById("message").style.display = "block";
		}

		
		myInput.onblur = function() {
			document.getElementById("message").style.display = "none";
		}

	
		myInput.onkeyup = function() {
			// Validate lowercase letters
			var status=true;
			var lowerCaseLetters = /[a-z]/g;
			if (myInput.value.match(lowerCaseLetters)) {
				letter.classList.remove("invalid");
				letter.classList.add("valid");
			} else {
				letter.classList.remove("valid");
				letter.classList.add("invalid");
				status=false;
			}

			// Validate capital letters
			var upperCaseLetters = /[A-Z]/g;
			if (myInput.value.match(upperCaseLetters)) {
				capital.classList.remove("invalid");
				capital.classList.add("valid");
			} else {
				capital.classList.remove("valid");
				capital.classList.add("invalid");
				status=false;
			}

			// Validate numbers
			var numbers = /[0-9]/g;
			if (myInput.value.match(numbers)) {
				number.classList.remove("invalid");
				number.classList.add("valid");
			} else {
				number.classList.remove("valid");
				number.classList.add("invalid");
				status=false;
			}

			// Validate length
			if (myInput.value.length >= 8) {
				length.classList.remove("invalid");
				length.classList.add("valid");
			} else {
				length.classList.remove("valid");
				length.classList.add("invalid");
				status=false;
			}
			if(status){
				document.getElementById("message").style.display = "none";
			}
		}
	</script>


</body>
</html>


