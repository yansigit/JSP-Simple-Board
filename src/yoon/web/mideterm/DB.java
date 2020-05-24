package yoon.web.mideterm;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    Connection conn = null;
    PreparedStatement pstmt = null;

    public DB() {
        Connection setupConn = null;
        Statement setupStmt = null;
        try {
            setupConn = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "1234");
            setupStmt = setupConn.createStatement();
            setupStmt.executeUpdate("CREATE DATABASE IF NOT EXISTS `midterm_board`");

            setupConn.close();
            setupStmt.close();

            setupConn = DriverManager.getConnection("jdbc:mysql://localhost/midterm_board", "root", "1234");
            setupStmt = setupConn.createStatement();

            String tableSql = "CREATE TABLE IF NOT EXISTS `members` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  `member_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `member_id` (`member_id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
            setupStmt.executeUpdate(tableSql);


            tableSql = "CREATE TABLE IF NOT EXISTS `posts` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '기본 타이틀',\n" +
                    "  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '익명',\n" +
                    "  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  `views` int DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
            setupStmt.executeUpdate(tableSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(setupConn != null) {
                try {
                    setupConn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(setupStmt != null) {
                try {
                    setupStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/midterm_board", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Member getMember(String member_id) {
        connect();
        Member member = new Member();
        try {
            pstmt = conn.prepareStatement("SELECT * from members WHERE member_id = ?");
            pstmt.setString(1, member_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                member.setMember_id(rs.getString("member_id"));
                member.setMember_name(rs.getString("member_name"));
                member.setMember_password(rs.getString("member_password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            disconnect();
        }
        return member;
    }

    public boolean loginMember(Member member) {
        String member_id = member.getMember_id();
        String member_password = member.getMember_password();

        connect();
        try {
            pstmt = conn.prepareStatement("select * from members where member_id = ?");
            pstmt.setString(1, member_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                if (rs.getString("member_password").equals(member_password)) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }
        return false;
    }

    public boolean registerMember(Member member) {
        String id = member.getMember_id();
        String name = member.getMember_name();
        String password = member.getMember_password();

        connect();
        try {
            pstmt = conn.prepareStatement("INSERT INTO `members` (`member_id`, `member_name`, `member_password`) VALUES (?, ?, ?)");
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public boolean writePost(Post post) {
        String title = post.getTitle();
        String author = post.getAuthor();
        String body = post.getBody();

        connect();
        try {
            pstmt = conn.prepareStatement("INSERT INTO `posts` (`title`, `author`, `body`, `created_date`, `updated_date`) VALUES (?, ?, ?, NOW(), NOW());");
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, body);

            int result = pstmt.executeUpdate();
            System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public boolean updatePost(Post post) {
        int id = post.getId();
        String title = post.getTitle();
        String author = post.getAuthor();
        String body = post.getBody();

        connect();
        try {
            pstmt = conn.prepareStatement("UPDATE `posts` SET `title`=?, `author`=?, `body`=?, `updated_date`=now() WHERE `id`=?;");
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, body);
            pstmt.setInt(4, id);
            int result = pstmt.executeUpdate();
            System.out.println(result);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public boolean deletePost(int post_id) {
        connect();
        try {
            pstmt = conn.prepareStatement("DELETE FROM `posts` WHERE  `id`=?");
            pstmt.setInt(1, post_id);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public ArrayList<Post> getPostsList() {
        connect();
        ArrayList<Post> posts = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement("select * from posts order by id desc");
            ResultSet rs = pstmt.executeQuery();
            int i = 1;

            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("views"),
                        "",
                        rs.getTimestamp("created_date"),
                        rs.getTimestamp("updated_date")
                ));
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return posts;
    }

    public boolean upPostView(Post post) {
        connect();
        try {
            pstmt = conn.prepareStatement("UPDATE posts set views=views+1 where id = ?");
            pstmt.setInt(1, post.getId());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public Post getPost(int id) {
        connect();
        Post post = null;

        try {
            pstmt = conn.prepareStatement("select * from posts where id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                post = new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("views"),
                        rs.getString("body"),
                        rs.getTimestamp("created_date"),
                        rs.getTimestamp("updated_date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return post;
    }
}
