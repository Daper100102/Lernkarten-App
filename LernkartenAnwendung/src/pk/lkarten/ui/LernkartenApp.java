package pk.lkarten.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;
import pk.lkarten.Lernkarte;
import pk.lkarten.Lernkartei;
import pk.lkarten.MehrfachantwortKarte;

public class LernkartenApp extends Application {
	public static Lernkartei lk = new Lernkartei();
	
	public static ListView<String> listview;
	public static int Sortierung = 0; 
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		BorderPane bp = new BorderPane();	
		
		MenuBar mb = new MenuBar();
		
		Menu m1 = new Menu("Datei");
		Menu m2 = new Menu("Lernkartei");
		Menu m3 = new Menu("Sortieren");
		
		MenuItem mi1 = new MenuItem("Laden");
		MenuItem mi2 = new MenuItem("Speichern");
		MenuItem mi3 = new MenuItem("CSV-Export");
		MenuItem mi4 = new MenuItem("Beenden");
		
		MenuItem mi21 = new MenuItem("Einzelantwortkarte hinzufügen");
		MenuItem mi22 = new MenuItem("Mehrfachantwortkarten hinzufügen");
		
		MenuItem mi31 = new MenuItem("Nach ID Absteigend");
		MenuItem mi32 = new MenuItem("Nach ID Aufsteigend");
		
		listview = new ListView<String>();
		ScrollPane sp = new ScrollPane(listview);
		
