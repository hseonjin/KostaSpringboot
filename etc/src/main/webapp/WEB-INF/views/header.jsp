<%--
  Created by IntelliJ IDEA.
  User: 선진
  Date: 2023-11-10
  Time: 오전 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String data = (String) request.getAttribute("data1");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
JSTL 표기 <br/>
header.jsp : <%= data %> <br/>

EL 표기 <br/>
header.jsp : ${param.data} <br/>
</body>
</html>
