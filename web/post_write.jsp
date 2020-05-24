<%@ page import="yoon.web.mideterm.Member" %>
<%@ page import="yoon.web.mideterm.Post"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<jsp:useBean id="db" class="yoon.web.mideterm.DB" scope="session" />
<%
    Member member = (Member) session.getAttribute("member");
    if (member == null) {
        throw new Exception("게시물을 작성하려면 로그인하세요");
    }
    String mem_name = member.getMember_name();

    String update = request.getParameter("update");
    int doc_id = 0;
    Post post = null;
    if(update != null) {
        doc_id = Integer.parseInt(request.getParameter("doc"));
        post = db.getPost(doc_id);
    }
%>
<html>
<head>
    <title>글쓰기</title>
    <link rel="stylesheet" href="board_cicweb.css" media="all">
    <meta charset="utf8">
</head>
<body>
<article class="board-write">
    <form action="controller.jsp" method="post">
        <input type="hidden" name="id" value="<%= (update != null) ? doc_id : "" %>">
        <input type="hidden" name="action" value="<%= (update != null) ? "update" : "write" %>">
        <ul class="infoBox">
            <li><strong>제목</strong><label>
                <input class="inputTxt" type="text" name="title" value="<%= (update != null) ? post.getTitle() : "" %>">
            </label></li>
        </ul>
        <ul class="infoBox">
            <li><strong>작성자</strong><label>
                <input class="inputTxt" type="text" name="author" value="<%= (update != null) ? post.getAuthor() : mem_name %>" readonly>
            </label></li>
        </ul>
        <div class="viewBox">
            <label>
                <textarea class="textarea" name="body"><%= (update != null) ? post.getBody() : "" %></textarea>
            </label>
        </div>
        <a href="index.jsp" class="button">목록</a>
        <input class="button" type="submit" value="<%= (update != null) ? "업데이트" : "글쓰기" %>">
    </form>
</article>
</body>
</html>