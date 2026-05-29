<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:55 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.entity.News" %>
<html>
<head><title>Admin - Quản lý tin tức</title></head>
<body>
<h2>Admin - Quản lý tin tức</h2>
<a href="logout">Đăng xuất</a>

<form action="news" method="post">
    <input type="hidden" name="action" value="create">
    Tiêu đề: <input type="text" name="title"><br>
    Nội dung: <textarea name="content"></textarea><br>
    Ảnh (tên file): <input type="text" name="image"><br>
    Loại tin: <input type="text" name="categoryId"><br>
    <input type="submit" value="Thêm tin mới">
</form>

<hr>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>Tác giả</th>
        <th>Ngày đăng</th>
        <th>Thao tác</th>
    </tr>
    <%
        List<News> list = (List<News>) request.getAttribute("newsList");
        if (list != null) {
            for (News n : list) {
    %>
    <tr>
        <td><%=n.getId()%>
        </td>
        <td><%=n.getTitle()%>
        </td>
        <td><%=n.getAuthor()%>
        </td>
        <td><%=n.getPostedDate()%>
        </td>
        <td>
            <form action="news" method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%=n.getId()%>">
                <input type="submit" value="Sửa">
            </form>
            <form action="news" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%=n.getId()%>">
                <input type="submit" value="Xóa">
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
