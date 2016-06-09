package teendut.client;

import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import fr.iutinfo.skeleton.api.Utilisateur;

public class SeeProfiles {
	
	
    public SeeProfiles() {
    }

    public void getProfiles(Utilisateur utilisateur) {
        int id= 1;
        String str ="";
        System.out.println("Salut "+utilisateur.getPrenom()+", profils à voir :");
        Utilisateur u = new Utilisateur();
        
        while(u != null) {

            u.setId(id);
        	Entity<Utilisateur> userEntity = Entity.entity(u, MediaType.APPLICATION_JSON);
            String url = "http://localhost:8080/v1/utilisateur/"+id;    
             u = ClientBuilder.newClient().target(url).request().get(Utilisateur.class);
            if (u == null)
            	return;
            if (!(u.getId() == utilisateur.getId())) {
                System.out.println("Nom : "+u.getNom()+", Prenom : "+u.getPrenom());
              Scanner sc = new Scanner(System.in);
              while (sc.hasNextLine())
            	  str = sc.nextLine();
                while (!(str.equals("yes")) && !(str.equals("no"))) {
                    System.out.println("Veuillez répondre par yes ou no");
                    str = sc.nextLine();
                }
                sc.close();
                if (str.equals("yes")) {
                    //Like like = new Like(utilisateur.getId(),u.getId());
                    System.out.println("Vous avez aimé cette personne\n");
                } else if (str.equals("no")) {
                    System.out.println("Vous n'avez pas aimé cette personne\n");
                }
            }
            id++;
        }
    }
}
