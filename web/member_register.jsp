<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <title>회원가입</title>
    <meta charset="utf8">
    <link href="members.css" rel="stylesheet">
</head>
<body>
<div class="mem_box">
    <form method="post" action="controller.jsp">
        <input type="hidden" name="action" value="register">
        <div class="mem_input_wrapper">
            <label>아이디
                <input name="member_id" type="text" class="mem_input">
            </label>
        </div>
        <div class="mem_input_wrapper">
            <label>이름
                <input name="member_name" type="text" class="login_input">
            </label>
        </div>
        <div class="mem_input_wrapper">
            <label>패스워드
                <input name="member_password" type="password" class="login_input">
            </label>
        </div>
        <hr>
        <button type="submit" class="button">회원가입</button>
    </form>
</div>
</body>
</html>
