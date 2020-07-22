package gomez.victor.bloggapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "Temas")
public class Theme {

    @Id
    @GeneretedValue(Strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public Theme(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Transient
    public String action;

    public Theme() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
