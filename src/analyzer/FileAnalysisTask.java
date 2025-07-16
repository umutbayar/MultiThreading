// FileAnalysisTask implemented by Sevval

package analyzer;

import java.io.*;

import java.util.concurrent.ConcurrentHashMap;

public class FileAnalysisTask implements Runnable {

    private final File file;

    private final ConcurrentHashMap<String, FileStats> resultMap;

    public FileAnalysisTask(File file, ConcurrentHashMap<String, FileStats> resultMap) {

        this.file = file;

        this.resultMap = resultMap;

    }

    @Override

    public void run() {

        int lineCount = 0;

        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                lineCount++;

                charCount += line.length(); // boşluklar dahil

            }

            // Sonucu resultMap'e kaydet (thread-safe)

            resultMap.put(file.getName(), new FileStats(lineCount, charCount));

        } catch (IOException e) {

            System.err.println("Dosya okunurken hata oluştu: " + file.getName());

        }

    }

}

