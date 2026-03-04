package pk.lkarten;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class LernkarteiListe {
	
	private ArrayList<Lernkarte> Lernkartei;

	public LernkarteiListe() {
		
		Lernkartei = new ArrayList<Lernkarte>();
	}
	
	public void hinzufuegen(Lernkarte karte) {
		this.Lernkartei.add(karte);
		System.out.println("\n Lernkarte [" + karte.getId() + "] wurde erfolgreich hinzugefuegt \n");
	}
	
	public void druckeAlleKarten() {
		Iterator<Lernkarte> iterator = Lernkartei.iterator();
		while (iterator.hasNext()) {
			iterator.next().druckeKarte();
		}
	}
	
	public int gibAnzahlKarten() {
		return Lernkartei.size();
	}
	
	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		Lernkarte[] lkk = new Lernkarte[Lernkartei.size()];
		int i = 0;
		for (Lernkarte k: Lernkartei) {
				if(k.getKategorie().equals(kategorie)) {
					lkk[i] = k;
					i++;
				}
		}
		return lkk;
	}
	
	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		Lernkarte[] lkr = new Lernkarte[anzahlKarten];
		for (int i = 0; i < anzahlKarten; i++) {
			int r = ThreadLocalRandom.current().nextInt(0, Lernkartei.size());
			lkr[i] = Lernkartei.get(r);
		}
		return lkr;
	}
}