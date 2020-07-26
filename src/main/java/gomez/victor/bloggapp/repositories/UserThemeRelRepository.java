package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.UserTheme;
import gomez.victor.bloggapp.entities.UserThemeRel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserThemeRelRepository extends CrudRepository<UserThemeRel, Integer> {
    public List<UserThemeRel> findByUserId(int userId);
    public void deleteByUserIdAndThemeId(@Param("userId") int userId, @Param("themeId") int themeId);
}
