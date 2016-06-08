package fr.iutinfo.skeleton.utils;

import fr.iutinfo.skeleton.api.Utilisateur;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class RestClient {
	
	public String getUrlAsString (String url) {
		return ClientBuilder.newClient()//
        .target(url)
        .request()
        .get(String.class);
	}
	
	public List<Utilisateur> getUrlAsUser (String url) {
		return ClientBuilder.newClient()//
        .target(url)
        .request()
        .get(new GenericType<List<Utilisateur>>(){});
	}
	
	public Utilisateur addUser (Utilisateur user, String url) {
		Entity<Utilisateur> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
		
		return ClientBuilder.newClient()
				.target(url)
				.request()
				.post(userEntity)
				.readEntity(Utilisateur.class);
	}
}
