package pk.lkarten;

import java.io.Serializable;
import java.util.Objects;

public abstract class Lernkarte implements Comparable<Lernkarte>, ValidierbareKarte, CsvExportable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8588143922134590320L;
	private static int counter = 1;
	private int id;
	private String kategorie;
	private String title;
	private String frage;
	
	public Lernkarte(String kategorie, String title, String frage) {
		
		this.id = counter++;
		this.kategorie = kategorie;
		this.title = title;
		this.frage = frage;
	}
	
	@Override
	public void validiere() throws UngueltigeKarteException {
		if(kategorie == null || kategorie.isBlank()) throw new UngueltigeKarteException("Kategorie darf nicht leer sein!");
		if(title == null || title.isBlank()) throw new UngueltigeKarteException("Title darf nicht leer sein!");
		if(frage == null || frage.isBlank()) throw new UngueltigeKarteException("Frage darf nicht leer sein!");
	}

	@Override
	public String exportiereAlsCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(",").append(kategorie).append(",").append(title).append(",").append(frage);
		return sb.toString();
	}
	
	public boolean vergleicheKategorien(String k) {
		String kat1 = kategorie.trim().replaceAll("\\s+", "");
		String kat2 = k.trim().replaceAll("\\s+", "");
		
		return kat1.equalsIgnoreCase(kat2);
	}

	@Override
	public String toString() {
		return "Lernkarte [id=" + id + ", kategorie=" + kategorie.trim().replaceAll("\\s+", " ") + ", title=" + title.trim().replaceAll("\\s+", " ") + "]";
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

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getFrage() {
		return frage;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Lernkarte.counter = counter;
	}
}
