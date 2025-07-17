package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveTask implements Runnable {

    private final File inputDirectory;
    private final File outputZipFile;

    public ArchiveTask(File inputDirectory, File outputZipFile) {
        this.inputDirectory = inputDirectory;
        this.outputZipFile = outputZipFile;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();

        File[] txtFiles = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (txtFiles == null || txtFiles.length == 0) {
            System.err.println("Arşivlenecek .txt dosyası bulunamadı.");
            return;
        }

        // Ensure output directory exists
        File parentDir = outputZipFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(outputZipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (File file : txtFiles) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }

                    zos.closeEntry();
                } catch (IOException e) {
                    System.err.println("Hata: " + file.getName() + " arşivlenemedi → " + e.getMessage());
                }
            }

            long endTime = System.nanoTime();
            System.out.println("Dosyalar başarıyla ziplenmiştir: " + outputZipFile.getPath());
            System.out.println("Arşivleme süresi: " + (endTime - startTime) / 1_000_000 + " ms");

        } catch (IOException e) {
            System.err.println("Arşiv oluşturulamadı: " + e.getMessage());
        }
    }
}
