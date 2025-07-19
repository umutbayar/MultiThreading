import analyzer.ArchiveTask; // Dosyaları arşivlemek için kullanılan sınıf
import analyzer.FileAnalyzer; // Dosya analizlerini gerçekleştiren sınıf
import java.util.concurrent.ConcurrentHashMap; // Thread-safe HashMap
import analyzer.FileStats; // Dosya istatistiklerini tutan sınıf
import analyzer.FileStatsManager; // Dosya istatistiklerini yöneten sınıf
import analyzer.Utils; // Yardımcı metotları barındıran sınıf

public class Main {
    public static void main(String[] args) {
        System.out.println("Program başlatılıyor"); // Programın başladığını konsola yazdır

        long startTime = System.nanoTime(); // Programın başlangıç zamanını nanosecond cinsinden al

        try {
            // Thread-safe bir map oluştur. Her dosya için FileStats nesnelerini tutacak
            ConcurrentHashMap<String, FileStats> resultMap = new ConcurrentHashMap<>();

            // === Dosya Analizi ===
            System.out.println("Dosya analizi başlatılıyor");
            // input klasöründeki dosyaları analiz et
            FileAnalyzer analyzer = new FileAnalyzer("input", resultMap); 
            analyzer.analyzeFiles(); // Dosya analizini başlat

            // === Dosya Arşivleme ===
            System.out.println("Arşivleme başlatılıyor");
            // input klasöründeki dosyaları output/archived-files.zip içerisine arşivle
            ArchiveTask archiver = new ArchiveTask(
                new java.io.File("input"), 
                new java.io.File("output/archived-files.zip")
            );
            Thread archiveThread = new Thread(archiver); // Arşivleme işlemini ayrı bir thread'de çalıştır
            archiveThread.start(); // Arşivleme işlemini başlat
            archiveThread.join();  // Arşivleme işlemi tamamlanana kadar bekle

            // === Raporlama ===
            System.out.println("Raporlama yapılıyor");
            // Analiz sonuçlarını kullanarak raporlama işlemini gerçekleştir
            FileStatsManager manager = new FileStatsManager(resultMap);
            manager.printAllStats(); // Tüm dosya istatistiklerini yazdır

        } catch (Exception e) {
            // Herhangi bir hata oluşursa hata mesajını konsola yazdır
            System.err.println("Hata: " + e.getMessage());
            e.printStackTrace();
        }

        // Programın toplam çalışma süresini hesapla ve yazdır
        long endTime = System.nanoTime(); // Programın bitiş zamanını al
        double totalTimeSec = Utils.nanoToSeconds(endTime - startTime); // Nanosecond'u saniyeye çevir

        System.out.printf("Program tamamlandı. Toplam süre: %.3f saniye\n", totalTimeSec);
    }
}
