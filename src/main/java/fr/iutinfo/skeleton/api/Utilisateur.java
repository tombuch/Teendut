package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class Utilisateur implements Principal {
    final static Logger logger = LoggerFactory.getLogger(Utilisateur.class);

    private String nom;
    private String prenom;
    private String email;
    private int id;
    
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

	@Override
	public String getName() {
		return this.nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
