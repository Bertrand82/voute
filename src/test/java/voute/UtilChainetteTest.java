package voute;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilChainetteTest {

	@Test
	void test() {
		assertTrue(true);
	}
	
	@Test
	void testCalcul() {
			
			chainetteTest(600, 600);
			chainetteTest(500, 500);
			chainetteTest(400, 400);
			chainetteTest(300, 300);
			chainetteTest(200, 200);
			chainetteTest(100, 100);
	}

	private void chainetteTest(double i, double j) {
		double a = UtilChainette.getChainetteCoef(i, j);
		assertTrue(a > 0);
		double y = UtilChainette.getChainetteY(i, a);
		double delta = Math.abs(y-j);
		assertTrue(delta < 0.00001);
	}
	

}
