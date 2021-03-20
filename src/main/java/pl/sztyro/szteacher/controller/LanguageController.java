package pl.sztyro.szteacher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sztyro.szteacher.enums.Language;

@RestController
@RequestMapping("api/language")
public class LanguageController {

    @GetMapping
    public Object getLanguages(){
        return Language.values();
    }
}
