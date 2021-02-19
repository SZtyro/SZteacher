package pl.sztyro.szteacher.model;

import javax.persistence.*;

@Entity
public class Word {

    @Id
    @GeneratedValue
    long id;

    @ManyToOne(optional = false)
    Language language;

    @Column(nullable = false)
    String original;

    @ManyToOne(optional = false)
    Language translationLanguage;

    @Column(nullable = false)
    String translation;


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
}
