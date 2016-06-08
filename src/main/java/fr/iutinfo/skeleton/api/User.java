package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);

    private String nom;
    private String prenom;
    private String email;
    
    public User(String nom, String prenom, String mail) {
        this.nom=nom;
        this.prenom=prenom;
        this.email=mail;
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
}
