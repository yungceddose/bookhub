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
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.persistence.GenreRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGenre() {
        Genre genre = new Genre();
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.createGenre(genre);
        assertNotNull(result);
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    void testFindAllGenres() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Genre> genrePage = new PageImpl<>(Arrays.asList(new Genre(), new Genre()));
        when(genreRepository.findAll(pageable)).thenReturn(genrePage);

        Page<Genre> result = genreService.findAllGenres(pageable);
        assertEquals(2, result.getTotalElements());
        verify(genreRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdateGenre() {
        Genre genre = new Genre();
        when(genreRepository.existsById(1L)).thenReturn(true);
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        Genre result = genreService.updateGenre(1L, genre);
        assertNotNull(result);
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    void testDeleteGenre() {
        genreService.deleteGenre(1L);
        verify(genreRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindGenreByName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Genre> genrePage = new PageImpl<>(Arrays.asList(new Genre(), new Genre()));
        when(genreRepository.findByName("Fantasy", pageable)).thenReturn(genrePage);

        Page<Genre> result = genreService.findGenreByName("Fantasy", pageable);
        assertEquals(2, result.getTotalElements());
        verify(genreRepository, times(1)).findByName("Fantasy", pageable);
    }
}