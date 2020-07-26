package gomez.victor.bloggapp.repositories;

import org.springframework.stereotype.Repository;
import gomez.victor.bloggapp.entities.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findById(int id);
    public User findAllByUsernameAndPassword(String username, String password);
    public User findByUsername(String username);
}
