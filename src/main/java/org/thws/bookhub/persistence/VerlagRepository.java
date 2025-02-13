package org.thws.bookhub.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Verlag;

@Repository
public interface VerlagRepository extends JpaRepository<Verlag, Long> {

    Page<Verlag> findAllBy(Pageable pageable);

    Verlag findByName(String name);

    Page<Verlag> findBySitz(String sitz, Pageable pageable);

    Page<Verlag> findByGruendungsjahr(int gruendungsjahr, Pageable pageable);


}
