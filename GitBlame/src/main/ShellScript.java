package main;
import 
java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class ShellScript {

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
						fw = new FileWriter(f.getAbsoluteFile() + "/" + f.getName() + "_output.txt");
						System.out.println("Save in " + f.getAbsoluteFile() + "/" + f.getName() + ".txt");
						bw = new BufferedWriter(fw);
						calc(f, bw, fw);
					}
				}
			}
		}

	}

	private static void calc(File arq, BufferedWriter bw, FileWriter fw) throws IOException, InterruptedException {

		File dir = new File(arq + "/");
		String[] extensions = new String[] { "java" };
		Process process = null;
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			System.out.println("file: " + file.getCanonicalPath());
			String[] cmd = { "/bin/sh", "-c", "cd " + dir.toString() + "; git blame -l " + file.getCanonicalFile() };
//git blame -l -c  --date=iso --pretty=format:'"%h","%an","%ad","%s"' FileSystemCrawler.java > t.csv
			//--date=iso --pretty=format:'"%h","%an","%ad","%s"'
			// Process process = Runtime.getRuntime().exec(cmd);  teste
			process = Runtime.getRuntime().exec(cmd);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuffer output = new StringBuffer();

			// p.waitFor();
			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				System.out.println(line + "\n");
				bw.write(line + "\n");
			}

		}
		// Check result
		/*if (process.waitFor() == 0) {
			System.out.println("Success!");
			System.exit(0);
		}*/
		bw.close();
		fw.close();
	}
}