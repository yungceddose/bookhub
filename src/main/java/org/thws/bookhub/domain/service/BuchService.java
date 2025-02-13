package org.thws.bookhub.domain.service;
import org.springframework.cglib.core.Local;
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
    public List<Buch> findAllBuecher() {
        return buchRepository.findAll();
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
    public List<Buch> findBuecherByTitel(String titel) {
        return buchRepository.findByTitel(titel);
    }

    // Bücher nach Veröffentlichungsdatum suchen
    public List<Buch> findBuecherByVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum) {
        return buchRepository.findByVeroeffentlichungsdatum(veroeffentlichungsdatum);
    }

    // Bücher nach Genre suchen
    public List<Buch> findBuecherByGenre(Genre genre) {
        return buchRepository.findByGenre(genre);
    }

    // Bücher nach Seitenanzahl suchen
    public List<Buch> findBuecherBySeitenanzahl(int seitenanzahl) {
        return buchRepository.findBySeitenanzahl(seitenanzahl);
    }

}
