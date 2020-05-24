<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Timestamp" errorPage="error.jsp" %>
<jsp:useBean id="db" class="yoon.web.mideterm.DB" scope="session" />
<jsp:useBean id="post" class="yoon.web.mideterm.Post" scope="page" />
<jsp:useBean id="member" class="yoon.web.mideterm.Member" />

<% request.setCharacterEncoding("utf-8"); %>

<jsp:setProperty name="post" property="*" />
<jsp:setProperty name="member" property="*" />

<%
    String action = request.getParameter("action");
    
    switch (action) {
        case "write":
            if (db.writePost(post)) {
                response.sendRedirect("index.jsp");
            } else {
                throw new Exception("게시물 작성에 실패했습니다");
            }
            break;
        case "update":
            if (db.updatePost(post)) {
                response.sendRedirect("post_read.jsp?id=" + post.getId());
            } else {
                throw new Exception("게시물 수정에 실패했습니다");
            }
            break;
        case "delete":
            if (db.deletePost(Integer.parseInt(request.getParameter("doc")))) {
                response.sendRedirect("index.jsp");
            } else {
                throw new Exception("게시물 삭제에 실패했습니다");
            }
            break;
        case "login":
            if (db.loginMember(member)) {
                member = db.getMember(member.getMember_id());
                session.setAttribute("member", member);
                response.sendRedirect("index.jsp");
            } else {
                throw new Exception("로그인에 실패했습니다");
            }
            break;
        case "register":
            if (db.registerMember(member)) {
                response.sendRedirect("member_login.jsp");
            } else {
                throw new Exception("회원가입에 실패했습니다.");
            }
            break;
        case "logout":
            session.setAttribute("member", null);
            session.invalidate();
            response.sendRedirect("member_login.jsp");
            break;
    }
%>