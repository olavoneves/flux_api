package br.com.fiap.resource;

import br.com.fiap.bo.CompetenciaBO;
import br.com.fiap.to.CompetenciaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/competencia")
public class CompetenciaResource {
    private CompetenciaBO competenciaBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CompetenciaTO competencia) {
        competenciaBO = new CompetenciaBO();
        CompetenciaTO resultado = competenciaBO.save(competencia);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid CompetenciaTO competencia) {
        competenciaBO = new CompetenciaBO();
        competencia.setId(id);
        CompetenciaTO resultado = competenciaBO.update(competencia);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        competenciaBO = new CompetenciaBO();
        Response.ResponseBuilder response = null;

        if (competenciaBO.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        competenciaBO = new CompetenciaBO();
        CompetenciaTO resultado = competenciaBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        competenciaBO = new CompetenciaBO();
        ArrayList<CompetenciaTO> resultado = competenciaBO.findAll();
        Response.ResponseBuilder response = null;

        if (!resultado.isEmpty()) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
