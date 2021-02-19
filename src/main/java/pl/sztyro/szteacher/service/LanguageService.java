package pl.sztyro.szteacher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.repository.LanguageRepository;

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
}
