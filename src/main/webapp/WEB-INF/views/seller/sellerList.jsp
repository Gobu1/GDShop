<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>SellerList</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="/css/header.css" />
<link rel="stylesheet" href="/css/index.css" />
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>

<body>
	<section class="container" style="width: 75%">
		<c:import url="../template/top_part.jsp"></c:import>
		<c:import url="../template/header.jsp"></c:import>
		<div style="margin-top: 3em; padding-bottom: 70px">

			<div class="d-flex justify-content-center mt-4">
				<div style="width: 60%; margin-bottom: 50px">
					<h1>SellerList</h1>
				</div>
			</div>

		</div>
		<c:import url="../template/footer.jsp"></c:import>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
			crossorigin="anonymous"></script>
	</section>
	<script src="/js/seller.js"></script>
</body>
</html>