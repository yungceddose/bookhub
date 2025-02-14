package org.thws.bookhub.persistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Benutzer;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long>{
    Page<Benutzer> findAll(Pageable pageable);

    Page<Benutzer> findByName(String name, Pageable pageable);

    Benutzer findByEmail(String email);


}
