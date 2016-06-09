package teendut.client;

import java.util.Scanner;
import fr.iutinfo.skeleton.api.Utilisateur;


public class IntegrationClient {
	Utilisateur connexion = null;
	
	// PRESENTATION

	
	// CONNEXION OU ENREGISTREMENT


	/*} else if (str.equals("connecter")) {
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
	}*/
}
