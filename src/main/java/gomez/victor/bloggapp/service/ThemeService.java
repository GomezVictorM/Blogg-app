package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepo themeRepo;

    public List<Theme> findAllThemes() {
        List<Theme> themes = (List<Theme>) themeRepo.findAll();
        return themes;
    }

    public Theme findOneTheme(int id) {
        Theme theme = themeRepo.findByID(id);
        if (theme == null) return null;

        return theme;
    }

}
