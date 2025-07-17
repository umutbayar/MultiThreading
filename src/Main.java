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

            //  Raporlama (Şevval) için sonuçları al
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
