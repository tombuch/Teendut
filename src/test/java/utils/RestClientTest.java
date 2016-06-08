package utils;

import fr.iutinfo.skeleton.api.BDDFactory;
import fr.iutinfo.skeleton.api.Utilisateur;
import fr.iutinfo.skeleton.api.UtilisateurDBResource;
import fr.iutinfo.skeleton.api.UtilisateurDao;
import fr.iutinfo.skeleton.utils.RestClient;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RestClientTest extends JerseyTest {
    private static UtilisateurDao userDao;

    @Override
    protected Application configure() {
        UtilisateurDao User = BDDFactory.getDbi().open(UtilisateurDao.class);
        //return new Api();
        return new ResourceConfig(UtilisateurDBResource.class);
    }

    @Before
    public void cleanupDb() {
  //      userDao.dropUserTable();
    //    userDao.createUserTable();
    }

    @Test
    public void should_return_2_clients() {
        String baseUrl = this.getBaseUri() + "userdb/";
        RestClient client = new RestClient();
        client.addUser(new Utilisateur(), baseUrl);
        client.addUser(new Utilisateur(), baseUrl);
        List<Utilisateur> users = client.getUrlAsUser(baseUrl);
        assertEquals(2, users.size());
    }
}
