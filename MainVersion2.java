import java.beans.Statement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;

public class MainVersion2 {

	private static final MainVersion2 Main = new MainVersion2();
	private static Connection connection; 
	//Pfad zur Datenbank
	private static final String DB_PATH = "C:" + "/" + "Users" + "/" + "Tommy B" + 
			"/" + "Documents" + "/" + "HTL 5AHW" + "/" + "INFI-DBP" + "/" + "Tankstellen" + "/" + "sprit.db"; 
	//C:\Users\Tommy B\Documents\HTL 5AHW\INFI-DBP\Tankstellen\sprit.db
	
	public static int countEintraege(){
		int countEintraege = 0;
		
		try{
		java.sql.Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM sprit_data;");
		
		while(rs.next()){
			countEintraege++;
		}
		return countEintraege;
		}catch (SQLException e) { 
			System.err.println("Couldn't get Data"); 
			e.printStackTrace(); 
		} 
		return 0;
	}
	
	public static long[] datum = new long [26692];
	public static long[] value = new long [26692];
	public static long[] tankenr = new long [26692];
	final DefaultCategoryDataset datasetBar = new DefaultCategoryDataset();
	final static TimeSeriesCollection dataset = new TimeSeriesCollection();

	//Erzeugung der Variablen für das Herausschreiben in ein Textfile
	public static FileWriter out;
	public static BufferedWriter AnotherOut;

	public static int counterTage;
	public static int counter;

	static { 
		try { 
			//Laden des JDBC-Treibers
			Class.forName("org.sqlite.JDBC"); 
		} catch (ClassNotFoundException e) { 
			System.err.println("Fehler beim Laden des JDBC-Treibers"); 
			e.printStackTrace(); 
		} 
	} 

	private MainVersion2(){ 
	} 

	public static MainVersion2 getInstance(){ 
		return Main; 
	} 

	private static void getConnection() { 
		try { 
			if (connection != null) 
				return; 
			//Connection zur Datenbank herstellen
			System.out.println("Creating Connection to Database..."); 
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH); 
			if (!connection.isClosed()) 
				System.out.println("...Connection established"); 
		} catch (SQLException e) { 
			throw new RuntimeException(e); 
		} 

