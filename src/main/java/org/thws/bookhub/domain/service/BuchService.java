package org.thws.bookhub.domain.service;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.persistence.BuchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BuchService {

    private final BuchRepository buchRepository;

    @Autowired
    public BuchService(BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    // Buch erstellen
    public Buch createBuch(Buch buch) {
        return buchRepository.save(buch);
    }

    // Alle Bücher abrufen
    public Page<Buch> findAllBuecher(Pageable pageable) {
        return buchRepository.findAllBy(pageable);
    }

    // Buch nach ID abrufen
    public Optional<Buch> findBuchById(String isbn) {
        return buchRepository.findById(isbn);
    }

    // Buch aktualisieren
    public Buch updateBuch(String isbn, Buch buch) {
        if (buchRepository.existsById(isbn)) {
            buch.setIsbnNummer(isbn); // ISBN beibehalten
            return buchRepository.save(buch);
        }
        return null;
    }

    // Buch löschen
    public void deleteBuch(String isbn) {
        buchRepository.deleteById(isbn);
    }

    // Bücher nach Titel suchen
    public Page<Buch> findBuecherByTitel(String titel, Pageable pageable) {
        return buchRepository.findByTitel(titel, pageable);
    }

    // Bücher nach Veröffentlichungsdatum suchen
    public Page<Buch> findBuecherByVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum, Pageable pageable) {
        return buchRepository.findByVeroeffentlichungsdatum(veroeffentlichungsdatum, pageable);
    }

    // Bücher nach Genre suchen
    public Page<Buch> findBuecherByGenre(Genre genre, Pageable pageable) {
        return buchRepository.findByGenre(genre, pageable);
    }

    // Bücher nach Seitenanzahl suchen
    public Page<Buch> findBuecherBySeitenanzahl(int seitenanzahl, Pageable pageable) {
        return buchRepository.findBySeitenanzahl(seitenanzahl, pageable);
    }

}
