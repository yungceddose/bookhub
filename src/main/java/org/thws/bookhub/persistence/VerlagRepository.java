package org.thws.bookhub.persistence;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Verlag;

@Repository
public interface VerlagRepository extends JpaRepository<Verlag, Long> {

    Verlag findByName(String name);

    List<Verlag> findBySitz(String sitz);

    List<Verlag> findByGruendungsjahr(int gruendungsjahr);


}