		Runtime.getRuntime().addShutdownHook(new Thread() { 
			public void run() { 
				try { 
					if (!connection.isClosed() && connection != null) { 
						connection.close(); 
						if (connection.isClosed()) 
							System.out.println("Connection to Database closed"); 
					} 
				} catch (SQLException e) { 
					e.printStackTrace(); 
				} 
			} 
		}
				); 
	} 

	public static void getData() { 
		int count = 0;
		try { 
			java.sql.Statement stmt = connection.createStatement();
			//Herausholen der Daten aus der Datenbank
			ResultSet rs = stmt.executeQuery("SELECT * FROM sprit_data;"); 
			while (rs.next()) { 
				//System.out.println(rs.getInt("datum"));
				//Umwandlung von unix-timestamp in ein lesbares Format
				long epoch = rs.getInt("datum");
				String dat = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
				//Ausgabe eines lesbaren Formats
				System.out.println("Datum = " + dat + " mit Tankstelle: " + rs.getInt("tankenr") + " und Wert: " + rs.getDouble("value"));
				//Zuweisung der Daten an die jeweilige richtige Stelle im Array
				datum[count] = rs.getInt("datum");
				value[count] = (long) rs.getDouble("value");
				tankenr[count] = rs.getInt("tankenr");
				if(count != 0 && datum[count] - datum[count-1] >= 86400){
					counterTage++;
				}
				count++;
			}     			

			rs.close();
		} catch (SQLException e) { 
			System.err.println("Couldn't get Data"); 
			e.printStackTrace(); 
		} 
	} 

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

	public static long timestamp(String arg) throws ParseException{
		return df.parse(arg).getTime();
	}

	public static int[] berechnung(String aktDatum) throws ParseException{
		/*
		//31536000 = unix timestamp für ein Jahr
		//86400 = unix timestamp für einen Tag
		long[] durchschnittswerteTag = new long[365];
		long[] tag = new long[counterTage];

		try{
			java.sql.Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sprit_data;"); 
			while (rs.next()) { 
				if(rs.getInt("datum") >= (timestamp(aktDatum) - 31536000)){ //wenn Datum in einem Jahr liegt

					for (int i = 0; i < tag.length; i++) {
						tag[i] = (timestamp(aktDatum) - 31536000) + 86400 * (i+1);
						java.sql.Statement stmt2 = connection.createStatement();
						ResultSet rs2 = stmt2.executeQuery("SELECT value FROM sprit_data WHERE datum >= " + tag[i] + " AND datum <= " + tag[i-1] + ";"); 
						//wenn keine Werte drinnen sind => 0, wenn Werte drinnen sind => Durchschnitt
						if(rs2.getInt("value") == 0){ //nicht 0 aber nicht vorhanden?
								durchschnittswerteTag[i] = 0;
						}else{
							int summe = 0, durchschnitt;
							while(rs2.next()){
								summe += rs2.getInt("value");
							}
							durchschnitt = summeLänge von rs2?;
							durchschnittswerteTag[i] = 0;
						}
						rs2.close();
					}		
				}
			}
			rs.close();
			connection.close();

		}catch(SQLException e){
			System.err.println("Couldn't handle DB-Query");
			e.printStackTrace();
		}
		 */


		return null;
	}

	public static ArrayList<Long> avaragePreis(String aktDatum) throws SQLException, ParseException{

		String  sql = null;
		ResultSet rs = null, rs2 = null;
		long[] tag = new long[counterTage];
		ArrayList<Long> avarages = new ArrayList<Long>();

		java.sql.Statement stmt = connection.createStatement();
		rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM sprit_data;"); 

		if(rs.getInt("datum") >= (timestamp(aktDatum) - 31536000)){ //wenn Datum in einem Jahr liegt
			for(int i = 0; i< counterTage-1; i++){

				sql = "SELECT AVG(value), tankenr FROM sprit_data WHERE datum >= " + tag[i] + " AND datum < " + tag[i+1] + " GROUP BY tankenr";
				java.sql.Statement stmt2 = connection.createStatement();
				rs2 = ((java.sql.Statement) stmt2).executeQuery(sql);

				while(rs.next()){
					avarages.add((long) rs2.getInt("tankenr")); 
					avarages.add((long) rs2.getDouble("AVG(value)"));
					avarages.add(tag[i]);
				}
			}
		}
		return avarages;
	}

	public static ArrayList<Long> absolutPreis(String aktDatum) throws SQLException, ParseException{
/*
		String  sql = null;
		ResultSet rs = null, rs2 = null;
		long[] tag = new long[counterTage];
		ArrayList<Long> absolut = new ArrayList<Long>();

		java.sql.Statement stmt = connection.createStatement();
		rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM sprit_data;"); 

		if(rs.getInt("datum") >= (timestamp(aktDatum) - 31536000)){ //wenn Datum in einem Jahr liegt
			for(int i = 0; i < counterTage-1; i++){
				for(int j = 1; j < anzahlTanknr(); j++){
					sql = "SELECT tankenr, SUM(value) FROM sprit_data WHERE tankenr = " + j + " AND datum >= " + tag[i] + " AND datum < " + tag[i+1];
					java.sql.Statement stmt2 = connection.createStatement();
					rs2 = ((java.sql.Statement) stmt2).executeQuery(sql);

					while(rs.next()){
						absolut.add((long) rs2.getInt("tankenr")); 
						absolut.add((long) rs2.getDouble("AVG(value)"));
						absolut.add(tag[i]);
					}
				}
			}
		}
		return absolut;
		*/
		
		String  sql = null;
		ResultSet rs = null, rs2 = null;
		long[] tag = new long[counterTage];
		ArrayList<Long> absolut = new ArrayList<Long>();

		java.sql.Statement stmt = connection.createStatement();
		rs = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM sprit_data;"); 

		if(rs.getInt("datum") >= (timestamp(aktDatum) - 31536000)){ //wenn Datum in einem Jahr liegt
			for(int i = 0; i< counterTage-1; i++){

				sql = "SELECT SUM(value), tankenr FROM sprit_data WHERE datum >= " + tag[i] + " AND datum < " + tag[i+1] + " GROUP BY tankenr";
				java.sql.Statement stmt2 = connection.createStatement();
				rs2 = ((java.sql.Statement) stmt2).executeQuery(sql);

				while(rs.next()){
					absolut.add((long) rs2.getInt("tankenr")); 
					absolut.add((long) rs2.getDouble("AVG(value)"));
					absolut.add(tag[i]);
				}
			}
		}
		return absolut;
	}

	public static int anzahlTanknr() throws SQLException{

		String  s = null;
		ResultSet rs = null;
		int a = 0;

		s = "SELECT tankenr FROM (SELECT tankenr FROM sprit_data GROUP BY tankenr)";
		java.sql.Statement stmt = connection.createStatement();
		rs = ((java.sql.Statement) stmt).executeQuery(s);

		while(rs.next()){
			a = rs.getInt("tankenr");
			counter++;
			System.out.println(a);
		}

		return a;
	}

	public static void makePlot() throws IOException{
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Spritpreis",      // chart title
				"t",                      // x axis label
				"€",                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
				);

		XYPlot plot = chart.getXYPlot();
		plot.getRangeAxis().setLowerBound(1.155);
		plot.getRangeAxis().setUpperBound(1.255);

		int width = 1400; /* Width of the image */
		int height = 900; /* Height of the image */ 
		File lineChart = new File( "Chart.jpeg" ); 
		ChartUtilities.saveChartAsPNG(lineChart ,chart, width ,height);
	}

	public static void main(String[] args) throws ParseException, SQLException, IOException {
		getConnection();
		getData();
		anzahlTanknr();
		makePlot();
		System.out.println("BB");
	}
}
