<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>에러 페이지</title>
    <meta charset="utf8">
</head>
<body>
<h2>에러가 발생했습니다</h2>
에러 타입: <%= exception.getClass().getName() %><br>
에러 메세지: <%= exception.getMessage() %>
</body>
</html>
