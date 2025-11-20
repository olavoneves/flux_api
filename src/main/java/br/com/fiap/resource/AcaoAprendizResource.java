package br.com.fiap.resource;

import br.com.fiap.bo.AcaoAprendizBO;
import br.com.fiap.to.AcaoAprendizTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/acao-aprendiz")
public class AcaoAprendizResource {
    private AcaoAprendizBO acaoAprendizBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid AcaoAprendizTO acaoAprendiz) {
        acaoAprendizBO = new AcaoAprendizBO();
        AcaoAprendizTO resultado = acaoAprendizBO.save(acaoAprendiz);
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
    public Response update(@PathParam("id") Long id, @Valid AcaoAprendizTO acaoAprendiz) {
        acaoAprendizBO = new AcaoAprendizBO();
        acaoAprendiz.setId(id);
        AcaoAprendizTO resultado = acaoAprendizBO.update(acaoAprendiz);
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
        acaoAprendizBO = new AcaoAprendizBO();
        Response.ResponseBuilder response = null;

        if (acaoAprendizBO.delete(id)) {
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
        acaoAprendizBO = new AcaoAprendizBO();
        AcaoAprendizTO resultado = acaoAprendizBO.findById(id);
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
        acaoAprendizBO = new AcaoAprendizBO();
        ArrayList<AcaoAprendizTO> resultado = acaoAprendizBO.findAll();
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
