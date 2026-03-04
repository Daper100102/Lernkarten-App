package pk.lkarten;

import java.util.Objects;

public abstract class Lernkarte implements ValidierbareKarte, CsvExportable{
	
	private static int counter = 1;
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
	
	@Override
	public void validiere() throws UngueltigeKarteException {
		if(kategorie == null || kategorie.isBlank() || kategorie.isEmpty()) throw new UngueltigeKarteException("Kategorie darf nicht leer sein!");
		if(title == null || title.isBlank() || title.isEmpty()) throw new UngueltigeKarteException("Title darf nicht leer sein!");
		if(frage == null || frage.isBlank() || frage.isEmpty()) throw new UngueltigeKarteException("Frage darf nicht leer sein!");
	}

	@Override
	public String exportiereAlsCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(",").append(kategorie).append(",").append(title).append(",").append(frage);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(frage, kategorie, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lernkarte other = (Lernkarte) obj;
		return Objects.equals(frage, other.frage) && Objects.equals(kategorie, other.kategorie) && Objects.equals(title, other.title);
	}
	
	public int compareTo(Lernkarte o) {
		if(o==null)
			return 1;
		return Integer.compare(this.getId(), o.getId());
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
