<%--Header inclusion--%>
<%@include file="includes/header.jsp"%>
<%--Navigation bar inclusion--%>
<%@include file="includes/navigation.jsp"%>

<div class="container">
    <div class="jumbotron text-center">
        <h1 class="display-4">Page Not Found</h1>
        <p class="lead">The page you are looking for does not exist.</p>
        <hr class="my-4">
        <p>It seems we can't find what you're looking for. You might want to check the URL or return to the home page.</p>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/home" role="button">Go to Home</a>
    </div>
</div>

<%-- Footer inclusion --%>
<%@include file="includes/footer.jsp"%>