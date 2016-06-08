package fr.iutinfo.skeleton.web;


import fr.iutinfo.skeleton.api.BDDFactory;
import fr.iutinfo.skeleton.api.Utilisateur;
import fr.iutinfo.skeleton.api.UtilisateurDao;

import org.glassfish.jersey.server.mvc.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public class SecureViews {
    final static Logger logger = LoggerFactory.getLogger(SecureViews.class);
    private static UtilisateurDao dao = BDDFactory.getDbi().open(UtilisateurDao.class);

    @GET
    @Template
    @RolesAllowed({"user"})
    @Path("/secure")
    public SecureDto allUsers(@Context SecurityContext context) {
        SecureDto secureDto = new SecureDto();
        secureDto.setUsers(dao.all());
        secureDto.setCurrentUser((Utilisateur) context.getUserPrincipal());
        return secureDto;
    }

    @GET
    @Path("/login")
    public Utilisateur login(@Context SecurityContext context, @QueryParam("user") String oldLogin, @Context UriInfo uriInfo) throws URISyntaxException {
        Utilisateur currentUser = (Utilisateur) context.getUserPrincipal();
        Utilisateur oldUser = dao.findByName(oldLogin);
      //  if (oldUser == null) {
       //     oldUser = Utilisateur.getAnonymousUser();
        //}
        logger.debug("User - current : " + currentUser.toString() + ", old : " + oldUser.toString());
        if (currentUser.getId() == oldUser.getId()) {
            requestLoginForm();
        } else {
            setCookieAndRedirectToUserDetail(currentUser, uriInfo);
        }
        return null;
    }

    private void requestLoginForm() {
        throw new WebApplicationException(Response
                .status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Mon application\"")
                .entity("Ressouce requires login.").build());
    }

    private void setCookieAndRedirectToUserDetail(Utilisateur currentUser, UriInfo uriInfo) throws URISyntaxException {
        URI location = UriBuilder.fromResource(UserViews.class).path("/" + currentUser.getId()).build();
        logger.debug("Redirect to " + location);
        throw new WebApplicationException(Response
                .temporaryRedirect(location)
                .cookie(new NewCookie("user", currentUser.getNom()))
                .build()
        );
    }
}

