package fr.iutinfo.skeleton.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		
		Connection connection = null;
		try{
			Scanner sc = new Scanner(System.in);
			connection = DriverManager.getConnection("jdbc:sqlite:bddTeendut.db");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select * from Utilisateurs;");
			while(rs.next()){
				System.out.println("Salut " + rs.getString(2));
				System.out.println("Yes or No");
				sc.nextLine();
			}
			connection.close();
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
	}
}
