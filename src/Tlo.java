import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tlo extends JPanel {
	private String url = "d:/naoooka/java/projekty/Makao/obrazki/";
	private Font czcionka = new Font("Monospaced", Font.BOLD, 20);
	private int boundsX;
	private int boundsY;
	private int boundsW;
	private int boundsH;
	private String urlTla = "";
	private String napis="";

	public Tlo(int x, int y, int w, int h, String uT, String n) {
		this.boundsX = x;
		this.boundsY = y;
		this.boundsW = w;
		this.boundsH = h;
		this.urlTla = uT;
		this.napis=n;
	}

	public void paintComponent(Graphics g) {
		ImageIcon im = new ImageIcon(url + urlTla);// "panel_tytul.jpg");
		Image img = im.getImage();
		g.drawImage(img, 0, 0, null, null);

		g.setFont(czcionka);
		g.setColor(Color.white);
		g.drawString(napis, 190, 30);
		setVisible(true);
	}

	public int getBoundsX() {
		return boundsX;
	}

	public int getBoundsY() {
		return boundsY;
	}

	public int getBoundsW() {
		return boundsW;
	}

	public int getBoundsH() {
		return boundsH;
	}

	public String getUrlTla() {
		return urlTla;
	}

	public String toString() {
		return "==--Makao 2009==--";
	}
}