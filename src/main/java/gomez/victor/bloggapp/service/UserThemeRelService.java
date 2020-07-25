package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.UserThemeRel;
import gomez.victor.bloggapp.repositories.UserThemeRelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserThemeRelService {
    @Autowired
    UserThemeRelRepository userThemeRelRepository;

    public void deleteRelation(int userId, int channelId) {
        userThemeRelRepository.deleteByUserIdAndChannelId(userId, channelId);
    }

    public UserThemeRel postRelation(UserThemeRel userThemeRel) {
        return userThemeRelRepository.save(userThemeRel);
    }

}
