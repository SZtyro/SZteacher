package pl.sztyro.szteacher.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sztyro.szteacher.service.LanguageService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Word {


    @Id
    @GeneratedValue
    long id;

    @ManyToOne(optional = false, targetEntity = Language.class)
    @JoinColumn(name = "language_id")
    Language language;

    @Column(nullable = false)
    String original;

    @ManyToOne(optional = false, targetEntity = Language.class)
    @JoinColumn(name = "transaltion_language_id")
    Language translationLanguage;

    @Column(nullable = false)
    @NotBlank
    String translation;

    public Word() {
    }

    public Word(Language language, String original, Language translationLanguage, String translation) {
        this.language = language;
        this.original = original;
        this.translationLanguage = translationLanguage;
        this.translation = translation;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }

    public void setTranslationLanguage(Language translationLanguage) {
        this.translationLanguage = translationLanguage;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", language=" + language +
                ", original='" + original + '\'' +
                ", translationLanguage=" + translationLanguage +
                ", translation='" + translation + '\'' +
                '}';
    }
}
