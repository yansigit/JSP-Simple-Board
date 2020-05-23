<%@ page import="yoon.web.mideterm.Post" %>
<%@ page import="yoon.web.mideterm.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="db" class="yoon.web.mideterm.DB" scope="page" />
<%
    Member member = (Member) session.getAttribute("member");
    int id = Integer.parseInt(request.getParameter("id"));
    Post post = db.getPost(id);
    if (post != null) {
        db.upPostView(post);
    }
%>
<html>
<head>
    <title><%= post.getTitle() %></title>
    <link rel="stylesheet" href="board_cicweb.css" media="all">
</head>
<body>
<article class="board-text">
    <ul class="infoBox">
        <li><strong>작성자</strong><%= post.getAuthor() %></li>
        <li><strong>작성일</strong><%= post.getUpdated_date() %></li>
        <li><strong>조회수</strong><%= post.getViews() %></li>
    </ul>
    <h1 class="tit"><strong>제목</strong><%= post.getTitle() %></h1>
    <div class="viewBox">
        <%= post.getBody() %>
    </div>
</article>
<div class="button_wrapper">
    <a href="index.jsp" class="button">목록</a>
    <% if (post.getAuthor().equals(member.getMember_name())) { %>
    <a href="post_write.jsp?update=true&doc=<%= post.getId() %>" class="button">수정</a>
    <a href="controller.jsp?action=delete&doc=<%= post.getId() %>" class="button">삭제</a>
    <% } %>
</div>
</body>
</html>
