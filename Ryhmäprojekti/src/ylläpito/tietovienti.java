package yll�pito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class tietovienti {
	public static void vietieto() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		// Yhteyden tiedot
		String db = "trtkp20a3";
		String url = "jdbc:mysql://shell.hamk.fi/" + db + "?useSSL=false";
		String username = "trtkp20a3";
		String password = "trtkp20a3passwd";

		// Tietokannan taulun kent�t tulostusta varten
		int tuote_id;
		String nimi;
		int hinta;
		int kalorit;
		int tuoteryhma;
		int valintanro;
		Scanner in = new Scanner(System.in);

		System.out.println();
		System.out.println("Lis�� tuotteita:");
		System.out.println();
		System.out.println("Anna tuotteen nimi");
		nimi = in.nextLine();

		System.out.println("Anna tuoteryhm� 1: Leip�	2: T�yte	3: Lis�t�yte\n4: Kastike	5: Juoma");
		tuoteryhma = Integer.parseInt(in.nextLine());

		System.out.println("Anna tuotteen hinta");
		hinta = Integer.parseInt(in.nextLine());

		System.out.println("Sy�t� kalorim��r�");
		kalorit = Integer.parseInt(in.nextLine());

		try {
			valintanro = 0 ;
			// 1. yhteys
			connection = DriverManager.getConnection(url, username, password);
			
			// 2. MySQL-kysely
			statement = connection.createStatement();
			String querySelect = "SELECT valintanro FROM ryhm�10_taulu WHERE tuoteryhma =" + tuoteryhma;
			resultSet = statement.executeQuery(querySelect);
			
			
			while (resultSet.next()) {
				valintanro = resultSet.getInt("valintanro");
				
			}
//			System.out.println("\nSy�t� valintanro");
			valintanro++;
			
			String queryInsert = "INSERT INTO ryhm�10_taulu (tuoteryhma,nimi,hinta,kalorit,valintanro) VALUES ('" + tuoteryhma
					+ "','" + nimi + "','" + hinta + "','" + kalorit + "'," + valintanro +")";

			// 3. Suoritetaan kysely
			statement.executeUpdate(queryInsert); // lis�� tietokantaan

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 5. Yhteyden sulkeminen ja kyselyjen nollaus
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
		}
	}
}
