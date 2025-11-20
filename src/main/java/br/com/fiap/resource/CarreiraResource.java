package br.com.fiap.resource;

import br.com.fiap.bo.CarreiraBO;
import br.com.fiap.to.CarreiraTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/carreira")
public class CarreiraResource {
    private CarreiraBO carreiraBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CarreiraTO carreira) {
        carreiraBO = new CarreiraBO();
        CarreiraTO resultado = carreiraBO.save(carreira);
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
    public Response update(@PathParam("id") Long id, @Valid CarreiraTO carreira) {
        carreiraBO = new CarreiraBO();
        carreira.setId(id);
        CarreiraTO resultado = carreiraBO.update(carreira);
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
        carreiraBO = new CarreiraBO();
        Response.ResponseBuilder response = null;

        if (carreiraBO.delete(id)) {
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
        carreiraBO = new CarreiraBO();
        CarreiraTO resultado = carreiraBO.findById(id);
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
        carreiraBO = new CarreiraBO();
        ArrayList<CarreiraTO> resultado = carreiraBO.findAll();
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
