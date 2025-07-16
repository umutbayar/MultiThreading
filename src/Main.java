public class Main {
    public static void main(String[] args) {
        System.out.println("Program başlatılıyor");

        long startTime = System.nanoTime();

        try {
            // FileAnalyzer (Bayram) ile dosya analizi başlat
            System.out.println("Dosya analizi başlatılıyor");
            FileAnalyzer analyzer = new FileAnalyzer("input"); // input klasörünü analiz et
            analyzer.analyzeFiles();

            // ArchiveTask (Furkan) ile arşivleme başlat
            System.out.println("Arşivleme başlatılıyor");
            ArchiveTask archiver = new ArchiveTask("output.zip");
            archiver.start();
            archiver.join(); 

            //  Raporlama (Şevval) için sonuçları al
            System.out.println("Raporlama yapılıyor");
            Utils.printSummary();

        } catch (Exception e) {
            System.err.println("Hata: " + e.getMessage());
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        double totalTimeSec = Utils.nanoToSeconds(endTime - startTime);

        System.out.printf("Program tamamlandı. Toplam süre: %.3f saniye\n", totalTimeSec);
    }
}
