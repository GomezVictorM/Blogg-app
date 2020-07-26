package gomez.victor.bloggapp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int admin_id;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Theme(Integer id, String title, Integer admin_id) {
        this.id = id;
        this.title = title;
        this.admin_id = admin_id;
    }

    @Transient
    public String action;

    public Theme() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
