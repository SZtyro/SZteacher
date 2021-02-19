package pl.sztyro.szteacher.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sztyro.szteacher.model.Language;

public interface LanguageRepository extends CrudRepository<Language, Long> {
}
