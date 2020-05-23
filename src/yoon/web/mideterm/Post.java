package yoon.web.mideterm;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String author;
    private String body;
    private int views;
    private Timestamp created_date;
    private Timestamp updated_date;

    public Post() {
        // 명시적으로 적어주지 않으면 내부에서 오류 발생 (500)
    }

    public Post(int id, String title, String author, int views, String body, Timestamp created_date, Timestamp updated_date) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setBody(body);
        setCreated_date(created_date);
        setUpdated_date(updated_date);
        setViews(views);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreated_date() {
        return this.created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Timestamp getUpdated_date() {
        return this.updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }


}
