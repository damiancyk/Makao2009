import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Gracz extends JPanel {
	private String urlKarta = "d:/naoooka/java/projekty/Makao/obrazki/";
	private Font czcionka = new Font("Monospaced", Font.BOLD, 20);
	Border krawedz = BorderFactory.createLineBorder(Color.black, 1);
	private Karta karty[][];
	private Stol stol;
	protected int nrGracza;
	private String imieGracza;
	protected int ileKart = 4;

	public Gracz(final int nrGracza, final Karta karty[][], final Stol stol,
			String imieGracza) {
		this.nrGracza = nrGracza;
		this.karty = karty;
		this.stol = stol;
		this.imieGracza = imieGracza;
		this.setBackground(new Color(11, 11, 11));
		this.setBorder(krawedz);

		addMouseListener(new MouseListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if ((stol.getCzyjaKolej() == nrGracza)
						&& (stol.getCzyjaKolej() == stol.getMojNr())) {
					stol.zamienKarte(karty);
					repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});
	}

	public int getnrGracza() {
		return nrGracza;
	}

	public int getPozycjaStartowa() {
		int pos = 0;
		if (nrGracza == 0) {
			pos = 25;
		}
		if (nrGracza == 1) {
			pos = 49;
		}
		if (nrGracza == 2) {
			pos = 73;
		}
		if (nrGracza == 3) {
			pos = 97;
		}
		return pos;
	}

	private String getUrlTlo() {
		String urlGracz = "";
		if (nrGracza == 0) {
			urlGracz = "_1";
		}
		if (nrGracza == 1) {
			urlGracz = "_2";
		}
		if (nrGracza == 2) {
			urlGracz = "_3";
		}
		if (nrGracza == 3) {
			urlGracz = "_4";
		}
		return urlGracz;
	}

	public void paintComponent(Graphics g) {
		int pos = getPozycjaStartowa();
		String sIleKartPosiada = "Kart: " + getIleKart() + ".";
		String sImie = getImieGracza();
		String sMakao = "makao!";
		String urlPodsw = "";

		ImageIcon urlKarta = new ImageIcon(getUrlKarta() + "karta.jpg");

		if ((stol.getCzyjaKolej() == nrGracza)) {
			urlPodsw = "";
		} else {
			urlPodsw = "_x";
		}

		ImageIcon urlPanelGracz = new ImageIcon(getUrlKarta() + "tlo_gracz"
				+ getUrlTlo() + urlPodsw + ".jpg");

		ImageIcon urlKartaZakryta = new ImageIcon(getUrlKarta()
				+ "karta_zakryta.jpg");

		Image panel_gracz = urlPanelGracz.getImage();
		g.drawImage(panel_gracz, 0, 0, null, null);
		Image karta;
		int b = 20;

		if (nrGracza == stol.getMojNr()) {
			for (int x = pos; x < pos + 24; x++) {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 4; j++) {
						if (karty[i][j].getPozycja() == x) {
							if (x == (pos + ileKart - 1)) {
								urlKarta = new ImageIcon(karty[i][j].getUrl()
										+ ".jpg");
								karta = urlKarta.getImage();
								g.drawImage(karta, b / 2, b * 2, null, null);
								b = b + 10;

							} else {
								urlKarta = new ImageIcon(karty[i][j].getUrl()
										+ "_p.jpg");
								karta = urlKarta.getImage();
								g.drawImage(karta, b / 2, b * 2, null, null);
								b = b + 10;
							}

						}
					}
				}
			}
		} else {
			for (int x = pos; x < pos + 24; x++) {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 4; j++) {
						if (karty[i][j].getPozycja() == x) {
							urlKarta = new ImageIcon(karty[i][j].getUrl());
							karta = urlKartaZakryta.getImage();
							g.drawImage(karta, b / 2, b * 2, null, null);
							b = b + 10;
						}
					}
				}
			}
		}
		g.setColor(Color.white);

		g.drawString(sIleKartPosiada, 10, this.getHeight() - 4);
		g.setFont(czcionka);

		g.setColor(Color.orange);
		g.setFont(getFont());
		g.drawString(sImie, 10, 10);

		if (stol.getCzyjaKolej() == nrGracza) {
			g.setColor(Color.orange);
			g.setFont(getFont());
			g.drawString("posiada ruch", 0, this.getHeight() - 14);
		} else {
			g.setColor(Color.black);
			g.setFont(getFont());
			g.drawString("czeka..", 0, this.getHeight() - 14);
		}

		if (ileKart == 1) {
			g.setColor(Color.red);
			g.setFont(getFont());
			g.drawString(sMakao, 10, 25);
		}

		setVisible(true);
	}

	public void ustawImieGracza(String imie) {
		this.imieGracza = imie;
	}

	public void zmienIleKart(int x) {
		this.ileKart = x;
	}

	public int getIleKart() {
		return ileKart;
	}

	public String getImieGracza() {
		return imieGracza;
	}

	public String toString() {
		return imieGracza;
	}

	public String getUrlKarta() {
		return urlKarta;
	}
}
