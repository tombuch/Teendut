package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


@Path("/secure")
public class SecureResource {
    final static Logger logger = LoggerFactory.getLogger(SecureResource.class);

    @GET
    @Path("/forall")
    public Utilisateur secureForAll(@Context SecurityContext context) {
        return (Utilisateur) context.getUserPrincipal();
    }

    @GET
    @Path("/byannotation")
    @RolesAllowed({"user"})
    public Utilisateur secureByAnnotation(@Context SecurityContext context) {
        return (Utilisateur) context.getUserPrincipal();
    }

}
