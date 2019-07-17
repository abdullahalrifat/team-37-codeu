<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<nav>
      <ul id="navigation">
        <li><a href="/">Home</a></li>
        <li><a href="/aboutus.html">About Our Team</a></li>
      </ul>
    </nav>
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