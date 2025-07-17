import analyzer.ArchiveTask;
import analyzer.FileAnalyzer;
import java.util.concurrent.ConcurrentHashMap;
import analyzer.FileStats;
import analyzer.FileStatsManager;
import analyzer.Utils;
public class Main {
public static void main(String[] args) {

System.out.println("Program başlatılıyor");
long startTime = System.nanoTime();
try {

// Initialize a thread-safe map for storing file stats

ConcurrentHashMap<String, FileStats> resultMap = new ConcurrentHashMap<>();

// FileAnalyzer (Bayram) ile dosya analizi başlat

System.out.println("Dosya analizi başlatılıyor");

FileAnalyzer analyzer = new FileAnalyzer("input", resultMap); // input klasörünü analiz et

analyzer.analyzeFiles();

// ArchiveTask (Furkan) ile arşivleme başlat

System.out.println("Arşivleme başlatılıyor");

ArchiveTask archiver = new ArchiveTask(new java.io.File("input"), new java.io.File("output/archived-files.zip"));

Thread archiveThread = new Thread(archiver);

archiveThread.start();

archiveThread.join();

// Raporlama (Şevval) için sonuçları al

System.out.println("Raporlama yapılıyor");

FileStatsManager manager = new FileStatsManager(resultMap);

manager.printAllStats();

} catch (Exception e) {

System.err.println("Hata: " + e.getMessage());

e.printStackTrace();

}
long endTime = System.nanoTime();
double totalTimeSec = Utils.nanoToSeconds(endTime - startTime);

System.out.printf("Program tamamlandı. Toplam süre: %.3f saniye\n", totalTimeSec);

}

}

 
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

 