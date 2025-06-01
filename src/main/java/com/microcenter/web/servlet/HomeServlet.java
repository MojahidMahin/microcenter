package com.microcenter.web.servlet;

import com.microcenter.web.dto.ProductDTO;
import com.microcenter.web.repository.DummyProductRepositoryImpl;
import com.microcenter.web.service.ProductService;
import com.microcenter.web.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl(new DummyProductRepositoryImpl());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ProductDTO> allProducts = productService.findAllProductsSortedByName();

        req.setAttribute("products", allProducts);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
