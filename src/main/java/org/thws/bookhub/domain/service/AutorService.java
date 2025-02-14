package org.thws.bookhub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.persistence.AutorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Alle Autoren abrufen
    @Cacheable("autoren")
    public Page<Autor> getAllAutoren(Pageable pageable){
        return autorRepository.findAll(pageable);
    }

    // Neuen Autor hinzufügen (Create)
    @CacheEvict(value = "autoren", allEntries = true)
    public Autor addAutor (Autor autor){
        return autorRepository.save(autor);
    }

    // Autor nach ID abrufen (Read)
    @Cacheable(value = "autoren", key = "#id")
    public Autor getAutorById(Long id){
        Optional<Autor> autor= autorRepository.findById(id);
        return autor.orElse(null);
    }


    // Autor aktualisieren (Update)
    @CacheEvict(value = "autoren", key = "#id")
    public Autor updateAutor(Long id, Autor updatedAutor){
        Optional<Autor> existingAutor = autorRepository.findById(id);
        if(existingAutor.isPresent()){
            Autor autor = existingAutor.get();
            autor.setVorname(updatedAutor.getVorname());
            autor.setNachname(updatedAutor.getNachname());
            autor.setGeburtsdatum(updatedAutor.getGeburtsdatum());
            autor.setNationalitaet(updatedAutor.getNationalitaet());
            return autorRepository.save(autor);
        }
        return null;
    }

    // Autor löschen (Delete)
    @CacheEvict(value = "autoren", key = "#id")
    public void deleteAutor(Long id){
        autorRepository.deleteById(id);
    }

    // Autor(en) nach Vorname abrufen
    @Cacheable(value = "autoren", key = "#vorname")
    public Page<Autor> getAutorByVorname(String vorname, Pageable pageable){
        return autorRepository.findByVorname(vorname, pageable);
    }

    // Autor(en) nach Nachname abrufen
    @Cacheable(value = "autoren", key = "#nachname")
    public Page<Autor> getAutorByNachname (String nachname, Pageable pageable){
        return autorRepository.findByNachname(nachname, pageable);
    }

    // Autoren nach Geburtsdatum abrufen
    @Cacheable(value = "autoren", key = "#date")
    public Page<Autor> getAutorByGeburtsdatum (LocalDate date, Pageable pageable){
        return autorRepository.findByGeburtsdatum(date, pageable);
    }

    // Autoren nach Nationalität abrufen
    @Cacheable(value = "autoren", key = "#nationalitaet")
    public Page<Autor> getAutorByNationalitaet (String nationalitaet, Pageable pageable){
        return autorRepository.findByNationalitaet(nationalitaet, pageable);
    }



}
