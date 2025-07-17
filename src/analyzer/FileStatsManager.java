package analyzer;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FileStatsManager {
	
	private final ConcurrentHashMap<String, FileStats> statsMap;

	public FileStatsManager(ConcurrentHashMap<String, FileStats> statsMap) {
		this.statsMap = statsMap;
	}

	public void addStats(String fileName, FileStats stats) {
		statsMap.put(fileName, stats);
	}

	public Set<String> getAllFileNames() {
		return statsMap.keySet();
	}

	public FileStats getStats(String fileName) {
		return statsMap.get(fileName);
	}

	public void printAllStats() {
		int totalLines = 0;
		int totalChars = 0;
		for (Map.Entry<String, FileStats> entry : statsMap.entrySet()) {
			String fileName = entry.getKey();
			FileStats stats = entry.getValue();
			System.out.println(
					fileName + " - " + stats.getLineCount() + " satır / " + stats.getCharCount() + " karakter");
			totalLines += stats.getLineCount();
			totalChars += stats.getCharCount();
		}
		
		System.out.println("\nToplam: " + totalLines + " satır / " + totalChars + " karakter");
		
	}

	public Map<String, FileStats> getStatsMap() {
		return statsMap;
	}

}