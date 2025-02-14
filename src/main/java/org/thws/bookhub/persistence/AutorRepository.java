package org.thws.bookhub.persistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

    Page<Autor> findAll(Pageable pageable);

    Page<Autor> findByVorname(String vorname, Pageable pageable);

    Page<Autor> findByNachname(String nachname, Pageable pageable);

    Page<Autor> findByGeburtsdatum(LocalDate geburtsdatum, Pageable pageable);

    Page<Autor> findByNationalitaet(String nationalitaet, Pageable pageable);
}

