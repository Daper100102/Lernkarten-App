package pk.lkarten;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Anwendung {

	public static void main(String[] args) {
		
		LernkarteiListe lk = new LernkarteiListe();
		Scanner scanner = new Scanner(System.in);
		int eingabe = 0;
		do {
			eingabe = scanner.nextInt();	
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
					lk.hinzufuegen(new EinzelantwortKarte(kategorie, titel, frage, antwort));
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
			}
		} while (eingabe != 5);
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
