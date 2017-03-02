import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection; 
import java.sql.Date; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;
import java.util.ArrayList;

public class SqliteManager {

	private static final SqliteManager SqliteManager = new SqliteManager();
	private static Connection connection; 
	//Pfad zur Datenbank
	private static final String DB_PATH = "C:" + "/" + "Users" + "/" + "Tommy B" + 
			"/" + "Documents" + "/" + "HTL 5AHW" + "/" + "INFI-DBP" + "/" + "Stromverbrauch_Diagramm" + "/" + "verbrauch.db"; 
	//C:\Users\Tommy B\Documents\HTL 5AHW\INFI-DBP\JsFiddle\verbrauch.db
	public static long[] datum = new long [60];
	public static long[] value = new long [60];
	//Erzeugen der Counter für den späteren Gebrauch
	public static int counterJaen = 0;
	public static int counterFebr = 0;
	public static int counterMaer = 0;
	public static int counterApri = 0;
	public static int counterMai = 0;
	public static int counterJun = 0;
	public static int counterJul = 0;
	public static int counterAugu = 0;
	public static int counterSept = 0;
	public static int counterOkto = 0;
	public static int counterNove = 0;
	public static int counterDeze = 0;
	//Erzeugung der Variablen für das Herausschreiben in ein Textfile
	public static FileWriter out;
	public static BufferedWriter AnotherOut;

	static { 
		try { 
			//Laden des JDBC-Treibers
			Class.forName("org.sqlite.JDBC"); 
		} catch (ClassNotFoundException e) { 
			System.err.println("Fehler beim Laden des JDBC-Treibers"); 
			e.printStackTrace(); 
		} 
	} 

	private SqliteManager(){ 
	} 

	public static SqliteManager getInstance(){ 
		return SqliteManager; 
	} 

