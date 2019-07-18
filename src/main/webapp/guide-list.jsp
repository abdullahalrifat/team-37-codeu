<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Guide Registration</title>
	<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}

input[type=text] {
  width: 40%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
}

input[type=submit] {
  width: 40%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
}

select{
	width: 40%;
	padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
}
</style>
</head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<body>

		<ul>
			<li><a href="/">Home</a></li>
			<li><a href="/aboutus.html">About Our Team</a></li>
			<li><a href="/guide-reg.html">Guide Registration</a></li>
		</ul>

		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Address</th>
					<th scope="col">Contact No</th>
					<th scope="col">Gender</th>
					<th scope="col">Location</th>
					<th scope="col">Charge</th>
				</tr>
			</thead>


			<tbody>
				<%
				int guidesCount = (int) request.getAttribute("guidesCount");
				for(int i = 0; i < guidesCount; i++) {
				%>
				    <tr>
				        <td>${guides.get(i).getName()}</td>
				        <td>${guides.get(i).getAddress()}</td>
				        <td>${guides.get(i).getContactNo()}</td>
				        <td>${guides.get(i).getGender()}</td>
				        <td>${guides.get(i).getLocation()}</td>
				        <td>${guides.get(i).getCharge()}</td>
				    </tr>
				<% } %>
			</tbody>
		</table>
</body>
</html>