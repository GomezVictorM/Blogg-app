package entities;

import javax.persistence.*;


// Article = {
//  title: String,
//  content: String,
//  published: Long
//}


@Entity
public class Article {
    @Id
    @GeneratedValue(Strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private Long published;



}
