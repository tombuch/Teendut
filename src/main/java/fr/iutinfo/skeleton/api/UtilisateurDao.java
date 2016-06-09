package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UtilisateurDao {
	

	@SqlUpdate("insert into Utilisateurs (nom,prenom,mail) values (:nom, :prenom, :email)")
	@GetGeneratedKeys
	int insert(@BindBean() Utilisateur user);
	
	@SqlQuery("select * from Utilisateurs where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
	Utilisateur findByName(@Bind("nom") String nom);

	//@SqlUpdate("drop table if exists users")
	//void dropUserTable(); 


	@SqlQuery("select * from Utilisateurs where id = 1")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Utilisateur all();

	@SqlQuery("select * from Utilisateurs where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Utilisateur findById(@Bind("id") int id);
	

	void close();
}
