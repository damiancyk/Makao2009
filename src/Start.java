import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Start extends JApplet {

	// komponenty //
	private String urlKarta = "d:/naoooka/java/projekty/Makao/obrazki/";
	private JCheckBox cB1 = new JCheckBox("one", false), cB2 = new JCheckBox(
			"two", false), cB3 = new JCheckBox("three", false),
			cB4 = new JCheckBox("four", false);
	private JTextArea tObszarTekstowy = new JTextArea(8, 2);
	private JTextField tPoleTekstowe = new JTextField();
	private JTextField tImie = new JTextField();
	private JScrollPane jScrollPane = new JScrollPane(tObszarTekstowy);
	private JLabel lStatus = new JLabel("status: ...");
	private JLabel lKtoryGracz = new JLabel("gracz ...");
	private JLabel lStrzalkiL = new JLabel(">>");
	private JLabel lStrzalkiP = new JLabel("<<");
	private JLabel lPodajImie = new JLabel("podaj swoje imiê:");
	private JLabel lWybierzNr = new JLabel("<<nr");
	private JLabel lStrzalki = new JLabel("<<");
	private static JButton bTasuj = new JButton("tasuj");
	private static JButton bRozdaj = new JButton("rozdaj");
	private JButton bRozlacz = new JButton("roz³¹cz");
	private static JButton bPoloz = new JButton("po³ó¿");
	private static JButton bWezKarte = new JButton("weŸ kartê");
	private JButton bStworzSerwer = new JButton("stwórz grê");
	private JButton bDolaczDoGry = new JButton("do³¹cz do gry");
	private JButton bAutorzy = new JButton("autorzy");

	// wczytywane obiekty //
	private static Karta[][] karty = new Karta[6][4];
	private static Gracz gracze[] = new Gracz[5];
	private Tlo tytul = new Tlo(0, 0, 600, 50, "panel_tytul.jpg",
			"--==MAKAO 2009==--");
	private Menu menu = new Menu();
	private Stol stol = new Stol(karty, gracze, jScrollPane, tObszarTekstowy,
			tPoleTekstowe);

	Klient klient;

	public Start() {
	}

	public void init() {

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					utworz();
				}
			});
		} catch (Exception e) {
		}
	}

	public void utworz() {
		// tworzenie obiektów (inicjalizacja) //
		karty[0][0] = new Karta(urlKarta + "9_czerwo", "9", "czerwo", 1);
		karty[0][1] = new Karta(urlKarta + "9_dzwonek", "9", "dzwonek", 2);
		karty[0][2] = new Karta(urlKarta + "9_zoledz", "9", "zoledz", 3);
		karty[0][3] = new Karta(urlKarta + "9_wino", "9", "wino", 4);
		karty[1][0] = new Karta(urlKarta + "10_czerwo", "10", "czerwo", 5);
		karty[1][1] = new Karta(urlKarta + "10_dzwonek", "10", "dzwonek", 6);
		karty[1][2] = new Karta(urlKarta + "10_zoledz", "10", "zoledz", 7);
		karty[1][3] = new Karta(urlKarta + "10_wino", "10", "wino", 8);
		karty[2][0] = new Karta(urlKarta + "J_czerwo", "J", "czerwo", 9);
		karty[2][1] = new Karta(urlKarta + "J_dzwonek", "J", "dzwonek", 10);
		karty[2][2] = new Karta(urlKarta + "J_zoledz", "J", "zoledz", 11);
		karty[2][3] = new Karta(urlKarta + "J_wino", "J", "wino", 12);
		karty[3][0] = new Karta(urlKarta + "D_czerwo", "D", "czerwo", 13);
		karty[3][1] = new Karta(urlKarta + "D_dzwonek", "D", "dzwonek", 14);
		karty[3][2] = new Karta(urlKarta + "D_zoledz", "D", "zoledz", 15);
		karty[3][3] = new Karta(urlKarta + "D_wino", "D", "wino", 16);
		karty[4][0] = new Karta(urlKarta + "K_czerwo", "K", "czerwo", 17);
		karty[4][1] = new Karta(urlKarta + "K_dzwonek", "K", "dzwonek", 18);
		karty[4][2] = new Karta(urlKarta + "K_zoledz", "K", "zoledz", 19);
		karty[4][3] = new Karta(urlKarta + "K_wino", "K", "wino", 20);
		karty[5][0] = new Karta(urlKarta + "A_czerwo", "A", "czerwo", 21);
		karty[5][1] = new Karta(urlKarta + "A_dzwonek", "A", "dzwonek", 22);
		karty[5][2] = new Karta(urlKarta + "A_zoledz", "A", "zoledz", 23);
		karty[5][3] = new Karta(urlKarta + "A_wino", "A", "wino", 24);
		gracze[0] = new Gracz(0, karty, stol, "...");
		gracze[1] = new Gracz(1, karty, stol, "...");
		gracze[2] = new Gracz(2, karty, stol, "...");
		gracze[3] = new Gracz(3, karty, stol, "...");

		// applet -ustawienia //
		setName("Makao");
		setSize(600, 500);
		setLayout(null);
		getContentPane().setBackground(new Color(30, 130, 100));

		// w³aœciwoœci komponentów //
		bTasuj.setBackground(new Color(111, 111, 111));
		bTasuj.setForeground(Color.red);
		bTasuj.setVisible(false);
		bAutorzy.setBackground(new Color(111, 111, 111));
		bAutorzy.setForeground(Color.red);
		bRozdaj.setBackground(new Color(111, 111, 111));
		bRozdaj.setForeground(Color.black);
		bRozdaj.setVisible(false);
		bPoloz.setBackground(new Color(110, 210, 190));
		bPoloz.setForeground(new Color(0, 0, 0));
		bPoloz.setVisible(false);
		bRozlacz.setBackground(new Color(11, 111, 111));
		bRozlacz.setForeground(Color.black);
		bRozlacz.setVisible(false);
		bDolaczDoGry.setEnabled(false);
		bDolaczDoGry.setVisible(false);
		bWezKarte.setBackground(new Color(11, 11, 1));
		bWezKarte.setForeground(new Color(222, 222, 0));
		bWezKarte.setVisible(false);
		cB1.setBackground(new Color(20, 155, 55));
		cB2.setBackground(new Color(35, 140, 40));
		cB3.setBackground(new Color(50, 125, 25));
		cB4.setBackground(new Color(65, 110, 10));
		bStworzSerwer.setVisible(false);
		bStworzSerwer.setEnabled(false);
		lKtoryGracz.setFont(new Font("SansSerif", Font.BOLD, 14));
		lKtoryGracz.setForeground(new Color(1, 1, 1));
		lStatus.setFont(new Font("SansSerif", Font.ITALIC, 13));
		lStatus.setForeground(new Color(1, 1, 1));
		lPodajImie.setForeground(new Color(120, 1, 1));
		lPodajImie.setVisible(false);
		lStrzalki.setForeground(new Color(120, 1, 1));
		lStrzalki.setVisible(false);
		lWybierzNr.setForeground(new Color(120, 1, 1));
		tImie.setEnabled(false);
		tObszarTekstowy.setBackground(new Color(90, 90, 90));
		tObszarTekstowy.setForeground(new Color(1, 1, 1));
		tObszarTekstowy.setBorder(BorderFactory
				.createLineBorder(Color.black, 1));
		tObszarTekstowy.setEditable(false);
		tObszarTekstowy.setLineWrap(true);
		tObszarTekstowy.setFont(new Font("Monospaced", Font.ITALIC, 11));
		tObszarTekstowy.setAutoscrolls(true);
		tPoleTekstowe.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		tPoleTekstowe.setEnabled(false);

		// pozycjonowanie bezwzglêdne //
		tytul.setBounds(0, 0, 600, 50);
		stol.setBounds(180, 40, 240, 360);
		gracze[0].setBounds(0, 50, 90, 350);
		gracze[1].setBounds(90, 50, 90, 350);
		gracze[2].setBounds(420, 50, 90, 350);
		gracze[3].setBounds(510, 50, 90, 350);

		bPoloz.setBounds(120, 410, 100, 25);
		bWezKarte.setBounds(120, 435, 100, 25);
		lPodajImie.setBounds(120, 457, 100, 20);
		tImie.setBounds(120, 475, 100, 20);
		bTasuj.setBounds(10, 410, 100, 20);
		bRozdaj.setBounds(10, 430, 100, 20);
		bAutorzy.setBounds(10, 450, 100, 20);
		cB1.setBounds(230, 410, 20, 20);
		cB2.setBounds(250, 410, 20, 20);
		cB3.setBounds(270, 410, 20, 20);
		cB4.setBounds(290, 410, 20, 20);
		lWybierzNr.setBounds(310, 410, 30, 30);
		lStatus.setBounds(230, 445, 120, 20);
		lKtoryGracz.setBounds(230, 430, 120, 20);
		bStworzSerwer.setBounds(230, 470, 120, 20);
		bDolaczDoGry.setBounds(230, 470, 120, 20);
		jScrollPane.setBounds(360, 405, 230, 70);
		tPoleTekstowe.setBounds(375, 475, 195, 20);
		lStrzalki.setBounds(350, 470, 15, 20);
		lStrzalkiL.setBounds(360, 475, 15, 20);
		lStrzalkiP.setBounds(570, 475, 15, 20);
		menu.setBounds(0, 400, 600, 100);

		// wczytanie obiektów //
		getContentPane().add(tytul);
		getContentPane().add(lStrzalki);
		getContentPane().add(gracze[0]);
		getContentPane().add(gracze[1]);
		getContentPane().add(gracze[2]);
		getContentPane().add(gracze[3]);
		getContentPane().add(stol);
		getContentPane().add(bTasuj);
		getContentPane().add(bRozdaj);
		getContentPane().add(bPoloz);
		getContentPane().add(bWezKarte);
		getContentPane().add(lPodajImie);
		getContentPane().add(lWybierzNr);
		getContentPane().add(tImie);
		getContentPane().add(cB1);
		getContentPane().add(cB2);
		getContentPane().add(cB3);
		getContentPane().add(cB4);
		getContentPane().add(lStatus);
		getContentPane().add(lKtoryGracz);
		getContentPane().add(bStworzSerwer);
		getContentPane().add(bDolaczDoGry);
		getContentPane().add(jScrollPane);
		getContentPane().add(tPoleTekstowe);
		getContentPane().add(lStrzalkiL);
		getContentPane().add(lStrzalkiP);
		getContentPane().add(menu);
		getContentPane().add(bAutorzy);
		
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				repaint();
			}

			@Override
			public void focusLost(FocusEvent arg0) {

			}

		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				repaint();
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
		bDolaczDoGry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				klient = new Klient(stol, gracze);
				klient.uaktualnijKomende("cImie");
				// !!!!!!!!!!!!

				klient.uaktualnijNrGracza(stol.getMojNr());
				klient.uaktualnijImie((stol.getMojNr()),
						gracze[stol.getMojNr()].getImieGracza());

				try {
					klient.uruchom(gracze[stol.getMojNr()].getImieGracza());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					klient.wyslijDane();
				} catch (IOException e2) {
					e2.printStackTrace();
				}

				tPoleTekstowe.setEnabled(true);
				lStrzalki.setVisible(false);
				bDolaczDoGry.setEnabled(false);
				//rozpoczynasz();
				kladzieszIBierzesz(true);

				repaint();
			}
		});

		bTasuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.potasujKarty(karty);
				bTasuj.setForeground(Color.black);
				bTasuj.setEnabled(false);
				bRozdaj.setForeground(Color.red);
				repaint();
			}
		});

		bRozdaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.rozdajKarty(karty);
				bRozdaj.setForeground(Color.black);
				bRozdaj.setEnabled(false);
				bPoloz.setForeground(Color.red);
				repaint();
			}

		});

		bPoloz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stol.getCzyjaKolej() == stol.getMojNr()) {
					if (stol.polozKarte(karty) == true) {

						klient.uaktualnijKomende("cKladzie");
						klient.uaktualnijNrGracza(stol.getMojNr());
						klient.uakatualnijIlekart(gracze[stol.getMojNr()]
								.getIleKart());
						klient.uaktualnijKarty(karty);

						for (int i = 0; i < 6; i++) {
							for (int j = 0; j < 4; j++) {
								klient.uaktualnijPozycjeKart(i, j, karty[i][j]
										.getPozycja());
							}
						}

						try {
							klient.wyslijDane();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						repaint();
					}
				}
			}
		});

		bWezKarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stol.getCzyjaKolej() == stol.getMojNr()) {
					stol.wezKarte(karty);
					if (stol.getIleZakrytych() == 0) {
						stol.ujmijKart(karty);
					}
					repaint();
				}
			}
		});

		bStworzSerwer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lStrzalki.setVisible(false);
				bStworzSerwer.setEnabled(false);
				serwerRozpoczyna();
				repaint();
			}
		});

		tPoleTekstowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				klient.uaktualnijKomende("cPisze");
				klient.uaktualnijNapis(tPoleTekstowe.getText());
				try {
					klient.wyslijDane();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				stol.napisz();
			}
		});

		cB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.ustawMojNr(0);
				lWybierzNr.setVisible(false);
				lPodajImie.setVisible(true);
				bDolaczDoGry.setVisible(true);
				lStatus.setText("klient :)");
				lKtoryGracz.setText("gracz: 1");
				tImie.setEnabled(true);
				cB1.setEnabled(false);
				cB2.setEnabled(false);
				cB3.setEnabled(false);
				cB4.setEnabled(false);
			}
		});

		cB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.ustawMojNr(1);
				lWybierzNr.setVisible(false);
				lPodajImie.setVisible(true);
				bDolaczDoGry.setVisible(true);
				lStatus.setText("status: klient");
				lKtoryGracz.setText("gracz: 2");
				tImie.setEnabled(true);
				cB1.setEnabled(false);
				cB2.setEnabled(false);
				cB3.setEnabled(false);
				cB4.setEnabled(false);
			}
		});

		cB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.ustawMojNr(2);
				lWybierzNr.setVisible(false);
				lPodajImie.setVisible(true);
				bDolaczDoGry.setVisible(true);
				lStatus.setText("status: klient");
				lKtoryGracz.setText("gracz: 3");
				tImie.setEnabled(true);
				cB1.setEnabled(false);
				cB2.setEnabled(false);
				cB3.setEnabled(false);
				cB4.setEnabled(false);
			}
		});

		cB4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stol.ustawMojNr(3);
				lWybierzNr.setVisible(false);
				lPodajImie.setVisible(true);
				bDolaczDoGry.setVisible(true);
				lStatus.setText("status: klient");
				lKtoryGracz.setText("gracz: 4");
				tImie.setEnabled(true);
				cB1.setEnabled(false);
				cB2.setEnabled(false);
				cB3.setEnabled(false);
				cB4.setEnabled(false);
			}
		});

		tImie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gracze[stol.getMojNr()].ustawImieGracza(tImie.getText());
				lKtoryGracz.setText("gracz: " + (stol.getMojNr()) + " ("
						+ gracze[stol.getMojNr()].getImieGracza() + ")");
				lPodajImie.setVisible(false);
				lStrzalki.setVisible(true);
				tImie.setEnabled(false);

				if (stol.getMojNr() < 4) {
					bDolaczDoGry.setEnabled(true);
				}
				repaint();
			}
		});

		setVisible(true);
	}

	public void serwerRozpoczyna() {
		bTasuj.setVisible(true);
		bRozdaj.setVisible(true);
		bRozlacz.setVisible(true);
		bPoloz.setVisible(true);
		bWezKarte.setVisible(true);
	}

	public static void rozpoczynasz(Boolean x) {
		bTasuj.setVisible(x);
		bRozdaj.setVisible(x);

	}

	public static void kladzieszIBierzesz(Boolean x) {
		bPoloz.setVisible(x);
		bWezKarte.setVisible(x);
	}

	public static void uaktualnijKarty(Karta[][] x) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				karty[i][j].pozycja = x[i][j].getPozycja();
			}
		}
	}

	public static void zmienIleKartGracz(int nr, int x) {
		gracze[nr].zmienIleKart(x);
	}

	public void paint() {

	}

}