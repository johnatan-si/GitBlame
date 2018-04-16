package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExecuteShellComand {

	private static final String FILENAME = "/home/johnatan/pk.txt";

	public static void main(String[] args) throws IOException {
		

		BufferedWriter bw = null;
		FileWriter fw = null;
		
		List<String> commands = new ArrayList<String>();
		commands.add("/bin/bash");
		commands.add("-c");
		// Add arguments
		commands.add("cd /home/johnatan/Test/angularjs-eclipse/org.eclipse.angularjs.core/src/org/eclipse/angularjs/core/");
		commands.add("git blame -l ScriptsFolder.java");
		
		ExecuteShellComand obj = new ExecuteShellComand();
		
		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);

		//String output = obj.executeCommand(commands,bw,fw);

		//System.out.println(output);

	
		StringBuffer output = new StringBuffer();

		Process p = null;
		try {
			for(int i=0; i<commands.size(); i++) {
				p = Runtime.getRuntime().exec(commands.get(i));
				p.waitFor();
			}
			
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

		

	}

}