	private void getConnection() { 
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
		}); 
	} 

	public void getData() { 
		try { 
			Statement stmt = connection.createStatement();
			//Herausholen der Daten aus der Datenbank
			//(in diesem Fall für den Stromverbrauch)
			ResultSet rs = stmt.executeQuery("SELECT * FROM strom_verbrauch;"); 
			while (rs.next()) { 
				//Umwandlung von unix-timestamp in ein lesbaren Format
				long epoch = rs.getInt("datum");
				String datum = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
				//Ausgabe des lesbaren Formats mit den Werten des Strom- bzw. Wasserverbrauchs
				System.out.println("Datum = " + datum + " und Wert = " + rs.getInt("value")); 
			}     
			while(rs.next()){
				//Zuweisung der Daten an die jeweilige richtige Stelle im Array
				for(int i = 0; i <= 60; i++){
					datum[i] = rs.getInt("datum");
					value[i] = rs.getInt("value");
				}
			}            
			rs.close();
			connection.close(); 
		} catch (SQLException e) { 
			System.err.println("Couldn't handle DB-Query"); 
			e.printStackTrace(); 
		} 
	} 

	public static long[] Interpolation(){
		long[] endgueltigeWerte = new long[13];

		while(datum.length <= 60){
			for(int i = 0; i < 60; i++){

				/*
    			if(datum[i] > 1451606399 && datum [i] < 1454284801){    				
    				counterJaen++;
    			}
    			long[] yJaen = new long[counterJaen];
    			long[] xJaen = new long[counterJaen];

    			if(datum[i] > 1451606399 && datum [i] < 1454284801){    				
    				xJaen[i] += datum[i];
    				yJaen[i] += value[i];
    			}
    			if(datum[i] > 1451606399 && datum [i] < 1456790401){    				
    				counterFebr++;
    			}
    			long[] yFebr = new long[counterFebr];
    			long[] xFebr = new long[counterFebr];

    			if(datum[i] > 1451606399 && datum [i] < 1456790401){    				
    				xFebr[i] += datum[i];
    				yFebr[i] += value[i];
    			}
				 */

				//Zählen wie groß das spätere Array sein muss
				//das heißt zählen, wie viele Werte in jedem Monat gespeichert sind
				if(datum[i] > 1451606399 && datum [i] < 1454284801){
					counterJaen++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1456790401){
					counterFebr++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1459468801){
					counterMaer++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1462060801){
					counterApri++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1464739201){
					counterMai++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1467331201){
					counterJun++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1470009601){
					counterJul++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1472688001){
					counterAugu++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1475280001){
					counterSept++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1477958401){
					counterOkto++;
				}

				if(datum[i] > 1451606399 && datum [i] < 1480550401){
					counterNove++;
				};

				if(datum[i] > 1451606399 && datum [i] < 1483228801){
					counterDeze++;
				};
			}

			//Erzeugen der Arrays für die x- und y-Werte eines bestimmten Monats
			//mit der vorher gezählten Länge des Counters
			long[] yJaen = new long[counterJaen];
			long[] xJaen = new long[counterJaen];
			long[] yFebr = new long[counterFebr];
			long[] xFebr = new long[counterFebr];
			long[] yMaer = new long[counterMaer];
			long[] xMaer = new long[counterMaer];
			long[] yApri = new long[counterApri];
			long[] xApri = new long[counterApri];
			long[] yMai = new long[counterMai];
			long[] xMai = new long[counterMai];
			long[] yJun = new long[counterJun];
			long[] xJun = new long[counterJun];
			long[] yJul = new long[counterJul];
			long[] xJul = new long[counterJul];
			long[] yAugu = new long[counterAugu];
			long[] xAugu = new long[counterAugu];
			long[] ySept = new long[counterSept];
			long[] xSept = new long[counterSept];
			long[] yOkto = new long[counterOkto];
			long[] xOkto = new long[counterOkto];
			long[] yNove = new long[counterNove];
			long[] xNove = new long[counterNove];
			long[] yDeze = new long[counterDeze];
			long[] xDeze = new long[counterDeze];

			//Counter für späteren Gebrauch auf 0 setzen
			counterJaen = 0;
			counterFebr = 0;
			counterMaer = 0;
			counterApri = 0;
			counterMai = 0;
			counterJun = 0;
			counterJul = 0;
			counterAugu = 0;
			counterSept = 0;
			counterOkto = 0;
			counterNove = 0;
			counterDeze = 0;

			//Hinzufügen der Werte in der Datenbank an der Stelle i
			//in die x- und y-Arrays
			for(int i = 0; i < 60; i++){
				if(datum[i] > 1451606399 && datum [i] < 1454284801){    				
					xJaen[counterJaen] += datum[i];
					yJaen[counterJaen] += value[i];
					counterJaen++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1456790401){    				
					xFebr[counterFebr] += datum[i];
					yFebr[counterFebr] += value[i];
					counterFebr++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1459468801){    				
					xMaer[counterMaer] += datum[i];
					yMaer[counterMaer] += value[i];
					counterMaer++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1462060801){    				
					xApri[counterApri] += datum[i];
					yApri[counterApri] += value[i];
					counterApri++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1464739201){    				
					xMai[counterMai] += datum[i];
					yMai[counterMai] += value[i];
					counterMai++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1467331201){    				
					xJun[counterJun] += datum[i];
					yJun[counterJun] += value[i];
					counterJun++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1470009601){    				
					xJul[counterJul] += datum[i];
					yJul[counterJul] += value[i];
					counterJul++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1472688001){    				
					xAugu[counterAugu] += datum[i];
					yAugu[counterAugu] += value[i];
					counterAugu++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1475280001){    				
					xSept[counterSept] += datum[i];
					ySept[counterSept] += value[i];
					counterSept++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1477958401){    				
					xOkto[counterOkto] += datum[i];
					yOkto[counterOkto] += value[i];
					counterOkto++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1480550401){    				
					xNove[counterNove] += datum[i];
					yNove[counterNove] += value[i];
					counterNove++;
				}
				if(datum[i] > 1451606399 && datum [i] < 1483228801){    				
					xDeze[counterDeze] += datum[i];
					yDeze[counterDeze] += value[i];
					counterDeze++;
				}
			}

			//Erzeugen der Werte, die in das Text-File kommen
			long ersterWertJaen;
			long letzterWertJaen;
			long letzterWertFebr;
			long letzterWertMaer;
			long letzterWertApri;
			long letzterWertMai;
			long letzterWertJun;
			long letzterWertJul;
			long letzterWertAugu;
			long letzterWertSept;
			long letzterWertOkto;
			long letzterWertNove;
			long letzterWertDeze;

			//Falls Werte in Datenbank Minus- und 0-Werte sind, wird die Stelle im endgueltigen Array auf 0 gesetzt
			/* if(yJaen[0] <= 0){
				endgueltigeWerte[0] = 0;
			}else */ if(yJaen[yJaen.length-1] <= 0){
				endgueltigeWerte[1] = 0;

				//Formel siehe Zettel
			}else{
				ersterWertJaen = ((yJaen[yJaen.length-1] * xJaen[0]) - (yJaen[yJaen.length-1] * 1451606401) -
						(yJaen[0] * xJaen[xJaen.length-1]) + (yJaen[0] * 1451606401)) / (xJaen[0] - xJaen[xJaen.length-1]);

				letzterWertJaen = ((yJaen[yJaen.length-1] * 1454284801) - (yJaen[yJaen.length-1] * xJaen[0]) -
						(yJaen[0] * 1454284801) + (yJaen[0] * xJaen[xJaen.length-1])) / (xJaen[xJaen.length-1] - xJaen[0]);

				endgueltigeWerte[0] = ersterWertJaen;
				endgueltigeWerte[1] = letzterWertJaen;
			}
			if(yFebr[0] <= 0 || yFebr[yFebr.length-1] <= 0){
				endgueltigeWerte[2] = 0;

			}else{
				letzterWertFebr = ((yFebr[yFebr.length-1] * 1454284801) - (yFebr[yFebr.length-1] * xFebr[0]) -
						(yFebr[0] * 1454284801) + (yFebr[0] * xFebr[xFebr.length-1])) / (xFebr[xFebr.length-1] - xFebr[0]);

				endgueltigeWerte[2] = letzterWertFebr;
			}
			if(yMaer[0] <= 0 || yMaer[yMaer.length-1] <= 0){
				endgueltigeWerte[3] = 0;

			}else{
				letzterWertMaer = ((yMaer[yMaer.length-1] * 1454284801) - (yMaer[yMaer.length-1] * xMaer[0]) -
						(yMaer[0] * 1454284801) + (yMaer[0] * xMaer[xMaer.length-1])) / (xMaer[xMaer.length-1] - xMaer[0]);

				endgueltigeWerte[3] = letzterWertMaer;
			}
			if(yApri[0] <= 0 || yApri[yApri.length-1] <= 0){
				endgueltigeWerte[4] = 0;

			}else{
				letzterWertApri = ((yApri[yApri.length-1] * 1454284801) - (yApri[yApri.length-1] * xApri[0]) -
						(yApri[0] * 1454284801) + (yApri[0] * xApri[xApri.length-1])) / (xApri[xApri.length-1] - xApri[0]);

				endgueltigeWerte[4] = letzterWertApri;
			}
			if(yMai[0] <= 0 || yMai[yMai.length-1] <= 0){
				endgueltigeWerte[5] = 0;

			}else{
				letzterWertMai = ((yMai[yMai.length-1] * 1454284801) - (yMai[yMai.length-1] * xMai[0]) -
						(yMai[0] * 1454284801) + (yMai[0] * xMai[xMai.length-1])) / (xMai[xMai.length-1] - xMai[0]);

				endgueltigeWerte[5] = letzterWertMai;
			}
			if(yJun[0] <= 0 || yJun[yJun.length-1] <= 0){
				endgueltigeWerte[6] = 0;

			}else{
				letzterWertJun = ((yJun[yJun.length-1] * 1454284801) - (yJun[yJun.length-1] * xJun[0]) -
						(yJun[0] * 1454284801) + (yJun[0] * xJun[xJun.length-1])) / (xJun[xJun.length-1] - xJun[0]);

				endgueltigeWerte[6] = letzterWertJun;
			}
			if(yJul[0] <= 0 || yJul[yJul.length-1] <= 0){
				endgueltigeWerte[7] = 0;

			}else{
				letzterWertJul = ((yJul[yJul.length-1] * 1454284801) - (yJul[yJul.length-1] * xJul[0]) -
						(yJul[0] * 1454284801) + (yJul[0] * xJul[xJul.length-1])) / (xJul[xJul.length-1] - xJul[0]);

				endgueltigeWerte[7] = letzterWertJul;
			}
			if(yAugu[0] <= 0 || yAugu[yAugu.length-1] <= 0){
				endgueltigeWerte[8] = 0;

			}else{
				letzterWertAugu = ((yAugu[yAugu.length-1] * 1454284801) - (yAugu[yAugu.length-1] * xAugu[0]) -
						(yAugu[0] * 1454284801) + (yAugu[0] * xAugu[xAugu.length-1])) / (xAugu[xAugu.length-1] - xAugu[0]);

				endgueltigeWerte[8] = letzterWertAugu;
			}
			if(ySept[0] <= 0 || ySept[ySept.length-1] <= 0){
				endgueltigeWerte[9] = 0;

			}else{
				letzterWertSept = ((ySept[ySept.length-1] * 1454284801) - (ySept[ySept.length-1] * xSept[0]) -
						(ySept[0] * 1454284801) + (ySept[0] * xSept[xSept.length-1])) / (xSept[xSept.length-1] - xSept[0]);

				endgueltigeWerte[9] = letzterWertSept;
			}
			if(yOkto[0] <= 0 || yOkto[yOkto.length-1] <= 0){
				endgueltigeWerte[10] = 0;

			}else{
				letzterWertOkto = ((yOkto[yOkto.length-1] * 1454284801) - (yOkto[yOkto.length-1] * xOkto[0]) -
						(yOkto[0] * 1454284801) + (yOkto[0] * xOkto[xOkto.length-1])) / (xOkto[xOkto.length-1] - xOkto[0]);

				endgueltigeWerte[10] = letzterWertOkto;
			}
			if(yNove[0] <= 0 || yNove[yNove.length-1] <= 0){
				endgueltigeWerte[11] = 0;

			}else{
				letzterWertNove = ((yNove[yNove.length-1] * 1454284801) - (yNove[yNove.length-1] * xNove[0]) -
						(yNove[0] * 1454284801) + (yNove[0] * xNove[xNove.length-1])) / (xNove[xNove.length-1] - xNove[0]);

				endgueltigeWerte[11] = letzterWertNove;
			}
			if(yDeze[0] <= 0 || yDeze[yDeze.length-1] <= 0){
				endgueltigeWerte[12] = 0;

			}else{
				letzterWertDeze = ((yDeze[yDeze.length-1] * 1454284801) - (yDeze[yDeze.length-1] * xDeze[0]) -
						(yDeze[0] * 1454284801) + (yDeze[0] * xDeze[xDeze.length-1])) / (xDeze[xDeze.length-1] - xDeze[0]);

				endgueltigeWerte[12] = letzterWertDeze;
			}
			datum = new long[61];
		}
		return endgueltigeWerte;
	}

	//Werte in ein Textfile schreiben
	public static void Ausgabe(String wert){
		if(wert.equals("Strom")){

			try {
				out = new FileWriter("C:/Users/Tommy B/Documents/HTL 5AHW/INFI-DBP/Stromverbrauch_Diagramm/Strom.txt");
			}catch(IOException e){}
		}

		if(wert.equals("Wasser")){
			try {
				out = new FileWriter("C:/Users/Tommy B/Documents/HTL 5AHW/INFI-DBP/Stromverbrauch_Diagramm/Wasser.txt");
			}catch(IOException e){}

		}

		/*
		AnotherOut = new BufferedWriter(out);

		for(int i = 0; i < Interpolation().length; i ++){

			int a = toIntExact(Interpolation()[i]);
			try {
				AnotherOut.write(Integer.toString(a));
			} catch (IOException e) {}	

		}
		 */
	}

	public static void main(String[] args) { 
		SqliteManager sm = SqliteManager.getInstance(); 
		sm.getConnection(); 
		sm.getData();
		Interpolation();
		Ausgabe("Strom");
	} 
}
