package org.thws.bookhub.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.thws.bookhub.api.assembler.GenreModelAssembler;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.domain.service.GenreService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @MockBean
    private GenreModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Genre> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGenre() throws Exception {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Thriller");

        given(genreService.createGenre(any(Genre.class))).willReturn(genre);

        mockMvc.perform(post("/api/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Thriller\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllGenres() throws Exception {
        Page<Genre> page = new PageImpl<>(Arrays.asList(new Genre(), new Genre()));
        given(genreService.findAllGenres(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(any(Page.class), any(GenreModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Genre())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateGenre() throws Exception {
        Genre genre = new Genre();
        given(genreService.updateGenre(any(Long.class), any(Genre.class))).willReturn(genre);

        mockMvc.perform(put("/api/genres/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Thriller\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteGenre() throws Exception {
        mockMvc.perform(delete("/api/genres/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetGenreByName() throws Exception {
        Page<Genre> page = new PageImpl<>(Arrays.asList(new Genre(), new Genre()));
        given(genreService.findGenreByName(any(String.class), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(any(Page.class), any(GenreModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Genre())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/genres/by-name")
                        .param("name", "Thriller"))
                .andExpect(status().isOk());
    }
}