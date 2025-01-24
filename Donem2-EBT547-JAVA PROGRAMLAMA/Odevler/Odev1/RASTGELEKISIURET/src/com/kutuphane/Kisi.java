/**
*
* @author Sedat ÖZTÜRK sedat.ozturk3@ogr.sakarya.edu.tr
* @since 28/11/2024
* <p>
* Rastgele Kişi Ad, Soyadi Yaş, Kimlik No ve Telefon numarası üreten class.
* </p>
*/
package com.kutuphane;

public class Kisi {
	private String isim;
    private String soyisim;
    private int yas;
    private KimlikNo kimlikNo;
    private Telefon telefonNo;

    public Kisi(String isim, String soyisim, int yas, KimlikNo kimlikNo, Telefon telefonNo) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.yas = yas;
        this.kimlikNo = kimlikNo;
        this.telefonNo = telefonNo;
    }

    @Override
    public String toString() {
        return this.kimlikNo + " " + this.isim + " " + this.soyisim + " " + this.yas + " " + this.telefonNo;
    }

}
