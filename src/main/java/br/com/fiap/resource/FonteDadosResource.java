package br.com.fiap.resource;

import br.com.fiap.bo.FonteDadosBO;
import br.com.fiap.to.FonteDadosTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/fonte-dados")
public class FonteDadosResource {
    private FonteDadosBO fonteDadosBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid FonteDadosTO fonteDados) {
        fonteDadosBO = new FonteDadosBO();
        FonteDadosTO resultado = fonteDadosBO.save(fonteDados);
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
    public Response update(@PathParam("id") Long id, @Valid FonteDadosTO fonteDados) {
        fonteDadosBO = new FonteDadosBO();
        fonteDados.setId(id);
        FonteDadosTO resultado = fonteDadosBO.update(fonteDados);
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
        fonteDadosBO = new FonteDadosBO();
        Response.ResponseBuilder response = null;

        if (fonteDadosBO.delete(id)) {
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
        fonteDadosBO = new FonteDadosBO();
        FonteDadosTO resultado = fonteDadosBO.findById(id);
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
        fonteDadosBO = new FonteDadosBO();
        ArrayList<FonteDadosTO> resultado = fonteDadosBO.findAll();
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
