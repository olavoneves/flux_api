package br.com.fiap.resource;

import br.com.fiap.bo.TendenciaMercadoBO;
import br.com.fiap.to.TendenciaMercadoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/tendencia-mercado")
public class TendenciaMercadoResource {
    private TendenciaMercadoBO tendenciaMercadoBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid TendenciaMercadoTO tendenciaMercado) {
        tendenciaMercadoBO = new TendenciaMercadoBO();
        TendenciaMercadoTO resultado = tendenciaMercadoBO.save(tendenciaMercado);
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
    public Response update(@PathParam("id") Long id, @Valid TendenciaMercadoTO tendenciaMercado) {
        tendenciaMercadoBO = new TendenciaMercadoBO();
        tendenciaMercado.setId(id);
        TendenciaMercadoTO resultado = tendenciaMercadoBO.update(tendenciaMercado);
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
        tendenciaMercadoBO = new TendenciaMercadoBO();
        Response.ResponseBuilder response = null;

        if (tendenciaMercadoBO.delete(id)) {
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
        tendenciaMercadoBO = new TendenciaMercadoBO();
        TendenciaMercadoTO resultado = tendenciaMercadoBO.findById(id);
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
        tendenciaMercadoBO = new TendenciaMercadoBO();
        ArrayList<TendenciaMercadoTO> resultado = tendenciaMercadoBO.findAll();
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
