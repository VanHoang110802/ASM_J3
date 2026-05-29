package com.example.controller;

import com.example.dao.NewsDAO;
import com.example.dao.NewsDAOImpl;
import com.example.entity.News;
import com.example.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
    private NewsDAO newsDAO = new NewsDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<News> list = newsDAO.findAll();
        req.setAttribute("newsList", list);
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            News n = new News();
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            n.setImage("images/" + req.getParameter("image"));
            n.setCategoryId(req.getParameter("categoryId"));
            n.setAuthor(((User) req.getSession().getAttribute("user")).getId());
            n.setPostedDate(new Date());
            newsDAO.insert(n);

        } else if ("update".equals(action)) {
            String id = req.getParameter("id");
            News n = newsDAO.findById(id);
            n.setTitle(req.getParameter("title"));
            n.setContent(req.getParameter("content"));
            newsDAO.update(n);

        } else if ("delete".equals(action)) {
            String id = req.getParameter("id");
            newsDAO.delete(id);
        }

        resp.sendRedirect("news");
    }
}
