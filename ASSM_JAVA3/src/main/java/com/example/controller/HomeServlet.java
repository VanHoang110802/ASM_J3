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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy danh sách loại tin để hiển thị menu
        List<Category> categories = categoryDAO.findAll();

        // Lấy 5 tin hot nhất
        List<News> hotNews = newsDAO.findHotNews(5);

        // Lấy 5 tin mới nhất
        List<News> latestNews = newsDAO.findLatestNews(5);

        // Gắn dữ liệu vào request
        request.setAttribute("categories", categories);
        request.setAttribute("hotNews", hotNews);
        request.setAttribute("latestNews", latestNews);

        // Forward sang trang home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Ví dụ: xử lý form tìm kiếm trên trang chủ
        String keyword = request.getParameter("keyword");

        List<News> searchResult = null;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // Có thể viết thêm hàm search trong NewsDAO
            searchResult = newsDAO.findByCategory(keyword);
        }

        request.setAttribute("searchResult", searchResult);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
