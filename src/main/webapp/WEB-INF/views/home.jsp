<%@ page import="java.util.List" %>
<%@ page import="com.microcenter.web.dto.ProductDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Products</title>
</head>
<body>
    <% List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products"); %>

    <table>
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <% for (ProductDTO product : products) { %>
                <tr>
                    <td><%= product.getProductId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getDescription() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
