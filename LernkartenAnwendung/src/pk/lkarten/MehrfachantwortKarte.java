package pk.lkarten;

import java.util.Arrays;

public class MehrfachantwortKarte extends Lernkarte {
	
	String[] moeglicheAntworten;
	int[] richtigeAntworten;
	
	public MehrfachantwortKarte(String kategorie, String title, String frage, String[] moeglicheAntworten, int[] richtigeAntworten) {
		
		super(kategorie, title, frage);
		this.moeglicheAntworten = moeglicheAntworten;
		this.richtigeAntworten = richtigeAntworten;
	}
	
	public void zeigeVorderseite() {
		super.zeigeVorderseite();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.moeglicheAntworten.length; i++) {
			sb.append("\n").append(Integer.toString(1+i)).append(": ").append(this.moeglicheAntworten[i]);
		}
		System.out.println(sb);
	}

	@Override
	public void zeigeRueckseite() {
		System.out.println("Die richtigen Antworten sind:");
		for (int i: this.richtigeAntworten) {
			System.out.println(Integer.toString(1+i) + ": " + this.moeglicheAntworten[i]);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	@Override
	public void validiere() throws UngueltigeKarteException {
		// TODO Auto-generated method stub
		super.validiere();
		if(moeglicheAntworten.length < 2) throw new UngueltigeKarteException("Es muss min. 2 moegliche Antworten geben");
		for(String s: moeglicheAntworten) {
			if(s == null || s.isBlank() || s.isEmpty()) throw new UngueltigeKarteException("moegliche Antwort darf nicht leer sein!");
		} 
		if(richtigeAntworten.length < 1) throw new UngueltigeKarteException("min. 1 Antwort muss als richtig makiert sein (keine trick Fragen)!");
		for(int i: richtigeAntworten) {
			if(i < 1 || i >= moeglicheAntworten.length) throw new UngueltigeKarteException("Antworten müssen sich auf eine mogliche Antwort bezichen!");
		}
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

	public void setMoeglicheAntworten(String[] moeglicheAntworten) {
		this.moeglicheAntworten = moeglicheAntworten;
	}

	public int[] getRichtigeAntworten() {
		return richtigeAntworten;
	}

	public void setRichtigeAntworten(int[] richtigeAntworten) {
		this.richtigeAntworten = richtigeAntworten;
	}
}
