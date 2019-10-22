package voute;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Bertrand
 *  
 */
public class PanelChainetteEntre2Cercles extends JPanel {

	private static final long serialVersionUID = 1L;
	static int wMax = 2 * 500;
	static int hMax = 400;
	static int xMax = 400; 
	static int rM =300;
	static double ch_1= 1.54308063482;

	public PanelChainetteEntre2Cercles() {
		Dimension dim = new Dimension(wMax, hMax + 100);
		this.setSize(dim);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("chainette entre 1/2 cercles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelChainetteEntre2Cercles panel = new PanelChainetteEntre2Cercles();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, wMax, hMax + 20);

		Curve cercle_1 = new CurveCercle(rM);
		Curve cercle_2 = new CurveCercle( 0.9*rM);
		Curve chainette_1 = new CurveChainette(rM);
		
		chainette_1.paintCurve(g, Color.BLUE);
		cercle_1.paintCurve(g, Color.YELLOW);
		cercle_2.paintCurve(g, Color.YELLOW);
		
		
	}

	class CurveCercle extends Curve {
		CurveCercle(double r) {
			super((int) r);
			this.listPts = getCercle(r);
		}

		private List<PointC> getCercle(double r) {
			List<PointC> list = new ArrayList<>();
			for (int x = -xMax; x <= xMax; x++) {
				int y = (int) Math.sqrt(r * r - x * x);
				//System.out.println("Cercle x:  " + x + "   y:" + y);
				PointC p = new PointC(x, y);
				list.add(p);
			}
			return list;
		}
	}

	class CurveChainette extends Curve {
		CurveChainette(double r) {
			this((int)r, 0.0001);
		}

		CurveChainette(int r, double omega) {
			super(r);
			this.listPts = getChainette(r, omega);
		}

		private List<PointC> getChainette(double r, double omega) {
			List<PointC> list = new ArrayList<>();
			double a = UtilChainette.getChainetteCoef(r, r);
			for (int x = -xMax; x <= xMax; x++) {
				double yChainette = UtilChainette.getChainetteY(x, a);
				int y = (int) (-yChainette+r);
				PointC p = new PointC(x, y);
				list.add(p);
			}
			return list;
		}
	}

	abstract class Curve {
		int r;

		Curve(int rr) {
			this.r = rr;
		}

		List<PointC> listPts = new ArrayList<>();

		void paintCurve(Graphics g, Color color) {
			g.setColor(color);
			for (PointC p : listPts) {
				paintCercle(g,p.getW(), p.getH(),  2);
			}
			paintCercle(g,PanelChainetteEntre2Cercles.getW(this.r), PanelChainetteEntre2Cercles.getH(0), 10);
			paintCercle(g,PanelChainetteEntre2Cercles.getW(-1 * this.r), PanelChainetteEntre2Cercles.getH(0),  10);
		}
		void paintCercle(Graphics g,int w,int h,int r) {
			g.drawOval(w-r, h-r,2* r, 2*r);
		}
		
		
	}

	class PointC {
		public double x;
		public double y;

		public PointC(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public int getW() {
			return PanelChainetteEntre2Cercles.getW(x);
		}

		public int getH() {
			return PanelChainetteEntre2Cercles.getH(y);
		}
		
		
	}

	public static int getW(double x) {
		return (int) (x + wMax / 2);
	}
	public static double getX(int w) {
		return  (w - wMax / 2);
	}

	public static int getH(double y) {
		return (int) (hMax - y);
	}	
	
	public static int getY(int h) {
		return (int) (hMax - h);
	}
}
