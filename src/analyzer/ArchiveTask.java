package analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveTask implements Runnable {

    // Arşivlenecek dosyaların bulunduğu klasör
    private final File inputDirectory;

    // Zip dosyasının oluşacağı konum ve adı
    private final File outputZipFile;

    public ArchiveTask(File inputDirectory, File outputZipFile) {
        this.inputDirectory = inputDirectory;
        this.outputZipFile = outputZipFile;
    }

    @Override
    public void run() {
        // Başlangıç zamanı – işlem süresini ölçmek için
        long startTime = System.nanoTime();

        // Sadece .txt uzantılı dosyaları al
        File[] txtFiles = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // Hiç dosya yoksa uyarı verip çık
        if (txtFiles == null || txtFiles.length == 0) {
            System.err.println("Arşivlenecek .txt dosyası bulunamadı.");
            return;
        }

        // Zip dosyasının yazılacağı klasör yoksa oluştur
        File parentDir = outputZipFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        // Zip dosyasını yazmaya başla
        try (FileOutputStream fos = new FileOutputStream(outputZipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            // Tüm .txt dosyalarını sırayla zip'e ekle
            for (File file : txtFiles) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    // Her dosya için zip girişi oluştur
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);

                    // Dosyayı okuma-yazma işlemi
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }

                    // Girişi kapat
                    zos.closeEntry();
                } catch (IOException e) {
                    // Bir dosyada hata olursa uyar ama devam et
                    System.err.println("Hata: " + file.getName() + " arşivlenemedi → " + e.getMessage());
                }
            }

            // İşlem süresini hesapla ve bilgi ver
            long endTime = System.nanoTime();
            System.out.println("Dosyalar başarıyla ziplenmiştir: " + outputZipFile.getPath());
            System.out.println("Arşivleme süresi: " + (endTime - startTime) / 1_000_000 + " ms");

        } catch (IOException e) {
            // Zip dosyası oluşturulamazsa genel hata
            System.err.println("Arşiv oluşturulamadı: " + e.getMessage());
        }
    }
}
