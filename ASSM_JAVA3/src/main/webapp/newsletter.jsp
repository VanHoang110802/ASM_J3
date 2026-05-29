<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, com.example.entity.Newsletter" %>
<html>
<head><title>Đăng ký nhận tin</title></head>
<body>
<h2>Đăng ký nhận bản tin</h2>
<form action="newsletter" method="post">
    Email: <input type="text" name="email">
    <input type="submit" value="Đăng ký">
</form>

<% String message = (String) request.getAttribute("message");
    if (message != null) { %>
<p><%=message%>
</p>
<% } %>

<h3>Danh sách email đã đăng ký</h3>
<ul>
    <% List<Newsletter> emails = (List<Newsletter>) request.getAttribute("emails");
        if (emails != null) {
            for (Newsletter n : emails) { %>
    <li><%=n.getEmail()%> - <%=n.isEnabled() ? "Đang nhận tin" : "Ngừng nhận tin"%>
    </li>
    <% }
    } %>
</ul>
</body>
</html>

