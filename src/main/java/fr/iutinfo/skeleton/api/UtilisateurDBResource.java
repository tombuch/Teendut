package fr.iutinfo.skeleton.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/userdb")
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
			tmp.setEmail("moncul@gmail.com");
			tmp.setSexe(1);
			tmp.setRecherche(2);
			tmp.setDateNaissance("12/12/1995");
			tmp.setBio("slt c jean mdr tro bi1 , je cherch dla zouz");
			tmp.setFormation("Ordinateur");
			tmp.setPassword("kikoolol123");
			tmp.setUrlphoto("http://tg.com/jean");
			System.out.println(tmp.getDateNaissance().toString());
			System.out.println(dao.findByName("heyse").getNom());
			dao.insert(tmp);
		} catch (Exception e) {
			System.out.println(e);
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
	public List<Utilisateur> getAllUsers() {
		return dao.all();
	}
	
	public static void main(String[] args) {
		UtilisateurDBResource test = new UtilisateurDBResource();
		System.out.println(test.getAllUsers());
	}
}
