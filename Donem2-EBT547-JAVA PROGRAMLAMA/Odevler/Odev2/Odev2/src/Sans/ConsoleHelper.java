/**
*
* @author Sedat Öztürk sedat.ozturk3@ogr.sakarya.edu.tr
* @since 18/12/2024
* <p>
* Youtube kanalında olduğu gibi işletim sistemine ekranı temizleme komutu gönderilir.
* Windows ise cls
* Linux ve Mac ise claar 
* </p>
*/
package Sans;

import java.io.IOException;

public class ConsoleHelper {
	public static void clear() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex){
			System.out.println(ex.getMessage());
		}
	}
}
