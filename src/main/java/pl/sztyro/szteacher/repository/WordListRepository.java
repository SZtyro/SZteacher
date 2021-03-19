package pl.sztyro.szteacher.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.sztyro.szteacher.model.Language;
import pl.sztyro.szteacher.model.Word;

import java.util.List;

public interface ListRepository extends CrudRepository<List, Long> {

}
