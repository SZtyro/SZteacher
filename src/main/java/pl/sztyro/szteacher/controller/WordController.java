package pl.sztyro.szteacher.controller;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import pl.sztyro.szteacher.config.HibernateUtil;
import pl.sztyro.szteacher.enums.Language;
import pl.sztyro.szteacher.model.Word;
import pl.sztyro.szteacher.repository.WordRepository;
import pl.sztyro.szteacher.service.AuthService;
import pl.sztyro.szteacher.service.WordService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/word")
public class WordController {

    private static final Logger _logger = LoggerFactory.getLogger(WordController.class);

    @Autowired
    WordService wordService;

    @Autowired
    HibernateUtil hibernateUtil;


    WordRepository wordRepository;

    @Autowired
    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostMapping()
    public void addWord(@RequestBody() Object object, Principal principal) {

        JSONObject jsonObject = new JSONObject(new Gson().toJson(object));

        _logger.info(jsonObject.toString(2));
        try {
            String list = jsonObject.getJSONArray("translation").toList().toString();

            Word word = wordRepository.findWordByLanguageAndOriginal(
                    Language.valueOf(jsonObject.getString("language")),
                    Language.valueOf(jsonObject.getString("translationLanguage")),
                    jsonObject.getString("original").toLowerCase()
            );

            if (word != null) {
                word.setTranslation(list.substring(1, list.length() - 1).toLowerCase());
                wordRepository.save(word);
            } else {
                wordService.addWord(new Word(
                        AuthService.getPrincipalDetails(principal).get("email"),
                        Language.valueOf(jsonObject.getString("language")),
                        jsonObject.getString("original").toLowerCase(),
                        Language.valueOf(jsonObject.getString("translationLanguage")),
                        list.substring(1, list.length() - 1).toLowerCase()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Word getWord(@PathVariable(name = "id") long id) throws Exception {
        return this.wordService.getWord(id).orElseThrow(() -> new Exception("Word no found. Id: " + id));
    }

    @GetMapping()
    public List<Word> getWords(
            @RequestParam() String filter,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String translationLanguage,
            @RequestParam(required = false) Integer limit) {
        if (language == null && translationLanguage == null)
            return this.wordService.findAllByOriginalContains(filter);
        else
            return this.wordRepository.findAllByOriginalAndLanguageAndTranslationLanguage(filter,Language.valueOf(language) , Language.valueOf(translationLanguage));
    }

}
