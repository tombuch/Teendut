package fr.iutinfo.skeleton.web;

import fr.iutinfo.skeleton.api.Utilisateur;

import java.util.List;

public class SecureDto {
    private List<Utilisateur> users;
    private Utilisateur currentUser;

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setCurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

}
