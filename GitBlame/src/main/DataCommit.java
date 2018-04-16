package main;

public class DataCommit {

	String author, dateCommit, hourCommit, path, classe, snippetSourceCode; 
	int codeCommit;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateCommit() {
		return dateCommit;
	}
	public void setDateCommit(String dateCommit) {
		this.dateCommit = dateCommit;
	}
	public String getHourCommit() {
		return hourCommit;
	}
	public void setHourCommit(String hourCommit) {
		this.hourCommit = hourCommit;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getSnippetSourceCode() {
		return snippetSourceCode;
	}
	public void setSnippetSourceCode(String snippetSourceCode) {
		this.snippetSourceCode = snippetSourceCode;
	}
	public int getCodeCommit() {
		return codeCommit;
	}
	public void setCodeCommit(int codeCommit) {
		this.codeCommit = codeCommit;
	}
}
