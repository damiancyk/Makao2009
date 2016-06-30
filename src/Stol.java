import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Stol extends JPanel {
	private String url = "d:/naoooka/java/projekty/Makao/obrazki/";
	private JScrollPane jScrollPane;
	private JTextArea tObszarTekstowy;
	private JTextField tPoleTekstowe;
	private Font czcionka = new Font("Monospaced", Font.BOLD, 14);
	private Border krawedz = BorderFactory.createLineBorder(Color.black, 1);
	private Karta karty[][];
	private Gracz gracze[];
	private int ileZakrytych = 24;
	private int ilePolozonych = 0;
	protected int czyjaKolej = 10;
	private int wygranaDla = 0;
	private int mojNr = 10;
	private int ileKart[] = new int[4];
	private int ileLinii = 0;
	private static int wygrDla=10;

	public Stol(Karta karty[][], Gracz gracze[], JScrollPane jScrollPane,
			JTextArea tObszarTekstowy, JTextField tPoleTekstowe) {
		this.jScrollPane = jScrollPane;
		this.tObszarTekstowy = tObszarTekstowy;
		this.tPoleTekstowe = tPoleTekstowe;
		this.karty = karty;
		this.gracze = gracze;

		this.setBackground(new Color(11, 11, 11));
		this.setBorder(krawedz);
	}

	public void potasujKarty(Karta karty[][]) {
		int tmp = 0;
		int losowa_i, losowa_j;
		for (int x = 0; x < 10; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if ((karty[i][j].getPozycja() >= 1)
							&& (karty[i][j].getPozycja() <= 24)) {
						losowa_i = (int) (Math.random() * 5);
						losowa_j = (int) (Math.random() * 3);
						tmp = karty[losowa_i][losowa_j].getPozycja();
						karty[losowa_i][losowa_j].pozycja = karty[i][j].pozycja;
						karty[i][j].pozycja = tmp;
					}
				}
			}
		}
		dopiszZdarzenie(">> karty potasowano..");
		repaint();
	}

	public void rozdajKarty(Karta karty[][]) {
		ileZakrytych = 8;
		czyjaKolej = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if ((karty[i][j].getPozycja() <= 24)
						&& (karty[i][j].getPozycja() >= 21)) {
					karty[i][j].pozycja += 4;
					if (karty[i][j].getPozycja() == 28) {
						gracze[0].ileKart = 4;
					}
				}
				if ((karty[i][j].getPozycja() <= 20)
						&& (karty[i][j].getPozycja() >= 17)) {
					karty[i][j].pozycja += 32;
					if (karty[i][j].getPozycja() == 52) {
						gracze[1].ileKart = 4;
					}
				}
				if ((karty[i][j].getPozycja() <= 16)
						&& (karty[i][j].getPozycja() >= 13)) {
					karty[i][j].pozycja += 60;
					if (karty[i][j].getPozycja() == 76) {
						gracze[2].ileKart = 4;
					}
				}
				if ((karty[i][j].getPozycja() <= 12)
						&& (karty[i][j].getPozycja() >= 9)) {
					karty[i][j].pozycja += 88;
					if (karty[i][j].getPozycja() == 100) {
						gracze[3].ileKart = 4;
					}
				}
			}
		}
		dopiszZdarzenie(">> go! rozpoczyna gracz 1");
	}

	public boolean polozKarte(Karta karty[][]) {
		boolean b = false;
		if (gracze[czyjaKolej].getIleKart() > 0) {
			int pozycjaGracz = gracze[czyjaKolej].getPozycjaStartowa()
					+ gracze[czyjaKolej].getIleKart() - 1;
			int pozycjaTalia = this.ilePolozonych + 121;
			int is = 0, js = 0, xs = 0, ys = 0;

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].getPozycja() == pozycjaGracz) {
						is = i;
						js = j;
					}
				}
			}
			if (this.ilePolozonych != 0) {
				for (int x = 0; x < 6; x++) {
					for (int y = 0; y < 4; y++) {
						if (karty[x][y].getPozycja() == (pozycjaTalia - 1)) {
							xs = x;
							ys = y;
						}
					}
				}
				if ((is == xs) || (js == ys)) {
					b = true;
					karty[is][js].pozycja = pozycjaTalia;
					ilePolozonych += 1;
					gracze[czyjaKolej].ileKart -= 1;
					dopiszZdarzenie(">> " + gracze[this.getCzyjaKolej()]
							+ " k³adzie " + karty[is][js]);
					nastepnyGracz(karty);
				} else {
					b = false;
					// dopiszZdarzenie(">> " + gracze[this.getCzyjaKolej()]
					// + " próbuje czitowaæ!");
				}
			} else {
				b = true;
				karty[is][js].pozycja = pozycjaTalia;
				ilePolozonych += 1;
				gracze[czyjaKolej].ileKart -= 1;
				nastepnyGracz(karty);
				dopiszZdarzenie(">> " + gracze[this.getCzyjaKolej()]
						+ " k³adzie " + karty[is][js]);
			}
			repaint();
		}
		return b;
	}

	public void zamienKarte(Karta karty[][]) {
		int pozycjaWGracz = gracze[czyjaKolej].getPozycjaStartowa()
				+ gracze[czyjaKolej].getIleKart() - 1;
		int is = 0, js = 0;
		for (int x = gracze[czyjaKolej].getPozycjaStartowa(); x <= pozycjaWGracz; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].pozycja == gracze[czyjaKolej]
							.getPozycjaStartowa()) {
						is = i;
						js = j;
					}
				}
			}
		}
		for (int x = gracze[czyjaKolej].getPozycjaStartowa() + 1; x <= pozycjaWGracz; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].pozycja == x) {
						karty[i][j].pozycja--;
					}
				}
			}
		}
		karty[is][js].pozycja = pozycjaWGracz;
	}

	public void ujmijKart(Karta karty[][]) {
		int pozycjaOstatniejPolozonej = this.getIlePolozonych() + 120;
		int iw = 0, jw = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (karty[i][j].pozycja == pozycjaOstatniejPolozonej) {
					iw = i;
					jw = j;
				}
			}
		}
		for (int x = 121; x < pozycjaOstatniejPolozonej; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].pozycja == x) {
						karty[i][j].pozycja -= 120;
						this.ileZakrytych++;
					}
				}
			}
		}
		dopiszZdarzenie(">> potasowano odkryte karty..");
		karty[iw][jw].pozycja = 121;
		this.ilePolozonych = 1;
	}

	public void nastepnyGracz(Karta karty[][]) {
		if (gracze[czyjaKolej].getIleKart() == 0) {
			wygranaDla = gracze[czyjaKolej].nrGracza;
		}
		if (czyjaKolej == 3) {
			czyjaKolej = 0;
		} else {
			czyjaKolej++;
		}
	}

	public void wezKarte(Karta karty[][]) {
		if (this.ileZakrytych > 0) {
			int pozycjaGracz = gracze[czyjaKolej].getPozycjaStartowa()
					+ gracze[czyjaKolej].getIleKart();
			int pozycjaTalia = this.ileZakrytych;
			if (this.ileZakrytych > 0) {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 4; j++) {
						if (karty[i][j].getPozycja() == pozycjaTalia) {
							karty[i][j].pozycja = pozycjaGracz;
							dopiszZdarzenie(">>" + gracze[this.getCzyjaKolej()]
									+ " bierze kartê");
						}
					}
				}
			}
			ileZakrytych -= 1;
			gracze[czyjaKolej].ileKart += 1;
			repaint();
		}
	}

	public void nowaGra() {
		int x = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				x++;
				karty[i][j].pozycja = x;
				ileZakrytych = 24;
				ilePolozonych = 0;
			}
		}
		dopiszZdarzenie(">> nowa gra..");
		gracze[0].ileKart = 0;
		gracze[1].ileKart = 0;
		gracze[2].ileKart = 0;
		gracze[3].ileKart = 0;
		wygranaDla = 0;
		potasujKarty(karty);
	}

	public void paintComponent(Graphics g) {
		ImageIcon urlStol = new ImageIcon(url + "tlo_gora.jpg");
		ImageIcon urlKartaZakryta = new ImageIcon(url + "karta_zakryta.jpg");
		ImageIcon urlKarta = new ImageIcon();

		Image iStol = urlStol.getImage();
		Image iKarta = urlKarta.getImage();
		g.drawImage(iStol, 0, 10, null, null);
		for (int x = 0; x < 24; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].getPozycja() == x) {
						urlKarta = new ImageIcon(karty[i][j].getUrl() + ".jpg");
						iKarta = urlKartaZakryta.getImage();
						g.drawImage(iKarta, (int) (Math.random() * 50) + 120,
								(int) (Math.random() * 50 + 45), null, null);
					}
				}
			}
		}
		for (int x = 121; x < 145; x++) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {
					if (karty[i][j].getPozycja() == x) {
						if (x == (120 + ilePolozonych)) {
							urlKarta = new ImageIcon(karty[i][j].getUrl()
									+ ".jpg");
							iKarta = urlKarta.getImage();
							g.drawImage(iKarta,
									(int) (Math.random() * 50) + 20,
									(int) (Math.random() * 50 + 140), null,
									null);
						} else {
							urlKarta = new ImageIcon(karty[i][j].getUrl()
									+ "_p.jpg");
							iKarta = urlKarta.getImage();
							g.drawImage(iKarta,
									(int) (Math.random() * 50) + 20,
									(int) (Math.random() * 50 + 140), null,
									null);
						}
					}
				}
			}
		}

		String sKartPolozonych = "Kart po³o¿onych: " + getIlePolozonych() + ".";
		String sKartZakrytych = "Kart zakrytych: " + getIleZakrytych() + ".";
		String sZwyciezca = "Zwyciêzc¹ jest gracz nr "
				+ (gracze[wygranaDla].nrGracza - 1);
		g.setFont(czcionka);
		g.setColor(Color.ORANGE);

		g.setColor(Color.WHITE);
		g.drawString(sKartPolozonych, 20, this.getHeight() - 13);
		g.drawString(sKartZakrytych, 20, this.getHeight() - 3);
		if (wygranaDla != 0) {
			g.drawString(sZwyciezca, 20, 10);
		}
		
		if(wygrDla!=10){
			g.drawString("zwyciêzc¹ jest gracz nr: "+wygrDla, 5, 5);
			Start.rozpoczynasz(false);
			Start.kladzieszIBierzesz(false);
		}
		// g.drawString(sZwyciezca, 20, 10);

		//		
		// g.drawRect(10, 10, this.getWidth()-20, this.getHeight()-20);
		// g.drawRect(11, 11, this.getWidth()-22, this.getHeight()-22);

		setVisible(true);
	}

	public void napisz() {
		String napis = null;
		napis = tPoleTekstowe.getText();
		tPoleTekstowe.setText("");
		tObszarTekstowy.append(gracze[this.getMojNr()].getImieGracza() + ">>"
				+ napis + "\n");
		jScrollPane.getVerticalScrollBar().setValue(
				jScrollPane.getVerticalScrollBar().getMaximum());
	}

	public void dodajLinie() {
		ileLinii++;
	}

	public int getLinie() {
		return ileLinii;
	}

	public void dopiszZdarzenie(String napis) {
		tObszarTekstowy.append(napis + "\n");
		jScrollPane.getVerticalScrollBar().setValue(
				jScrollPane.getVerticalScrollBar().getMaximum());
	}

	public void zmienIleZakrytych(int x) {
		ileZakrytych = x;
	}

	public void zmienIlePolozonych(int x) {
		ilePolozonych = x;
	}

	public void zmienCzyjaKolej(int x) {
		czyjaKolej = x;
	}

	public void ustawMojNr(int x) {
		mojNr = x;
	}

	public int getMojNr() {
		return mojNr;
	}

	public int getIleZakrytych() {
		return ileZakrytych;
	}

	public int getIlePolozonych() {
		return ilePolozonych;
	}

	public int getCzyjaKolej() {
		return czyjaKolej;
	}

	public int getWygranaDla() {
		return wygranaDla;
	}

	public int[] getIleKart() {
		return ileKart;
	}

	public void uaktualnijIleKart(int i) {
		ileKart[0] = 5;
	}

	public void refresh() {
		repaint();
	}

	public void refreshAll() {
		repaint();
		gracze[0].repaint();
		gracze[1].repaint();
		gracze[2].repaint();
		gracze[3].repaint();
	}

	public String toString() {
		return "stol do gry w makao";
	}

	public static void setWygrDla(int wygrDla) {
		Stol.wygrDla = wygrDla;
	}

	public static int getWygrDla() {
		return wygrDla;
	}
}