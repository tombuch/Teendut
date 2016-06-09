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
		.target("http://localhost:8080/v1/utilisateur")
		.request()
		.get(Utilisateur.class);
		

	System.out.println(s.getId());
	System.out.println(s.getNom());
	System.out.println(s.getPrenom());
	}
	
	public static void main(String[] args) {
		
		// PRESENTATION

		//new TeendutClient().testhttp();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("TeenDUT");
		System.out.println("Ne repars pas qu'avec ton DUT\n");

		Utilisateur connect = null;
		
		System.out.println("Tapez enregistrer ou connecter");
		String str = sc.nextLine();
		while (!(str.equals("enregistrer")) && !(str.equals("connecter"))) {
			System.out.println("Veuillez répondre par enregistrer ou connecter");
			str = sc.nextLine();
			
		}
		if (str.equals("enregistrer")){
			connect = new CreateUser().getUser();
		}
		else if (str.equals("connecter")){
			Login log = new Login();
			while (connect == null){
				connect = log.trylog();
				if (connect == null)
					System.out.println("fail connect");
			}
		}
		sc.close();
		new SeeProfiles().getProfiles(connect);
	//	System.out.println(connect.getId());
	}
}
