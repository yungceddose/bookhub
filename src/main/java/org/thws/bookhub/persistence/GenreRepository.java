package org.thws.bookhub.persistence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Page<Genre> findAll(Pageable pageable);

    Page<Genre> findByName(String name, Pageable pageable);
}
