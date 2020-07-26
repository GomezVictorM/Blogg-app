package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.configs.MyUserDetailsConfig;
import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.entities.User;
import gomez.victor.bloggapp.entities.UserThemeRel;
import gomez.victor.bloggapp.repositories.ThemeRepository;
import gomez.victor.bloggapp.repositories.UserThemeRelRepository;
import gomez.victor.bloggapp.repositories.UserRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsConfig myUserDetailsConfig;

    @Autowired
    private UserThemeRelRepository userThemeRelRepo;

    @Autowired
    private SocketService socketService;

    @Autowired
    private ThemeRepository themeRepository;


    public List<User> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();

        return users;
    }

    public User findOneUser(int id) {
        User user = userRepository.findById(id);
        if (user == null) return null;

        return user;
    }

    public User createNewUser(User user) {
        User dbUser = null;
        try {
            dbUser = userRepository.save(user);
            dbUser.action = "new-user";
            socketService.sendToAll(dbUser, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbUser;
    }

    public User login(String username, String password) {
        User user = null;

        try {
            user = userRepository.findAllByUsernameAndPassword(username, password);
            System.out.println(user.getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findCurrentUser() {
        // the login session is stored between page reloads,
        // and we can access the current authenticated user with this
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        try {
            user = addThemeToCurrentUser(user);
            user = addOtherThemeToCurrentUser(user);
        } catch (Exception e) {
            System.out.println("There is no user currently logged in");
        }
        return user;
    }

    public User registerUser(User user) {
        return myUserDetailsConfig.addUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName());
    }

    public User addThemeToCurrentUser(User user) {
        List<UserThemeRel> foundUserThemes = userThemeRelRepo.findByUserId(user.getId());
        ArrayList<Theme> themes = new ArrayList<>();
        for (UserThemeRel foundUserTheme : foundUserThemes) {
            themes.add(themeRepository.findByID(foundUserTheme.getThemeId()));
        }
        user.setListOfThemes(themes);
        return user;

    }

    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public User addOtherThemeToCurrentUser(User user) {

        ArrayList<Theme> otherThemes = new ArrayList<>();

        SQLQuery query = this.getSession()
                .createSQLQuery("SELECT id, title, admin_id from themes WHERE NOT EXISTS (SELECT 'm' FROM user_theme_rel where user_theme_rel.channel_id = themes.id and user_theme_rel.user_id = ?)")
                .setParameter(1, user.getId());

        List<Object[]> rows = query.list();

        for (Object[] row : rows) {
            Theme newTheme = new Theme();
            newTheme.setId(Integer.parseInt(row[0].toString()));
            newTheme.setTitle(row[1].toString());
            newTheme.setAdmin_id(Integer.parseInt(row[2].toString()));
            otherThemes.add(newTheme);
        }

        user.setOtherThemes(otherThemes);
        return user;
    }
}