package teendut.client;

import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import fr.iutinfo.skeleton.api.Utilisateur;

public class CreateUser {

	Utilisateur utilisateur;
	
	public CreateUser(){
	}
	
	public Utilisateur getUser() {
		
		Scanner sc = new Scanner(System.in);
		String str;
		utilisateur = new Utilisateur();
		
		System.out.println("Veuillez rentrer votre nom : ");
		str = sc.nextLine();
		utilisateur.setNom(str);
		System.out.println("Veuillez rentrer votre prenom : ");
		str = sc.nextLine();
		utilisateur.setPrenom(str);
		System.out.println("Veuillez rentrer votre mail : ");
		str = sc.nextLine();
		utilisateur.setEmail(str);
		System.out.println("Veuillez rentrer votre password : ");
		str = sc.nextLine();
		utilisateur.setPassword(str);
		
		Entity<Utilisateur> userEntity = Entity.entity(utilisateur, MediaType.APPLICATION_JSON);
		
		ClientBuilder.newClient()
		.target("http://localhost/v1/Utilisateur")
		.request()
		.post(userEntity)
		.readEntity(Utilisateur.class);
	
		sc.close();
		return utilisateur;
	}
	
}
