package org.thws.bookhub.persistence;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Benutzer;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long>{
    List<Benutzer> findByName(String name);

    List<Benutzer> findByEmail(String email);


}
