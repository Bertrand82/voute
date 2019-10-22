package voute;

public class UtilChainette {

	public static void main(String[] args) {
		System.out.println(" r = 500 ; y0 : " + getChainetteY(0, 500));

		getChainetteCoef(600, 600);
		getChainetteCoef(500, 500);
		getChainetteCoef(400, 400);
		getChainetteCoef(300, 300);
		getChainetteCoef(200, 200);
		getChainetteCoef(100, 100);
	}
	/**
	 * y = a cosh(x/a)
	 * 
	 * @param x0
	 * @param y0
	 * @return le paametre a carateristique de la chainette passant par les points (0,0) et (x0,y0)
	 */
	public static double getChainetteCoef(double x0, double y0) {
		
		double a0 = 1;
		double delta = 1;
	
		double a1 = chainette( a0,  delta,  x0,  y0);
		double a2 = chainette( a1,  delta/100,  x0,  y0);
		double a3 = chainette( a2,  delta/10000,  x0,  y0);
		double a4 = chainette( a3,  delta/1000000,  x0,  y0);
		return a4;
	}

	private static double chainette(double a, double delta, double x0, double y0) {
		double y_z_1=0;
		double a_z_1 = a;
		for (int i = 0; i < 100000; i++) {
			
			double y = getChainetteY(x0, a);
			if (y < y0) {
				return a_z_1;
			}
			 a_z_1 = a;
			a = a + delta;
			y_z_1=y;
		}
		return a;
	}

	/**
	 * 
	 * @param x
	 * @param a
	 * @return l'ordonnéee d'une chainette de caracteristique a et passant par le point (0,0)
	 */
	public static double getChainetteY(double x, double a) {
		double y = (a * Math.cosh(x / a)) - a;
		return y;
	}

}
