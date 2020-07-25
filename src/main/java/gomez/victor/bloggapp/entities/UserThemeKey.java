package gomez.victor.bloggapp.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserThemeKey implements Serializable {
    @Column(name = "member_id")
    int member_id;

    @Column(name = "theme_id")
    int theme_id;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public UserThemeKey(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserThemeKey)) return false;
        UserThemeKey that = (UserThemeKey) o;
        return getMember_id() == that.getMember_id() &&
                getTheme_id() == that.getTheme_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMember_id(), getTheme_id());
    }

    @Override
    public String toString() {
        return "Member: " + member_id + " (" + theme_id + ")";
    }
}
