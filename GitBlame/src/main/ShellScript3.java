package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Lists;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class ShellScript3 {

	public static void main(String[] args) throws IOException, InterruptedException {

		BufferedWriter bw = null;
		FileWriter fw = null;

		JFileChooser fc = new JFileChooser();
		String path = null, saveCsv = null;
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = fc.showOpenDialog(null);

		
		
		if (res == JFileChooser.APPROVE_OPTION) {

			File diretorio = fc.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Directory Selected: " + diretorio.getName());

			File[] arquivos = diretorio.listFiles();
			if (arquivos != null) {
				int length = arquivos.length;
				for (int i = 0; i < length; ++i) {
					File f = arquivos[i];
					if (f.isFile()) {
					} else if (f.isDirectory()) {

						// System.out.println("Directory: " + f.getAbsolutePath());

						// Conferindo andamento
						// System.out.println(i + 1 + " | " + length);

						// path = f.getAbsolutePath();
						fw = new FileWriter(f.getName());
						bw = new BufferedWriter(fw);
						calc(f, bw, fw);
					}
				}
			}
		}

		// System.out.println("--- FIM ---");

		String FILENAME = "/home/johnatan/jesus.txt";

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

	private static void calc(File arq, BufferedWriter bw, FileWriter fw) throws IOException, InterruptedException {
		
		
		File dir = new File(arq+"/");
		String[] extensions = new String[] {"java"};
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			System.out.println("file: " + file.getCanonicalPath());
			String[] cmd = { "/bin/sh", "-c","cd "+dir.toString()+"; git blame -l "+file.getCanonicalFile() }; 
			
			 Process p = Runtime.getRuntime().exec(cmd);
			  
			  BufferedReader reader = new BufferedReader(new
			  InputStreamReader(p.getInputStream())); StringBuffer output = new
			  StringBuffer();
			  
			  p.waitFor();
			  String line = ""; 
			  while ((line = reader.readLine()) != null) {
				  output.append(line + "\n"); 
				  System.out.println(line + "\n"); bw.write(line +"\n");
		}	
		
		bw.close(); 
		fw.close();
	}

	}
}