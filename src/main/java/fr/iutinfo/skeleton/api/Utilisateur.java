package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilisateur {
    final static Logger logger = LoggerFactory.getLogger(Utilisateur.class);

    private String nom;
    private String prenom;
    private String email;
    private int id;
    private String password;
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Utilisateur(){
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
}
