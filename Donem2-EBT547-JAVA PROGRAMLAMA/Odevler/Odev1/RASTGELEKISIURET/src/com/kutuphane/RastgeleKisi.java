package com.kutuphane;

public class RastgeleKisi {
	public static Kisi kisiUret() {
        String[] isimler = {
        		"Ahmet", "Mehmet", "Fikret", "Saffet", "Can", 
        		"Canan", "Cavidan", "Mustafa", "Gazi", "Sedat", 
        		"Kürşat", "Bekir", "Karani", "Reha", "Murat", 
        		"Suat", "Hasan", "Hüseyin", "Serhat", "Cemil"
        		};
        
        String[] soyisimler = {
        		"Arat", "Öztürk", "Becit", "Gönüller", "İnce", 
        		"Boz", "Kaçan", "Erdem", "Erdağ", "Türk", 
        		"Boztürk", "Acar", "Topbaş", "Yeşil", 
        		"Karakullukçu", "Çelik", "Şahin", "Noyan"
    			};
        
        String isim = isimler[(int) (Math.random() * isimler.length)]; //İsim listesinden random isim seçecektir.
        String soyisim = soyisimler[(int) (Math.random() * soyisimler.length)]; //Soyisim listesinden random bir soyisim seçilecektir.
        int yas = (int) (Math.random() * 101); //1 ile 100 arasında bir yaş seçilecektir.
        
        return new Kisi(isim, soyisim, yas, new KimlikNo(), new Telefon());
    }
}
