package pl.sztyro.szteacher.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.model.Word;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {

    @Query("SELECT w FROM Word w WHERE w.original LIKE :filter%")
    List<Word> findAllByOriginalContains(String filter);

    @Query("SELECT w FROM Word w WHERE w.original=:original AND w.language=:language AND w.translationLanguage=:translationLanguage")
    Word findWordByLanguageAndOriginal(Language language, Language translationLanguage, String original);


}
