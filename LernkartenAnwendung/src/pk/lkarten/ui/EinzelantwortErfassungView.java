package pk.lkarten.ui;

import java.util.Iterator;

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
		
		if(einzelantwort != null) {
			ta1.setText(einzelantwort.getAntwort());
		}
		
		HBox hb4 = new HBox(l1,ta1);
		hb4.setPadding(new Insets(15.0));
		hb4.setSpacing(10.0);
		gp.add(hb4, 0, 3);
		
		b1.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent ae) {
				try {
					LernkartenApp.lk.hinzufuegen(new EinzelantwortKarte(tf1.getText(), tf2.getText(), tf3.getText(), ta1.getText()));
				} catch (UngueltigeKarteException e) {
					JOptionPane.showConfirmDialog(null, e, "UngueltigeKarteException", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					Lernkarte.setCounter(Lernkarte.getCounter()-1);
				}
				LernkartenApp.listview.getItems().clear();
				Iterator<Lernkarte> i = LernkartenApp.lk.getIterator();
				while(i.hasNext()) {
					 LernkartenApp.listview.getItems().add(i.next().toString());
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
		ScrollPane scroll = new ScrollPane(gp);
		scroll.setFitToWidth(true);
		scroll.setPannable(true);

		s.setScene(new Scene(scroll));
		s.show();
	}	

}
