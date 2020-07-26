package gomez.victor.bloggapp.repositories;

import gomez.victor.bloggapp.entities.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends CrudRepository <Theme, Integer> {
    public Theme findByID(int id);

}
