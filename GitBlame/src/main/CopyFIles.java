package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class CopyFIles {

	public static void main(String[] args) throws IOException {
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

						
						calc(f);
					}
				}
			}
		}

	}

	private static void calc(File arq) throws IOException {
		File dir = new File(arq + "/");
		String[] extensions = new String[] {"csv"};

		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		
		
		File destinationDir = new File("/home/johnatan/output-brute/");

		
		
		for (File file : files) {
		 if(file.getName().endsWith("_output.csv")) {
			 File fileac = new File(file.getAbsolutePath());
			 System.out.println(fileac+" "+destinationDir);
			 FileUtils.copyFileToDirectory(fileac, destinationDir);
			 }
		 }
	}

}