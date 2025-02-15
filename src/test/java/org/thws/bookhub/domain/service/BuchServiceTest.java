package org.thws.bookhub.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.persistence.BuchRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuchServiceTest {

    @Mock
    private BuchRepository buchRepository;

    @InjectMocks
    private BuchService buchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBuch() {
        Buch buch = new Buch();
        when(buchRepository.save(any(Buch.class))).thenReturn(buch);

        Buch result = buchService.createBuch(buch);
        assertNotNull(result);
        verify(buchRepository, times(1)).save(buch);
    }

    @Test
    void testFindAllBuecher() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Buch> buchPage = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        when(buchRepository.findAll(pageable)).thenReturn(buchPage);

        Page<Buch> result = buchService.findAllBuecher(pageable);
        assertEquals(2, result.getTotalElements());
        verify(buchRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindBuchById() {
        Buch buch = new Buch();
        when(buchRepository.findById("1234567890")).thenReturn(Optional.of(buch));

        Optional<Buch> result = buchService.findBuchById("1234567890");
        assertTrue(result.isPresent());
        verify(buchRepository, times(1)).findById("1234567890");
    }

    @Test
    void testUpdateBuch() {
        Buch buch = new Buch();
        when(buchRepository.existsById("1234567890")).thenReturn(true);
        when(buchRepository.save(any(Buch.class))).thenReturn(buch);

        Buch result = buchService.updateBuch("1234567890", buch);
        assertNotNull(result);
        verify(buchRepository, times(1)).save(buch);
    }

    @Test
    void testDeleteBuch() {
        buchService.deleteBuch("1234567890");
        verify(buchRepository, times(1)).deleteById("1234567890");
    }

    @Test
    void testFindBuecherByTitel() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Buch> buchPage = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        when(buchRepository.findByTitel("Ein Titel", pageable)).thenReturn(buchPage);

        Page<Buch> result = buchService.findBuecherByTitel("Ein Titel", pageable);
        assertEquals(2, result.getTotalElements());
        verify(buchRepository, times(1)).findByTitel("Ein Titel", pageable);
    }

    @Test
    void testFindBuecherByVeroeffentlichungsdatum() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Buch> buchPage = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        LocalDate date = LocalDate.of(2020, 1, 1);
        when(buchRepository.findByVeroeffentlichungsdatum(date, pageable)).thenReturn(buchPage);

        Page<Buch> result = buchService.findBuecherByVeroeffentlichungsdatum(date, pageable);
        assertEquals(2, result.getTotalElements());
        verify(buchRepository, times(1)).findByVeroeffentlichungsdatum(date, pageable);
    }

    @Test
    void testFindBuecherByGenre() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Buch> buchPage = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        Genre genre = new Genre();
        genre.setName("Fiction");

        when(buchRepository.findByGenre(genre, pageable)).thenReturn(buchPage);

        Page<Buch> result = buchService.findBuecherByGenre(genre, pageable);
        assertEquals(2, result.getTotalElements());
        verify(buchRepository, times(1)).findByGenre(genre, pageable);
    }

    @Test
    void testFindBuecherBySeitenanzahl() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Buch> buchPage = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        when(buchRepository.findBySeitenanzahl(300, pageable)).thenReturn(buchPage);

        Page<Buch> result = buchService.findBuecherBySeitenanzahl(300, pageable);
        assertEquals(2, result.getTotalElements());
        verify(buchRepository, times(1)).findBySeitenanzahl(300, pageable);
    }
}