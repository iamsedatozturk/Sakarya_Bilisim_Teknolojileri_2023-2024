package com.kutuphane;

public class Kisi {
    private String isim;
    private String soyisim;
    private int yas;
    private KimlikNo kimlikNo;
    private Telefon telefon;

    public Kisi(String isim, String soyisim, int yas, KimlikNo kimlikNo, Telefon telefon) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.yas = yas;
        this.kimlikNo = kimlikNo;
        this.telefon = telefon;
    }

    public static Kisi rastgeleKisiUret() {
        String[] isimler = {"Ahmet", "Mehmet", "Ayþe", "Fatma", "Ali", "Zeynep"};
        String[] soyisimler = {"Koþar", "Yýlmaz", "Demir", "Çelik", "Kaya"};
        String isim = isimler[(int) (Math.random() * isimler.length)];
        String soyisim = soyisimler[(int) (Math.random() * soyisimler.length)];
        int yas = (int) (Math.random() * 101);
        return new Kisi(isim, soyisim, yas, new KimlikNo(), new Telefon());
    }

    @Override
    public String toString() {
        return this.kimlikNo + " " + this.isim + " " + this.soyisim + " " + this.yas + " " + this.telefon;
    }
}
