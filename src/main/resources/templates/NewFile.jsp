
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Products</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Products</h1>
			<p>All the available products in our store</p>
		</div>
	</div>
	</section>

	<div class="col-md-5">
		<img alt="${product.name }"
			src="<c:url value="/${product.id }.png"></c:url>"
			style="width: 100%" />
	</div>

</body>
</html>