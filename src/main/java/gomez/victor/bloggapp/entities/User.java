package gomez.victor.bloggapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    boolean isActive;
    @Transient
    private ArrayList<Theme> listOfThemes;

    @Transient
    private ArrayList<Theme> otherThemes;

    public void setOtherThemes(ArrayList<Theme> otherThemes){
        this.otherThemes = otherThemes;
    }

    public ArrayList getOtherThemes(){
        return this.otherThemes;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "userfriends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")})
    @JsonIgnoreProperties("friends")
    private Set<User> friends = new HashSet<>();

    public Object[] getFriends() {
        return friends.toArray();
    }


    @Transient
    public String action;

    public User() {
    }

    public User(String username, String password, String email, String firstName, String lastName, boolean isActive){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public void setId ( int id){
        this.id = id;
    }

    public void setUsername (String username){
        this.username = username;
    }

    @JsonProperty
    public void setPassword (String password){
        this.password = password;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setIsActive ( boolean isActive){
        this.isActive = isActive;
    }

    public int getId () {
        return id;
    }

    public String getUsername () {
        return username;
    }

    @JsonIgnore
    public String getPassword () {
        return password;
    }

    public String getEmail () {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isIsActive () {
        return isActive;
    }

    public ArrayList<Theme> getListOfThemes() {
        return listOfThemes;
    }

    public void setListOfThemes(ArrayList<Theme> listOfThemes) {
        this.listOfThemes = listOfThemes;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode () {
        return Objects.hash(getId());
    }
}
