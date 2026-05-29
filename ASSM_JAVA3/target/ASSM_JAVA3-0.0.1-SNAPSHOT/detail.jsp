<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 29/05/2026
  Time: 11:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.entity.News" %>
<html>
<head><title>Chi tiết tin</title></head>
<body>
<% News news = (News) request.getAttribute("news");
    if (news != null) { %>
<h2><%=news.getTitle()%>
</h2>
<p><b>Tác giả:</b> <%=news.getAuthor()%>
</p>
<p><b>Ngày đăng:</b> <%=news.getPostedDate()%>
</p>
<p><b>Lượt xem:</b> <%=news.getViewCount()%>
</p>
<p><%=news.getContent()%>
</p>
<img src="<%=news.getImage()%>" alt="Ảnh minh họa" style="max-width:400px;">
<% } else { %>
<p>Không tìm thấy tin tức.</p>
<% } %>
</body>
</html>
