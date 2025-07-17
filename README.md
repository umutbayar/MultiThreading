Projenin Amacı
Java ile multithreading konusunu uygulamalı göstermek. Proje, input klasöründeki .txt dosyalarını okuyarak her dosya için ayrı threadler oluşturan ve sonuçları output dizinine .zip formatında kaydeden bir uygulamadır.

📂 Klasör Yapısı
src/: Java kaynak kodları
input/: Örnek 3 adet .txt dosyası
output/: Oluşan .zip dosyası
.gitignore: Gereksiz dosyaları dışarıda tutar (ör. /target, .class, .idea/)

🛠️ Çalıştırma
input/ klasörüne .txt dosyalarını ekleyin.
Projeyi derleyin ve çalıştırın:

bash
Kopyala
Düzenle
javac -d bin src/*.java
java -cp bin Main
Çıktılar output/ dizininde result.zip olarak oluşur.

👨‍💻 Ekip Üyeleri ve Görev Dağılımı

ŞEVVAL: RAPORLAMA Code Review

BAYRAM:THREAD YÖNETİM 
FileAnalyzer.java, FileAnalysisTask.java
- input klasörünü tarayan sınıfı yazar
- Her dosya için bir thread oluşturan yapıyı kurar
- Thread başlatma ve join() işlemlerini tasarlar
 
FURKAN:ARŞİVLEME SORUMLUSU
Arşivleme Sorumlusu	ArchiveTask.java	
- Tüm analizlerin tamamlanmasını bekleyen thread’i yazar
- Dosyaları .zip arşivine alan kodu geliştirir
- Hata kontrolü ve konsol bilgilendirme mesajları ekler
 
UMUT :Main Akış + Utils + Zaman Ölçümü	Main.java, Utils.java	- Programın giriş noktası (main metodu)
- Aşamaların sürelerini System.nanoTime() ile ölçer
- Program akışını düzenler ve kontrol noktaları koyar
 
HAKAN:Veri Modelleme ve Sonuç Yapısı
FileStats.java, ConcurrentHashMap	
- Her dosya için sonuçların tutulduğu sınıfı (FileStats) oluşturur
- Thread-safe veri yapısını (ConcurrentHashMap) oluşturur
- Sonuçların ekrana formatlı yazılmasını sağlar

📦 Örnek Çıktı
yaml
Kopyala
Düzenle
file1.txt - 3 satır / 120 karakter
file2.txt - 10 satır / 987 karakter
file3.txt - 6 satır / 266 karakter
Toplam: 19 satır / 1373 karakter
📝 Notlar
Commit mesajları açık ve anlamlı tutulmuştur.

Pull request ve merge süreçleri uygulanmıştır.

Kodda açıklayıcı yorum satırları bulunmaktadır.
