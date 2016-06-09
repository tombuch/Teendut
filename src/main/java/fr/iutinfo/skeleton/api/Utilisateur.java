package fr.iutinfo.skeleton.api;

import java.security.Principal;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilisateur {
    final static Logger logger = LoggerFactory.getLogger(Utilisateur.class);

    private String nom;
    private String prenom;
    private String dateNaissance;
    private int sexe;
	private String email;
    private int id;
    private String password;
    private int recherche;
    private String urlphoto;
    private String formation;
    private String bio;
    
	public Utilisateur(){
		
	}
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlphoto() {
		return urlphoto;
	}

	public void setUrlphoto(String urlphoto) {
		this.urlphoto = urlphoto;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

    public int getRecherche(){
    	return this.recherche;
    }
    
    public void setRecherche(int recherche){
    	this.recherche=recherche;
    }
    
    public int getSexe(){
    	return sexe;

    }
    
    public void setSexe(int sexe) {
		this.sexe = sexe;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }
    
    public String getPrenom() {
    	return prenom;
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}
	
	 public String getDateNaissance() {
	  		return dateNaissance;
	  }

	public void setDateNaissance(String date) {
		this.dateNaissance = date;
	}

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", sexe=" + sexe
				+ ", email=" + email + ", id=" + id + ", recherche=" + recherche + ", password=" + password
				+ ", urlphoto=" + urlphoto + ", formation=" + formation + ", bio=" + bio + "]";
	}

	
}
