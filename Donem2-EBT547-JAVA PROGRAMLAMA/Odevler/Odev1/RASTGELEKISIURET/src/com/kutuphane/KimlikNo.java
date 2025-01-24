/**
*
* @author Sedat ÖZTÜRK sedat.ozturk3@ogr.sakarya.edu.tr
* @since 28/11/2024
* <p>
* Rastgele kimlik numarası üreten class.
* Tc kimlik numarasının üretme kuralları bulunmaktadır.
* 11 haneli sayısal değer olacaktır
* İlk Rakam 0 olamaz
* 1. 3. 5. 7. ve 9. hanelerin toplamının 7 katından, * 2. 4. 6. ve 8. hanelerin toplamı çıkarılır, Elde edilen sonuç Mod10’u bize 10. haneyi verir.
* 1. 2. 3. 4. 5. 6. 7. 8. 9. ve 10. hanelerin toplamından elde edilen sonucun Mod10’u bize 11. haneyi verir.
* </p>
*/
package com.kutuphane;

import java.util.Random;

public class KimlikNo {
	private String tcKimlikNo;

	public KimlikNo() {
        this.tcKimlikNo = kimlikNoUret();
    }
	
	public static String kimlikNoUret() {
		Random random = new Random();
        
		int dijit1 = random.nextInt(9) + 1; //ilk rakam 0 olamaz.
		int dijit2 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit3 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit4 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit5 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit6 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit7 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit8 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		int dijit9 = random.nextInt(10); //0 ile 9 arasında bir rakam olabilir.
		
		int karakterler1Toplam = dijit1 + dijit3 + dijit5 + dijit7 + dijit9; //Tc kimlik numarasının 1,3,5,7 ve 9 karakteri
	    int karakterler2Toplam = dijit2 + dijit4 + dijit6 + dijit8; //Tc kimlik numarasının 2,4,6 ve 8 karakteri
	 
	    int dijit10 = ((karakterler1Toplam * 7) - karakterler2Toplam) % 10; //10. karakter hesaplanıyor.
	    int dijit11 = (karakterler1Toplam + karakterler2Toplam + dijit10) % 10; //11. karakter hesaplanıyor.
	 
	    return String.valueOf(dijit1) + String.valueOf(dijit2) + String.valueOf(dijit3) + String.valueOf(dijit4) + 
	    		String.valueOf(dijit5) + String.valueOf(dijit6) + String.valueOf(dijit7) + String.valueOf(dijit8) + 
	    		String.valueOf(dijit9) + String.valueOf(dijit10) + String.valueOf(dijit11);
    }
	
	public static boolean kimlikNoDogrula(String TCNo)
	{
		boolean sonuc = false;
	    int toplam = 0;
	 
	    String dijit13579 = String.format("%c%c%c%c%c", TCNo.charAt(0), TCNo.charAt(2), TCNo.charAt(4), TCNo.charAt(6), TCNo.charAt(8));
	    String dijit2468 = String.format("%c%c%c%c", TCNo.charAt(1), TCNo.charAt(3), TCNo.charAt(5), TCNo.charAt(7));
	    
	    int toplam13579 = ToplamAl(dijit13579); //1 3 5 7 9 numaralı karakterle toplanıyor.
	    int toplam2468 = ToplamAl(dijit2468); //2 4 6 8 numaralı karakterler toplanıyor.
	    
	    int dijit10 = ((toplam13579 * 7) - toplam2468) % 10; //10. karakter hesaplanıyor.

	    //Eğer 10. karakter dijit10 değişkenine eşitse
	    if (dijit10 == Character.getNumericValue(TCNo.charAt(9))) 
	    {		    
	    	toplam = toplam13579 + toplam2468 + dijit10; //1 den 10. karaktere kadar toplamı
	    	//TCNo 11. karakter ile toplam değişkeninin mod 10 u eşitse 
	        if ((toplam % 10) == Character.getNumericValue(TCNo.charAt(10))) 
	            sonuc = true;
	        else
	            sonuc = false;
	    }
	    else
	        sonuc = false;

	    return sonuc;
	}
	
	private static int ToplamAl(String str) {
		int total = 0;
		
		for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);  // Her karakteri al
            if (Character.isDigit(ch)) {  // Eğer karakter bir rakam ise
                total += Character.getNumericValue(ch);  // Sayıya dönüştürüp toplamla
            }
        }
		
		return total;
	}
    
    @Override
    public String toString() {
        return this.tcKimlikNo;
    }
}
