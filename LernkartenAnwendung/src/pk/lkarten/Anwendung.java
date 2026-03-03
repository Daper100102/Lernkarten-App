package pk.lkarten;

public class Anwendung {

	public static void main(String[] args) {
		
		Lernkartei lk = new Lernkartei(4);
		
		Lernkarte l1 = new Lernkarte("1", "1", "1", "1");
		Lernkarte l2 = new Lernkarte("1", "2", "2", "2");
		Lernkarte l3 = new Lernkarte("1", "3", "3", "3");
		Lernkarte l4 = new Lernkarte("2", "4", "4", "4");
		Lernkarte l5 = new Lernkarte("2", "5", "5", "5");
		
		lk.hinzufuegen(l1);
		lk.hinzufuegen(l2);
		lk.hinzufuegen(l3);
		lk.hinzufuegen(l4);
		lk.hinzufuegen(l5);
		lk.druckeAlleKarten();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(lk.gibAnzahlKarten());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Lernkarte k: lk.gibKartenZuKategorie("1")) {
			if(k != null)k.druckeKarte();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(Lernkarte k: lk.erzeugeDeck(10)) {
			if(k != null) k.druckeKarte();
		}

	}

}
