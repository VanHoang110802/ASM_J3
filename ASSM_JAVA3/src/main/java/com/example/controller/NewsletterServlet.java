package com.example.controller;

import com.example.dao.NewsletterDAO;
import com.example.dao.NewsletterDAOImpl;
import com.example.entity.Newsletter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/newsletter")
public class NewsletterServlet extends HttpServlet {
    private NewsletterDAO newsletterDAO = new NewsletterDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Hiển thị danh sách email đã đăng ký
        request.setAttribute("emails", newsletterDAO.findAll());
        request.getRequestDispatcher("newsletter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Nhận email từ form
        String email = request.getParameter("email");

        if (email != null && !email.trim().isEmpty()) {
            Newsletter n = new Newsletter();
            n.setEmail(email);
            n.setEnabled(true);

            newsletterDAO.insert(n);
            request.setAttribute("message", "Đăng ký thành công!");
        } else {
            request.setAttribute("message", "Email không hợp lệ!");
        }

        request.getRequestDispatcher("newsletter.jsp").forward(request, response);
    }
}
