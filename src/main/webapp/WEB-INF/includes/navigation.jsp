<%@ taglib prefix="sec" uri="http://microcenter.com/functions" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/" />"> MicroCenter</a>

        <!-- Toggle button for responsive navigation -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse align-middle" id="navbarNav">
            <ul class="navbar-nav ms-auto"> <%--in bootstrap 5 ml-auto is replaced by ms-auto--%>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home</a>
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
                <c:choose>
                    <c:when test="${sec:isAuthenticated(pageContext.request)}">
                        <a class="nav-link" href="#" onclick="logout()">Logout
                        [${sec:getCurrentUser(pageContext.request).firstName}]</a>
                        <script>
                            function logout() {
                                document.getElementById("logoutForm").submit();
                            }
                        </script>

                        <form style="visibility: hidden" id="logoutForm" action="<c:url value='/logout' />" method="POST"></form>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="<c:url value='/login' />">Login</a>
                    </c:otherwise>
                </c:choose>
            </ul>
            <form class="d-flex mb-0">
                <input class="form-control me-2 align-" type="search" placeholder="Search products..." aria-label="Search">
                <button class="btn btn-outline-light align-middle" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>