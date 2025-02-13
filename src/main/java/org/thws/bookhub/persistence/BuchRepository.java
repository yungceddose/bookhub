package org.thws.bookhub.persistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;

@Repository
public interface BuchRepository extends JpaRepository<Buch, String>{
    Page<Buch> findAllBy(Pageable pageable);

    Page<Buch> findByTitel(String titel, Pageable pageable);

    Page<Buch> findByVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum, Pageable pageable);

    Page<Buch> findByGenre(Genre genre, Pageable pageable);

    Page<Buch> findBySeitenanzahl(int seitenanzahl, Pageable pageable);

    Buch findByIsbnNummer(String isbnNummer);
    
}
