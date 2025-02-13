package org.thws.bookhub.persistence;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;

@Repository
public interface BuchRepository extends JpaRepository<Buch, Long>{

    List<Buch> findByTitel(String titel);

    List<Buch> findByVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum);

    List<Buch> findByGenre(Genre genre);

    List<Buch> findBySeitenanzahl(int seitenanzahl);
    
}
