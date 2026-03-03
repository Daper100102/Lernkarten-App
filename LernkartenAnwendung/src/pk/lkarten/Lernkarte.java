package pk.lkarten;

public abstract class Lernkarte {
	
	private static int counter = 0;
	private final int id;
	private String kategorie;
	private String title;
	private String frage;
	
	public Lernkarte(String kategorie, String title, String frage) {
		
		this.id = counter++;
		this.kategorie = kategorie;
		this.title = title;
		this.frage = frage;
	}
	
	public void zeigeVorderseite() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(this.id).append(", ").append(this.kategorie).append("] ").append(this.title).append(": \n").append(this.frage);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(sb);
	}
	
	public abstract void zeigeRueckseite();
	
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
}
