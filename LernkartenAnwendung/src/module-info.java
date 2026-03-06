module pk {
	requires java.desktop;
	requires javafx.controls;
	requires javafx.graphics;
	
	opens pk.lkarten.ui to javafx.graphics;
}