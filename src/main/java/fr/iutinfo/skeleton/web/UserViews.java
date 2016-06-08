package fr.iutinfo.skeleton.web;


import fr.iutinfo.skeleton.api.BDDFactory;
import fr.iutinfo.skeleton.api.*;
import org.glassfish.jersey.server.mvc.Template;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public class UserViews {
    private static UserDao dao = BDDFactory.getDbi().open(UserDao.class);

    @GET
    @Template
    public List<User> getAll() {
        return dao.all();
    }

    @GET
    @Template(name = "detail")
    @Path("/{id}")
    public User getDetail(@PathParam("id") String id) {
        User user = null;
        user = dao.findById(Integer.parseInt(id));
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

}

