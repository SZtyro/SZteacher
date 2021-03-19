package pl.sztyro.szteacher.model;

import javax.persistence.*;

@Entity
public class List {

    @Id
    @GeneratedValue
    long id;

    @Column
    String name;

//    @Column
//    User owner;

    @Column
    boolean isPrivate = true;

    @Column
    String words;

    @ManyToOne(optional = false, targetEntity = Language.class)
    @JoinColumn(name = "language_id")
    Language language;




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
}
