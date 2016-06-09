package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

import javax.inject.Singleton;

@Singleton
public class BDDFactory {
    private static DBI dbi = null;

    public static DBI getDbi() {
        if(dbi == null) {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:database/bddTeendut.db");
            dbi = new DBI(ds);
        }
        return dbi;
    }
}