<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
			<% String name = request.getParameter("name"); %>
			<% String address = request.getParameter("address"); %>
			<% double contact_no = Double.parseDouble( request.getParameter("contact_no")); %>
			<% String gender = request.getParameter("gender"); %>
			<% String location = request.getParameter("location"); %>
			<% double charge = Double.parseDouble(request.getParameter("charge")); %>

			<tbody>
				<tr>
					<td> <%=name%> </td>
					<td> <%=address%> </td>
					<td> <%=contact_no%> </td>
					<td> <%=gender%> </td>
					<td> <%=location%> </td>
					<td> <%=charge%> </td>
				</tr>
			</tbody>
		</table>