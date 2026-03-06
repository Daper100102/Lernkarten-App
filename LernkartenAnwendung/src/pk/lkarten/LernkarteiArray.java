package pk.lkarten;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class LernkarteiArray {
	
	private final Lernkarte[] Lernkartei;

	public LernkarteiArray(int karteigroesse) {
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
	
	public void druckeAlleKarten() throws IOException {
		for (int i = 0; i < Lernkartei.length; i++) {
			if(Lernkartei[i] != null) Lernkartei[i].druckeKarte();
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
			if(k != null) {
				if(k.getKategorie() == kategorie) {
					lkk[i] = k;
					i++;
				}
			}
		}
		return lkk;
	}
	
	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		Lernkarte[] lkr = new Lernkarte[anzahlKarten];
		for (int i = 0; i < anzahlKarten; i++) {
			int r = ThreadLocalRandom.current().nextInt(0, Lernkartei.length);
			if(Lernkartei[r] != null) {
				lkr[i] = Lernkartei[r];
			} else {
				anzahlKarten++;
			}
		}
		return lkr;
	}
}
