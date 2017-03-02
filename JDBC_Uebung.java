import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class JDBC_Uebung {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
	static final String USER = "root";
	static final String PASS = "";
	static Connection conn = null;
	static Statement stmt = null;

	public static String InsertMotorradfahrer(int MotorradfahrerID, String Vorname,
			String Nachname, String Motorrad, String Geschlecht){

		String sql = "INSERT INTO Motorradfahrer (MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht)" +
				"VALUES ( '" + MotorradfahrerID + "', '" + Vorname + "', '" + Nachname + "', '" + Motorrad + "', '" +
				Geschlecht + "')";
		return sql;
	}

	public static String InsertStrecke(int StreckenID, String Startort, String StartPlz,
			String StartLand, String Zielort, String ZielPlz, String ZielLand){

		String sql = "INSERT INTO Strecke (StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand)" +
				"VALUES (" + StreckenID + ", '" + Startort + "', '" + StartPlz + "', '" + StartLand + "', '" +
				Zielort + "', '" + ZielPlz + "', '" + ZielLand + "')";
		return sql;
	}

	public static String InsertAusfahrt(int MotorradfahrerID, int StreckenID, String Beschreibung, String Zeitpunkt){

		String sql = "INSERT INTO Ausfahrt (MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung)" +
				"VALUES (" + MotorradfahrerID + ", " + StreckenID + ", " + "'" + Zeitpunkt + "'" + ", '" + Beschreibung + "')";
		return sql;
	}

	/*
	public static String UpdateMotorradfahrerIDMotorradfahrer(int motorradfahrerIDalt, int motorradfahrerIDneu){

		String sql = "UPDATE Motorradfahrer " +
				"SET MotorradfahrerID = " + motorradfahrerIDneu +
				" WHERE MotorradfahrerID = " + motorradfahrerIDalt;
		return sql;
	}
	 */

	public static String UpdateVornameMotorradfahrer(int motorradfahrerID, String vorname){

		String sql = "UPDATE Motorradfahrer " +
				"SET Vorname = " + "'" +  vorname + "'" +
				" WHERE MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String UpdateNachnameMotorradfahrer(int motorradfahrerID, String nachname){

		String sql = "UPDATE Motorradfahrer " +
				"SET Nachname = " + "'" + nachname + "'" +  
				" WHERE MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String UpdateMotorradMotorradfahrer(int motorradfahrerID, String motorrad){

		String sql = "UPDATE Motorradfahrer " +
				"SET Motorrad = " + "'" + motorrad + "'" +  
				" WHERE MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String UpdateGeschlechtMotorradfahrer(int motorradfahrerID, String geschlecht){

		String sql = "UPDATE Motorradfahrer " +
				"SET Geschlecht = " + "'" +  geschlecht + "'" +  
				" WHERE MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}
	/*
	public static String UpdateStreckenIDStrecke(int streckenIDalt, int streckenIDneu){

		String sql = "UPDATE Strecke " +
				"SET StreckenID = " + streckenIDneu +
				" WHERE StreckenID = " + streckenIDalt;
		return sql;
	}
	 */
	public static String UpdateStartOrtStrecke(int streckenID, String startOrt){

		String sql = "UPDATE Strecke " +
				"SET StartOrt = " + "'" +  startOrt + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateStartPlzStrecke(int streckenID, String startPlz){

		String sql = "UPDATE Strecke " +
				"SET StartPlz = " + "'" +  startPlz + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateStartLandStrecke(int streckenID, String startLand){

		String sql = "UPDATE Strecke " +
				"SET StartLand = " + "'" +  startLand + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateZielOrtStrecke(int streckenID, String zielOrt){

		String sql = "UPDATE Strecke " +
				"SET ZielOrt = " + "'" +  zielOrt + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateZielPlzdStrecke(int streckenID, String zielPlz){

		String sql = "UPDATE Strecke " +
				"SET ZielPlz = " + "'" +  zielPlz + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateZielLandStrecke(int streckenID, String zielLand){

		String sql = "UPDATE Strecke " +
				"SET ZielLand = " + "'" +  zielLand + "'" +  
				" WHERE StreckenID = " + streckenID;
		return sql;
	}
	/*
	public static String UpdateMotorradfahrerIDAusfahrt(int motorradfahrerIDalt, int motorradfahrerIDneu, int streckenID){

		String sql = "UPDATE Ausfahrt " +
				"SET MotorradfahrerID = " + motorradfahrerIDneu +
				" WHERE MotorradfahrerID = " + motorradfahrerIDalt + " AND StreckenID = " + streckenID;
		return sql;
	}

	public static String UpdateStreckenIDAusfahrt(int streckenIDalt, int streckenIDneu, int motorradfahrerID){

		String sql = "UPDATE Ausfahrt " +
				"SET StreckenID = " + streckenIDneu +
				" WHERE StreckenID = " + streckenIDalt + " AND MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}
	 */
	public static String UpdateBeschreibungAusfahrt(int motorradfahrerID, int streckenID, String beschreibung){

		String sql = "UPDATE Ausfahrt " +
				"SET Beschreibung = " + "'" +  beschreibung + "'" +  
				" WHERE StreckenID = " + streckenID + " AND MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String UpdateZeitpunktAusfahrt(int motorradfahrerID, int streckenID, String zeitpunkt){

		String sql = "UPDATE Ausfahrt " +
				"SET Zeitpunkt = " + "'" +  zeitpunkt + "'" +  
				" WHERE StreckenID = " + streckenID + " AND MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String DeleteMotorradfahrer(int motorradfahrerID){

		String sql = "DELETE FROM Motorradfahrer " +
				"WHERE MotorradfahrerID = " + motorradfahrerID;
		return sql;
	}

	public static String DeleteStrecke(int streckenID){

		String sql = "DELETE FROM Strecke " +
				"WHERE StreckenID = " + streckenID;
		return sql;
	}

	public static String DeleteAusfahrt(int motorradfahrerID, int streckenID){

		String sql = "DELETE FROM Ausfahrt " +
				"WHERE MotorradfahrerID = " + motorradfahrerID + " AND StreckenID = " + streckenID;
		return sql;
	}

	public static void main(String[] args) {



		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			// Create
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();      
			String sql = "CREATE TABLE IF NOT EXISTS Motorradfahrer" +
					"(MotorradfahrerID INT NOT NULL, " +
					" Vorname VARCHAR(30) NOT NULL, " + 
					" Nachname VARCHAR(30) NOT NULL, " + 
					" Motorrad VARCHAR(30) NOT NULL, " +
					" Geschlecht CHAR(1) NOT NULL, " +
					" PRIMARY KEY(MotorradfahrerID))"; 
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS Strecke " +
					"(StreckenID INT NOT NULL, " +
					" Startort VARCHAR(30) NOT NULL, " + 
					" StartPlz VARCHAR(10) NOT NULL, " + 
					" StartLand VARCHAR(3) NOT NULL, " +
					" Zielort VARCHAR(30) NOT NULL, " +
					" ZielPlz VARCHAR(10) NOT NULL, " +
					" ZielLand VARCHAR(3) NOT NULL, " +
					" PRIMARY KEY(StreckenID))";
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE IF NOT EXISTS Ausfahrt " +
					"(MotorradfahrerID INT NOT NULL, " +
					" StreckenID INT NOT NULL, " + 
					" Zeitpunkt DATETIME NOT NULL, " + 
					" Beschreibung VARCHAR(100) NOT NULL, " +
					" KEY(StreckenID), " +
					" KEY(MotorradfahrerID), " +
					" FOREIGN KEY (StreckenID) REFERENCES Strecke (StreckenID) ON DELETE CASCADE, " +
					" FOREIGN KEY (MotorradfahrerID) REFERENCES Motorradfahrer (MotorradfahrerID) ON DELETE CASCADE)";			
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Created table in given database...");
			// Insert
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();

			//			stmt.executeUpdate(DatenEinlesenMotorradfahrer("C:/Users/Tommy B/Desktop/dbM.txt"));
			//			stmt.executeUpdate(DatenEinlesenStrecke("C:/Users/Tommy B/Desktop/dbS.txt"));
			//			stmt.executeUpdate(DatenEinlesenAusfahrt("C:/Users/Tommy B/Desktop/dbA.txt"));
			/*
						System.out.println("Geben sie bitte die Daten in der folgenden Reihenfolge"
					+ " (jeweils mit einem Absatz getrennt) f�r den Motorradfahrer ein: MotorradfahrerID,"
					+ " Vorname, Nachname, Motorrad, Geschlecht [w, m]");					
			System.out.println("Geben sie bitte die Daten in der folgenden Reihenfolge"
					+ " (jeweils mit einem Absatz getrennt) f�r die Strecke ein: StreckenID,"
					+ " Startort, Startort-PLZ, Startland, Zielort, Zielort-PLZ, Zielland"); 
			System.out.println("Geben sie bitte die Daten in der folgenden Reihenfolge"
					+ " (jeweils mit einem Absatz getrennt) f�r die Ausfahrt ein:\nMotorradfahrerID,"
					+ " StreckenID, Datum [JJJJ-MM-TT hh:mm:ss], zus�tzliche Beschreibung"); */
			//			stmt.executeUpdate(DatenAusConsoleEinlesenMotorradfahrer());
			//			stmt.executeUpdate(DatenAusConsoleEinlesenStrecke());
			//			stmt.executeUpdate(DatenAusConsoleEinlesenAusfahrt());

			/*
			String sql2 = "INSERT INTO Motorradfahrer (MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht)" +
					"VALUES ( '" + MotorradfahrerID + "', '" + Vorname + "', '" + Nachname + "', '" + Motorrad + "', '" +
					+ Geschlecht + "')";
		//	stmt.executeUpdate(sql2);

		sql2 = "INSERT INTO Strecke (StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand)" +
					"VALUES (" + StreckenID + ", '" + Startort + "', '" + StartPlz + "', '" + StartLand + "', '" +
					Zielort + "', '" + ZielPlz + "', '" + ZielLand + "')";
		//	stmt.executeUpdate(sql2);
			sql2 = "INSERT INTO Ausfahrt (MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung)" +
					"VALUES (" + MotorradfahrerID + ", " + StreckenID + ", " + "'" + Zeitpunkt + "'" + ", '" + Beschreibung + "')";
		//	stmt.executeUpdate(sql2);
			 * */

			//char? datetime?

			/*
			stmt.executeUpdate(InsertMotorradfahrer(1, "Tommy", "Braunegger", "Yamaha MT-03", "m"));
			stmt.executeUpdate(InsertMotorradfahrer(2, "Max", "Ebner", "KTM Duke 125", "?"));
			stmt.executeUpdate(InsertMotorradfahrer(3, "K�ll�", "S", "Yamaha XVS1300A", "m"));
			 */
			/*
			InsertMotorradfahrerPreSta(4, "Gutrun", "Horacher", "Vespa 125", "w");
			 */
			/*
			UpdateStreckeZielPlzPreSta(2, "2222");
			 */
			/*
			 DeleteMotorradfahrerPreSta(4);
			 */
			/*			
			stmt.executeUpdate(InsertAusfahrt(1, 1, "Zuerst ins Nachbardorf", "2016-06-06 10:50:10"));
			stmt.executeUpdate(InsertAusfahrt(1, 2, "Schoene Tour nach Bozen", "2016-06-06 11:09:45"));
			stmt.executeUpdate(InsertAusfahrt(1, 3, "Heimweg zum Startort", "2016-06-06 14:15:30"));
			stmt.executeUpdate(InsertAusfahrt(2, 1, "Zuerst ins Nachbardorf", "2016-06-06 10:50:10"));
			stmt.executeUpdate(InsertAusfahrt(2, 2, "Schoene Tour nach Bozen", "2016-06-06 11:09:45"));
			stmt.executeUpdate(InsertAusfahrt(2, 3, "Heimweg zum Startort", "2016-06-06 14:15:30"));
			stmt.executeUpdate(InsertAusfahrt(3, 1, "Zuerst ins Nachbardorf", "2016-06-06 10:50:10"));
			stmt.executeUpdate(InsertAusfahrt(3, 2, "Schoene Tour nach Bozen", "2016-06-06 11:09:45"));
			stmt.executeUpdate(InsertAusfahrt(3, 3, "Heimweg zum Startort", "2016-06-06 14:15:30"));
			 */
			/*
			stmt.executeUpdate(InsertStrecke(1, "Mutters", "6162", "AUT", "G�tzens", "6091", "AUT"));
			stmt.executeUpdate(InsertStrecke(2, "G�tzens", "6091", "AUT", "Bolzano", "1111", "ITA"));
			stmt.executeUpdate(InsertStrecke(3, "Bolzano", "1111", "ITA", "Mutters", "6162", "AUT"));
			stmt.executeUpdate(InsertStrecke(10, "Telfs", "1111", "AUT", "Kufstein", "1111", "AUT"));
			stmt.executeUpdate(InsertStrecke(11, "Kufstein", "1111", "AUT", "Rosenheim", "1111", "GER"));
			stmt.executeUpdate(InsertStrecke(12, "Rosenheim", "1111", "GER", "Rattenberg", "1111", "AUT"));
			stmt.executeUpdate(InsertStrecke(13, "Rattenberg", "1111", "AUT", "Telfs", "1111", "AUT"));
			stmt.executeUpdate(InsertStrecke(20, "Mutters", "6162", "AUT", "Grinzens", "1111", "AUT"));
			stmt.executeUpdate(InsertStrecke(21, "Grinzens", "1111", "AUT", "Mutters", "1111", "AUT"));
			 */
			stmt.close();
			System.out.println("Inserted records into the table...");

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			// Select
			String sql3 = "SELECT MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht FROM Motorradfahrer";
			ResultSet rs = stmt.executeQuery(sql3);
			while(rs.next()){
				//Retrieve by column name
				int mID  = rs.getInt("MotorradfahrerID");
				String g = rs.getString("Geschlecht");
				String vn = rs.getString("Vorname");
				String nn = rs.getString("Nachname");
				String mr = rs.getString("Motorrad");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", Geschlecht: " + g);
				System.out.print(", Vorname: " + vn);
				System.out.print(", Nachname: " + nn);
				System.out.println(", Motorrad: " + mr);
			}
			rs.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql4 = "SELECT StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand FROM Strecke";
			ResultSet rs2 = stmt.executeQuery(sql4);
			while(rs2.next()){
				//Retrieve by column name
				int sID  = rs2.getInt("StreckenID");
				String so = rs2.getString("Startort");
				String sp = rs2.getString("StartPlz");
				String sl = rs2.getString("StartLand");
				String zo = rs2.getString("Zielort");
				String zp = rs2.getString("ZielPlz");
				String zl = rs2.getString("ZielLand");

				//Display values
				System.out.print("StreckenID: " + sID);
				System.out.print(", Startort: " + so);
				System.out.print(", StartPlz: " + sp);
				System.out.print(", StartLand: " + sl);
				System.out.print(", Zielort: " + zo);
				System.out.print(", ZielPlz: " + zp);
				System.out.println(", ZielLand: " + zl);
			}
			rs2.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql5 = "SELECT MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung FROM Ausfahrt";
			ResultSet rs3 = stmt.executeQuery(sql5);
			while(rs3.next()){
				//Retrieve by column name
				int mID = rs3.getInt("MotorradfahrerID");
				int sID = rs3.getInt("StreckenID");
				String zp = rs3.getString("Zeitpunkt");
				String bs = rs3.getString("Beschreibung");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", StreckenID: " + sID);
				System.out.print(", Zeitpunkt: " + zp);
				System.out.println(", Beschreibung: " + bs);
			}
			rs3.close();
			stmt.close();
			// Update
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*
			stmt.executeUpdate(UpdateVornameMotorradfahrer(1, "Thomas"));
			 */
			// Now you can extract all the records
			// to see the updated records
			String sqlUpdate = "SELECT MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht FROM Motorradfahrer";
			ResultSet rs4 = stmt.executeQuery(sqlUpdate);

			while(rs4.next()){
				//Retrieve by column name
				int mID  = rs4.getInt("MotorradfahrerID");
				String g = rs4.getString("Geschlecht");
				String vn = rs4.getString("Vorname");
				String nn = rs4.getString("Nachname");
				String mr = rs4.getString("Motorrad");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", Geschlecht: " + g);
				System.out.print(", Vorname: " + vn);
				System.out.print(", Nachname: " + nn);
				System.out.println(", Motorrad: " + mr);
			}
			rs4.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*			
			stmt.executeUpdate(UpdateStartLandStrecke(1, "OES"));
			 */
			// Now you can extract all the records
			// to see the updated records
			sqlUpdate = "SELECT StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand FROM Strecke";
			ResultSet rs5 = stmt.executeQuery(sqlUpdate);

			while(rs5.next()){
				//Retrieve by column name
				int sID  = rs5.getInt("StreckenID");
				String so = rs5.getString("Startort");
				String sp = rs5.getString("StartPlz");
				String sl = rs5.getString("StartLand");
				String zo = rs5.getString("Zielort");
				String zp = rs5.getString("ZielPlz");
				String zl = rs5.getString("ZielLand");

				//Display values
				System.out.print("StreckenID: " + sID);
				System.out.print(", Startort: " + so);
				System.out.print(", StartPlz: " + sp);
				System.out.print(", StartLand: " + sl);
				System.out.print(", Zielort: " + zo);
				System.out.print(", ZielPlz: " + zp);
				System.out.println(", ZielLand: " + zl);
			}
			rs5.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*			
			stmt.executeUpdate(UpdateBeschreibungAusfahrt(2, 3, "Test"));
			 */
			// Now you can extract all the records
			// to see the updated records
			sqlUpdate = "SELECT MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung FROM Ausfahrt";
			ResultSet rs6 = stmt.executeQuery(sqlUpdate);

			while(rs6.next()){
				//Retrieve by column name
				int mID = rs6.getInt("MotorradfahrerID");
				int sID = rs6.getInt("StreckenID");
				String zp = rs6.getString("Zeitpunkt");
				String bs = rs6.getString("Beschreibung");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", StreckenID: " + sID);
				System.out.print(", Zeitpunkt: " + zp);
				System.out.println(", Beschreibung: " + bs);
			}
			rs6.close();
			stmt.close();

			// Delete
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*			
			stmt.executeUpdate(DeleteMotorradfahrer(2));
			 */
			// Now you can extract all the records
			// to see the remaining records
			String sqlDelete = "SELECT MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht FROM Motorradfahrer";
			ResultSet rs7 = stmt.executeQuery(sqlDelete);

			while(rs7.next()){
				//Retrieve by column name
				int mID  = rs7.getInt("MotorradfahrerID");
				String g = rs7.getString("Geschlecht");
				String vn = rs7.getString("Vorname");
				String nn = rs7.getString("Nachname");
				String mr = rs7.getString("Motorrad");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", Geschlecht: " + g);
				System.out.print(", Vorname: " + vn);
				System.out.print(", Nachname: " + nn);
				System.out.println(", Motorrad: " + mr);
			}
			rs7.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*
			stmt.executeUpdate(DeleteStrecke(2));
			 */
			// Now you can extract all the records
			// to see the remaining records
			sqlDelete = "SELECT StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand FROM Strecke";
			ResultSet rs8 = stmt.executeQuery(sqlDelete);

			while(rs8.next()){
				//Retrieve by column name
				int sID  = rs8.getInt("StreckenID");
				String so = rs8.getString("Startort");
				String sp = rs8.getString("StartPlz");
				String sl = rs8.getString("StartLand");
				String zo = rs8.getString("Zielort");
				String zp = rs8.getString("ZielPlz");
				String zl = rs8.getString("ZielLand");

				//Display values
				System.out.print("StreckenID: " + sID);
				System.out.print(", Startort: " + so);
				System.out.print(", StartPlz: " + sp);
				System.out.print(", StartLand: " + sl);
				System.out.print(", Zielort: " + zo);
				System.out.print(", ZielPlz: " + zp);
				System.out.println(", ZielLand: " + zl);
			}
			rs8.close();
			stmt.close();

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			/*
			stmt.executeUpdate(DeleteAusfahrt(1, 1));
			 */
			// Now you can extract all the records
			// to see the remaining records
			sqlDelete = "SELECT MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung FROM Ausfahrt";
			ResultSet rs9 = stmt.executeQuery(sqlDelete);

			while(rs9.next()){
				//Retrieve by column name
				int mID = rs9.getInt("MotorradfahrerID");
				int sID = rs9.getInt("StreckenID");
				String zp = rs9.getString("Zeitpunkt");
				String bs = rs9.getString("Beschreibung");

				//Display values
				System.out.print("MotorradfahrerID: " + mID);
				System.out.print(", StreckenID: " + sID);
				System.out.print(", Zeitpunkt: " + zp);
				System.out.println(", Beschreibung: " + bs);
			}
			rs9.close();
			stmt.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Machs nicht gut, machs besser!");
	}

	static public String DatenEinlesenMotorradfahrer(String fName) throws FileNotFoundException
	{
		Scanner s = new Scanner (new File(fName));
		String sql = "";
		while (s.hasNextLine()){
			sql = "INSERT INTO Motorradfahrer (MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht)" +
					"VALUES ( '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" +
					s.nextLine() + "')";
		}
		return sql;
	}

	static public String DatenEinlesenStrecke(String fName) throws FileNotFoundException
	{
		Scanner s = new Scanner (new File(fName));
		String sql = "";
		while (s.hasNextLine()){
			sql = "INSERT INTO Strecke (StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand)" +
					"VALUES (" + s.nextLine() + ", '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" +
					s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "')";
		}
		return sql;
	}

	static public String DatenEinlesenAusfahrt(String fName) throws FileNotFoundException
	{
		Scanner s = new Scanner (new File(fName));
		String sql = "";
		while (s.hasNextLine()){
			sql = "INSERT INTO Ausfahrt (MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung)" +
					"VALUES (" + s.nextLine() + ", " + s.nextLine() + ", " + "'" + s.nextLine() + "'" + ", '" + s.nextLine() + "')";
		}
		return sql;
	}

	static public String DatenAusConsoleEinlesenMotorradfahrer(){
		Scanner s = new Scanner(System.in);
		String sql = "";

		sql = "INSERT INTO Motorradfahrer (MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht)" +
				"VALUES ( '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" +
				s.nextLine() + "')";
		return sql;
	}

	static public String DatenAusConsoleEinlesenStrecke() throws FileNotFoundException
	{
		Scanner s = new Scanner (System.in);
		String sql = "";
		sql = "INSERT INTO Strecke (StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand)" +
				"VALUES (" + s.nextLine() + ", '" + s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "', '" +
				s.nextLine() + "', '" + s.nextLine() + "', '" + s.nextLine() + "')";
		return sql;
	}

	static public String DatenAusConsoleEinlesenAusfahrt() throws FileNotFoundException
	{
		Scanner s = new Scanner (System.in);
		String sql = "";
		sql = "INSERT INTO Ausfahrt (MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung)" +
				"VALUES (" + s.nextLine() + ", " + s.nextLine() + ", " + "'" + s.nextLine() + "'" + ", '" + s.nextLine() + "')";
		return sql;
	}

	public static void InsertMotorradfahrerPreSta(int MotorradfahrerID, String Vorname,
			String Nachname, String Motorrad, String Geschlecht){
		PreparedStatement pst = null;
		try{
			String sql = "INSERT INTO Motorradfahrer (MotorradfahrerID, Vorname, Nachname, Motorrad, Geschlecht)" +
					"VALUES ( '" + MotorradfahrerID + "', '" + Vorname + "', '" + Nachname + "', '" + Motorrad + "', '" +
					Geschlecht + "')";
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void InsertStreckePreSta(int StreckenID, String Startort, String StartPlz,
			String StartLand, String Zielort, String ZielPlz, String ZielLand){
		PreparedStatement pst = null;
		try{
			String sql = "INSERT INTO Strecke (StreckenID, Startort, StartPlz, StartLand, Zielort, ZielPlz, ZielLand)" +
					"VALUES (" + StreckenID + ", '" + Startort + "', '" + StartPlz + "', '" + StartLand + "', '" +
					Zielort + "', '" + ZielPlz + "', '" + ZielLand + "')";
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void InsertAusfahrtPreSta(int MotorradfahrerID, int StreckenID, String Beschreibung, String Zeitpunkt){
		PreparedStatement pst = null;
		try{
			String sql = "INSERT INTO Ausfahrt (MotorradfahrerID, StreckenID, Zeitpunkt, Beschreibung)" +
					"VALUES (" + MotorradfahrerID + ", " + StreckenID + ", " + "'" + Zeitpunkt + "'" + ", '" + Beschreibung + "')";
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateMotorradfahrerVornamePreSta(int motorradfahrerID, String vorname){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Motorradfahrer " +
					"SET Vorname = " + "'" +  vorname + "'" +
					" WHERE MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateMotorradfahrerNachnamePreSta(int motorradfahrerID, String nachname){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Motorradfahrer " +
					"SET Nachname = " + "'" + nachname + "'" +  
					" WHERE MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateMotorradfahrerMotorradPreSta(int motorradfahrerID, String motorrad){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Motorradfahrer " +
					"SET Motorrad = " + "'" + motorrad + "'" +  
					" WHERE MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateMotorradfahrerGeschlechtPreSta(int motorradfahrerID, String geschlecht){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Motorradfahrer " +
					"SET Geschlecht = " + "'" +  geschlecht + "'" +  
					" WHERE MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateStreckeStreckenIDPreSta(int streckenID, String startOrt){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET StartOrt = " + "'" +  startOrt + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateStreckeStartPlzPreSta(int streckenID, String startPlz){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET StartPlz = " + "'" +  startPlz + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}


	public static void UpdateStreckeStartLandPreSta(int streckenID, String startLand){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET StartLand = " + "'" +  startLand + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}


	public static void UpdateStreckeZielOrtPreSta(int streckenID, String zielOrt){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET ZielOrt = " + "'" +  zielOrt + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}


	public static void UpdateStreckeZielPlzPreSta(int streckenID, String zielPlz){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET ZielPlz = " + "'" +  zielPlz + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}


	public static void UpdateStreckeZielLandPreSta(int streckenID, String zielLand){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Strecke " +
					"SET ZielLand = " + "'" +  zielLand + "'" +  
					" WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateAusfahrtZeitpunktPreSta(int streckenID, int motorradfahrerID, String zeitpunkt){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Ausfahrt " +
					"SET Zeitpunkt = " + "'" +  zeitpunkt + "'" +  
					" WHERE StreckenID = " + streckenID + " AND MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void UpdateAusfahrtBeschreibungPreSta(int streckenID, int motorradfahrerID, String beschreibung){
		PreparedStatement pst = null;
		try{
			String sql = "UPDATE Ausfahrt " +
					"SET Beschreibung = " + "'" +  beschreibung + "'" +  
					" WHERE StreckenID = " + streckenID + " AND MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void DeleteMotorradfahrerPreSta(int motorradfahrerID){
		PreparedStatement pst = null;
		try{
			String sql = "DELETE FROM Motorradfahrer " +
					"WHERE MotorradfahrerID = " + motorradfahrerID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void DeleteStreckePreSta(int streckenID){
		PreparedStatement pst = null;
		try{
			String sql = "DELETE FROM Strecke " +
					"WHERE StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}

	public static void DeleteAusfahrtPreSta(int motorradfahrerID, int streckenID){
		PreparedStatement pst = null;
		try{
			String sql = "DELETE FROM Ausfahrt " +
					"WHERE MotorradfahrerID = " + motorradfahrerID + " AND StreckenID = " + streckenID;
			pst = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
	}
}