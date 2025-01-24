/**
*
* @author Sedat ÖZTÜRK sedat.ozturk3@ogr.sakarya.edu.tr
* @since 28/11/2024
* <p>
* Rastgele telefon numarası üreten class.
* 05 ile başlaması gerekmektedir.
* 05 den sonra Operatörler 0, 1, 2, 3, 4 ile devam etmesi gerekmektedir.
* Diğer kalan dijitler rastgele üretilecektir 
* </p>
*/

package com.kutuphane;

import java.util.Random;

public class Telefon {
	private String telefonNo;
	private IMEINo imeiNo;
	
	public Telefon() {
		this.telefonNo = telefonNoUret();
		this.imeiNo = new IMEINo();
	}
	
	public static String telefonNoUret() {
        Random random = new Random();

        int[] operatorKodlari = {0, 1, 2, 3, 4}; //Operatör kodlarına göre 05 den sonra gelecek sayılar 0,1,2,3,4 olabilir
        String dijitler = ""; //05? den sonraki dijitler
        
        int dijitOperator = operatorKodlari[random.nextInt(operatorKodlari.length)]; //alarından biri seçilecek
        
        //05? den sonra kalan 8 dijit rastgele olarak üretiliyor 
        for (int i = 0; i < 8; i++) {
        	dijitler += random.nextInt(10); 
		}

        return String.format("05%d%s", dijitOperator, dijitler);
    }
	
	@Override
	public String toString() {
		return this.telefonNo + " (" + this.imeiNo.toString() + ")";
	}
}
