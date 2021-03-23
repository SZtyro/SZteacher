package pl.sztyro.szteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import pl.sztyro.szteacher.enums.Language;
import pl.sztyro.szteacher.exception.DuplicateException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
})
@Entity
public class WordList {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "owner", nullable = false)
    private String owner;

    @JsonIgnore
    @Column(nullable = false)
    private String author;

    @Column
    private String description;

    @Column(name = "is_private")
    private boolean isPrivate = true;

    @JsonIgnore
    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private String[] wordsIds;

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

    public String[] getWordsIds() {
        return wordsIds;
    }

    public void setWordsIds(String[] wordsIds) {
        this.wordsIds = wordsIds;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addWord(long id) throws DuplicateException {

        String[] array = getWordsIds();
        List<String> list;

        if (array == null)
            list = new ArrayList<>();
        else
            list = new ArrayList<>(Arrays.asList(array));


        String sId = String.valueOf(id);
        if (!list.contains(sId))
            list.add(sId);
        else
            throw new DuplicateException("toast.list.word.exists");

        setWordsIds(list.toArray(new String[list.size()]));


    }

    public void deleteWord(long id) {

        String[] array = getWordsIds();
        List<String> list;

        if (array != null) {
            list = new ArrayList<>(Arrays.asList(array));
            list.remove(String.valueOf(id));
            setWordsIds(list.toArray(new String[list.size()]));
        }
    }

}
