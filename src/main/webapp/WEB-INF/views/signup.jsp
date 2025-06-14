<%--Header inclusion--%>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%--Navigation bar inclusion--%>
<%@include file="/WEB-INF/includes/navigation.jsp" %>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mt-4 text-center">Sign Up</h2>
            <form role="form" action="<c:url value='/signup' />" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control form-control-md" id="username" value="${userDTO.getUsername()}" name="username" required>
                    <c:if test="${errors.Username != null}">
                        <small class="text-danger mt-2">
                            ${errors.Username}
                        </small>

                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control form-control-md" id="firstName" value="${userDTO.getFirstName()}" name="firstName" required>
                    <c:if test="${errors.FirstName != null}">
                        <small class="text-danger mt-2">
                            ${errors.FirstName}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control form-control-md" id="lastName" value="${userDTO.getLastName()}" name="lastName" required>
                    <c:if test="${errors.LastName != null}">
                        <small class="text-danger mt-2">
                            ${errors.LastName}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control form-control-md" id="email" value="${userDTO.getEmail()}" name="email" required
                           placeholder="you@example.com">
                    <c:if test="${errors.Email != null}">
                        <small class="text-danger mt-2">
                            ${errors.Email}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control form-control-md" id="password" name="password" required>
                    <c:if test="${errors.Password != null}">
                        <small class="text-danger mt-2">
                            ${errors.Password}
                        </small>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="passwordConfirm" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control form-control-md" id="passwordConfirm"
                           name="passwordConfirm" required>
                    <c:if test="${errors.PasswordConfirmed != null}">
                        <small class="text-danger mt-2">
                            ${errors.PasswordConfirmed}
                        </small>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary w-100">Sign Up</button>
            </form>
        </div>
    </div>
</div>

<%-- Footer inclusion --%>
<%@ include file="/WEB-INF/includes/footer.jsp" %>