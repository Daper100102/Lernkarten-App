package pk.lkarten;

import java.util.Arrays;

public class MehrfachantwortKarte extends Lernkarte {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6815835446565694685L;
	String[] moeglicheAntworten;
	int[] richtigeAntworten;
	
	public MehrfachantwortKarte(String kategorie, String title, String frage, String[] moeglicheAntworten, int[] richtigeAntworten) {
		
		super(kategorie, title, frage);
		this.moeglicheAntworten = moeglicheAntworten;
		this.richtigeAntworten = richtigeAntworten;
	}
	
	@Override
	public void validiere() throws UngueltigeKarteException {
		// TODO Auto-generated method stub
		super.validiere();
		if(moeglicheAntworten.length < 2) throw new UngueltigeKarteException("Es muss min. 2 moegliche Antworten geben");
		for(String s: moeglicheAntworten) {
			if(s == null || s.isBlank()) throw new UngueltigeKarteException("moegliche Antwort darf nicht leer sein!");
		} 
		if(richtigeAntworten.length < 1) throw new UngueltigeKarteException("min. 1 Antwort muss als richtig makiert sein (keine trick Fragen)!");
		for(int i: richtigeAntworten) {
			if(i < 1 || i > moeglicheAntworten.length) throw new UngueltigeKarteException("Antworten müssen sich auf eine mogliche Antwort bezichen!");
		}
	}

	@Override
	public String exportiereAlsCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.exportiereAlsCsv()).append(",").append(Arrays.toString(moeglicheAntworten)).append(",").append(Arrays.toString(richtigeAntworten));
		return sb.toString();
	}

	@Override
	public String toString() {
		return super.toString() + " (MK)";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(richtigeAntworten);
		result = prime * result + Arrays.hashCode(moeglicheAntworten);
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
		MehrfachantwortKarte other = (MehrfachantwortKarte) obj;
		return Arrays.equals(richtigeAntworten, other.richtigeAntworten) && Arrays.equals(moeglicheAntworten, other.moeglicheAntworten);
	}

	public String[] getMoeglicheAntworten() {
		return moeglicheAntworten;
	}
	
	public String moeglicheAntwortenString() {
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < moeglicheAntworten.length; j++) {
			sb.append((j+1)).append(": ").append(moeglicheAntworten[j]).append("\n");
		}
		return sb.toString();
	}

	public int[] getRichtigeAntworten() {
		return richtigeAntworten;
	}
	
	public String richtigeAntwortenString() {
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < moeglicheAntworten.length; j++) {
			if(richtigeAntwortenContains((j+1))) {
				sb.append(j+1).append(": ").append(moeglicheAntworten[j]).append("\n");
			}
		}
		return sb.toString();
	}
	
	public Boolean richtigeAntwortenContains(int i) {
		boolean a = false;
		for(int j: richtigeAntworten) {
			if(j == i) a = true;
		}
		return a;
	}

}
