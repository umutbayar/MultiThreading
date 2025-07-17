package analyzer;

public class FileStats {
	
	private int lineCount;
	private int charCount;

	public FileStats(int lineCount, int charCount) {
		this.lineCount = lineCount;
		this.charCount = charCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getCharCount() {
		return charCount;
	}
	
}
