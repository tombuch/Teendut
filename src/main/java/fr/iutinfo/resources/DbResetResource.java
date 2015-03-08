package fr.iutinfo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.iutinfo.App;
import fr.iutinfo.beans.Level;
import fr.iutinfo.dao.FriendsRelationsDao;
import fr.iutinfo.dao.InstructionsDao;
import fr.iutinfo.dao.LevelDao;
import fr.iutinfo.dao.UserDao;
import fr.iutinfo.utils.Utils;


@Path("/resetDb")
@Produces(MediaType.TEXT_HTML)
public class DbResetResource {

	private static FriendsRelationsDao friendDao = App.dbi.open(FriendsRelationsDao.class);
	private static UserDao userDao = App.dbi.open(UserDao.class);
	private static LevelDao levelDao = App.dbi.open(LevelDao.class);
	private static InstructionsDao instructionsDao = App.dbi.open(InstructionsDao.class);


	
	@GET
	public String whichDatabase() {
		return "<ul>"
					+ "<li><a href='resetDb/users'>Reset users table</a></li>"
					+ "<li><a href='resetDb/relations'>Reset relations table</a></li>"
					+ "<li><a href='resetDb/levels'>Reset levels table</a></li>"
					+ "<li><a href='resetDb/instructions'>Reset instructions table</a></li>"
					+ "<li><a href='resetDb/all'>Reset ALL tables</a></li>"
				+ "</ul>";
	}
	
	
	@GET
	@Path("all")
	public String resetDatabase() {

		resetDbUsers();
		resetDbInstructions();
		resetDbLevels(); 
		resetDbFriendsRelations();
		

		return "All Tables Reset";
	}

	@GET
	@Path("users")
	public String resetDbUsers() {
		userDao.dropUserTable();

		userDao.createUserTable();

		userDao.insert("toto", Utils.hashMD5("toto"), "toto@toto.to");
		userDao.insert("titi", Utils.hashMD5("titi"), "titi@titi.ti");

		return "Table user Reset";
	}


	@GET
	@Path("relations")
	public String resetDbFriendsRelations() {
		friendDao.dropFriendsRelationsTable();

		friendDao.createFriendsRelationsTable();

		friendDao.createRelation(2, 1);
		friendDao.createRelation(1, 2);
		return "Table friendsRelations Reset";
	}


	@GET
	@Path("levels")
	public String resetDbLevels() {
		levelDao.dropLevelsTable();
		
		levelDao.createLevelsTable();

		levelDao.insert("Niveau 1", // name
				"1 2 1," + 			//
				"1 0 1," + 			// Level content
				"1 3 1", 			//
				"1", 				// instructions id list
				2,					// max number of instructions
				1,					// author id
				2);					// next level id

		levelDao.insert("Niveau 2", // name
				"1 1 1," + 			//
				"2 0 3," + 			// Level content
				"1 1 1", 			//
				"1,3", 				// instructions id list
				3,					// max number of instructions
				1,					// author id
				3);					// next level id
		
		
		levelDao.insert("Niveau 3", // name
				"1 2 1," + 			//
				"1 0 1," + 			// Level content
				"1 0 1," +
				"1 3 1", 			//
				"1,5", 				// instructions id list
				2,					// max number of instructions
				1,					// author id
				4);					// next level id
		
		levelDao.insert("Niveau 4", // name
				"2 1 3," + 			//
				"0 1 0," + 			// Level content
				"0 0 0", 			//
				"1,3,5", 				// instructions id list
				4,					// max number of instructions
				1,					// author id
				5);					// next level id
		
		levelDao.insert("Niveau 5", // name
				"2 1 1 1," + 			//
				"0 0 1 1," + 			// Level content
				"1 0 0 1," +
				"1 1 0 3", 			//
				"1,3,4,5", 				// instructions id list
				5,					// max number of instructions
				1,					// author id
				6);					// next level id
		
		levelDao.insert("Niveau 6", // name
				"2 1 1 1," + 			//
				"0 1 1 1," + 			// Level content
				"0 1 1 1," +
				"0 0 0 3", 			//
				"1,2,4,5", 				// instructions id list
				5,					// max number of instructions
				1,					// author id
				7);					// next level id
		
		levelDao.insert("Niveau 7", // name
				"3 0 0," + 			//
				"1 1 0," + 			// Level content
				"0 0 0," +
				"2 1 1", 			//
				"1,2,3,5", 				// instructions id list
				5,					// max number of instructions
				1,					// author id
				-1);					// next level id

		return "Table levels Reset";
	}


	@GET
	@Path("instructions")
	public String resetDbInstructions() {
		instructionsDao.dropInstructionsTable();

		instructionsDao.createInstructionsTable();

		instructionsDao.insert("Avancer", "player.moveForward();", 65, 0);					// ID 1
		instructionsDao.insert("Reculer", "player.moveBackward();", 65, 0);					// ID 2	
		instructionsDao.insert("Tourner à gauche", "player.turnLeft();", 65, 0);			// ID 3
		instructionsDao.insert("Tourner à droite", "player.turnRight();", 65, 0);			// ID 4
		instructionsDao.insert("Répeter 3 fois", "for (var i = 0; i < 3; ++i)", 100, 1);	// ID 5

		return "Table instructions Reset";
	}

}