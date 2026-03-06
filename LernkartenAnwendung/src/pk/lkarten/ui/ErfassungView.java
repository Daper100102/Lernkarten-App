package pk.lkarten.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;

public abstract class ErfassungView extends Stage {
	Stage stage;
	Lernkarte karte;
	protected GridPane gp;
	protected Button b1;
	protected Button b2;
	protected Label l1;
	protected Label l2;
	protected Label l3;
	protected TextField tf1;
	protected TextField tf2;
	protected TextField tf3;
	
	protected ErfassungView(Stage s, Lernkarte k) {
		this.stage = s;
		this.karte = k;
	}
	
	public void showView() {		
		gp = new GridPane();
		b1 = new Button("Ok");
		b2 = new Button("Abbrechen");
		l1 = new Label("Kategorie:");
		l2 = new Label("Titel:");
		l3 = new Label("Frage:");
		tf1 = new TextField("");
		tf2 = new TextField("");
		tf3 = new TextField("");
			
		if(karte != null) {
			tf1.setText(karte.getKategorie());
			tf2.setText(karte.getTitle());
			tf3.setText(karte.getFrage());
		}
		
		var hb1 = new HBox(l1,tf1);
		var hb2 = new HBox(l2,tf2);
		var hb3 = new HBox(l3,tf3);
		var hb9 = new HBox(b1,b2);
		
		hb1.setPadding(new Insets(15.0));
		hb1.setSpacing(10.0);
		hb2.setPadding(new Insets(15.0));
		hb2.setSpacing(10.0);
		hb3.setPadding(new Insets(15.0));
		hb3.setSpacing(10.0);
		hb9.setPadding(new Insets(15.0));
		hb9.setSpacing(10.0);
		
		gp.add(hb1, 0, 0);
		gp.add(hb2, 0, 1);
		gp.add(hb3, 0, 2);
	}
}
