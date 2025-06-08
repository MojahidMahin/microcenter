<%--Header inclusion--%>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%--Navigation bar inclusion--%>
<%@ include file="/WEB-INF/includes/navigation.jsp" %>

<div class="container">
    <div class="jumbotron text-center">
        <h1 class="display-4">Error Occurred</h1>
        <p class="lead">An unexpected error has occurred.</p>
        <hr class="my-4">
        <p>Sorry, we couldn't process what you requested.</p>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/home" role="button">Go to Home</a>
    </div>
</div>

<%-- Footer inclusion --%>
<%@ include file="/WEB-INF/includes/footer.jsp" %>