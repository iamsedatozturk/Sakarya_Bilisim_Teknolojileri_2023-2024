/**
*
* @author Sedat Öztürk sedat.ozturk3@ogr.sakarya.edu.tr
* @since 18/12/2024
* <p>
* Kişiler.txt dosyasındaki bilgilere göre adSoyad, kasa, oran ve sansliSayi değerleri tutan class
* Kişiler dosyasındaki satırı # lere split edilir.
* </p>
*/
package Sans;

public class Kisi {
	String adSoyad;
    double kasa;
    double oran;
    int sansliSayi;

    public Kisi(String adSoyad, double kasa, double oran, int sansliSayi) {
        this.adSoyad = adSoyad;
        this.kasa = kasa;
        this.oran = oran;
        this.sansliSayi = sansliSayi;
    }
}
