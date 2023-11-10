<%--
  Created by IntelliJ IDEA.
  User: 선진
  Date: 2023-11-10
  Time: 오전 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String data1 = (String) request.getAttribute("data");
    String data2 = (String) session.getAttribute("data");
    String data3 = request.getParameter("data");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<% pageContext.include("header.jsp");%> <br/>
data1: <%= data1 %> <br/>
data2: <%= data2 %> <br/>
data3: <%= data3 %> <br/>
</body>
</html>
