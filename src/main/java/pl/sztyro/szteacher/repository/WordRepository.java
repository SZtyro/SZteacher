package pl.sztyro.szteacher.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sztyro.szteacher.model.Word;

public interface WordRepository extends CrudRepository<Word,Long> {
}
