package pk.lkarten;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Anwendung {

	public static void main(String[] args) {
		
		Lernkartei lk = new Lernkartei();
		
		String[] ma1 = {"a" , "b", "c"};
		String[] ma2 = {"a" , "b", "c", "d", "f"};
		int[] ra1 = {1, 3};
		int[] ra2 = {1, 2 , 3};
		Lernkarte l1 = new MehrfachantwortKarte("1", "3", "3", ma1, ra1);
		Lernkarte l2 = new MehrfachantwortKarte("2", "4", "4", ma2, ra2);
		try {
			lk.hinzufuegen(l1);
			lk.hinzufuegen(l2);
		} catch (UngueltigeKarteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		int eingabe = 0;
		do {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Lernkarten-App");
			System.out.println("1. Lernen!");
			System.out.println("2. Einzelantwortkarte hinzufügen");
			System.out.println("3. Drucke alle Karten");
			System.out.println("4. Drucke Karten zu Kategorie");
			System.out.println("5. Exportiere Datei als Csv");
			System.out.println("6. Beenden");
			System.out.println("Bitte Aktion wählen: ");
			
			do {
				try {
					eingabe = scanner.nextInt();
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println(e);
				}
			} while (eingabe < 1 && eingabe > 6);
			
			switch(eingabe) {
			
				case 1:
					Lernkarte[] rlk = lk.erzeugeDeck(5);
					for(Lernkarte k: rlk) {
						k.zeigeVorderseite();
						warteAufEnter();
						k.zeigeRueckseite();
						warteAufEnter();
					}
					break;
					
				case 2:
					String kategorie = JOptionPane.showInputDialog(null, "Bitte Katogorie eingeben:");
					String titel = JOptionPane.showInputDialog(null, "Bitte Titel eingeben:");
					String frage = JOptionPane.showInputDialog(null, "Bitte Frage eingeben:");
					String antwort = JOptionPane.showInputDialog(null, "Bitte Antwort eingeben:");
					try {
						lk.hinzufuegen(new EinzelantwortKarte(kategorie, titel, frage, antwort));
					} catch(UngueltigeKarteException e) {
						JOptionPane.showConfirmDialog(null, e, "UngueltigeKarteException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					break;
					
				case 3:
					lk.druckeAlleKarten();
					break;
					
				case 4:
					String gesuchtekategorie = JOptionPane.showInputDialog(null, "Bitte gesuchte Katogorie eingeben:");
					Lernkarte[] glk = lk.gibKartenZuKategorie(gesuchtekategorie);
					for (Lernkarte k: glk) {
						if(k != null) k.druckeKarte();
					}
					break;
				case 5:
					String datei;
					int i = 0;
					File f = null;
					do {
						do {
							datei = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
							if(datei == null) break;
						} while(datei.isBlank() || datei.isEmpty());
						
						if(datei != null) {
							f = new File(datei);
							if(Files.exists(f.toPath())) {
								i = JOptionPane.showConfirmDialog(null,"Wollen sie den existierenden Dateipfad überschreiben?", "Dateipfad exsetiert bereits", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							}
						}
					} while(i != 0);
					if(datei != null) {
						try {
						lk.exportiereEintraegeAlsCsv(f.toPath());
						} catch (IOException e) {
							JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					}
					break;
			}
		} while (eingabe != 6);
		scanner.close();
	}
	
	private static void warteAufEnter() {
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			System.err.println("Fehler: " + e.getMessage());
		}
	}
	
}
