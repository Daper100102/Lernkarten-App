package pk;

public class FirstProgramm {

	public static void maleTreppe(int hoehe, int stufentiefe) {
	        for(int i = 1; i <= hoehe; i++) {
	            System.out.println();
	            for(int j = 1; j <= stufentiefe * i; j++) {
	                System.out.print("*");
	            }
	        }
	    }

	public static void maleTreppeRueckwaerts(int hoehe, int stufentiefe) {
	        for(int i = 1; i <= hoehe; i++) {
	            System.out.println();
	            for(int j = 1; j <= stufentiefe * hoehe; j++) {
	            	if(j <= stufentiefe*hoehe - stufentiefe*i) {
	            		System.out.print(" ");
	            	} else {
	            		System.out.print("*");
	            	}
	            }
	        }
	    }


	public static void main(String[] args) {
		maleTreppe(6, 2);
		maleTreppeRueckwaerts(6, 2);

	}

}
