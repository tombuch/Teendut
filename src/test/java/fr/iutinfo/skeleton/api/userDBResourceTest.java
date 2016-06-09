package fr.iutinfo.skeleton.api;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class userDBResourceTest extends JerseyTest {
	private Helper h;
    Logger logger = LoggerFactory.getLogger(userDBResourceTest.class);


	@Override
	protected Application configure() {
		return new Api();
	}

	@Before
	public void init() {
		h = new Helper();
		h.initDb();
	}

	@Test
	public void read_should_return_a_user_as_object() {
		h.createUserWithName("foo");
		Utilisateur utilisateur = target("/userdb/foo").request().get(Utilisateur.class);
		assertEquals("foo", utilisateur.getNom());
	}

	@Test
	public void read_user_should_return_good_alias() {
		h.createUserWithAlias("richard stallman", "rms");
		Utilisateur user = target("/userdb/richard stallman").request().get(Utilisateur.class);
		assertEquals("rms", user.getNom());
	}

	@Test
	public void read_user_should_return_good_email() {
		h.createUserWithEmail("Ian Murdock", "ian@debian.org");
		Utilisateur user = target("/userdb/Ian Murdock").request().get(Utilisateur.class);
		assertEquals("ian@debian.org", user.getEmail());
	}

	@Test
	public void create_should_return_the_user_with_valid_id() {
		Utilisateur user = new Utilisateur();
		user.setNom("thomas");
		Entity<Utilisateur> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		String json = target("/userdb").request().post(userEntity).readEntity(String.class);
		assertEquals("{\"id\":1,\"name\":\"thomas\"", json.substring(0, 23));
	}

	@Test
    public void list_should_return_all_users() {
        h.createUserWithName("foo");
        h.createUserWithName("bar");
        List<Utilisateur> users = target("/userdb/").request().get(new GenericType<List<Utilisateur>>() {
        });
        assertEquals(2, users.size());
    }

	@Test
	public void list_all_must_be_ordered() {
		h.createUserWithName("foo");
		h.createUserWithName("bar");
		List<Utilisateur> users = target("/userdb/").request().get(new GenericType<List<Utilisateur>>() {
		});
		assertEquals("foo", users.get(0).getNom());
	}

	@Test
	public void should_insert_utilisateur_in_bdd() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("Nom");
		utilisateur.setPrenom("prenom");
		utilisateur.setPassword("mot de passe");
		utilisateur.setEmail("mon@email.com");
		Entity<Utilisateur> userEntity = Entity.entity(utilisateur, MediaType.APPLICATION_JSON);


		Utilisateur savedUtilisateur = target("/utilisateur/").request().post(userEntity).readEntity(Utilisateur.class);
		Assert.assertTrue(utilisateur.getId() < savedUtilisateur.getId());
	}
}
