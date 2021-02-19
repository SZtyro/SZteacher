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
import pl.sztyro.szteacher.service.LanguageService;

@RestController
@RequestMapping("api/language")
public class LanguageController {

    private static final Logger _logger = LoggerFactory.getLogger(LanguageController.class);

    @Autowired
    LanguageService languageService;

    @Autowired
    HibernateUtil hibernateUtill;

    @PostMapping()
    public void addLanguage(@RequestBody() Object body) {

        JSONObject jsonObject = new JSONObject(new Gson().toJson(body));
        String code = jsonObject.getString("code");

        Session session = hibernateUtill.getSession();


        languageService.addLanguage(code);
        _logger.info(code);


        session.close();

    }

}
