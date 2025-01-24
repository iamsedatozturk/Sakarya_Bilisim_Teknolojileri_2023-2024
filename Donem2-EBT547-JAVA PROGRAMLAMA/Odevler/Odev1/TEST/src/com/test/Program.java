/**
*
* @author Sedat ÖZTÜRK sedat.ozturk3@ogr.sakarya.edu.tr
* @since 28/11/2024
* <p>
* odev.kütüphane paketini bu projeye referans ettikten sonra
* aşağıdaki main methodu ile menü oluşturulmuştur.
* menüden kişi üretme adedine göre kisi classındaki rastgelekişiuret methodu çağrılıyor.
* eğer çıkış seçilmişse program sonlanacaktır.   
* </p>
*/


package com.test;

import java.util.Scanner;

import com.kutuphane.Kisi;
import com.kutuphane.RastgeleKisi;

public class Program {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Herhangi bir doğru seçim yapana kadar menü görünecektir. 
        while (true) {
            System.out.println("RASTGELE KISI URETIM PROGRAMI");
            System.out.println("1 - Rastgele Kişi Üret");
            System.out.println("2 - Çıkış");
            System.out.print("Seçiminiz: ");
            
            int secim = scanner.nextInt();
            //Eğer rastgele kişi üret seçilmişse
            if (secim == 1) {
                System.out.print("Kaç kişi üretmek istiyorsunuz? ");
                int sayi = scanner.nextInt();
                for (int i = 0; i < sayi; i++) {
                	Kisi kisi = RastgeleKisi.kisiUret();
                    System.out.println(kisi);
                }
            //Eğer rastgele çıkış seçilmişse
            } else if (secim == 2) {
                System.out.println("Programdan çıkılıyor.");
                break;
            } else {
                System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
        scanner.close();

	}
}
