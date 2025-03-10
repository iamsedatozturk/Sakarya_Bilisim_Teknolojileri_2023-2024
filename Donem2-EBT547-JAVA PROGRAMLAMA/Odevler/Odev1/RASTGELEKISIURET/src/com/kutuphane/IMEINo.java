/**
*
* @author Sedat ÖZTÜRK sedat.ozturk3@ogr.sakarya.edu.tr
* @since 28/11/2024
* <p>
* Rastgele IMEINo üreten class.
* IMEI numarası 15 sayıdan oluşmaktadır.
* İlk 14 karakter random olarak üretilecek.
* Son karakter Luhn algoritmasına göre hesaplanmaktadır.
* 2. 4. 6. 8. 10. 12. 14. karakterler 2 ile çarpılacaktır. Çıkan değerler 9 dan büyükse 9 düşülür.
* İlk 14 karakter için oluşturulan yeni integer dizisi toplanır. 10dan ilk 14 karakter toplamının mod 10 u düşülür.
* Çıkan numara 15. karakteri belirtir.
* </p>
*/
package com.kutuphane;

import java.util.Random;

public class IMEINo {
	private String IMEI;

	public IMEINo() {
        this.IMEI = imeiNoUret();
    }

	public static String imeiNoUret() {
        Random random = new Random();
        String dijitler = "";

        //15 elemanlı integer bir Array oluşturulur.
        int[] imeiArray = new int[15]; 

        //14 elemen random olarak oluşturulur.
        for (int i = 0; i < 14; i++) { 
        	imeiArray[i] = random.nextInt(10);
        }
        
        //ilk 14 sayıya göre son dijit hesaplanıyor.
        imeiArray[14] = sonDijitBul(imeiArray);
        
        //Hesaplanan Imei numarası strine dönüştürülür ve return edilir.
        for (int dijit : imeiArray) {
        	dijitler += dijit;
        }
        
        return dijitler;
    }

    public static boolean imeiNoDogrula(String imei) {
        int toplam = 0;
        boolean isEvenPosition = false;

        //Sağdan sola doğru numarayı kontrol et. Yani geriye doğru
        for (int i = imei.length() - 1; i >= 0; i--) {
            char digitChar = imei.charAt(i);
            int dijit = Character.getNumericValue(digitChar);

            //her 2. basamağı 2 katına çıkaracak
            if (i % 2 == 1) {
            	dijit *= 2;

                // Eğer iki katı bir sayıysa, basamakları topla
                if (dijit > 9) {
                	dijit = dijit - 9; //Örneğin 14 çıkarsa 1+4=5 dir. 14-9=5 dir.
                }
            }

            toplam += dijit;

            isEvenPosition = !isEvenPosition;
        }

        // toplamın 10'a bölümü ile kalan 0 ise true
        return toplam % 10 == 0;
    }
	
    private static int sonDijitBul(int[] imei) {
        int toplam = 0;
        
        //14 eleman için for döngüsü yapılır
        for (int i = 0; i < 14; i++) { 
            int deger = imei[i]; //her eleman deger atılır
            
            //2,4,6,8,10,12,14. karakterler için hesaplama yapılır. 
        	if (i % 2 == 1) { 
                deger *= 2; //2 katı alınır
                //2 katı alınan değer 9 dan büyükse rakamlar toplamak yerine 9 dan çıkarılır. 
                //Sayıların toplanması ile aynıdır. Örneğin 14 çıkarsa 1+4=5 dir. 14-9=5 dir.
                if (deger > 9) deger -= 9; 
            }
            toplam += deger;
        }
        
        //toplam rakamın modu alınır ve 10 dan düşülür.
        return (10 - (toplam % 10)) % 10;
    }
   
    @Override
    public String toString() {
        return this.IMEI;
    }

}
