package br.com.fiap.resource;

import br.com.fiap.bo.MatchCarreiraBO;
import br.com.fiap.to.MatchCarreiraTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/match-carreira")
public class MatchCarreiraResource {
    private MatchCarreiraBO matchCarreiraBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid MatchCarreiraTO matchCarreira) {
        matchCarreiraBO = new MatchCarreiraBO();
        MatchCarreiraTO resultado = matchCarreiraBO.save(matchCarreira);
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
    public Response update(@PathParam("id") Long id, @Valid MatchCarreiraTO matchCarreira) {
        matchCarreiraBO = new MatchCarreiraBO();
        matchCarreira.setId(id);
        MatchCarreiraTO resultado = matchCarreiraBO.update(matchCarreira);
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
        matchCarreiraBO = new MatchCarreiraBO();
        Response.ResponseBuilder response = null;

        if (matchCarreiraBO.delete(id)) {
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
        matchCarreiraBO = new MatchCarreiraBO();
        MatchCarreiraTO resultado = matchCarreiraBO.findById(id);
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
        matchCarreiraBO = new MatchCarreiraBO();
        ArrayList<MatchCarreiraTO> resultado = matchCarreiraBO.findAll();
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
