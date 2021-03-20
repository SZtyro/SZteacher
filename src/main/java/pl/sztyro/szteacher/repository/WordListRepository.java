package pl.sztyro.szteacher.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.sztyro.szteacher.model.WordList;

import java.util.List;

public interface WordListRepository extends CrudRepository<WordList, Long> {

    @Query("FROM WordList ")
    List<WordList> findAllPublic();

    @Query("FROM WordList w WHERE w.isPrivate = true")
    List<WordList> findAllPrivate();
}
