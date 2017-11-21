package jdbcdemo;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {
	public static void main(String[] args) throws Exception {
		ConnectionToMySQL();
		Driver dObject = new Driver();
		//dObject.createTableAuthors();
		//dObject.createTableAuthorISBN();
		//dObject.createTablePublishers();
		//dObject.createTitles();
	}
	public static void getConnection() {//throws Exception {
		//Connection myConn = null;
		try{
			
			//Start database in workbench
			//https://www.youtube.com/watch?v=DCgRF4KOYIY
			String driver = "com.mysql.jdbc.Driver";
			String username = "root";
			String password = "sheethal1";
			Class.forName(driver);
			
					//1.Get a connection to database
			//here student is user id and then student is password as well
//			myConn = DriverManager.getConnection("jdbc:mysql://76.21.69.0:3306/myserverdb", username, password );
//			if(myConn!=null){
//				System.out.println("connected successfully");
//			}
//			//3. create a statemnent
//			Statement myState = myConn.createStatement();
//			//4. execute sql query
//			ResultSet myRs = myState.executeQuery("select * from employees");
//			//4. process the result sets
//			while(myRs.next())
//			{
//				System.out.println(myRs.getString("last_name")+ ", " + myRs.getString("first_name"));
//			}
//			return myConn;
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	static Connection connect;
	/*void createTableAuthors()
	{	
		try {
			getConnection();	
		String q = "CREATE TABLE authors("
				 + "authorID int PRIMARY KEY,"
				 + "firstName CHAR(20),"
				 + "lastName CHAR(20)"
				 + ");";
		
			Statement stmt = connect.createStatement();
			stmt.execute(q);
			System.out.println("successfully AUTHORS table created");
			stmt.close();
		} catch (SQLException e) {
			//Logger.getLogger(Driver.class.getName()).log(Level.SEVERE,null,ex);
			e.printStackTrace();
		}
				
	}
	void createTableAuthorISBN()
	{	
		try {
			getConnection();	
		String q = "CREATE TABLE authorISBN("
				 + "authorID int PRIMARY KEY,"
				 + "isbn CHAR(10)"
				 + ");";
		
			Statement stmt = connect.createStatement();
			stmt.execute(q);
			System.out.println("successfully authorISBN table created");
			stmt.close();
		} catch (SQLException e) {
			//Logger.getLogger(Driver.class.getName()).log(Level.SEVERE,null,ex);
			e.printStackTrace();
		}
				
	}
	void createTablePublishers()
	{	
		try {
			getConnection();	
		String q = "CREATE TABLE publishers("
				 + "publisherID int PRIMARY KEY,"
				 + "publisherName CHAR(100)"
				 + ");";
		
			Statement stmt = connect.createStatement();
			stmt.execute(q);
			System.out.println("successfully PUBLISHERS table created");
			stmt.close();
		} catch (SQLException e) {
			//Logger.getLogger(Driver.class.getName()).log(Level.SEVERE,null,ex);
			e.printStackTrace();
		}
	}
	void createTitles()
	{	
		try {
			getConnection();	
		String q = "CREATE TABLE titles("
				 + "isbn CHAR(10)PRIMARY KEY,"
				 + "title VARCHAR(500),"
				 + "editionNumber int,"
				 + "Year CHAR(4),"
				 + "publisherID int,"
				 + "price FLOAT(8,2)"//-2 did not work so i changed it to 2 unlike project description
				 + ");";
		//Resource for FLOAT: http://www.java2s.com/Code/SQL/Table-Index/Createtablesmallintdecimalandfloat.htm
			Statement stmt = connect.createStatement();
			stmt.execute(q);
			System.out.println("successfully authorISBN table created");
			stmt.close();
		} catch (SQLException e) {
			//Logger.getLogger(Driver.class.getName()).log(Level.SEVERE,null,ex);
			e.printStackTrace();
		}
	}*/
	public static void ConnectionToMySQL() {
		getConnection();
		String host = "jdbc:mysql://127.0.0.1:3306/books";
		String password = "sheethal1";
		String username = "root";
		try{
			connect = DriverManager.getConnection(host,username,password);
			System.out.println("works Connection.sql connect");
		} catch (SQLException e) {
			//System.out.println(e);
			e.printStackTrace();
			
		}
	}
	/**
	 * Reference of random word generator
	 * https://stackoverflow.com/questions/4951997/generating-random-words-in-java
	 * 
	 */
	public static String[] wordGenerator(int nameLength)
	{
		String[] words = new String[nameLength];
		Random rnd = new Random();
		for(int i =0; i< nameLength; i++)
		{
			char[] word = new char[rnd.nextInt(10)+0];//10 letter length name
			for(int j=0; j<word.length; j++)
			{
				word[j] = (char)('a' + rnd.nextInt(26));
			}
			words[i] = new String(word);
		}
		return words;
		
	}
	Statement stMt;
	public void populateTable()
	{
		try {
			String[] firstName = wordGenerator(20);
			String[] lastName = wordGenerator(20);
			String[] title = wordGenerator(20);//20 words or less, but lesst han 500 char the way we designed database
			String[] publisherName = wordGenerator(20);
			
			for(int i=1; i<19; i++)
			{
				//Lecture 19-21, slide#12
				String inAuthor = "INSERT INTO BOOKS.authors(authorID, firstName, lastName) VALUES (" + i+", ' " + firstName[i]+"', '" +lastName[i]+ "');";
				stMt.execute(inAuthor);
				String inPublisher = "INSERT INTO BOOKS.publishers (publisherID, publisherName) VALUES (" + i +", '" + publisherName[i] + "');";
				stMt.execute(inPublisher);
				String inTitles = "INSERT INTO BOOKS.titles (isbn, title, publisherID, copyright) VALUES ('isbn" + i + "', '" + title[i] + "', " +i +" , "+ (2000+i)+");";	
				stMt.execute(inTitles);
				String inAuthorISBN = "INSERT INTO BOOKS.authorISBN (authorID, isbn VALUES (" + i +", 'isbn" + i + "');";
				stMt.execute(inAuthorISBN);
				}
		}
		 catch (Exception e) {
	            e.printStackTrace();
		 }
	
	
	}
	
}