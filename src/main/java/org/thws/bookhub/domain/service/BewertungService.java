package org.thws.bookhub.domain.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Bewertung;
import org.thws.bookhub.persistence.BewertungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BewertungService {

    private final BewertungRepository bewertungRepository;

    @Autowired
    public BewertungService(BewertungRepository bewertungRepository) {
        this.bewertungRepository = bewertungRepository;
    }

    // Bewertung erstellen
    @CacheEvict(value = "bewertungen", allEntries = true)
    public Bewertung createBewertung(Bewertung bewertung) {
        return bewertungRepository.save(bewertung);
    }

    // Alle Bewertungen abrufen
    @Cacheable("bewertungen")
    public Page<Bewertung> findAllBewertungen(Pageable pageable) {
        return bewertungRepository.findAll(pageable);
    }

    // Bewertung nach ID abrufen
    @Cacheable(value = "bewertungen", key = "#id")
    public Optional<Bewertung> findBewertungById(Long id) {
        return bewertungRepository.findById(id);
    }

    // Bewertung aktualisieren
    @CacheEvict(value = "bewertungen", allEntries = true)
    public Bewertung updateBewertung(Long id, Bewertung bewertung) {
        if (bewertungRepository.existsById(id)) {
            bewertung.setId(id); // ID beibehalten
            return bewertungRepository.save(bewertung);
        }
        return null;
    }

    // Bewertung löschen
    @CacheEvict(value = "bewertungen", allEntries = true)
    public void deleteBewertung(Long id) {
        bewertungRepository.deleteById(id);
    }

    // Bewertungen nach Punktzahl suchen
    @Cacheable(value = "bewertungen", key = "#punktzahl")
    public Page<Bewertung> findBewertungenByPunktzahl(int punktzahl, Pageable pageable) {
        return bewertungRepository.findByPunktzahl(punktzahl, pageable);
    }

}
