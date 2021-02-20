package pl.sztyro.szteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public void addLanguage(String code) {
        languageRepository.save(new Language(code));
    }

    public List<Language> getLanguges() {
        return (List<Language>) languageRepository.findAll();
    }

    public Optional<Language> getLanguage(long id) {
        return languageRepository.findById(id);
    }
}
