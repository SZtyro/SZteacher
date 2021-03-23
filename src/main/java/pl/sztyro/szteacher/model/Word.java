package pl.sztyro.szteacher.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import pl.sztyro.szteacher.enums.Language;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
})
@Entity
public class Word {


    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Language language;

    @Column(nullable = false)
    private String original;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private Language translationLanguage;

    @Column(nullable = false,columnDefinition = "text[]")
    @NotBlank
    @Type(type = "string-array")
    private String[] translation;

    @Column(nullable = false)
    private String author;

    public Word() {
    }

    public Word(String author,Language language, String original, Language translationLanguage, String[] translation) {
        this.author = author;
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

    public String[] getTranslation() {
        return translation;
    }

    public void setTranslation(String[] translation) {
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
