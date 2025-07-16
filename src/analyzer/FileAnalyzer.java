package analyzer;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileAnalyzer {

    private final String inputDirectory;
    private final ConcurrentHashMap<String, FileStats> resultMap;

    public FileAnalyzer(String inputDirectory, ConcurrentHashMap<String, FileStats> resultMap) {
        this.inputDirectory = inputDirectory;
        this.resultMap = resultMap;
    }

    public void analyzeFiles() {
        File folder = new File(inputDirectory);
        File[] txtFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (txtFiles == null || txtFiles.length == 0) {
            System.out.println("input/ klasöründe .txt dosyası bulunamadı.");
            return;
        }

        List<Thread> threadList = new ArrayList<>();

        for (File file : txtFiles) {
            Thread thread = new Thread(new FileAnalysisTask(file, resultMap));
            threadList.add(thread);
            thread.start();
        }

        // Tüm threadlerin bitmesini bekle (join)
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Bir thread beklenirken hata oluştu: " + e.getMessage());
            }
        }

        // (Opsiyonel) Konsola sonucu yazdır
        printResults();
    }

    private void printResults() {
        int totalLines = 0;
        int totalChars = 0;

        for (Map.Entry<String, FileStats> entry : resultMap.entrySet()) {
            String fileName = entry.getKey();
            FileStats stats = entry.getValue();
            System.out.println(fileName + " - " + stats.getLineCount() + " satır / " + stats.getCharCount() + " karakter");

            totalLines += stats.getLineCount();
            totalChars += stats.getCharCount();
        }

        System.out.println("\nToplam: " + totalLines + " satır / " + totalChars + " karakter");
    }
}