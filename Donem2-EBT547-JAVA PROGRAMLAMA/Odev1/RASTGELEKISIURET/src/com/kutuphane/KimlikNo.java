package com.kutuphane;

public class KimlikNo {
    private String tcKimlik;

    public KimlikNo() {
        this.tcKimlik = rastgeleUret();
    }

    public static String rastgeleUret() {
        int[] tc = new int[11];
        for (int i = 0; i < 9; i++) {
            tc[i] = (int) (Math.random() * 10);
        }
        int tekler = tc[0] + tc[2] + tc[4] + tc[6] + tc[8];
        int ciftler = tc[1] + tc[3] + tc[5] + tc[7];
        tc[9] = (tekler * 7 - ciftler) % 10;
        int toplam = 0;
        for (int i = 0; i < 10; i++) {
            toplam += tc[i];
        }
        tc[10] = toplam % 10;

        StringBuilder sb = new StringBuilder();
        for (int digit : tc) {
            sb.append(digit);
        }
        return sb.toString();
    }

    public static boolean dogrula(String tcKimlik) {
        if (tcKimlik.length() != 11 || !tcKimlik.matches("\d+")) return false;
        int[] tc = new int[11];
        for (int i = 0; i < 11; i++) {
            tc[i] = Character.getNumericValue(tcKimlik.charAt(i));
        }
        int tekler = tc[0] + tc[2] + tc[4] + tc[6] + tc[8];
        int ciftler = tc[1] + tc[3] + tc[5] + tc[7];
        if (tc[9] != (tekler * 7 - ciftler) % 10) return false;
        int toplam = 0;
        for (int i = 0; i < 10; i++) {
            toplam += tc[i];
        }
        return tc[10] == toplam % 10;
    }

    @Override
    public String toString() {
        return this.tcKimlik;
    }
}
