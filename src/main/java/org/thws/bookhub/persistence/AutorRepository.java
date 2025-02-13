package org.thws.bookhub.persistence;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    List<Autor> findByVorname(String vorname);

    List<Autor> findByNachname(String nachname);

    List<Autor> findByGeburtsdatum(LocalDate geburtsdatum);

    List<Autor> findByNationalitaet(String nationalitaet);
}

