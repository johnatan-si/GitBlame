package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteShellComand2 {

	private static final String FILENAME = "/home/johnatan/pk.txt";

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		ExecuteShellComand2 obj = new ExecuteShellComand2();

		String domainName = "google.com";

		// in mac oxs
		String command = "ping -c 3 " + domainName;

		// in windows
		// String command = "ping -n 3 " + domainName;
		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);

		String output = obj.executeCommand(command,bw,fw);

		System.out.println(output);

	}

	private String executeCommand(String command, BufferedWriter bw, FileWriter fw) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				bw.write(line + "\n");

			}
			bw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
