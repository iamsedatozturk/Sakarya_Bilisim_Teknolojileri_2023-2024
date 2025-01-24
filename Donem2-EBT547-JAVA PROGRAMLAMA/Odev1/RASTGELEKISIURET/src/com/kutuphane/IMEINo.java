package com.kutuphane;

public class IMEINo {
    private String imei;

    public IMEINo() {
        this.imei = rastgeleUret();
    }

    public static String rastgeleUret() {
        int[] imei = new int[15];
        for (int i = 0; i < 14; i++) {
            imei[i] = (int) (Math.random() * 10);
        }
        imei[14] = hesaplaSonHane(imei);

        StringBuilder sb = new StringBuilder();
        for (int digit : imei) {
            sb.append(digit);
        }
        return sb.toString();
    }

    private static int hesaplaSonHane(int[] imei) {
        int toplam = 0;
        for (int i = 0; i < 14; i++) {
            int deger = imei[i];
            if (i % 2 == 1) {
                deger *= 2;
                if (deger > 9) deger -= 9;
            }
            toplam += deger;
        }
        return (10 - (toplam % 10)) % 10;
    }

    @Override
    public String toString() {
        return this.imei;
    }
}
