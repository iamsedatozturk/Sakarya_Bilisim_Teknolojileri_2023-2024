package com.kutuphane;

public class Telefon {
    private String telefonNo;
    private IMEINo imei;

    public Telefon() {
        this.telefonNo = rastgeleTelefonUret();
        this.imei = new IMEINo();
    }

    private String rastgeleTelefonUret() {
        int alanKodu = 500 + (int) (Math.random() * 100);
        int kalan = (int) (Math.random() * 9000000) + 1000000;
        return String.format("05%d%d", alanKodu, kalan);
    }

    @Override
    public String toString() {
        return this.telefonNo + " (" + this.imei.toString() + ")";
    }
}
