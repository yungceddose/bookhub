package org.thws.bookhub.persistence;
import org.thws.bookhub.domain.model.Bewertung;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BewertungRepository extends JpaRepository<Bewertung, Long> {
    List<Bewertung> findByPunktzahl(int punktzahl);
}
