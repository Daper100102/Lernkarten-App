package pk.lkarten;

import java.util.Objects;

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
	
	@Override
	public void validiere() throws UngueltigeKarteException {
		// TODO Auto-generated method stub
		super.validiere();
		if(antwort == null || antwort.isBlank() || antwort.isEmpty()) throw new UngueltigeKarteException("Antwort darf nicht leer sein!");
	}

	@Override
	public String exportiereAlsCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.exportiereAlsCsv()).append(",").append(antwort);
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(antwort);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EinzelantwortKarte other = (EinzelantwortKarte) obj;
		return Objects.equals(antwort, other.antwort);
	}

	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}
}
