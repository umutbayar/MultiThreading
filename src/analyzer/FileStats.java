package analyzer;

public class FileStats {
	
	private int lineCount;
	private int charCount;

	public FileStats(int lineCount, int charCount) {
		this.lineCount = lineCount;
		this.charCount = charCount;
	}

	//Satır sayısını döndürür.
	public int getLineCount() {
		return lineCount;
	}

	//Karakter sayısını döndürür.
	public int getCharCount() {
		return charCount;
	}
	
}
