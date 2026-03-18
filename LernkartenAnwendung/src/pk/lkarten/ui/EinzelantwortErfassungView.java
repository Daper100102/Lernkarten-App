package pk.lkarten.ui;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;
import pk.lkarten.Lernkarte;
import pk.lkarten.UngueltigeKarteException;
import pk.lkarten.VorhandeneKarteException;

public class EinzelantwortErfassungView extends ErfassungView {
	
	EinzelantwortKarte einzelantwort;

	public EinzelantwortErfassungView(Stage s, EinzelantwortKarte k) {
		super(s, k);
		this.einzelantwort = k;
	}

	@Override
	public void showView() {
		super.showView();
		
		final Stage s = new Stage();
	    s.initOwner(stage);
	    s.initModality(Modality.WINDOW_MODAL);
	    
		Label l1 = new Label("Antwort: ");
		TextArea ta1 = new TextArea(); 
		HBox hb4 = new HBox(l1,ta1);
		ScrollPane scroll = new ScrollPane(gp);
		
		hb4.setPadding(new Insets(15.0));
		hb4.setSpacing(10.0);
		scroll.setFitToWidth(true);
		scroll.setPannable(true);
		
		gp.add(hb4, 0, 3);
		
		if(einzelantwort != null) {
			ta1.setText(einzelantwort.getAntwort());
		}
		
		b1.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent ae) {
				try {
					karte = new EinzelantwortKarte(tf1.getText(), tf2.getText(), tf3.getText(), ta1.getText());
					LernkartenApp.alk.hinzufuegen(karte);
					
					if(LernkartenApp.kategorie != null) {
						if((LernkartenApp.kartentyp == 0 || LernkartenApp.kartentyp == 2) && karte.vergleicheKategorien(LernkartenApp.kategorie)) {
							LernkartenApp.lk.hinzufuegen(karte);
						}
					} else {
						if(LernkartenApp.kartentyp == 0 || LernkartenApp.kartentyp == 2) {
							LernkartenApp.lk.hinzufuegen(karte);
						}
					}
				} catch (UngueltigeKarteException e) {
					JOptionPane.showConfirmDialog(null, e, "UngueltigeKarteException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					Lernkarte.setCounter(Lernkarte.getCounter()-1);
					showView();
				} catch (VorhandeneKarteException e) {
					JOptionPane.showConfirmDialog(null, e, "Karte nicht hinzugefuegt", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}

				try {
					LernkartenApp.listview.getItems().clear();
					if(LernkartenApp.sortierung == 0)
						LernkartenApp.listview.getItems().addAll(LernkartenApp.lk.sortiertNachIdAbsteigend());
					if(LernkartenApp.sortierung == 1)
						LernkartenApp.listview.getItems().addAll(LernkartenApp.lk.sortiertNachIdAufsteigend());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, e, "IOException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				s.close();
			}
		});
		
		b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	s.close();
            }
        });
		
		s.setWidth(800);
		s.setHeight(600);
		s.setResizable(false);
		s.setScene(new Scene(scroll));
		s.show();
	}	

}
