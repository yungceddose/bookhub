package org.thws.bookhub.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import org.thws.bookhub.api.assembler.AutorModelAssembler;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.service.AutorService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutorController.class)
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @MockBean
    private AutorModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Autor> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setVorname("John");
        autor.setNachname("Doe");

        given(autorService.addAutor(any(Autor.class))).willReturn(autor);

        mockMvc.perform(post("/api/autoren")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"vorname\": \"John\", \"nachname\": \"Doe\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllAuthors() throws Exception {
        Page<Autor> page = new PageImpl<>(Arrays.asList(new Autor(), new Autor()));
        given(autorService.getAllAutoren(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(AutorModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Autor())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/autoren"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAutorById() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        given(autorService.getAutorById(1L)).willReturn(autor);
        given(assembler.toModel(any())).willReturn(EntityModel.of(autor));

        mockMvc.perform(get("/api/autoren/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateAuthor() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        given(autorService.updateAutor(eq(1L), any(Autor.class))).willReturn(autor);

        mockMvc.perform(put("/api/autoren/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"vorname\": \"John\", \"nachname\": \"Doe\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAuthor() throws Exception {
        Mockito.doNothing().when(autorService).deleteAutor(1L);

        mockMvc.perform(delete("/api/autoren/1"))
                .andExpect(status().isNoContent());
    }

    // Weitere Tests für die Filtermethoden...
}