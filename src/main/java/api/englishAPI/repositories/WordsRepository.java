package api.englishAPI.repositories;

import api.englishAPI.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  WordsRepository extends JpaRepository<Words, Long> {

}
