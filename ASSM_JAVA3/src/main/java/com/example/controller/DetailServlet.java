package com.example.controller;
import com.example.dao.NewsDAO;
import com.example.dao.NewsDAOImpl;
import com.example.entity.News;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Nhận newsId từ URL
        String newsId = request.getParameter("id");

        News news = null;
        if (newsId != null && !newsId.trim().isEmpty()) {
            news = newsDAO.findById(newsId);

            // Tăng viewCount (bạn có thể viết thêm hàm updateViewCount trong NewsDAO)
            // Ví dụ: newsDAO.updateViewCount(newsId);
        }

        request.setAttribute("news", news);
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu có form comment hoặc feedback cho bài viết
        String comment = request.getParameter("comment");
        request.setAttribute("message", "Cảm ơn bạn đã bình luận: " + comment);

        // Forward lại sang detail.jsp
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }
}
