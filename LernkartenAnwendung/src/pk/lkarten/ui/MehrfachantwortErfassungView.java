package pk.lkarten.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.MehrfachantwortKarte;

public class MehrfachantwortErfassungView extends ErfassungView{
	
	MehrfachantwortKarte mehrfachantwort;

	public MehrfachantwortErfassungView(Stage s, MehrfachantwortKarte k) {
		super(s, k);
		mehrfachantwort = k;
	}
	
	@Override
	public void showView() {
		super.showView();
		
		var s = new Stage();
	    s.initOwner(stage);
	    s.initModality(Modality.WINDOW_MODAL);
	    
	    Button add = new Button("Neue Antwort");
	    HBox hb5 = new HBox(b1,b2);
		hb5.setPadding(new Insets(15.0));
		hb5.setSpacing(10.0);
		
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
	    		super.gp.add(hb, 0, (i+3));
	    	}
	    	gp.add(add, 0, (4+mehrfachantwort.getMoeglicheAntworten().length));
	    	gp.add(hb5, 0, (5+mehrfachantwort.getMoeglicheAntworten().length));
	    } else {
	    	gp.add(add, 0, 4);
	    	gp.add(hb5, 0, 5);
	    }
	    
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
