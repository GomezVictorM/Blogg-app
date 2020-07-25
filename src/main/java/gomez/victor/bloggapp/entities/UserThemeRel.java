package gomez.victor.bloggapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_theme_rel")
public class UserThemeRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int userId;
    private int themeId;

    public UserThemeRel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChannelId() {
        return themeId;
    }

    public void setChannelId(int channelId) {
        this.themeId = channelId;
    }
}
