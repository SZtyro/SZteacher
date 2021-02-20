package pl.sztyro.szteacher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.*;

@Entity
public class Language {

    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = ".*([a-z][a-z]_[A-Z][A-Z]$)", message = "Wrong language!.")
    private String lang;

    public Language(String code) {
        this.lang = code;
    }

    public Language() {

    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
