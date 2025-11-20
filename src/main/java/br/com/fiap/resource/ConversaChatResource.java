package br.com.fiap.resource;

import br.com.fiap.bo.ConversaChatBO;
import br.com.fiap.to.ConversaChatTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/flux/conversa-chat")
public class ConversaChatResource {
    private ConversaChatBO conversaChatBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ConversaChatTO conversaChat) {
        conversaChatBO = new ConversaChatBO();
        ConversaChatTO resultado = conversaChatBO.save(conversaChat);
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
    public Response update(@PathParam("id") Long id, @Valid ConversaChatTO conversaChat) {
        conversaChatBO = new ConversaChatBO();
        conversaChat.setId(id);
        ConversaChatTO resultado = conversaChatBO.update(conversaChat);
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
        conversaChatBO = new ConversaChatBO();
        Response.ResponseBuilder response = null;

        if (conversaChatBO.delete(id)) {
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
        conversaChatBO = new ConversaChatBO();
        ConversaChatTO resultado = conversaChatBO.findById(id);
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
        conversaChatBO = new ConversaChatBO();
        ArrayList<ConversaChatTO> resultado = conversaChatBO.findAll();
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
