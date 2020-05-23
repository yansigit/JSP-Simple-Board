<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" errorPage="error.jsp" %>
<html>
<head>
    <title>로그인 페이지</title>
    <meta charset="utf8">
    <link href="members.css" rel="stylesheet">
</head>
<body>
<div class="mem_box">
    <form action="controller.jsp" method="post">
        <input type="hidden" name="action" value="login">
        <div class="mem_input_wrapper">
            <label>아이디
                <input name="member_id" type="text" class="login_input">
            </label>
        </div>
        <div class="mem_input_wrapper">
            <label>패스워드
                <input name="member_password" type="password" class="login_input">
            </label>
        </div>
        <hr>
        <button type="submit" class="button">로그인</button>
        <a href="member_register.jsp" class="button">회원가입</a>
    </form>
</div>
</body>
</html>
