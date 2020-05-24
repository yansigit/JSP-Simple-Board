<%@ page import="yoon.web.mideterm.Post" %>
<%@ page import="yoon.web.mideterm.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="db" class="yoon.web.mideterm.DB" scope="session" />
<html>
<head>
    <title>게시판 목록</title>
    <link rel="stylesheet" href="board_cicweb.css" media="all">
</head>
<body>
<div class="board-text">
    <%
        Member member = (Member) session.getAttribute("member");
        if(member == null) {
    %>
    <a class="button" href="member_login.jsp">로그인</a>
    <a class="button" href="member_register.jsp">회원가입</a>
    <% } else { %>
    <a class="button" href="controller.jsp?action=logout">로그아웃</a>
    <% } %>
    <span>로그인 유저: <%= member != null ? member.getMember_name() : "없음" %></span>
    <table>
        <colgroup>
            <col style="width:7%">
            <col style="width:*">
            <col style="width:10%">
            <col style="width:7%">
            <col style="width:15%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
        </thead>
    <%
        for (Post post:db.getPostsList()) {
    %>
        <tr>
            <td><%= post.getId() %></td>
            <td><a href="post_read.jsp?id=<%= post.getId() %>"><%= post.getTitle() %></a></td>
            <td><%= post.getAuthor()%></td>
            <td><%= post.getViews() %></td>
            <td><%= post.getUpdated_date()%></td>
        </tr>
    <%
        }
    %>
    </table>
    <a class="button" href="post_write.jsp">글쓰기</a>
</div>
</body>
</html>
