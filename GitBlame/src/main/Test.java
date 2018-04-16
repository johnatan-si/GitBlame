package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(final String[] args) throws IOException, InterruptedException {
		// Build command
		List<String> commands = new ArrayList<String>();
		commands.add("/bin/bash");
		commands.add("-c");
		// Add arguments
		commands.add("cd /home/johnatan/Test/angularjs-eclipse/org.eclipse.angularjs.core/src/org/eclipse/angularjs/core/");
		commands.add("git blame -l ScriptsFolder.java");
		System.out.println(commands);

		// Run macro on target
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.redirectErrorStream(true);
		
		Process process = pb.start();

		InputStream i = process.getInputStream();

		InputStreamReader isr = new InputStreamReader(i);

		BufferedReader br = new BufferedReader(isr);

		File f = new File("/home/johnatan/agora.txt");

		FileWriter fw = new FileWriter(f); // for appending use (f,true)

		BufferedWriter bw = new BufferedWriter(fw);

		while ((br.readLine()) != null) {

			bw.write(br.readLine()); // You can also use append.
		}
		bw.close();
		fw.close();

		// Check result
		if (process.waitFor() == 0) {
			System.out.println("Success!");
			System.exit(0);
		}

		// Abnormal termination: Log command parameters and output and throw
		// ExecutionException
		System.err.println(commands);
		System.exit(1);
	}
}