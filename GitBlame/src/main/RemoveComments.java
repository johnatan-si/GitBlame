package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class RemoveComments {

	public static void main(String[] args) throws IOException, InterruptedException {

		BufferedWriter bw = null;
		FileWriter fw = null;
		PrintWriter pw = null;

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
					}

						System.out.println("Save in " +"/home/johnatan/scam/output-withoutComments"+f.getName());
						pw = new PrintWriter(new FileWriter("/home/johnatan/scam/output-withoutComments/"+f.getName()));

						getNumberOfLines(f, pw);
					
				}
			}
		}

	}

	public static int getNumberOfLines(File f, PrintWriter pw) throws IOException {

		int count = 0;
		boolean commentBegan = false;
		String line = null;

		BufferedReader br = null;
		String cvsSplitBy = "	";
		br = new BufferedReader(new FileReader(f));

		while ((line = br.readLine()) != null) {
			line = line.trim();
			String[] country = line.split(cvsSplitBy);

			country[3] = country[3].replaceAll("[()]", "").replaceAll("[0-9]", "");
			System.out.println(country[3]);
			if ("".equals(country[3]) || country[3].startsWith("//")) {
				continue;
			}
			if (commentBegan) {
				if (commentEnded(country[3])) {
					country[3] = country[3].substring(country[3].indexOf("*/") + 2).trim();
					commentBegan = false;
					if ("".equals(country[3]) || country[3].startsWith("//")) {
						continue;
					}
				} else {
					continue;
				}
			}
			if (isSourceCodeLine(country[3])) {
				count++;
				pw.println(line);
				pw.flush();
			} else {

			}
			if (commentBegan(country[3])) {
				commentBegan = true;

			}
		}
		pw.close();
		br.close();

		return count;
	}

	/**
	 * 
	 * @param line
	 * @return This method checks if in the given line a comment has begun and has
	 *         not ended
	 */
	private static boolean commentBegan(String line) {
		// If line = /* */, this method will return false
		// If line = /* */ /*, this method will return true
		int index = line.indexOf("/*");
		if (index < 0) {
			return false;
		}
		int quoteStartIndex = line.indexOf("\"");
		if (quoteStartIndex != -1 && quoteStartIndex < index) {
			while (quoteStartIndex > -1) {
				line = line.substring(quoteStartIndex + 1);
				int quoteEndIndex = line.indexOf("\"");
				line = line.substring(quoteEndIndex + 1);
				quoteStartIndex = line.indexOf("\"");
			}
			return commentBegan(line);
		}
		return !commentEnded(line.substring(index + 2));
	}

	/**
	 * 
	 * @param line
	 * @return This method checks if in the given line a comment has ended and no
	 *         new comment has not begun
	 */
	private static boolean commentEnded(String line) {
		// If line = */ /* , this method will return false
		// If line = */ /* */, this method will return true
		int index = line.indexOf("*/");
		if (index < 0) {
			return false;
		} else {
			String subString = line.substring(index + 2).trim();
			if ("".equals(subString) || subString.startsWith("//")) {
				return true;
			}
			if (commentBegan(subString)) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 
	 * @param line
	 * @return This method returns true if there is any valid source code in the
	 *         given input line. It does not worry if comment has begun or not. This
	 *         method will work only if we are sure that comment has not already
	 *         begun previously. Hence, this method should be called only after
	 *         {@link #commentBegan(String)} is called
	 */
	private static boolean isSourceCodeLine(String line) {
		boolean isSourceCodeLine = false;
		line = line.trim();
		if ("".equals(line) || line.startsWith("//")) {
			return isSourceCodeLine;
		}
		if (line.length() == 1) {
			return true;
		}
		int index = line.indexOf("/*");
		if (index != 0) {
			return true;
		} else {
			while (line.length() > 0) {
				line = line.substring(index + 2);
				int endCommentPosition = line.indexOf("*/");
				if (endCommentPosition < 0) {
					return false;
				}
				if (endCommentPosition == line.length() - 2) {
					return false;
				} else {
					String subString = line.substring(endCommentPosition + 2).trim();
					if ("".equals(subString) || subString.indexOf("//") == 0) {
						return false;
					} else {
						if (subString.startsWith("/*")) {
							line = subString;
							continue;
						}
						return true;
					}
				}

			}
		}
		return isSourceCodeLine;
	}
}
