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
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Lernkartei {
	
	HashSet<Lernkarte> Lernkartei;
	
	public Lernkartei() {
		
		Lernkartei = new HashSet<Lernkarte>();
	}
	
	public void hinzufuegen(Lernkarte karte) throws UngueltigeKarteException{
		karte.validiere();
		if(!Lernkartei.contains(karte)) {
			Lernkartei.add(karte);
			System.out.println("\n Lernkarte [" + karte.getId() + "] wurde erfolgreich hinzugefuegt \n");
		}else {
			System.out.println("Lernkarte exsestiert bereits.");
		}
	}
	
	public void druckeAlleKarten() throws IOException {
		ArrayList<Lernkarte> list = new ArrayList<>(Lernkartei);
		Collections.sort(list, (o1, o2) -> -(o1.compareTo(o2)));
		for(Lernkarte k: list) {
			k.druckeKarte();
		}
	}
	
	public int gibAnzahlKarten() {
		return Lernkartei.size();
	}
	
	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		Lernkarte[] lkk = new Lernkarte[gibAnzahlKarten()];
		int i = 0;
		for(Lernkarte k: Lernkartei) {
			if(k.getKategorie().equals(kategorie)) {
				lkk[i] = k;
				i++;
			}
		}
		return lkk;
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
		Lernkarte.setCounter(hs.size()+1);
		for(Lernkarte k: Lernkartei) {
			if(k.getId() < Lernkarte.getCounter()) {
				k.setId(Lernkarte.getCounter());
				Lernkarte.setCounter((Lernkarte.getCounter()+1));
			}
		}
		Lernkartei.addAll(hs);
	}
	
	public Iterator<Lernkarte> getIterator() {
		return Lernkartei.iterator();
	}
}
