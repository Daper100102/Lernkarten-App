package pk.lkarten;

public class Anwendung {

	public static void main(String[] args) {
		
		Lernkartei lk = new Lernkartei(4);
		
		String[] ma1 = {"a" , "b", "c"};
		String[] ma2 = {"a" , "b", "c", "d", "f"};
		
		int[] ra1 = {0, 2};
		int[] ra2 = {0, 2 , 3};
		
		Lernkarte l1 = new EinzelantwortKarte("1", "1", "1", "1");
		Lernkarte l2 = new EinzelantwortKarte("1", "2", "2", "2");
		Lernkarte l3 = new MehrfachantwortKarte("1", "3", "3", ma1, ra1);
		Lernkarte l4 = new MehrfachantwortKarte("2", "4", "4", ma2, ra2);
		
		lk.hinzufuegen(l1);
		lk.hinzufuegen(l2);
		lk.hinzufuegen(l3);
		lk.hinzufuegen(l4);
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
