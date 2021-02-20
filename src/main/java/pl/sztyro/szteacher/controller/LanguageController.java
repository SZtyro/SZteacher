package pl.sztyro.szteacher.controller;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sztyro.szteacher.config.HibernateUtil;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/language")
public class LanguageController {

    private static final Logger _logger = LoggerFactory.getLogger(LanguageController.class);

    @Autowired
    LanguageService languageService;

    @Autowired
    HibernateUtil hibernateUtill;

    @PostMapping()
    public void addLanguage(@RequestBody() @Valid Language body) {

        languageService.addLanguage(body.getLang());
        _logger.info("Added new language: " + body.getLang());

    }


    @GetMapping()
    public List<Language> getAllLanguages(){
        return languageService.getLanguges();
    }

}