		Button b = new Button("Lernen!");
		Spinner<Integer> spin = new Spinner<Integer>();
		HBox hb = new HBox(b, spin);
		
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));			
		hb.setPadding(new Insets(15.0));
		hb.setSpacing(10.0);
		
		m1.getItems().add(mi1);
		m1.getItems().add(mi2);
		m1.getItems().add(new SeparatorMenuItem());
		m1.getItems().add(mi3);
		m1.getItems().add(new SeparatorMenuItem());
		m1.getItems().add(mi4);
		
		m2.getItems().add(mi21);
		m2.getItems().add(mi22);
		
		m3.getItems().add(mi31);
		m3.getItems().add(mi32);
		
		mb.getMenus().add(m1);
		mb.getMenus().add(m2);
		mb.getMenus().add(m3);
		
		bp.setTop(mb);
		bp.setCenter(sp);
		bp.setBottom(hb);

		listview.getItems().addAll(lk.sortiertNachIdAbsteigend());
		
		mi1.setAccelerator(KeyCombination.valueOf("Ctrl+L"));
		mi2.setAccelerator(KeyCombination.valueOf("Ctrl+S"));
		mi3.setAccelerator(KeyCombination.valueOf("Ctrl+C"));
		mi4.setAccelerator(KeyCombination.valueOf("Ctrl+B"));
		
		mi1.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				String sl;
				do {
					sl = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
					if (sl == null) break;
					if (sl.isBlank()) JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				} while (sl != null && sl.isBlank());
				
				if(sl != null) {
					File fl = new File(sl);
					try {
						lk.Laden(fl);
						listview.getItems().clear();
						if(Sortierung == 0)
							listview.getItems().addAll(lk.sortiertNachIdAbsteigend());
						if(Sortierung == 1)
							listview.getItems().addAll(lk.sortiertNachIdAufsteigend());
					} catch (ClassNotFoundException e) {
						JOptionPane.showConfirmDialog(null, e, "ClassNotFoundException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		
		mi2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String ss;
				File fs = null;
				int is = 0;
				do {
					is = JOptionPane.YES_OPTION;
					do {
						ss = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
						if (ss == null) break;
						if(ss.isBlank()) {
							JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE); 
						}
					} while (ss.isBlank() && ss != null);
					if(ss != null) {
						fs = new File(ss);
						if(Files.exists(fs.toPath())) {
							is = JOptionPane.showConfirmDialog(null,"Wollen sie den existierenden Dateipfad überschreiben?", "Dateipfad exsetiert bereits", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(is != JOptionPane.YES_OPTION) ss = null;
						}
					}
				} while(is != JOptionPane.YES_OPTION);	
				
				if(ss != null) {
					try {
						lk.Speichern(fs);
					} catch (FileNotFoundException e) {
						JOptionPane.showConfirmDialog(null, e, "FileNotFoundException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		
		mi3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String datei;
				int icsv = 0;
				File f = null;
				do {
					icsv = JOptionPane.YES_OPTION;
					do {
						datei = JOptionPane.showInputDialog(null, "Bitte Dateiname eingeben:");
						if(datei == null) break;
						if(datei.isBlank() && datei != null) {
							JOptionPane.showConfirmDialog(null, "Der Dateiname darf nicht leer sein!", "Dateiname ist Leer!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
					} while(datei.isBlank());
					
					if(datei != null) {
						f = new File(datei);
						if(Files.exists(f.toPath())) {
							icsv = JOptionPane.showConfirmDialog(null,"Wollen sie den existierenden Dateipfad überschreiben?", "Dateipfad exsetiert bereits", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
							if(icsv != JOptionPane.YES_OPTION) datei = null;
						}
					}
				} while(icsv != JOptionPane.YES_OPTION);
				
				if(datei != null) {
					try {
					lk.exportiereEintraegeAlsCsv(f.toPath());
					} catch (IOException e) {
						JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
		
		mi4.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		
		mi21.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent arg0) {
				Stage s = new Stage();
				EinzelantwortErfassungView eev = new EinzelantwortErfassungView(s, null);
				eev.showView();
				listview.getItems().clear();
			}
		});
		
		mi22.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				Stage s = new Stage();
				MehrfachantwortErfassungView mev = new MehrfachantwortErfassungView(s, null);
				mev.showView();
			}
		});
		
		mi31.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sortierung = 0;
				listview.getItems().clear();
				try {
					listview.getItems().addAll(lk.sortiertNachIdAbsteigend());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		mi32.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sortierung = 1;
				listview.getItems().clear();
				try {
					listview.getItems().addAll(lk.sortiertNachIdAufsteigend());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage s = new Stage();
				if(lk.gibAnzahlKarten() > 0) {
					zeigeVorderseite(lk.erzeugeDeck(spin.getValue()), 0, s);
				} else {
					JOptionPane.showConfirmDialog(null, "Es muessen Lernkarten vorhanden sein wenn du Lernen willst!", "Keine Karten Vorhanden!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		arg0.setWidth(1280);
		arg0.setHeight(768);
		arg0.setScene(new Scene(bp));
		arg0.setTitle("Lernkarten-App");
		arg0.show();
	}
	
	public static void zeigeVorderseite(Lernkarte[] lk, int i, Stage s) {
		if(i < lk.length) {
			s.setTitle(lk[i].getKategorie());
			
			Text t1 = new Text(lk[i].getTitle());
			t1.setFont(Font.font("Arial", 30));
			
			Separator sep = new Separator();
			VBox vb = null;
			Text t2 = null;
			
			if(lk[i] instanceof EinzelantwortKarte) {
				t2 = new Text(lk[i].getFrage());
			} else if(lk[i] instanceof MehrfachantwortKarte) {
				MehrfachantwortKarte mk = (MehrfachantwortKarte) lk[i];
				t2 = new Text(mk.getFrage() + "\n" + "\n" + mk.moeglicheAntwortenString());
			}
			t2.setFont(Font.font("Arial", 15));
			
			Button b = new Button("Zeige Rueckseite!");
			
			b.setOnAction(new EventHandler<ActionEvent>() {			
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					zeigeRueckseite(lk, i, s);
				}
			});
			
			vb = new VBox(t1,sep,t2,b);
			vb.setSpacing(20);
			vb.setPadding(new Insets(25));
			s.setWidth(800);
			s.setHeight(600);
			s.setResizable(false);
			ScrollPane scroll = new ScrollPane(vb);
			scroll.setFitToWidth(true);
			scroll.setPannable(true);
			s.setScene(new Scene(vb));
			s.show();
		} else {
			s.close();
		}
	}
	
	public static void zeigeRueckseite(Lernkarte[] lk, int i, Stage s) {
		if(i < lk.length) {
			s.setTitle(lk[i].getKategorie());
			
			Text t1 = new Text(lk[i].getTitle());
			t1.setFont(Font.font("Arial", 30));
			
			Separator sep = new Separator();
			Text t2 = null;
			VBox vb = null;
			
			if(lk[i] instanceof EinzelantwortKarte) {
				EinzelantwortKarte ek = (EinzelantwortKarte) lk[i];
 				t2 = new Text(ek.getAntwort());
			} else if(lk[i] instanceof MehrfachantwortKarte) {
				MehrfachantwortKarte mk = (MehrfachantwortKarte) lk[i];
				t2 = new Text(mk.richtigeAntwortenString());
			}
			t2.setFont(Font.font("Arial", 15));
			
			Button b;
			if(i+1 < lk.length) b = new Button("Naechste Karte");
			else b = new Button("Beenden");
			
			int j = i+1;
			b.setOnAction(new EventHandler<ActionEvent>() {			
				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					zeigeVorderseite(lk, j, s);
				}
			});
			
			vb = new VBox(t1,sep,t2,b);
			vb.setSpacing(20);
			vb.setPadding(new Insets(25));
			s.setWidth(800);
			s.setHeight(600);
			s.setResizable(false);
			ScrollPane scroll = new ScrollPane(vb);
			scroll.setFitToWidth(true);
			scroll.setPannable(true);
			s.setScene(new Scene(vb));
			s.show();
		} else {
			s.close();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
