package pk.lkarten;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Lernkartei {
	
	HashSet<Lernkarte> Lernkartei;
	
	public Lernkartei() {
		
		Lernkartei = new HashSet<Lernkarte>();
	}
	
	public void hinzufuegen(Lernkarte karte) throws UngueltigeKarteException, VorhandeneKarteException{
		karte.validiere();
		if(!Lernkartei.contains(karte)) {
			Lernkartei.add(karte);
		}else {
			Lernkarte.setCounter(Lernkarte.getCounter()-1);
			throw new VorhandeneKarteException("Lernkarte exsestiert bereits.");
		}
	}
	
	public ArrayList<String> sortiertNachIdAbsteigend() throws IOException {
		ArrayList<Lernkarte> list1 = new ArrayList<>(Lernkartei);
		ArrayList<String> list2 = new ArrayList<>();
		Collections.sort(list1, (o1, o2) -> -(o1.compareTo(o2)));
		for(Lernkarte l: list1) {
			list2.add(l.toString());
		}
		return list2;
	}
	
	public ArrayList<String> sortiertNachIdAufsteigend() throws IOException {
		ArrayList<Lernkarte> list1 = new ArrayList<>(Lernkartei);
		ArrayList<String> list2 = new ArrayList<>();
		Collections.sort(list1, (o1, o2) -> (o1.compareTo(o2)));
		for(Lernkarte l: list1) {
			list2.add(l.toString());
		}
		return list2;
	}
	
	public int gibAnzahlKarten() {
		return Lernkartei.size();
	}
	
	public void gibAlleEinträge(Lernkartei lk) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			Lernkartei.add(l);
		}
	}
	
	public void gibAlleEinträgeUndEinzelantwort(Lernkartei lk) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			if(l instanceof EinzelantwortKarte)
				Lernkartei.add(l);
		}
	}
	
	public void gibAlleEinträgeUndMehrfachantwort(Lernkartei lk) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			if(l instanceof MehrfachantwortKarte)
				Lernkartei.add(l);
		}
	}
	
	public void gibEinträgeZurKategorie(Lernkartei lk, String kategorie) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			if(l.vergleicheKategorien(kategorie))
				Lernkartei.add(l);
		}
	}
	
	public void gibEinträgeZurKategorieUndEinzelantwort(Lernkartei lk, String kategorie) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			if(l.vergleicheKategorien(kategorie) && l instanceof EinzelantwortKarte)
				Lernkartei.add(l);
		}
	}
	
	public void gibEinträgeZurKategorieUndMehrfachantwort(Lernkartei lk, String kategorie) {
		Lernkartei.clear();
		for(Lernkarte l: lk.Lernkartei) {
			if(l.vergleicheKategorien(kategorie) && l instanceof MehrfachantwortKarte)
				Lernkartei.add(l);
		}
	}
	
	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		Lernkarte[] lkr = new Lernkarte[anzahlKarten];
		ArrayList<Lernkarte> list = new ArrayList<>(Lernkartei);
		for (int i = 0; i < anzahlKarten; i++) {
			int r = ThreadLocalRandom.current().nextInt(0, list.size());
			lkr[i] = list.get(r);
		}
		return lkr;
	}
	
	public void exportiereEintraegeAlsCsvNio(Path datei) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(Lernkarte k: Lernkartei) {
			sb.append(k.exportiereAlsCsv()).append("\n");
		}
		Files.writeString(datei, sb.toString());
	}
	
	public void exportiereEintraegeAlsCsv(Path datei) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(Lernkarte k: Lernkartei) {
			sb.append(k.exportiereAlsCsv()).append("\n");
		}
		try(FileWriter fw = new FileWriter(datei.toFile()); PrintWriter pw = new PrintWriter(fw)) {
			pw.print(sb.toString());
		}
	}
	
	public void Speichern(File datei) throws FileNotFoundException, IOException {
		try(FileOutputStream fos = new FileOutputStream(datei); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(Lernkartei);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void Laden(File datei) throws IOException, ClassNotFoundException {
		HashSet<Lernkarte> hs;
		try(FileInputStream fis = new FileInputStream(datei); ObjectInputStream ois = new ObjectInputStream(fis)) {
			hs = (HashSet<Lernkarte>)ois.readObject();
		}
		Lernkartei.addAll(hs);
		
		int i = 1;
		for(Lernkarte k: Lernkartei) {
			k.setId(i++);
		}
		Lernkarte.setCounter(Lernkartei.size()+1);
	}
	
	public void entferneKarteMitId(int id) throws UngueltigeKarteException {
		
		Lernkarte ka = null;
		for(Lernkarte k: Lernkartei) {
			if(k.getId() == id) {
				ka = k;
			}
		}
		if(ka == null) throw new UngueltigeKarteException("Karte: "+Integer.toString(id)+" exsetiert nicht!");
		
		Lernkartei.remove(ka); 
		
		int i = 1;
		for(Lernkarte k2: Lernkartei) {
			k2.setId(i++);
		}
		Lernkarte.setCounter(Lernkartei.size()+1);
	}
	
	public Lernkarte gibKarteMitIdZurueck(int id) throws UngueltigeKarteException {
		Lernkarte karte;
		for(Lernkarte k: Lernkartei) {
			if(k.getId() == id) {
				karte = k;
				return karte;
			}
		}
		throw new UngueltigeKarteException("Karte: "+Integer.toString(id)+" exsetiert nicht!");
	}
}
