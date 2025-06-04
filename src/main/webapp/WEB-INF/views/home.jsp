<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %>
<%@ page import="com.microcenter.web.dto.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MicroCenter</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.1/css/bootstrap.min.css" />
</head>
<body class="pt-5 bg-dark text-white">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="<c:url value="/" />"> MicroCenter</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Categories</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Deals</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search products..." aria-label="Search">
                    <button class="btn btn-outline-light" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="d-flex align-items-center jumbotron">
            <img src="<c:url value="/image/micro_center.png"/>" style="height: 100px" class="me-3 img-fluid" alt="MicroCenter Logo">
            <h1 class="display-4 pt-1 pb-2">Welcome to MicroCenter!</h1>
        </div>
        <div class="d-flex flex-wrap">
            <c:forEach items="${products}" var="product">
                <div class="col-12 col-sm-6 col-md-4 text-black me-3 card h-150 mb-4 flex-column card-body">
                    <h5 class="card-title">
                        <c:out value="${product.name}" />
                    </h5>
                    <p class="card-text">
                        <c:out value="${product.description}" />
                    </p>
                    <p class="card-text">
                        Price: $<c:out value="${product.price}" />
                    </p>
                    <p class="card-text">
                        Category: <c:out value="${product.category}" />
                    </p>
                    <a href="#" class="card-link btn btn-outline-info">
                        Add to Cart
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

    <footer class="text-white mt-5">
        <div class="container">
            <span>Copyright &copy; 2025 MicroCenter</span>
        </div>
    </footer>

</body>
</html>
