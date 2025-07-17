package analyzer;

public class Utils {
    // Nanosecond to second dönüşümü
    public static double nanoToSeconds(long nanoTime) {
        return nanoTime / 1_000_000_000.0;
    }

    // Basit bir log metodu
    public static void log(String message) {
        System.out.println("[LOG] " + message);
    }

    // Sonuçların formatlı çıktısını ekrana yazdır (Hakan’ın FileStats’ını kullanarak)
    public static void printSummary(FileStatsManager manager) {
        System.out.println("\n=== Dosya Analizi Sonuçları ===");

        for (String fileName : manager.getAllFileNames()) {
            FileStats stats = manager.getStats(fileName);
            System.out.println(fileName + " - " + stats.getLineCount() + " satır / " + stats.getCharCount() + " karakter");
        }

        System.out.println("===============================");
    }
}
