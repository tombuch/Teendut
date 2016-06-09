package teendut.client;


import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import fr.iutinfo.skeleton.api.Utilisateur;

public class Login {

	public Login() {
	}

	public Utilisateur trylog() {
		Utilisateur user = new Utilisateur();
		Scanner sc = new Scanner(System.in);

		System.out.println("Veuillez rentrer votre mail : ");
		user.setEmail(sc.nextLine());

		System.out.println("Veuillez rentrer votre password : ");
		user.setPassword(sc.nextLine());

		String url = "http://localhost:8080/v1/utilisateur/login";
		Entity<Utilisateur> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);

		
		 user = ClientBuilder.newClient() .target(url) .request()
		  .post(userEntity) .readEntity(Utilisateur.class);
		 
		sc.close();
		return user;
	}
}
