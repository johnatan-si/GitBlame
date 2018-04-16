package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

public class ShellScript2 {

	public static void main(String[] args) throws IOException, InterruptedException {

		BufferedWriter bw = null;
		FileWriter fw = null;

		String FILENAME = "/home/johnatan/jesus.txt";
		fw = new FileWriter(FILENAME);
		bw = new BufferedWriter(fw);

		String[] cmd = { "/bin/sh", "-c",
				"cd /home/johnatan/Test/angularjs-eclipse/org.eclipse.angularjs.core/src/org/eclipse/angularjs/core/; git blame -l ScriptsFolder.java" };
		Process p = Runtime.getRuntime().exec(cmd);

		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuffer output = new StringBuffer();

		p.waitFor();

		String line = "";
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
			System.out.println(line + "\n");
			bw.write(line + "\n");

		}
		bw.close();
		fw.close();

	}
}