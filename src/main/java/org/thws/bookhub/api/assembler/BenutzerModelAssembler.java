package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.BenutzerController;
import org.thws.bookhub.domain.model.Benutzer;

@Component
public class BenutzerModelAssembler implements RepresentationModelAssembler<Benutzer, EntityModel<Benutzer>> {

    @Override
    public EntityModel<Benutzer> toModel(Benutzer benutzer) {
        return EntityModel.of(benutzer,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).getBenutzerById(benutzer.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).getAllBenutzer(null)).withRel("benutzer"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).getBenutzerByEmail(benutzer.getEmail())).withRel("search-by-email"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).getBenutzerByName(benutzer.getName(), null)).withRel("search-by-name"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).updateBenutzer(benutzer.getId(), benutzer)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BenutzerController.class).deleteBenutzer(benutzer.getId())).withRel("delete"));
    }
}
