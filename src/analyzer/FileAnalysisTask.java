package analyzer;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FileAnalysisTask sınıfı, Runnable arayüzünü uygular.
 * Bu sınıf, bir dosyanın satır ve karakter sayısını hesaplayıp
 * sonucu eş zamanlı bir haritaya (ConcurrentHashMap) kaydetmek için kullanılır.
 */
public class FileAnalysisTask implements Runnable {

    private final File file; // Analiz edilecek dosya
    private final ConcurrentHashMap<String, FileStats> resultMap; // Dosya adı ile istatistikleri tutan eşzamanlı harita

    /**
     * Yapıcı metod: analiz edilecek dosya ve sonuçların yazılacağı map alınır
     * @param file Analiz edilecek dosya
     * @param resultMap Sonuçların ekleneceği eşzamanlı harita
     */
    public FileAnalysisTask(File file, ConcurrentHashMap<String, FileStats> resultMap) {
        this.file = file;
        this.resultMap = resultMap;
    }

    /**
     * run metodu, thread çalıştırıldığında çağrılır.
     * Dosyanın içeriği satır satır okunur, satır ve karakter sayısı hesaplanır,
     * sonuç resultMap'e eklenir.
     */
    @Override
    public void run() {
        int lineCount = 0; // Toplam satır sayısı
        int charCount = 0; // Toplam karakter sayısı

        // Dosya okuma işlemi try-with-resources ile yapılır (otomatik kapatma)
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Dosya satır satır okunur
            while ((line = reader.readLine()) != null) {
                lineCount++; // Satır sayısı 1 artırılır
                charCount += line.length(); // Satırın karakter uzunluğu eklenir (boşluklar dahil)
            }

            // Hesaplanan istatistikler, dosya adı ile birlikte resultMap'e eklenir
            // ConcurrentHashMap sayesinde thread-safe bir şekilde yazılır
            resultMap.put(file.getName(), new FileStats(lineCount, charCount));

        } catch (IOException e) {
            // Dosya okunurken bir hata olursa ekrana yazdırılır
            System.err.println("Dosya okunurken hata oluştu: " + file.getName());
        }
    }
}
