package gomez.victor.bloggapp.controllers;

import gomez.victor.bloggapp.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ThemeController {

@Autowired
ThemeService themeService;

}
