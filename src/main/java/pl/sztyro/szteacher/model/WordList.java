package pl.sztyro.szteacher.model;

import pl.sztyro.szteacher.enums.Language;

import javax.persistence.*;

@Entity
public class WordList {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "is_private")
    private boolean isPrivate = true;

    @Column
    private String words;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Language translationLanguage;

    @Column
    private Double rating;

    public WordList() {
    }

    public WordList(String name, String ownerMail, boolean isPrivate, Language language) {
        this.name = name;
        this.owner = ownerMail;
        this.isPrivate = isPrivate;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Language getTranslationLanguage() {
        return translationLanguage;
    }

    public void setTranslationLanguage(Language translationLanguage) {
        this.translationLanguage = translationLanguage;
    }
}
