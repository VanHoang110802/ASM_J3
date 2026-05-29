<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:56 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.entity.News" %>
<html>
<head><title>Phóng viên - Quản lý tin tức</title></head>
<body>
<h2>Phóng viên - Quản lý tin tức</h2>
<a href="logout">Đăng xuất</a>

<!-- Form thêm tin mới -->
<form action="news" method="post">
  <input type="hidden" name="action" value="create">
  Tiêu đề: <input type="text" name="title"><br>
  Nội dung: <textarea name="content"></textarea><br>
  Ảnh (tên file): <input type="text" name="image"><br>
  Loại tin: <input type="text" name="categoryId"><br>
  <input type="submit" value="Thêm tin mới">
</form>

<hr>

<!-- Danh sách tin của phóng viên -->
<table border="1" cellpadding="5">
  <tr><th>ID</th><th>Tiêu đề</th><th>Ngày đăng</th></tr>
  <%
    List<News> list = (List<News>) request.getAttribute("newsList");
    if (list != null) {
      for (News n : list) {
        if (n.getAuthor().equals(((com.example.entity.User)session.getAttribute("user")).getId())) {
  %>
  <tr>
    <td><%=n.getId()%></td>
    <td><%=n.getTitle()%></td>
    <td><%=n.getPostedDate()%></td>
  </tr>
  <%
        }
      }
    }
  %>
</table>
</body>
</html>
