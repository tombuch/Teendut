package fr.iutinfo.skeleton.api;

//import org.simpleframework.util.thread.Daemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Utilisateur")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateurDBResource {
	private static UtilisateurDao dao = BDDFactory.getDbi().open(UtilisateurDao.class);
    final static Logger logger = LoggerFactory.getLogger(UtilisateurDBResource.class);

    public UtilisateurDBResource() {
		try {
			Utilisateur tmp = new Utilisateur();
			tmp.setNom("Jean");
			tmp.setPrenom("Jean");
			//tmp.setEmail("moncul@gmail.com");
			//dao.insert(tmp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@POST
	public Utilisateur createUser(Utilisateur user) {
       // user.resetPasswordHash();
        dao.insert(user);
        int id = dao.insert(user);
        user.setId(id);
		return user;
	}

	@GET
	@Path("/{nom}")
	public Utilisateur getUser(@PathParam("nom") String nom) {
		Utilisateur user = dao.findByName(nom);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	public Utilisateur getAllUsers() {
		return dao.all();
	}
	
	/*public static void main(String[] args) {
		UtilisateurDBResource test = new UtilisateurDBResource();
		System.out.println(test.getAllUsers());
	}*/
}
