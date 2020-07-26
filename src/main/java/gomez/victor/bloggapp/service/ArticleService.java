package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Article;
import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.entities.UserTheme;
import gomez.victor.bloggapp.entities.UserThemeRel;
import gomez.victor.bloggapp.repositories.ArticleRepository;
import gomez.victor.bloggapp.repositories.ThemeRepository;
import gomez.victor.bloggapp.repositories.UserThemeRelRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private UserThemeRelRepository userThemeRelRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private SocketService socketService;

    public List<Theme> findAllThemes() {
        List<Theme> channels = (List<Theme>) themeRepository.findAll();

        return channels;
    }

    public Theme findOneTheme(int id) {
        Theme theme = themeRepository.findById(id);
        if (theme == null) return null;


        return theme;
    }

    public Theme createNewChannel(Theme newTheme) {
        Theme dbTheme = null;
        try {
            dbTheme = themeRepository.save(newTheme);

            dbTheme.action = "new-theme";


            UserThemeRel newRelation = new UserThemeRel();
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

    public List<Theme> getUserOtherChannel (int user_id){

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
