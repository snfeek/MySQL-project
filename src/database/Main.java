package database;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.io.*;
public class Main {

	private static final String NULL = null;
	public static void main(String[] args) 
		throws Exception
	{
		menu();
		// TODO Auto-generated method stub
		//getConnection();		//polaczenie z baza danych
		//createTable();		
		//post();
		//getSongs();
		//selectSQL();
		
		
		
	}
	public static void menu()throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int option;
		
		for(;;)
		{
			
			System.out.println("Database: marceli");
			System.out.println("Choose option:");
			System.out.println("1. Create");
			System.out.println("2. Read");
			System.out.println("3. Update");
			System.out.println("4. Drop");
			System.out.println("select: ");
			option=Integer.parseInt(br.readLine());
			switch(option)
			{
			case 1:
				System.out.println("Select: \n1. Create Table\n2. Insert into the table");
				option=Integer.parseInt(br.readLine());
				switch(option)
				{
				case 1:
					createTable();
					
					break;
				case 2:
					post();
					break;
					
					default:
						System.exit(1);
						break;
				}
				
				break;
				
			case 2:
				int select;
				System.out.println("** Select option **");
				System.out.println("1. Show all songs");
				System.out.println("2. Show all songs from X genre");
				System.out.println("3. Show all songs from X author");
				System.out.println("4. Show all songs from X album");
				System.out.println("5. Show all songs sorted by popularity DESC");
				System.out.println("6. Show all songs sorted by length DESC");
				System.out.println("7. Show albums from X author");
				System.out.println("8. Search song by a fragment of text(%sth here%)");
				System.out.println("9. Show Tables in database");
				System.out.println("10. Your own SELECT function");
				select=Integer.parseInt(br.readLine());
				switch(select)
				{
				case 1:
					getSongs();
					//getInfo(select);
				break;
				case 2:
					getInfo(select);
					break;
				case 3:
					getInfo(select);
					break;
				case 4:
					getInfo(select);
					break;
				case 5:
					getInfo(select);
					break;
				case 6:
					getInfo(select);
					break;
				case 7:
					getInfo(select);
					break;
				case 8:
					getInfo(select);
					break;
				case 9:
					showTables();
					break;
				case 10:
					selectSQL();
					break;
					default:
						break;
				
				}
				//showTables();
				break;
			case 3:
				updateTable();
				break;
			case 4:
				dropSQL();
				break;
				default:
					System.exit(1);					
					break;
				
			}
			System.out.println();
			System.out.println();
		}
		
	}
	public static void getInfo(int option) throws Exception{
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			switch(option)
			{
			case 1:
				readySelectSQL("SELECT * FROM infoSong;");	
				break;
			case 2:
				System.out.println("Genre: ");
				System.out.println("1. Metal\n2. Rock\n\3. Rap\n4. Jazz\n5. Pop");
				System.out.print("Select (for example:Rock)");
				String gen=br.readLine();
				readySelectSQL("SELECT * FROM songs WHERE songs.genre_id =(SELECT genre_id FROM genres WHERE style='"+gen+"');");				
				break;
			case 3:
				System.out.println("Author: ");
				System.out.print("Select: ");
				String aut=br.readLine();
				readySelectSQL("SELECT * FROM songs "+ "WHERE songs.author_id =(SELECT author_id FROM authors"+ " WHERE name='"+aut+"');");			
				break;
			case 4:
				System.out.println("Album: ");
				System.out.print("Select: ");
				String alb=br.readLine();
				readySelectSQL("SELECT * FROM songs	WHERE songs.album_id =(SELECT album_id FROM albums WHERE name='"+alb+"');");	
				break;
			case 5:
				readySelectSQL("SELECT * FROM songs ORDER BY listen_views DESC;");
				break;
			case 6:
				readySelectSQL("SELECT * FROM songs	ORDER BY song_length DESC;");
				break;
			case 7:
				System.out.println("Author: ");
				
				String alba=br.readLine();
				readySelectSQL("SELECT * FROM albums WHERE author_id =(SELECT author_id FROM authors WHERE name='"+alba+"');");
				
				break;
			case 8:
				System.out.println("Fragment: ");
				
				String frag=br.readLine();
				readySelectSQL("SELECT * FROM songs WHERE name LIKE '%"+frag+"%';");
				
				break;
				default:
					break;
			}
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Completed");
		}
	}
	public static void readySelectSQL(String ready) throws Exception{
		try {		
			Connection con=getConnection();
			PreparedStatement sel=con.prepareStatement(ready);
			//sel.executeQuery();
			ResultSet rs = sel.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			//System.out.println(columnsNumber);
			String columnsName;
			for(int i=1;i<=columnsNumber;i++)
	        {
				columnsName = rsmd.getColumnName(i);
				System.out.print(columnsName+"\t\t");	
	        }
			System.out.println();
			System.out.println();
				
				
		
			while (rs.next()) {
				
		       
		        for(int i=1;i<=columnsNumber;i++)
		        {
		        	System.out.printf("%2.8s\t\t\t",rs.getString(i));
		        }
		        
		        // print the results
		        System.out.println();
			}
			
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Completed!");
		}
	}
	public static void selectSQL() throws Exception{
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("SELECT ");
			String select=br.readLine();
			
			Connection con=getConnection();
			PreparedStatement sel=con.prepareStatement("SELECT "+select);
			//sel.executeQuery();
			ResultSet rs = sel.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			//System.out.println(columnsNumber);
			String columnsName;
			for(int i=1;i<=columnsNumber;i++)
	        {
				columnsName = rsmd.getColumnName(i);
				System.out.print(columnsName+"\t\t");	
	        }
			System.out.println();
			System.out.println();
				
				
		
			while (rs.next()) {
				
		       
		        for(int i=1;i<=columnsNumber;i++)
		        {
		        	System.out.print(rs.getString(i)+"\t\t");
		        }
		        
		        // print the results
		        System.out.println();
			}
			
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Completed!");
		}
	}
	
	public static void dropSQL() throws Exception{
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("DROP ");
			String drop=br.readLine();
			
			Connection con=getConnection();
			PreparedStatement drops=con.prepareStatement("DROP "+drop);
			drops.executeUpdate();
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Deleted from database!");
		}
	}
	public static void updateTable() throws Exception{
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.print("UPDATE ");
			String updt=br.readLine();
			
			Connection con=getConnection();
			PreparedStatement update=con.prepareStatement("UPDATE "+updt);
			update.executeUpdate();
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Updated database!");
		}
	}
	public static void showTables() throws Exception{
		try {
		Connection con=getConnection();
		
		PreparedStatement show= con.prepareStatement("SHOW TABLES");
		ResultSet result =show.executeQuery();
		
		
		ResultSetMetaData rsmd = result.getMetaData();
		String name = rsmd.getColumnName(1);
		System.out.println(name);
		int i=1;
		while(result.next())
		{
			System.out.println(i+". "+result.getString(name));
			
			i++;
		}
		}catch(Exception e) {System.out.println(e);}
	}
	public static void post() throws Exception{					//dodanie do tablicy nowego elementu
		try {
		//ustawienia wstepne, polaczenie
		int option;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("where to insert(choose table)?");
		Connection con=getConnection();
		//--------------------------------------
		//pokazanie tabel
		PreparedStatement show= con.prepareStatement("SHOW TABLES");
		ResultSet result =show.executeQuery();
		//show.executeUpdate();
		
		ResultSetMetaData rsmd = result.getMetaData();
		String name = rsmd.getColumnName(1);
		System.out.println(name);
		int i=1;
		while(result.next())
		{
			System.out.println(i+". "+result.getString(name));
			
			i++;
		}
		
		//wpisanie INSERT INTO
		System.out.println("Type in MySql function: ");
		String sqlfun;
		sqlfun=br.readLine();		
		PreparedStatement posted= con.prepareStatement(sqlfun);
		posted.executeUpdate();
		
		}catch(Exception e){System.out.println(e);}
		finally {
			System.out.println("Insert complete");
			Thread.sleep(1000);
			System.out.flush();
		}
		/*final String var1="John";
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("INSERT INTO nowatabela (name) VALUES('"+var1+"')");
			posted.executeUpdate();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Insert Completed");
		}*/
	}
	public static void createTable() throws Exception{
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String sqlfun;
			System.out.println("Type in MySql function: \nCREATE TABLE");
			sqlfun=br.readLine();
			Connection con=getConnection();
			PreparedStatement create =con.prepareStatement("CREATE TABLE "+sqlfun);
			create.executeUpdate();
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function Complete");}		//tell us if table has been created(function has been completed)
	}
	public static ArrayList<String> get() throws Exception{
		try {
			Connection con= getConnection();
			PreparedStatement statement =con.prepareStatement("SELECT * FROM test");
			
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next()) {
				int id = rs.getInt("id");
		        String firstName = rs.getString("firstn");
		        String lastName = rs.getString("secondn");
		        
		        
		        // print the results
		        System.out.format("%s,\t %s,\t %s\n", id, firstName, lastName); // dateCreated, isAdmin, numPoints);
			}
			/*ResultSetMetaData rsmd = result.getMetaData();
			 String name = rsmd.getColumnName(1);
			 System.out.println(name);*/
			ArrayList<String> array= new ArrayList<String>();/*
			while(result.next())
			{
				System.out.println(result.getString("name"));
				array.add(result.getString("name"));
			}*/
			System.out.println("All records have been selected!");
			return array;
		}catch(Exception e) {System.out.println(e);}
		return null;
	}
	public static void getSongs() throws Exception{
		try {
			Connection con= getConnection();
			PreparedStatement statement =con.prepareStatement("SELECT * FROM songs");
			int i=1;
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String columnsName = rsmd.getColumnName(1);
			while(i<=7)
			{
				columnsName = rsmd.getColumnName(i);
				System.out.print(columnsName+"\t");
				i++;
				
			}
			System.out.println();
			while (rs.next()) {
				int id = rs.getInt("song_id");
		        String name = rs.getString("name");
		        Time songLength = rs.getTime("song_length");
		        int listenv=rs.getInt("listen_views");
		        int authorid=rs.getInt("author_id");
		        int genreid=rs.getInt("genre_id");
		        int albumid=rs.getInt("album_id");
		        
		        
		        // print the results
		        System.out.format("%s,\t %2.8s,\t %s,\t %s,\t %s,\t %s, \t %s\n", id, name,songLength,listenv,authorid,genreid,albumid); 
			}
			
			System.out.println("All records have been selected!");
			
		}catch(Exception e) {System.out.println(e);}
		
	}
	public static Connection getConnection() 
		throws Exception
	{
		try		//catch any errors that can occur
		{
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/marceli";	//lokalny adres, jesli strona to wpisujemy IP zamaist localhost
			String username = "admin";		//login do DB
			String password ="admin";	//haso do DB
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected to database.");
			
			return conn;
		}catch(Exception e) {System.out.println(e);}
		
		return null;
	}
}

