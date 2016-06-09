package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/UtilisateurMem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurResource {
    private static Map<Integer, Utilisateur> users = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(UtilisateurResource.class);

    @POST
    public Utilisateur createUser(Utilisateur user) {
        int id = users.size();
        user.setId(id + 1);
        users.put(user.getId(), user);
        return user;
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Integer id) {
        if (users.containsKey(id)) {
            return Response.accepted().status(Status.ACCEPTED).build();
        }
        return Response.accepted().status(Status.NOT_FOUND).build();
    }

    protected Utilisateur find(String name) {
        for (Utilisateur user : users.values()) {
            if (user.getNom().equals(name)) {
                return user;
            }
        }
        return null;
    }

    protected Utilisateur find(int id) {
        return users.get(id);
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id,
                               Utilisateur user) {
        Utilisateur oldUser = find(id);
        logger.info("Should update user with id: " + id + " (" + oldUser + ") to " + user);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        oldUser.setNom(user.getNom());
        return Response.status(200).entity(oldUser).build();
    }

    @GET
    @Path("/{name}")
    public Utilisateur getUser(@PathParam("name") String name) {
        Utilisateur out = find(name);
        if (out == null) {
            throw new WebApplicationException(404);
        }
        return out;
    }

    @GET
    public List<Utilisateur> getUsers(@DefaultValue("10") @QueryParam("limit") int limit) {
        return new ArrayList<>(users.values());
    }

}
