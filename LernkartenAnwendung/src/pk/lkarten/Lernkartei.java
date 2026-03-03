package pk.lkarten;

import java.util.concurrent.ThreadLocalRandom;

public class Lernkartei {
	
	private final Lernkarte[] Lernkartei;

	public Lernkartei(int karteigroesse) {
		this.Lernkartei = new Lernkarte[karteigroesse];
	}
	
	public void hinzufuegen(Lernkarte karte) {
		for (int i = 0; i < Lernkartei.length; i++) {
			if(Lernkartei[i] == null) {
				Lernkartei[i] = karte;
				System.out.println("\n Lernkarte [" + karte.getId() + "] wurde erfolgreich hinzugefuegt \n");
				return;
			} 
		}
		System.out.println("\n Die Lernkartei ist bereits voll! \n Die Karte konnte nicht hinzugefuegt werden. \n");
	}
	
	public void druckeAlleKarten() {
		for (int i = 0; i < Lernkartei.length; i++) {
			Lernkartei[i].druckeKarte();
		}
	}
	
	public int gibAnzahlKarten() {
		int anz = 0;
		for (int i = 0; i < Lernkartei.length; i++) {
			if(Lernkartei[i] != null) {
				anz++;
			}
		}
		return anz;
	}
	
	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		Lernkarte[] lkk = new Lernkarte[Lernkartei.length];
		int i = 0;
		for (Lernkarte k: Lernkartei) {
			if(k.getKategorie() == kategorie) {
				lkk[i] = k;
				i++;
			}
		}
		return lkk;
	}
	
	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		Lernkarte[] lkr = new Lernkarte[anzahlKarten];
		for (int i = 0; i < anzahlKarten; i++) {
			int r = ThreadLocalRandom.current().nextInt(0, Lernkartei.length);
			lkr[i] = Lernkartei[r];
		}
		return lkr;
	}
}
