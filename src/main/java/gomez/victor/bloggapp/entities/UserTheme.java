package gomez.victor.bloggapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "userxtheme")
public class UserTheme {
    @EmbeddedId
    private UserThemeKey id;
    private boolean admin;

    public UserTheme() {

    }

    public UserTheme(User user, Theme theme, boolean admin) {
        this.user = user;
        this.theme = theme;
        this.admin = admin;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("member_id")
    @JoinColumn(name = "member_id")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("theme_id")
    @JoinColumn(name = "theme_id")
    Theme theme;

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }
}
