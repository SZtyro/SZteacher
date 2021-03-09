package pl.sztyro.szteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.model.Word;
import pl.sztyro.szteacher.repository.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void addWord(Word word) {
        this.wordRepository.save(word);
    }

    public Optional<Word> getWord(long id) {

        return this.wordRepository.findById(id);
    }

    public List<Word> findAllByOriginalContains(String filter) {
        return this.wordRepository.findAllByOriginalContains(filter);
    }

}
