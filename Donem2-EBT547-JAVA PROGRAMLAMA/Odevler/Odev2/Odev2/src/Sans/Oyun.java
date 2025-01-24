/**
*
* @author Sedat Öztürk sedat.ozturk3@ogr.sakarya.edu.tr
* @since 18/12/2024
* <p>
* Şans Oyunu
* Kisiler.txt dosyasında her satır okunur ve bir Array List de saklanır.
* Array list bir iteratore aktarılır ve içerisi boşaltılana kadar While döngüsü kullanılır.
* Her tur da gerekli hesaplamalar yapar kişi kasası, masa kasası hesaplanır.
* Her turun çıktısı ekrana yazılır ve oyun bitince oyun bitti çıktısı oluşturulur.  
* </p>
*/
package Sans;

import java.io.*;
import java.util.*;

public class Oyun {
	private static int genislik = 45;
	private static int konsolBosluk = 110;
	
	public static void main(String[] args) throws InterruptedException {
		List<Kisi> kisiler = new ArrayList<>();
        Random random = new Random();
        double masaParasi = 0;
        int tur = 0;

        // Kisiler.txt dosyasından öncelikle kisiler listesi dolduruluyor
        try (InputStream inputStream = Oyun.class.getResourceAsStream("/Kisiler.txt");
    		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {	
            String line;
            while ((line = br.readLine()) != null) { //son satıra gelene kadar
                String[] parts = line.split("#"); //# karakteri ile split parçalanıyor.
                String adSoyad = parts[0];
                double kasa = Double.parseDouble(parts[1]);
                double oran = Double.parseDouble(parts[2]);
                int sansliSayi = Integer.parseInt(parts[3]);
                
                kisiler.add(new Kisi(adSoyad, kasa, oran, sansliSayi));
            }
        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
            return;
        }
        
        // Kişiler bitene kadar bir döngü oluşturulur.
        while (!kisiler.isEmpty()) {
            tur++;
            ConsoleHelper.clear(); // Ekranı temizleme
            int sansliSayi = random.nextInt(10) + 1; //Random olarak 1 ile 10 arasında bir seçim yapılır.
            Kisi enZengin = null; 

            Iterator<Kisi> iterator = kisiler.iterator(); //kişiler listesinden silme işlemi yapıldığı için foreach yerine iterator kullanıyoruz. 
            while (iterator.hasNext()) {
                Kisi kisi = iterator.next();
                
                double yatirilanPara = kisi.kasa * kisi.oran; //yatırılacak olan para hesaplanıyor
                yatirilanPara = Math.min(yatirilanPara, kisi.kasa); //yatırılacak olan para kasadan büyükse maksimum kasa kadar para yatırılacaktır.

                if (kisi.sansliSayi == sansliSayi) {
                    double kazanc = yatirilanPara * 10; //yatırılan paranın 10 katı kazanılıyor
                    
                    kisi.kasa += kazanc; //kazanılan para kasaya aktarılıyor
                    masaParasi -= kazanc; //masanın parası azalıyor.
                } else {
                    kisi.kasa -= yatirilanPara; //yatırılan para kasadan düşülüyor.
                    masaParasi += yatirilanPara; //masanın parası yatırılan para kadar artırılıyor.
                }

                if (kisi.kasa < 1000) { //1000 liranın altında olan kişiler listeden çıkarılıyor.
                    iterator.remove();
                }

                //En zengin kişi yoksa veya en zengin kişiden daha fazla kasa da parası varsa 
                if (enZengin == null || kisi.kasa > enZengin.kasa) {
                    enZengin = kisi;
                }
            }

            //kişileri dolaşırken şans oyununu oynar ve sonucunu ekrana yazar.
            bozlukCiz();
            bozlukCiz();
            bozlukCiz();
            bozlukCiz();
            bozlukCiz();
            diezCiz();
            icerikYaz("SANSLI SAYI:", String.valueOf(sansliSayi));
            diezCiz();

            diezCiz();
            icerikYaz("TUR:", String.valueOf(tur));
            icerikYaz("MASA BAKIYE:", String.valueOf(Math.round(masaParasi)) + " TL");
            icerikYaz("","");
            eksiCiz();

            if (enZengin != null) {
            	icerikYaz("EN ZENGIN KISI", "");
                icerikYaz(enZengin.adSoyad,"");
                icerikYaz("BAKIYESI:", String.valueOf(Math.round(enZengin.kasa)));
            }
            icerikYaz("","");
            diezCiz();

            Thread.sleep(30);
        }

        //kişiler bittiğinden sonra oyun bitti görünümü
        ConsoleHelper.clear();
        bozlukCiz();
        bozlukCiz();
        bozlukCiz();
        bozlukCiz();
        bozlukCiz();
        diezCiz();
        icerikYaz("TUR:", String.valueOf(tur));
        icerikYaz("MASA BAKIYE:",String.valueOf(Math.round(masaParasi)) + " TL");
        icerikYaz("","");
        eksiCiz();
        icerikYaz("OYUN BITTI", "");
        icerikYaz("","");
        icerikYaz("","");
        icerikYaz("","");
        diezCiz();
	}
		
	public static void bozlukCiz() {
		System.out.println("");
	}
	
	public static void eksiCiz() {
	    String mesaj = "##" + tekrarEt("-", genislik - 2) + "##";
	    int solBosluk = (konsolBosluk - genislik) / 2;
	    System.out.println(tekrarEt(" ", solBosluk) + mesaj);
	}
	
	public static void diezCiz() {
	    String mesaj = tekrarEt("#", genislik);
	    int solBosluk = (konsolBosluk - genislik) / 2;
	    System.out.println(tekrarEt(" ", solBosluk) + "#" + mesaj + "#");
	}
	
	public static void icerikYaz(String label, String value) {
	    String mesaj = label + (value.isEmpty() ? "" : " " + value);
	    int solBosluk = (konsolBosluk - genislik) / 2;
	    int bosluk = (genislik - mesaj.length()) / 2;

	    System.out.println(tekrarEt(" ", solBosluk) + "##" + tekrarEt(" ", bosluk) + mesaj + tekrarEt(" ", genislik - mesaj.length() - bosluk - 2) + "##");
	}
	
	public static String tekrarEt(String str, int count) {
	    return str.repeat(count);
	}
}