package pk.lkarten.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;
import pk.lkarten.MehrfachantwortKarte;

public class LernkartenApp extends Application {
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		EinzelantwortErfassungView eev1 = new EinzelantwortErfassungView(arg0, new EinzelantwortKarte("Mathe", "Zahlenmenge", "welche zahlen sind 1<=x && 5>=x", "1, 2, 3, 4 und 5"));
		EinzelantwortErfassungView eev2 = new EinzelantwortErfassungView(arg0, null);
		
		String[] a = {"1", "7", "8", "3", "4", "42"};
		int[] al = {1,4,5,6};
		String[] b = {"1", "7", "3", "4"};
		int[] bl = {1,3,4};
		
		MehrfachantwortErfassungView mev1 = new MehrfachantwortErfassungView(arg0, new MehrfachantwortKarte("Mathe", "Zahlenmenge","welche zahlen sind 1<=x && 5>=x" , a, al));
		MehrfachantwortErfassungView mev2 = new MehrfachantwortErfassungView(arg0, new MehrfachantwortKarte("Mathe", "Zahlenmenge","welche zahlen sind 1<=x && 5>=x" , b, bl));
		MehrfachantwortErfassungView mev3 = new MehrfachantwortErfassungView(arg0, null);
		
		eev1.showView();
		eev2.showView();
		mev1.showView();
		mev2.showView();
		mev3.showView();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
