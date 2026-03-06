package pk.lkarten.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;

public class EinzelantwortErfassungView extends ErfassungView {
	
	EinzelantwortKarte einzelantwort;

	public EinzelantwortErfassungView(Stage s, EinzelantwortKarte k) {
		super(s, k);
		this.einzelantwort = k;
	}

	@Override
	public void showView() {
		super.showView();
		
		var s = new Stage();
	    s.initOwner(stage);
	    s.initModality(Modality.WINDOW_MODAL);
	    
		Label l1 = new Label("Antwort:");
		TextArea ta1 = new TextArea(); 
		
		if(einzelantwort != null) {
			ta1.setText(einzelantwort.getAntwort());
		}
		
		HBox hb4 = new HBox(l1,ta1);
		
		hb4.setPadding(new Insets(15.0));
		hb4.setSpacing(10.0);
		
		gp.add(hb4, 0, 3);
		
		HBox hb5 = new HBox(b1,b2);
		hb5.setPadding(new Insets(15.0));
		hb5.setSpacing(10.0);
		gp.add(hb5, 0, 4);
		

		s.setWidth(600);
		s.setHeight(400);
		s.setResizable(false);
		ScrollPane scroll = new ScrollPane(gp);
		scroll.setFitToWidth(true);
		scroll.setPannable(true);

		s.setScene(new Scene(scroll));
		s.show();
	}	

}
