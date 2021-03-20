package pl.sztyro.szteacher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.sztyro.szteacher.model.WordList;
import pl.sztyro.szteacher.repository.WordListRepository;
import pl.sztyro.szteacher.service.AuthService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/list")
public class ListController {

    private static final Logger _logger = LoggerFactory.getLogger(ListController.class);

    WordListRepository wordListRepository;

    @Autowired
    public ListController(WordListRepository wordListRepository) {
        this.wordListRepository = wordListRepository;
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
    public WordList addList(@RequestBody WordList body, Principal principal, Authentication authentication) {

        Map<String, String> details = AuthService.getPrincipalDetails(principal);
        _logger.info("Saving list: " + body.getName() + ", id: " + body.getId());
        body.setOwner(details.get("given_name") + " " + details.get("family_name").charAt(0));
        return wordListRepository.save(body);

    }
}
