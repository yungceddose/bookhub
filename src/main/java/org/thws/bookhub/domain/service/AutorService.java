package org.thws.bookhub.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Autor> getAllAutoren(){
        return autorRepository.findAll();
    }

    // Neuen Autor hinzufügen (Create)
    public Autor addAutor (Autor autor){
        return autorRepository.save(autor);
    }

    // Autor nach ID abrufen (Read)
    public Autor getAutorById(Long id){
        Optional<Autor> autor= autorRepository.findById(id);
        return autor.orElse(null);
    }


    // Autor aktualisieren (Update)
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
    public void deleteAutor(Long id){
        autorRepository.deleteById(id);
    }

    // Autor(en) nach Vorname abrufen
    public List<Autor> getAutorByVorname(String name){
        return autorRepository.findByVorname(name);
    }

    // Autor(en) nach Nachname abrufen
    public List<Autor> getAutorbyNachname (String name){
        return autorRepository.findByNachname(name);
    }

    // Autoren nach Geburtsdatum abrufen
    public List<Autor> getAutorByGeburtsdatum (LocalDate date){
        return autorRepository.findByGeburtsdatum(date);
    }

    // Autoren nach Nationalität abrufen
    public List<Autor> getAutorByNationalitaet (String nationalitaet){
        return autorRepository.findByNationalitaet(nationalitaet);
    }



}
