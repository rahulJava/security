<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<html>
<head>

<title>Ratra group of companies</title>
</head>

<body>

	<h2>Ratra company Home Page- yoooho!</h2>
	<hr>

	welcome to the ratra company homepage
	<p>Welcome to the ratra compnay home page</p>

	<hr>
	<!-- display user name and role -->
	<p>
		User:
		<security:authentication property="principal.username" />
		<br>
		<br> Roles(s):
		<security:authentication property="principal.authorities" />
	</p>

	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip
				Meeting</a> (Only for Manager Peeps)
		</p>
	</security:authorize>

	<hr>

	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">It Systems
				Meeting</a> (Only for Admin Peeps)
		</p>

	</security:authorize>

	<form:form action="${pageContext.request.contextPath}/logout"
		method="post">
		<input type="submit" value="logout" />
	</form:form>
</body>
</html>