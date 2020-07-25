package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.UserTheme;
import gomez.victor.bloggapp.entities.UserThemeKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserThemeRepository extends CrudRepository<UserTheme, UserThemeKey> {
    public Optional<UserTheme> findById(UserThemeKey id);
    public List<UserTheme> findByUserId(int User_id);
}
