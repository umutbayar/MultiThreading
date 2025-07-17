package analyzer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileStatsManager {
	
	private final ConcurrentHashMap<String, FileStats> statsMap = new ConcurrentHashMap<>();

	public void addStats(String fileName, FileStats stats) {
	    statsMap.put(fileName, stats);
	}

	
	public void printAllStats() {
	    int totalLines = 0;
	    int totalChars = 0;

	    for (Map.Entry<String, FileStats> entry : statsMap.entrySet()) {
	        String fileName = entry.getKey();
	        FileStats stats = entry.getValue();

	       
	        System.out.println(fileName + " - " + stats.getLineCount() + " satır / " + stats.getCharCount() + " karakter");

	        totalLines += stats.getLineCount();
	        totalChars += stats.getCharCount();
	    }

	  
	    System.out.println("\nToplam: " + totalLines + " satır / " + totalChars + " karakter");
	}

	
	public Map<String, FileStats> getStatsMap() {
	    return statsMap;
	}
//test
}
