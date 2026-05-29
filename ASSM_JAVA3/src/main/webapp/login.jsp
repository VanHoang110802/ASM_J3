<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:15 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Đăng nhập</title></head>
<body>
<h2>Đăng nhập hệ thống</h2>
<form action="login" method="post">
    <label for="id">Tài khoản:</label>
    <input type="text" name="id" id="id"><br><br>

    <label for="password">Mật khẩu:</label>
    <input type="password" name="password" id="password"><br><br>

    <input type="submit" value="Đăng nhập">
</form>

<% String message = (String) request.getAttribute("message");
    if (message != null) { %>
<p style="color:red;"><%=message%>
</p>
<% } %>
</body>
</html>
