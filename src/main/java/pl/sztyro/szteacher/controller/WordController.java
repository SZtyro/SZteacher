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
import pl.sztyro.szteacher.model.Word;
import pl.sztyro.szteacher.repository.WordRepository;
import pl.sztyro.szteacher.service.LanguageService;
import pl.sztyro.szteacher.service.WordService;

import java.util.List;

@RestController
@RequestMapping("api/word")
public class WordController {

    private static final Logger _logger = LoggerFactory.getLogger(WordController.class);

    @Autowired
    WordService wordService;

    @Autowired
    LanguageService languageService;

    @Autowired
    HibernateUtil hibernateUtil;


    WordRepository wordRepository;

    @Autowired
    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostMapping()
    public void addWord(@RequestBody() Object object) {

        JSONObject jsonObject = new JSONObject(new Gson().toJson(object));

        _logger.info(jsonObject.toString(2));
        try {
            String list = jsonObject.getJSONArray("translation").toList().toString();

            Word word = wordRepository.findWordByLanguageAndOriginal(
                    languageService.getLanguage(jsonObject.getLong("languageId")).orElseThrow(() -> new Exception("Language not found.")),
                    languageService.getLanguage(jsonObject.getLong("translationLanguageId")).orElseThrow(() -> new Exception("Language not found.")),
                    jsonObject.getString("original").toLowerCase()
            );

            if (word != null) {
                word.setTranslation(list.substring(1, list.length() - 1).toLowerCase());
                wordRepository.save(word);
            } else {
                wordService.addWord(new Word(
                        languageService.getLanguage(jsonObject.getLong("languageId")).orElseThrow(() -> new Exception("Language not found.")),
                        jsonObject.getString("original").toLowerCase(),
                        languageService.getLanguage(jsonObject.getLong("translationLanguageId")).orElseThrow(() -> new Exception("Language not found.")),
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
    public List<Word> getWords(@RequestParam() String filter) {
        return this.wordService.findAllByOriginalContains(filter);
    }

}
