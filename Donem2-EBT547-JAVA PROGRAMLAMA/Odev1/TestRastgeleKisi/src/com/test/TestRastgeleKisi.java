package com.test;

import java.util.Scanner;
import com.kutuphane.Kisi;

public class TestRastgeleKisi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("RASTGELE KISI URETIM PROGRAMI");
            System.out.println("1 - Rastgele Kiþi Üret");
            System.out.println("2 - Çýkýþ");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            if (secim == 1) {
                System.out.print("Kaç kiþi üretmek istiyorsunuz? ");
                int sayi = scanner.nextInt();
                for (int i = 0; i < sayi; i++) {
                    Kisi kisi = Kisi.rastgeleKisiUret();
                    System.out.println(kisi);
                }
            } else if (secim == 2) {
                System.out.println("Programdan çýkýlýyor.");
                break;
            } else {
                System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
        scanner.close();
    }
}
