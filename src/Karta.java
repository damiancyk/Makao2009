import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
 class Karta extends JPanel {
	private Font czcionka = new Font("Monospaced", Font.BOLD, 20);
	private String url;
	private final String kolor;
	private final String figura;
	private int pos_x = 10;
	private int pos_y = 10;
	protected int pozycja;

	public Karta(String url, String figura, String kolor, int pozycja) {

		this.url = url;
		this.kolor = kolor;
		this.figura = figura;
		this.pozycja = pozycja;
	}

	public void changePos(int nowa) {
		pozycja = nowa;
	}

	public String getUrl() {
		return url;
	}

	public int getPozycja() {
		return pozycja;
	}

	public void paintComponent(Graphics g) {

		ImageIcon url_karta = new ImageIcon(url);

		Image karta = url_karta.getImage();

		g.drawImage(karta, pos_x, pos_y, null, null);
		g.setFont(czcionka);
		g.setColor(Color.red);
		setVisible(true);
	}

	public String toString() {
		return this.figura + " " + this.kolor;
	}
}