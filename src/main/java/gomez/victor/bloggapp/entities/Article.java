package gomez.victor.bloggapp.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Article {

    public String action;
    @Transient
    private User sender;

    @Transient
    private User receiver;

    @Transient
    private Theme theme;

    @Transient
    private String senderName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String articleDate;
    private String article;
    private boolean direct;
    private boolean read;
    private int senderId;

    //@Column(nullable = true)
    private int themeId;
    private int receiverId;

    public Article() {
    }

    public Article(String articleDate, String article, Boolean read, int senderId, int themeId, int receiverId, boolean direct) {
        this.articleDate = articleDate;
        this.article = article;
        this.read = read;
        this.senderId = senderId;
        this.themeId = themeId;
        this.receiverId = receiverId;
        this.direct = direct;
    }

    public Article(User sender, User receiver, Theme theme, int id, String article_date, String message, boolean direct, boolean read, int sender_id, Integer theme_id, int receiver_id) {
        this.sender = sender;
        this.receiver = receiver;
        this.theme = theme;
        this.id = id;
        this.articleDate = article_date;
        this.direct = direct;
        this.read = read;
        this.senderId = sender_id;
        this.themeId = theme_id;
        this.receiverId = receiver_id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
