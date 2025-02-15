package org.thws.bookhub.domain.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.service.AutorService;
import org.thws.bookhub.persistence.AutorRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor("Max", "Mustermann", LocalDate.of(1985, 5, 20), "Deutsch");
    }

    @Test
    void testGetAllAutoren() {
        Pageable pageable = PageRequest.of(0, 10);
        when(autorRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(autor)));

        Page<Autor> result = autorService.getAllAutoren(pageable);

        assertEquals(1, result.getTotalElements());
        verify(autorRepository, times(1)).findAll(pageable);
    }

    @Test
    void testAddAutor() {
        when(autorRepository.save(autor)).thenReturn(autor);

        Autor newAutor = autorService.addAutor(autor);

        assertEquals("Max", newAutor.getVorname());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    void testGetAutorById() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        Autor foundAutor = autorService.getAutorById(1L);

        assertNotNull(foundAutor);
        assertEquals("Max", foundAutor.getVorname());
        verify(autorRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAutor() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepository.save(autor)).thenReturn(autor);

        Autor updatedAutorInfo = new Autor("Maximilian", "Mustermann", LocalDate.of(1985, 5, 20), "Deutsch");
        Autor updatedAutor = autorService.updateAutor(1L, updatedAutorInfo);

        assertEquals("Maximilian", updatedAutor.getVorname());
        verify(autorRepository, times(1)).save(autor);
    }

    @Test
    void testDeleteAutor() {
        autorService.deleteAutor(1L);

        verify(autorRepository, times(1)).deleteById(1L);
    }
}