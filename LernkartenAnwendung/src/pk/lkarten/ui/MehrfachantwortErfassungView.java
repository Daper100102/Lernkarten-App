package pk.lkarten.ui;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;
import pk.lkarten.MehrfachantwortKarte;
import pk.lkarten.UngueltigeKarteException;
import pk.lkarten.VorhandeneKarteException;

public class MehrfachantwortErfassungView extends ErfassungView{
	
	MehrfachantwortKarte mehrfachantwort;
	int antwort;
	VBox ant;

	public MehrfachantwortErfassungView(Stage s, MehrfachantwortKarte k) {
		super(s, k);
		mehrfachantwort = k;
		antwort = 1;
	}
	
	@Override
	public void showView() {
		super.showView();
		
		var s = new Stage();
	    s.initOwner(stage);
	    s.initModality(Modality.WINDOW_MODAL);
	    
	    ant = new VBox();
	    Button b3 = new Button("Neue Antwort");
	    HBox hb5 = new HBox(b3);
	    ScrollPane scroll = new ScrollPane(gp);
	    
	    hb5.setPadding(new Insets(15.0));
		hb5.setSpacing(10.0);
		scroll.setFitToWidth(true);
		scroll.setPannable(true);
		
	    if(mehrfachantwort != null) {
	    	for(int i = 0; i < mehrfachantwort.getMoeglicheAntworten().length; i++) {
	    		Label l = new Label("Antwort" + (i+1) + (": "));
	    		TextArea ta = new TextArea();
	    		ta.setText(mehrfachantwort.getMoeglicheAntworten()[i]);
	    		CheckBox cb = new CheckBox();
	    		for(int r: mehrfachantwort.getRichtigeAntworten()) {
	    			if(r == i+1) cb.setSelected(true);
	    		}
	    		HBox hb = new HBox(l, ta, cb);
	    		hb.setPadding(new Insets(15.0));
	    		hb.setSpacing(10.0);
	    		ant.getChildren().add(hb);
	    	}
	    }
	    	gp.add(ant, 0, 3);
	    	gp.add(hb5, 0, 4);

	    
	    b3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Label l = new Label("Antwort " + (antwort) + (": "));
	    		TextArea ta = new TextArea();
	    		CheckBox cb = new CheckBox();
	    		HBox hb = new HBox(l, ta, cb);
	    		hb.setPadding(new Insets(15.0));
	    		hb.setSpacing(10.0);
	    		antwort++;
	    		ant.getChildren().add(hb);
			}
		});
	    
	    b1.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent ae) {
				int i = 0;
				String[] moeglicheAntworten = new String[antwort-1];
				ArrayList<Integer> hia = new ArrayList<Integer>();
				for(Node n: ant.getChildren()) {
					for(Node no: ((HBox) n).getChildren()) {
						if(no instanceof TextArea) moeglicheAntworten[i] = ((TextArea) no).getText();
						if(no instanceof CheckBox) {
							if(((CheckBox) no).isSelected()) {
								hia.add((i+1));
							}
						}
					}
					i++;
				}
				
				int[] richtigeAntworten = new int[hia.size()];
				for (int j = 0; j < hia.size(); j++) {
					richtigeAntworten[j] = hia.get(j);
				}

				try {
					LernkartenApp.lk.hinzufuegen(new MehrfachantwortKarte(tf1.getText(), tf2.getText(), tf3.getText(), moeglicheAntworten, richtigeAntworten));
				} catch (UngueltigeKarteException e) {
					JOptionPane.showConfirmDialog(null, e, "UngueltigeKarteException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					Lernkarte.setCounter(Lernkarte.getCounter()-1);
					showView();
				} catch (VorhandeneKarteException e) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null, e, "Karte nicht hinzugefuegt", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				LernkartenApp.listview.getItems().clear();
				Iterator<Lernkarte> it = LernkartenApp.lk.getIterator();
				while(it.hasNext()) {
					 LernkartenApp.listview.getItems().add(it.next().toString());
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
