package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CSVReader {

	public CSVReader(FileReader fileReader) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {

		String csvFile = "/home/johnatan/commits.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "	";

        Connection conn = ConexaoBD.getConnection();
		
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				// the mysql insert statement
				String query = " insert into commit (codeCommit, name, date, code)" + " values (?, ?, ?, ?)";

				// create the mysql insert preparedstatement
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, country[0]);
				preparedStatement.setString(2, country[1].replaceAll("[()]", ""));
				preparedStatement.setString(3, country[2].replaceAll("[()]", ""));
				preparedStatement.setString(4, country[3].replaceAll("[()]", "").replaceAll("[0-9]",""));

				// execute the preparedstatement
				//preparedStatement.execute();

			//	System.out.println(" [codeCommit= " + country[0] + " , name=" + country[1] + ", date=" + country[2]	+ ", code=" + country[3] + "]");
				String replaced = country[1].replaceAll("[()]", "");
				
				if(country[3].toLowerCase().contains("angular")) {
					System.out.println(country[3]);
				}
				
				
			}
			conn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}