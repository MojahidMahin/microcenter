<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/includes/header.jsp" %>
<%@include file="/WEB-INF/includes/navigation.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mt-4 text-center">Login</h2>
            <form action="<c:url value="/login" />" method="post">
                <div class="mb-3 form-group">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" value="${loginDTO.username}" required>
                    <c:if test="${errors.username != null}">
                        <small class="text-danger">
                                ${errors.username}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3 form-group">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <c:if test="${errors.password != null}">
                        <small class="text-danger">
                                ${errors.password}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3 form-group">
                    <label for="rememberMe" class="form-label">Remember Me:</label>
                    <input type="checkbox" id="rememberMe" name="rememberMe">

                </div>
                <div class="form-group d-flex">
                    <button type="submit" class="m-2 btn btn-primary w-50">Login</button>
                    <a href="<c:url value="/signup" />" class="m-2 btn btn-primary w-50">Register</a>
                </div>
            </form>
        </div>
    </div>
</div>

        <%@include file="/WEB-INF/includes/footer.jsp" %>


