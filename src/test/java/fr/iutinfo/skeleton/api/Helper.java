package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private final static Logger logger = LoggerFactory.getLogger(Helper.class);
    private static UserDao dao;

    public Helper() {
        dao = BDDFactory.getDbi().open(UserDao.class);
    }

    public void initDb() {
        dao.dropUserTable();
        dao.createUserTable();
    }

    Utilisateur createUserWithName(String name) {
        Utilisateur user = new Utilisateur(0, name);
        return createUser(user);
    }

    Utilisateur createUserWithAlias(String name, String alias) {
        Utilisateur user = new Utilisateur(0, name, alias);
        return createUser(user);
    }

    Utilisateur createUserWithEmail(String name, String email) {
        Utilisateur user = new Utilisateur(0, name);
        user.setEmail(email);
        return createUser(user);
    }

    public Utilisateur createUserWithPassword(String name, String passwd, String salt) {
        Utilisateur user = new Utilisateur(0, name);
        user.setSalt(salt);
        user.setPassword(passwd);
        logger.debug("createUserWithPassword Hash : " + user.getPasswdHash());
        return createUser(user);
    }

    private Utilisateur createUser(Utilisateur user) {
        int id = dao.insert(user);
        user.setId(id);
        return user;
    }


}
