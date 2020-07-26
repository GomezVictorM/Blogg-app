package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.entities.User;
import gomez.victor.bloggapp.entities.UserThemeRel;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import gomez.victor.bloggapp.repositories.ThemeRepository;
import gomez.victor.bloggapp.repositories.UserRepository;
import gomez.victor.bloggapp.repositories.UserThemeRelRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocketService socketService;

    public Article postArticle(Article article) {
        Article dbArticle = null;

        try{
            dbArticle = articleRepository.save(article);
            ArrayList<Article> articles = new ArrayList<>();
            articles.add(dbArticle);
            dbArticle = addSenderName(articles).get(0);
            socketService.sendToAll(dbArticle, Article.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbArticle;
    }

    public List<Article> findAllArticles() {
        List<Article> articles = (List<Article>) articleRepository.findAll();

        articles.forEach(article -> {
            User sender = userRepository.findById(article.getSenderId());
            article.setSender(sender);

            User receiver = userRepository.findById(article.getReceiverId());
            article.setReceiver(receiver);

            Integer theme_id = article.getThemeId();
            if (theme_id != null) {
                Theme theme = themeRepository.findById((int) theme_id);
                article.setTheme(theme);
            }
        });

        return articles;
    }

    public Article findOneArticle(int id) {
        Article article = articleRepository.findById(id);
        if (article == null) return null;

        User sender = userRepository.findById(article.getSenderId());
        article.setSender(sender);

        User receiver = userRepository.findById(article.getReceiverId());
        article.setReceiver(receiver);

        Integer theme_id = article.getThemeId();
        Theme theme;
        if (theme_id != null) {
            theme = themeRepository.findById((int) theme_id);
        } else {
            theme = new Theme();
            theme.setId(0);
            theme.setTitle("Public Theme");
        }
        article.setTheme(theme);

        return article;
    }

    public List<Article> findArticlesByThemeId(int themeId) {
        List<Article> articles = articleRepository.findByArticleId(articleId);
        addSenderName(articles);

        for (Article article: articles){
            User sender = userRepository.findById(article.getSenderId());
            article.setSender(sender);
        }

        return messages;
    }

    public List<Article> addSenderName(List<Article> articles) {
        for (int i = 0; i < articles.size(); i++) {
            try {
                User user = userRepository.findById(articles.get(i).getSenderId());
                articles.get(i).setSenderName(user.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return articles;
    }

    public Theme findOneTheme(int id) {
        Theme theme = themeRepository.findById(id);
        if (theme == null) return null;


        return theme;
    }

    public Theme createNewTheme(Theme newTheme) {
        Theme dbTheme = null;
        try {
            dbTheme = themeRepository.save(newTheme);

            dbTheme.action = "new-theme";

            UserThemeRelRepository newRelation = new UserThemeRel();
            newRelation.setUserId(newTheme.getAdmin_id());
            newRelation.setChannelId(newTheme.getId());
            userThemeRelRepository.save(newRelation);

            socketService.sendToAll(dbTheme, Theme.class);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dbTheme;
    }

    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public List<Theme> getUserOtherTheme (int user_id){

        List<Theme> otherThemes = new ArrayList<>();

        SQLQuery query = this.getSession()
                .createSQLQuery("SELECT id, title, admin_id FROM themes WHERE NOT EXISTS (SELECT 'm' FROM user_theme_rel WHERE user_theme_rel.theme_id = themes.id AND user_theme_rel.user_id = ?)")
                .setParameter(1, user_id);

        List<Object[]> rows = query.list();

        for(Object[] row : rows){
            Theme newTheme = new Theme();
            newTheme.setId(Integer.parseInt(row[0].toString()));
            newTheme.setTitle(row[1].toString());
            newTheme.setAdmin_id(Integer.parseInt(row[2].toString()));

            otherThemes.add(newTheme);
        }

        return otherThemes;
    }

}
