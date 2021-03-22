package pl.sztyro.szteacher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import pl.sztyro.szteacher.exception.DuplicateException;
import pl.sztyro.szteacher.model.Word;
import pl.sztyro.szteacher.model.WordList;
import pl.sztyro.szteacher.repository.WordListRepository;
import pl.sztyro.szteacher.repository.WordRepository;
import pl.sztyro.szteacher.service.AuthService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/list")
public class ListController {

    private static final Logger _logger = LoggerFactory.getLogger(ListController.class);

    WordListRepository wordListRepository;
    WordRepository wordRepository;

    @Autowired
    public ListController(WordListRepository wordListRepository, WordRepository wordRepository) {
        this.wordListRepository = wordListRepository;
        this.wordRepository = wordRepository;
    }

    @GetMapping("/{id}")
    public Object getList(@PathVariable(name = "id") long id) {
        _logger.info("Fetching list: " + id);
        return wordListRepository.findById(id);
    }

    @GetMapping("/public")
    public Object getLists() {
        return wordListRepository.findAllPublic();
    }

    @GetMapping("/private")
    public Object getPrivateLists() {
        return wordListRepository.findAllPrivate();
    }

    @PostMapping()
    public WordList addList(@RequestBody WordList body, Principal principal) {

        Map<String, String> details = AuthService.getPrincipalDetails(principal);
        _logger.info("Saving list: " + body.getName() + ", id: " + body.getId());
        body.setOwner(details.get("given_name") + " " + details.get("family_name").charAt(0));
        body.setAuthor(details.get("email"));
        return wordListRepository.save(body);

    }

    @PostMapping("/{id}/word")
    public void addWordToList(@RequestBody Word body, @PathVariable(name = "id") long id, Principal principal) throws DuplicateException {

        Map<String, String> details = AuthService.getPrincipalDetails(principal);
        WordList list = wordListRepository.findById(id).get();

        _logger.info("Adding word: " + body.getOriginal() + " to list: " + list.getName() + ", id: " + id);
        list.addWord(body.getId());
        wordListRepository.save(list);

    }

    @GetMapping("/{id}/word")
    public Object getListWords(@PathVariable(name = "id") long id, Principal principal) {
        WordList list = wordListRepository.findById(id).get();
        _logger.info("Fetching words");

        if (list.isPrivate()) {
            if (!AuthService.getPrincipalMail(principal).equals(list.getAuthor()))
                throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "toast.list.private");
        }

        List<Word> words = new ArrayList<>();
        list.extractWordsIds().forEach(elem -> {
            words.add(wordRepository.findById(Long.parseLong(elem)).get());
        });

        return words;

    }

    @DeleteMapping("/{listId}/word/{wordId}")
    public void deleteWordFromList(@PathVariable(name = "listId") long listId, @PathVariable(name = "wordId") long wordId, Principal principal) {
        WordList list = wordListRepository.findById(listId).get();

        _logger.info("Deleting word from list: " + list.getName());

        if(AuthService.getPrincipalMail(principal).equals(list.getAuthor())){
            list.deleteWord(wordId);
            wordListRepository.save(list);
        }else{
            throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "toast.list.private");
        }
    }
}
