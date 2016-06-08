package fr.iutinfo.skeleton.api;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class UserResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new Api();
	}

	@Test
	public void testReadUserWithNameFooAsJsonString() {
		Utilisateur user = createUserWithName("foo");
		String json = target("/user/foo").request().get(String.class);
		assertTrue(json.contains("\"name\":\"foo\""));
	}

	@Test
	public void testReadUserWithNameFooAsObject() {
		Utilisateur utilisateur = target("/user/foo").request().get(Utilisateur.class);
		assertEquals("foo", utilisateur.getNom());
	}

	@Test
	public void create_user_should_return_testCreateUserMustReturnUserWithId() {
		Utilisateur savedUser = createUserWithName("thomas");
		assertTrue(savedUser.getId() > 0);
	}

	@Test
	public void testUpdateUserName() {
		Utilisateur u = createUserWithName("thomas");
		u.setNom("yann");
		Response rep = target("/user").path("" + u.getId()).request().put(Entity.entity(u, MediaType.APPLICATION_JSON));
		Utilisateur updatedUser = rep.readEntity(Utilisateur.class);
		assertEquals("yann", updatedUser.getNom());
	}

	@Test
	public void testGetingSameUserTwice() {
		Utilisateur user1 = target("/user/foo").request().get(Utilisateur.class);
		Utilisateur user2 = target("/user/foo").request().get(Utilisateur.class);
		assertEquals(user1.toString(), user2.toString());
	}

	@Test
	public void testReadUnavailableUser() {
		int status = target("/user/bar").request().get().getStatus();
		assertEquals(404, status);
	}

	@Test
	public void tesListAllUsers() {
		createUserWithName("toto");
		createUserWithName("titi");
		List<Utilisateur> users = target("/user/").request().get(new GenericType<List<Utilisateur>>() {
		});
		assertTrue(users.size() >= 2);
	}

	@Test
	public void after_delete_read_user_sould_return_202() {
		Utilisateur u = createUserWithName("toto");
		int status = target("/user/" + u.getId()).request().delete().getStatus();
		assertEquals(ACCEPTED.getStatusCode(), status);

	}

	private Utilisateur createUserWithName(String name) {
		Utilisateur user = new Utilisateur();
		user.setNom(name);
		Entity<Utilisateur> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		return target("/user").request().post(userEntity).readEntity(Utilisateur.class);
	}
}