package gomez.victor.bloggapp.service;

import gomez.victor.bloggapp.entities.Theme;
import gomez.victor.bloggapp.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepo;

    public List<Theme> findAllThemes() {
        List<Theme> themes = (List<Theme>) themeRepo.findAll();
        return themes;
    }

    public Theme findOneTheme(int id) {
        Theme theme = themeRepo.findById(id);
        if (theme == null) return null;

        return theme;
    }

}
