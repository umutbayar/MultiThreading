package analyzer;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FileAnalyzer sınıfı, belirtilen bir klasördeki tüm .txt dosyalarını analiz eder.
 * Her dosya ayrı bir thread ile işlenir ve analiz sonuçları eş zamanlı olarak saklanır.
 */
public class FileAnalyzer {

    private final String inputDirectory; // Girdi klasörünün yolu
    private final ConcurrentHashMap<String, FileStats> resultMap; // Dosya istatistiklerini saklayan eşzamanlı harita

    // Yapıcı metod: girdi klasörü yolu ve sonuç haritası alınır
    public FileAnalyzer(String inputDirectory, ConcurrentHashMap<String, FileStats> resultMap) {
        this.inputDirectory = inputDirectory;
        this.resultMap = resultMap;
    }

    /**
     * analyzeFiles metodu, inputDirectory'deki tüm .txt dosyalarını analiz eder.
     * Her dosya bir thread pool üzerinden işlenir.
     */
    public void analyzeFiles() {
        File folder = new File(inputDirectory);

        // Sadece .txt uzantılı dosyaları al
        File[] txtFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // Eğer dosya yoksa uyarı ver ve işlemi sonlandır
        if (txtFiles == null || txtFiles.length == 0) {
            System.out.println("input/ klasöründe .txt dosyası bulunamadı.");
            return;
        }

        // 10 thread'lik bir havuz oluştur (ThreadPool)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Her bir .txt dosyası için analiz görevini thread pool'a gönder
        for (File file : txtFiles) {
            executor.submit(new FileAnalysisTask(file, resultMap));
        }

        // Yeni görev kabul edilmeyecek
        executor.shutdown();

        try {
            // Tüm görevlerin bitmesi bekleniyor
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Bir thread beklenirken hata oluştu: " + e.getMessage());
        }

        // Sonuçları ekrana yazdır
        printResults();
    }

    /**
     * printResults metodu, her dosyanın satır ve karakter sayısını ekrana yazar.
     * Ayrıca toplam satır ve karakter sayısını da verir.
     */
    private void printResults() {
        int totalLines = 0; // Toplam satır sayısı
        int totalChars = 0; // Toplam karakter sayısı

        // Her bir dosyanın sonuçlarını yazdır
        for (Map.Entry<String, FileStats> entry : resultMap.entrySet()) {
            String fileName = entry.getKey();
            FileStats stats = entry.getValue();
            System.out.println(fileName + " - " + stats.getLineCount() + " satır / " + stats.getCharCount() + " karakter");

            totalLines += stats.getLineCount();
            totalChars += stats.getCharCount();
        }

        // Genel toplamları yazdır
        System.out.println("\nToplam: " + totalLines + " satır / " + totalChars + " karakter");
    }
}
