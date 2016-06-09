package teendut.client;

import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;

import fr.iutinfo.skeleton.api.Utilisateur;

public class TeendutClient {

	public TeendutClient() {
		// TODO Auto-generated constructor stub
	}
	
	public void testhttp(){
	
		Utilisateur s = ClientBuilder.newClient()
		.target("http://localhost:8080/v1/Utilisateur")
		.request()
		.get(Utilisateur.class);
		

	System.out.println(s.getId());
	System.out.println(s.getNom());
	System.out.println(s.getPrenom());
	}
	
	public static void main(String[] args) {
		
		// PRESENTATION

		Scanner sc = new Scanner(System.in);
		System.out.println("TeenDUT");
		System.out.println("Ne repars pas qu'avec ton DUT\n");

		Utilisateur connect = null;
		
		
	}
}
