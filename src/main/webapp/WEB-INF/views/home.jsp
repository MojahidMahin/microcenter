<%@ page import="java.util.List" %>
<%@ page import="com.microcenter.web.dto.ProductDTO" %>

<%--Header inclusion--%>
<%@ include file="/WEB-INF/includes/header.jsp" %>

<%--Navigation bar inclusion--%>
<%@include file="/WEB-INF/includes/navigation.jsp" %>

<%--taglib inclusion--%>
<%@taglib prefix="sec" uri="http://microcenter.com/functions" %>

<div class="container">
    <div class="d-flex align-items-center jumbotron">
        <img src="<c:url value="/image/micro_center.png"/>" style="height: 100px" class="me-3 img-fluid"
             alt="MicroCenter Logo" />
<%--        <c:if test="${sessionScope.user != null}">--%>
        <c:if test="${sec:isAuthenticated(pageContext.request)}">
<%--            <h1 class="display-4">Hello, <c:out value="${sessionScope.user.getUsername()}"/>!</h1>--%>
            <h1 class="display-4">Hello, <c:out value="${sec:getCurrentUser(pageContext.request).firstName}"/>!</h1>
        </c:if>
        <h1 class="display-4 pt-1 pb-2">Welcome to MicroCenter!</h1>
    </div>
    <div class="d-flex flex-wrap">
        <c:forEach items="${products}" var="product">
            <div class="col-12 col-sm-6 col-md-4 text-black me-3 card h-150 mb-4 flex-column card-body">
                <h5 class="card-title">
                    <c:out value="${product.name}"/>
                </h5>
                <p class="card-text">
                    <c:out value="${product.description}"/>
                </p>
                <p class="card-text">
                    Price: $<c:out value="${product.price}"/>
                </p>
                <p class="card-text">
                    Category: <c:out value="${product.category}"/>
                </p>
                <a href="#" class="card-link btn btn-outline-info">
                    Add to Cart
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<%-- Footer inclusion --%>
<%@ include file="/WEB-INF/includes/footer.jsp" %>