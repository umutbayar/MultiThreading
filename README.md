# 🧵 Java Multithread Dosya Analizi ve Arşivleme Projesi

Bu proje, Java'da çoklu iş parçacığı (multithreading) kullanımını uygulamalı olarak göstermek amacıyla geliştirilmiştir.  
Proje kapsamında, `input/` klasöründeki `.txt` dosyaları ayrı iş parçacıklarıyla analiz edilmekte ve ardından bu dosyalar `.zip` formatında `output/` klasörüne kaydedilmektedir.

---

## 🎯 Projenin Amacı

- Java'da multithreading mantığını gerçek bir senaryo ile pekiştirmek
- Thread yönetimi, join() kullanımı ve thread-safe veri yapılarının uygulanmasını sağlamak
- Dosya sistemi ile çalışma ve arşivleme işlemlerini öğrenmek
- Ekip çalışması ile Git versiyon kontrolü pratiği kazanmak

---

## 👥 Ekip Üyeleri ve Görev Dağılımı

| Üye     | Görev                   | Açıklama                                                                                 |
|---------|--------------------------|------------------------------------------------------------------------------------------|
| Şevval  | Raporlama & Code Review | Belgeler, raporlar, kod inceleme, class diagram ve akış şeması                           |
| Bayram  | Thread Yönetimi         | `FileAnalyzer.java`, `FileAnalysisTask.java` <br>• input klasörünü tarar <br>• Thread oluşturur ve join() işlemleri |
| Furkan  | Arşivleme Sorumlusu     | `ArchiveTask.java` <br>• Tüm analizler tamamlandığında zip işlemini gerçekleştirir <br>• Konsol bilgilendirmeleri |
| Umut    | Main Akış & Utils       | `Main.java`, `Utils.java` <br>• Programın giriş noktası <br>• Süre ölçümü <br>• Akışı ve zamanlamaları kontrol eder |
| Hakan   | Veri Modelleme          | `FileStats.java` <br>• Sonuçların tutulduğu POJO <br>• Thread-safe `ConcurrentHashMap` ile analiz verilerini saklar |

---


## 🧩 Kullanılan Teknolojiler

- Java 8+ (JDK 21 önerilir)
- Thread, Runnable, join()
- java.util.concurrent.ConcurrentHashMap
- java.util.zip.ZipOutputStream
- System.nanoTime() ile zaman ölçümü
- Git & GitHub

---





## ⚙️ Proje Yapısı

```bash
📁 src/
   ├── Main.java
   ├── FileAnalyzer.java
   ├── FileAnalysisTask.java
   ├── ArchiveTask.java
   ├── FileStats.java
   └── Utils.java

📁 input/
   ├── file1.txt
   ├── file2.txt
   └── ...

📁 output/
   └── archived-files.zip

📄 README.md
📄 .gitignore
````

## Örnek Çıktı


````markdown
file1.txt - 3 satır / 120 karakter
file2.txt - 10 satır / 987 karakter
file3.txt - 6 satır / 266 karakter
Toplam: 19 satır / 1373 karakter
````




## Notlar

- Commit mesajları açık ve anlamlı tutulmuştur.  
- Pull request ve merge süreçleri uygulanmıştır.  
- Kodda açıklayıcı yorum satırları bulunmaktadır.






