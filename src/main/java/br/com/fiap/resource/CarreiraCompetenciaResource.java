package br.com.fiap.resource;

import br.com.fiap.bo.CarreiraCompetenciaBO;
import br.com.fiap.to.CarreiraCompetenciaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/carreira-competencia")
public class CarreiraCompetenciaResource {
    private CarreiraCompetenciaBO carreiraCompetenciaBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CarreiraCompetenciaTO carreiraCompetencia) {
        carreiraCompetenciaBO = new CarreiraCompetenciaBO();
        CarreiraCompetenciaTO resultado = carreiraCompetenciaBO.save(carreiraCompetencia);
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
    public Response update(@PathParam("id") Long id, @Valid CarreiraCompetenciaTO carreiraCompetencia) {
        carreiraCompetenciaBO = new CarreiraCompetenciaBO();
        carreiraCompetencia.setId(id);
        CarreiraCompetenciaTO resultado = carreiraCompetenciaBO.update(carreiraCompetencia);
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
        carreiraCompetenciaBO = new CarreiraCompetenciaBO();
        Response.ResponseBuilder response = null;

        if (carreiraCompetenciaBO.delete(id)) {
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
        carreiraCompetenciaBO = new CarreiraCompetenciaBO();
        CarreiraCompetenciaTO resultado = carreiraCompetenciaBO.findById(id);
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
        carreiraCompetenciaBO = new CarreiraCompetenciaBO();
        ArrayList<CarreiraCompetenciaTO> resultado = carreiraCompetenciaBO.findAll();
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
