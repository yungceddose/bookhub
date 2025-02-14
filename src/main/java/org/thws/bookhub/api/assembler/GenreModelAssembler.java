package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.GenreController;
import org.thws.bookhub.domain.model.Genre;

@Component
public class GenreModelAssembler implements RepresentationModelAssembler<Genre, EntityModel<Genre>> {

    @Override
    public EntityModel<Genre> toModel(Genre genre) {
        return EntityModel.of(genre,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).getAllGenres(null)).withRel("genres"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).getGenreByName(genre.getName(), null)).withRel("search-by-name"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).updateGenre(genre.getId(), null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GenreController.class).deleteGenre(genre.getId())).withRel("delete"));
    }
}
