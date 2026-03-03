package pk.lkarten;

public class Lernkarte {
	
	private static int counter = 0;
	private final int id;
	private String kategorie;
	private String title;
	private String frage;
	private String antwort;
	
	public Lernkarte(String kategorie, String title, String frage, String antwort) {
		
		this.id = counter++;
		this.kategorie = kategorie;
		this.title = title;
		this.frage = frage;
		this.antwort = antwort;
	}
	
	public void zeigeVorderseite() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getId()).append(", ").append(getKategorie()).append("] ").append(getTitle()).append(": \n").append(getFrage());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(sb);
	}
	
	public void zeigeRueckseite() {
		System.out.println(getAntwort());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public void druckeKarte() {
		zeigeVorderseite();
		zeigeRueckseite();
	}

	public int getId() {
		return id;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrage() {
		return frage;
	}

	public void setFrage(String frage) {
		this.frage = frage;
	}

	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}
	
	
	
	

}
