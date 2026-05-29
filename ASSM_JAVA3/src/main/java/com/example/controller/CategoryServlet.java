package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.dao.CategoryDAOImpl;
import com.example.dao.NewsDAO;
import com.example.dao.NewsDAOImpl;
import com.example.entity.Category;
import com.example.entity.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Nhận categoryId từ URL
        String categoryId = request.getParameter("id");

        // Lấy danh sách loại tin để hiển thị menu
        List<Category> categories = categoryDAO.findAll();

        // Lấy danh sách tin theo loại
        List<News> newsByCategory = null;
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            newsByCategory = newsDAO.findByCategory(categoryId);
        }

        // Gắn dữ liệu vào request
        request.setAttribute("categories", categories);
        request.setAttribute("newsByCategory", newsByCategory);

        // Forward sang category.jsp
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ví dụ: xử lý form lọc tin theo loại
        String categoryId = request.getParameter("categoryId");

        List<News> newsByCategory = null;
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            newsByCategory = newsDAO.findByCategory(categoryId);
        }

        request.setAttribute("newsByCategory", newsByCategory);
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }
}
