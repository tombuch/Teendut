package teendut.client;

import java.util.Scanner;
import fr.iutinfo.skeleton.api.Utilisateur;


public class IntegrationClient {
	Utilisateur connexion = null;
	
	// PRESENTATION

	Scanner sc = new Scanner(System.in);
	System.out.println("TeenDUT");
	System.out.println("Ne repars pas qu'avec ton DUT\n");

	// CONNEXION OU ENREGISTREMENT

	System.out.println("Tapez enregistrer ou connecter");
	String str = sc.nextLine();
	while (!(str.equals("enregistrer")) && !(str.equals("connecter"))) {
		System.out.println("Veuillez répondre par enregistrer ou connecter");
		str = sc.nextLine();
	}
	if (str.equals("enregistrer")) {
		String nom;
		String prenom;
		String mail;
		String password;
		System.out.println("Veuillez rentrer votre nom : ");
		str = sc.nextLine();
		nom = str;
		System.out.println("Veuillez rentrer votre prenom : ");
		str = sc.nextLine();
		prenom = str;
		System.out.println("Veuillez rentrer votre mail : ");
		str = sc.nextLine();
		mail = str;
		System.out.println("Veuillez rentrer votre password : ");
		str = sc.nextLine();
		password = str;
		bdd.addUtilisateur(connexion = new Utilisateur(nom,prenom,mail,password));
		System.out.println("Vous êtes enregistré et connecté");
	} else if (str.equals("connecter")) {
		String nom;
		String password;
		System.out.println("Veuillez rentrer votre nom : ");
		str = sc.nextLine();
		nom = str;
		System.out.println("Veuillez rentrer votre password : ");
		str = sc.nextLine();
		password = str;
		for (Utilisateur uti : bdd.getUtilisateur()) {
			if (nom.equals(uti.getNom())) {
				if (password.equals(uti.getPassword())) {
					System.out.println("Vous êtes connecté");
					connexion = uti;
				}
			}
		}
		if (connexion == null) {
			System.out.println("Erreur de connexion");
			return;
		}
	}


	// PROFILS

	System.out.println("Salut "+connexion.getPrenom()+", profils à voir :");
	for(Utilisateur u : bdd.getUtilisateur()) {
		if (!u.equals(connexion)) {
			System.out.println("Nom : "+u.getNom()+", Prenom : "+u.getPrenom()+", Mail : "+u.getEmail());	
			str = sc.nextLine();
			while (!(str.equals("yes")) && !(str.equals("no"))) {
				System.out.println("Veuillez répondre par yes ou no");
				str = sc.nextLine();
			}
			if (str.equals("yes")) {
				bdd.addLikeur(connexion);
				bdd.addLike(u);
				System.out.println("Vous avez aimé cette personne\n");
			} else if (str.equals("no")) {
				System.out.println("Vous n'avez pas aimé cette personne\n");
			}
		}
	}

	bdd.addLikeur(ut6);
	bdd.addLike(connexion);
	
	// PERSONNES AIMEES

	System.out.println("Personnes aimées :");
	connexion.getLike(bdd);

	//NOMBRE DE LIKES

	System.out.println("\nNombre de likes :");
	int cpt=0;
	for (int i = 0;i < bdd.getLike().size() ;i++) {
		if (bdd.getLike().get(i).equals(connexion)) {
			cpt++;
		}
	}
	System.out.println(cpt);

	//MATCHS

	System.out.println("\nVoici avec qui tu as matché :");
	for (Utilisateur u : bdd.getUtilisateur()) {
		connexion.getMatch(u, bdd);
	}
}
