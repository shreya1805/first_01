<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.lti.training.maven.model.Product" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Pagination Project</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/mdb.min.css" />
	</head>
	<body>
		<div class="container-fluid">
		<h2 class="h2-responsive text-center p-3">Displaying Data from LocalDB/ProductionDB using Servlet</h2>
			<form method="post" action="ProductControllerServletMain" class="form table-responsive" >
				<table class="table-striped table">
						<thead>
							<tr>
								<th><b>ID</b></th>
								<th><b>Name</b></th>
								<th><b>Price</b></th>
								<th><b>Quantity</b></th>
								</tr>
						</thead>
						<tbody>
								<% 
									List<Product> products = (List<Product>) request.getAttribute("currentProducts");
									for(Product product: products){
								%>
								<tr>
									<td><%=product.getId() %></td>
									<td><%=product.getName() %></td>
									<td><%=product.getPrice() %></td>
									<td><%=product.getQuantity() %></td>
								</tr>
								<%
									}
								%>
						</tbody>
				</table>
				<div style="text-align:center">
					<button type="button" name="next" class="btn btn-default btn-md" onclick="go('prev')"/>Previous</button>
					<button type="button" name="previous" class="btn btn-default btn-md" onclick="go('next')" />Next</button>
				</div>
			</form>
		</div>
	</body>
	<script>
	function go(page){
		//window.location.href="ProductControllerServletMain?go=" +page; // url of my controller(servlet)
		window.location.href="ProductControllerServlet?go=" +page; // url of my controller(servlet)
	}
	
	</script>
</html>