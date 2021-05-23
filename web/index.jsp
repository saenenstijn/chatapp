<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>

<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<main>
		<%--errors worden hier getoont--%>
<c:if test="${errors.size()>0 }">
	<div class="danger">
		<ul>
			<c:forEach var="error" items="${errors }">
				<li>${error }</li>
			</c:forEach>
		</ul>
	</div>
	<%--check of er iemand is ingelogd--%>
</c:if> <c:choose>
			<%--als er ingelogd is dan zie je onderstaande code--%>
	<c:when test="${user!=null}">
		<p>Welcome ${user.getFirstName()}!</p>


		<div style="display: flex;">
			<p style="text-align: right">status:</p>
			<div id="status" onload="getStatus();"></div>

		</div>

		<h3>Friends</h3>
		<table>
			<thead>
            <tr>
			<th>Name</th>
			<th>Status</th>
            </tr>
			</thead>
			<tbody id="friends">

			</tbody>
		</table>

		<p style="display: block">Change status: <input type="text" id="change" name="status">
			<button id="changestatus" onclick="changeStatus();" > Change </button></p>


		<button class="knop">Klik hier om een friend toe te voegen</button>

		<p hidden  class="test" >add friends: <input type="text" id="add" name="addfriend">
			<button id="addfriend" onclick="addFriends()"> add friend </button></p>



		<form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>

        <div id="chat">

		</div>
	</c:when>
			<%--als er niet ingelogd is dan zie je onderstaande code--%>
	<c:otherwise>
		<form method="post" action="Controller?action=LogIn">
			<p>
				<label for="email">Your email </label>
				<input type="text" id="email" name="email" placeholder="jan.peeters@hotmail.com">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" placeholder="t">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
		</form>
		<h3>Register here:</h3>
		<form method="post" action="Controller?action=RegisterUser">
			<p>
				<label for="fname">First name</label>
				<input type="text" id="fname" name="fname" placeholder="Jan">
			</p>
			<p>
				<label for="lname">Last name</label>
				<input type="text" id="lname" name="lname" placeholder="Peeters">
			</p>
			<p>
				<label for="emailr">email</label>
				<input type="text" id="emailr" name="emailr" placeholder="jan.peeters@hotmail.com">
			</p>
			<p>
				<label for="geslacht">Geslacht</label>
				<input type="text" id="geslacht" name="geslacht" placeholder="Male">
			</p>
			<p>
				<label for="leeftijd">Leeftijd</label>
				<input type="text" id="leeftijd" name="leeftijd" placeholder="45">
			</p>
			<p>
				<label for="passwordr">Your password</label>
				<input type="password" id="passwordr" name="passwordr" >
			</p>
			<p>
				<label for="passwordrepeat">Your password</label>
				<input type="password" id="passwordrepeat" name="passwordrepeat" >
			</p>
			<p>
				<input type="submit" id="registerbutton" value="Register">
			</p>
		</form>
		<%--blog word hier ingevoegd--%>
		<jsp:include page="blog.jsp"/>
	</c:otherwise>
</c:choose> </main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<%--alles includes van de javascript files--%>
	<script src="js/statusUser.js" type="text/javascript"></script>
	<script src="js/friends.js" type="text/javascript"></script>
	<script src="js/blog.js" type="text/javascript"></script>
	<script src="js/chat.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
</html>
