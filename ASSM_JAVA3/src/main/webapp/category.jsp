<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.entity.News" %>
<html>
<head><title>Tin theo loại</title></head>
<body>
<h2>Danh sách tin theo loại</h2>
<ul>
    <% List<News> newsByCategory = (List<News>) request.getAttribute("newsByCategory");
        if (newsByCategory != null) {
            for (News n : newsByCategory) { %>
    <li><a href="detail?id=<%=n.getId()%>"><%=n.getTitle()%>
    </a></li>
    <% }
    } else { %>
    <li>Không có tin nào trong loại này.</li>
    <% } %>
</ul>
</body>
</html>

