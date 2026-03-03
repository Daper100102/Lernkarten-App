package pk.lkarten;

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
