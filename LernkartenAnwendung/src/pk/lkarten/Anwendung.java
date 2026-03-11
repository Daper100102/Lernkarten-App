package pk.lkarten;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Anwendung {

	public static void main(String[] args) {
		
		Lernkartei lk = new Lernkartei();

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
			System.out.println("6. Speicher Datei");
			System.out.println("7. Lede Datei");
			System.out.println("6. Beenden");
			System.out.println("Bitte Aktion wählen: ");
			
			do {
				try {
					eingabe = scanner.nextInt();
				} catch (InputMismatchException e) {
					scanner.nextLine();
					System.out.println(e);
				}
			} while (eingabe < 1 && eingabe > 8);
			
			switch(eingabe) {
			
				case 1:
					Lernkarte[] rlk = lk.erzeugeDeck(5);
					for(Lernkarte k: rlk) {
						try {
							k.zeigeVorderseite(System.out);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						warteAufEnter();
						try {
							k.zeigeRueckseite(System.out);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
					} catch (VorhandeneKarteException e) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null, e, "Karte nicht hinzugefuegt", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
					break;
					
				case 3:
				try {
					lk.druckeAlleKarten();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
					
				case 4:
					String gesuchtekategorie = JOptionPane.showInputDialog(null, "Bitte gesuchte Katogorie eingeben:");
					Lernkarte[] glk = lk.gibKartenZuKategorie(gesuchtekategorie);
					for (Lernkarte k: glk) {
						if(k != null) {
							try {
								k.druckeKarte();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					break;
				case 5:
					String datei;
					int icsv = 0;
					File f = null;
					do {
						icsv = JOptionPane.YES_OPTION;
						do {
							datei = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
							if(datei == null) break;
							if(datei.isBlank() && datei != null) {
								JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							}
						} while(datei.isBlank());
						
						if(datei != null) {
							f = new File(datei);
							if(Files.exists(f.toPath())) {
								icsv = JOptionPane.showConfirmDialog(null,"Wollen sie den existierenden Dateipfad überschreiben?", "Dateipfad exsetiert bereits", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
								if(icsv != JOptionPane.YES_OPTION) datei = null;
							}
						}
					} while(icsv != JOptionPane.YES_OPTION);
					
					if(datei != null) {
						try {
						lk.exportiereEintraegeAlsCsv(f.toPath());
						} catch (IOException e) {
							JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					}
					break;
				case 6:
					String ss;
					File fs = null;
					int is = 0;
					do {
						is = JOptionPane.YES_OPTION;
						do {
							ss = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
							if (ss == null) break;
							if(ss.isBlank()) {
								JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE); 
							}
						} while (ss.isBlank() && ss != null);
						if(ss != null) {
							fs = new File(ss);
							if(Files.exists(fs.toPath())) {
								is = JOptionPane.showConfirmDialog(null,"Wollen sie den existierenden Dateipfad überschreiben?", "Dateipfad exsetiert bereits", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
								if(is != JOptionPane.YES_OPTION) ss = null;
							}
						}
					} while(is != JOptionPane.YES_OPTION);	
					
					if(ss != null) {
						try {
							lk.Speichern(fs);
						} catch (FileNotFoundException e) {
							JOptionPane.showConfirmDialog(null, e, "FileNotFoundException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} catch (IOException e) {
							JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
					}
					break;
				case 7:
					String sl;
					do {
						sl = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
						if (sl == null) break;
						if (sl.isBlank()) JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					} while (sl != null && sl.isBlank());
					if (sl == null) break;
					File fl = new File(sl);
				try {
					lk.Laden(fl);
				} catch (ClassNotFoundException e) {
					JOptionPane.showConfirmDialog(null, e, "ClassNotFoundException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
					break;
			}
			scanner.nextLine();
		} while (eingabe != 8);
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
