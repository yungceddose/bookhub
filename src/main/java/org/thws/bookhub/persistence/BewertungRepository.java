package org.thws.bookhub.persistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Bewertung;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BewertungRepository extends JpaRepository<Bewertung, Long> {
    Page<Bewertung> findAllBy(Pageable pageable);

    Page<Bewertung> findByPunktzahl(int punktzahl, Pageable pageable);
}
