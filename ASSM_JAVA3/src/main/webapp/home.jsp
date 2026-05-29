<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:12 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.entity.News, com.example.entity.Category" %>
<html>
<head><title>Trang chủ</title></head>
<body>
<h2>Danh sách loại tin</h2>
<ul>
    <% List<Category> categories = (List<Category>) request.getAttribute("categories");
        if (categories != null) {
            for (Category c : categories) { %>
    <li><a href="category?id=<%=c.getId()%>"><%=c.getName()%>
    </a></li>
    <% }
    } %>
</ul>

<h2>Tin hot</h2>
<ul>
    <% List<News> hotNews = (List<News>) request.getAttribute("hotNews");
        if (hotNews != null) {
            for (News n : hotNews) { %>
    <li><a href="detail?id=<%=n.getId()%>"><%=n.getTitle()%>
    </a> - Views: <%=n.getViewCount()%>
    </li>
    <% }
    } %>
</ul>

<h2>Tin mới</h2>
<ul>
    <% List<News> latestNews = (List<News>) request.getAttribute("latestNews");
        if (latestNews != null) {
            for (News n : latestNews) { %>
    <li><a href="detail?id=<%=n.getId()%>"><%=n.getTitle()%>
    </a> - Ngày: <%=n.getPostedDate()%>
    </li>
    <% }
    } %>
</ul>
</body>
</html>