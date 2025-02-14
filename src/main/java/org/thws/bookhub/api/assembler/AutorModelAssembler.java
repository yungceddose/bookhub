package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.AutorController;
import org.thws.bookhub.domain.model.Autor;

@Component
public class AutorModelAssembler implements RepresentationModelAssembler<Autor, EntityModel<Autor>> {

    @Override
    public EntityModel<Autor> toModel(Autor autor) {
        return EntityModel.of(autor,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAutorById(autor.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAllAuthors(null)).withRel("autoren"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAutorenByNachname(autor.getNachname(), null)).withRel("search-by-last-name"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAutorenByVorname(autor.getVorname(), null)).withRel("search-by-first-name"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAutorenByNationalitaet(autor.getNationalitaet(), null)).withRel("search-by-nationality"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).getAutorenByGeburtsdatum(autor.getGeburtsdatum(), null)).withRel("search-by-birthdate"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).updateAuthor(autor.getId(), null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AutorController.class).deleteAuthor(autor.getId())).withRel("delete"));
    }
}
