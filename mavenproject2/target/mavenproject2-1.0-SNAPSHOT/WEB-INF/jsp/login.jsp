<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<style>
    html {
      font-family: sans-serif;
      background:#bada55;
	  box-sizing: border-box;
      background-size:cover;
      min-height:100vh;
      display:flex;
      justify-content: center;
      align-items: center;
      text-align: center;
    }
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Custom Login Form (XML)</h1>

	<div id="login-box">

		<h2>Login with Username and Password</h2>

		<c:if test="${not empty error}">
			<div class="error">Error</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">Not Empty</div>
		</c:if>
                        <c:url value="/j_spring_security_check" var="loginUrl" />
		<form name='loginForm'
		  action="${loginUrl}" method='POST'>

		  <table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
                            <td colspan='2'><center><input name="submit" type="submit"
                                                       value="Submit" /></center></td>
			</tr>
		  </table>

		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>