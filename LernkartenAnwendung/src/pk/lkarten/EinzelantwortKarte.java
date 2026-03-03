package pk.lkarten;

public class EinzelantwortKarte extends Lernkarte {
	
	private String antwort;
	
	public EinzelantwortKarte(String kategorie, String title, String frage, String antwort) {
		
		super(kategorie, title, frage);
		this.antwort = antwort;
	}
	

	@Override
	public void zeigeRueckseite() {
		System.out.println(this.antwort);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}
}
