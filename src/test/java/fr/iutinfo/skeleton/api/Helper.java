package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private static UtilisateurDao dao;

    public Helper() {
        dao = BDDFactory.getDbi().open(UtilisateurDao.class);
    }

    public void initDb() {
        
    }

    Utilisateur createUserWithName(String name) {
        Utilisateur user = new Utilisateur();
        return createUser(user);
    }

    Utilisateur createUserWithAlias(String name, String alias) {
        Utilisateur user = new Utilisateur();
        return createUser(user);
    }

    Utilisateur createUserWithEmail(String name, String email) {
        Utilisateur user = new Utilisateur();
        user.setEmail(email);
        return createUser(user);
    }

    public Utilisateur createUserWithPassword(String name, String passwd, String salt) {
        Utilisateur user = new Utilisateur();


        return createUser(user);
    }

    private Utilisateur createUser(Utilisateur user) {
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }


}
