import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Serwer {
	static final int PORT = 1234;
	private static JTextArea tObszarTekstowy;
	private static JScrollPane jScrollPane;
	private static JTextField tPoleTekstowe;
	private static JButton bStart;
	private static JLabel lStanSerwera;
	private static JLabel lKlienci[];
	private static JLabel lSerwer;
	private static Socket socketKlient[];
	private static SThread sThread[];
	private static JButton bRozpocznijGre;
	private static SDane sDane;
	private static int sWidth = 500;
	private static int sHeight = 500;
	private static Tlo tlo;
	private static ServerSocket socketServera;
	private static ObjectInputStream in[];
	private static ObjectOutputStream out[];

	public static void uruchom() throws IOException {
		socketServera = new ServerSocket(PORT);
		try {
			sThread = new SThread[4];
			socketKlient = new Socket[4];
			in= new ObjectInputStream[4];
			out=new ObjectOutputStream[4];

			socketKlient[0] = socketServera.accept();
			socketKlient[1] = socketServera.accept();
			socketKlient[2] = socketServera.accept();
			socketKlient[3] = socketServera.accept();
			
			in[0] = new ObjectInputStream(socketKlient[0].getInputStream());
			out[0] = new ObjectOutputStream(socketKlient[0].getOutputStream());
			
			in[1] = new ObjectInputStream(socketKlient[1].getInputStream());
			out[1] = new ObjectOutputStream(socketKlient[1].getOutputStream());
			
			in[2] = new ObjectInputStream(socketKlient[2].getInputStream());
			out[2] = new ObjectOutputStream(socketKlient[2].getOutputStream());
			
			in[3] = new ObjectInputStream(socketKlient[3].getInputStream());
			out[3] = new ObjectOutputStream(socketKlient[3].getOutputStream());
			
			sThread[0] = new SThread(0, in,out, sDane);
			sThread[0].setDaemon(true);
			sThread[0].start();
			
			sThread[1] = new SThread(1, in, out, sDane);
			sThread[1].setDaemon(true);
			sThread[1].start();
			
			sThread[2] = new SThread(2, in, out, sDane);
			sThread[2].setDaemon(true);
			sThread[2].start();
			
			sThread[3] = new SThread(3, in, out, sDane);
			sThread[3].setDaemon(true);
			sThread[3].start();
			
			System.out.println("uruchomiono serwer\n");
			tObszarTekstowy.append("\nw³¹czono serwer\n\n");
			moznaPisac(true);
			moznaRozpoczynac(true);
		} finally {
		}
	}

	public static void main(String[] args) throws IOException {
		sDane = new SDane();
		tlo = new Tlo(0, 0, Serwer.getWidth(), Serwer.getHeight(),
				"tlo_serwera.jpg", "");
		JFrame fSerwer = new JFrame("Makao serwer");
		tObszarTekstowy = new JTextArea(8, 2);
		tPoleTekstowe = new JTextField();
		jScrollPane = new JScrollPane(tObszarTekstowy);
		bStart = new JButton("start");
		lStanSerwera = new JLabel("");
		lSerwer = new JLabel("--==Serwer Makao 2009==--");
		lKlienci = new JLabel[4];
		bRozpocznijGre = new JButton("rozpocznij grê");

		fSerwer.setBackground(new Color(191, 191, 191));
		fSerwer.setSize(Serwer.getWidth(), Serwer.getHeight());
		fSerwer.setLayout(null);

		for (int i = 0; i < 4; i++) {
			lKlienci[i] = new JLabel("klient nr " + i + " :");
			lKlienci[i].setForeground(new Color(1, 1, 1));
			lKlienci[i].setFont(new Font("SansSerif", Font.ITALIC, 13));
		}
		tObszarTekstowy.setBackground(new Color(1, 1, 1));
		tObszarTekstowy.setForeground(new Color(1, 150, 1));
		tObszarTekstowy.setBorder(BorderFactory
				.createLineBorder(Color.black, 1));
		tObszarTekstowy.setEditable(false);
		tObszarTekstowy.setLineWrap(true);
		tObszarTekstowy.setFont(new Font("Monospaced", Font.ITALIC, 11));
		tObszarTekstowy.setAutoscrolls(true);
		tObszarTekstowy.append("@@serv>> czekam na po³¹czenia..");
		lSerwer.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		lStanSerwera.setVisible(true);
		tPoleTekstowe.setBackground(new Color(191, 191, 191));
		tPoleTekstowe.setForeground(new Color(190, 10, 11));
		tPoleTekstowe.setEnabled(false);
		bRozpocznijGre.setBackground(new Color(111, 111, 111));
		bRozpocznijGre.setForeground(Color.red);
		bRozpocznijGre.setEnabled(false);

		bStart.setBounds(20, 50, 100, 20);
		jScrollPane.setBounds(10, 180, 300, 250);
		lKlienci[0].setBounds(10, 80, 200, 20);
		lKlienci[1].setBounds(10, 100, 200, 20);
		lKlienci[2].setBounds(10, 120, 200, 20);
		lKlienci[3].setBounds(10, 140, 200, 20);
		tPoleTekstowe.setBounds(10, 430, 300, 20);
		lStanSerwera.setBounds(20, 220, 120, 20);
		lSerwer.setBounds(100, 10, 340, 20);
		bRozpocznijGre.setBounds(335, 390, 150, 40);
		tlo.setBounds(tlo.getBoundsX(), tlo.getBoundsY(), tlo.getBoundsW(), tlo
				.getBoundsH());

		fSerwer.add(bStart);
		fSerwer.add(lStanSerwera);
		fSerwer.add(lSerwer);
		fSerwer.add(jScrollPane);
		fSerwer.add(tPoleTekstowe);
		fSerwer.add(lKlienci[0]);
		fSerwer.add(lKlienci[1]);
		fSerwer.add(lKlienci[2]);
		fSerwer.add(lKlienci[3]);
		fSerwer.add(bRozpocznijGre);
		fSerwer.add(tlo);

		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);

				try {
					uruchom();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		bRozpocznijGre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bRozpocznijGre.setEnabled(false);

				try {
					sThread[0].wyslijDane(new KDane(10, "cRozpoczynasz", sDane
							.getImie(), tPoleTekstowe.getText(),0, null,0,
							0, 0, null), 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		});

		tPoleTekstowe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				napisz("@@serv>> " + tPoleTekstowe.getText() + "\n");
				try {
					sThread[0].wyslijDane(new KDane(10, "cPisze", sDane
							.getImie(), tPoleTekstowe.getText(),0, null,0,
							0, 0, null), 0);
					sThread[1].wyslijDane(new KDane(10, "cPisze", sDane
							.getImie(), tPoleTekstowe.getText(),0, null,0,
							0, 0, null), 1);
					sThread[2].wyslijDane(new KDane(10, "cPisze", sDane
							.getImie(), tPoleTekstowe.getText(),0, null,0,
							0, 0, null), 2);
					sThread[3].wyslijDane(new KDane(10, "cPisze", sDane
							.getImie(), tPoleTekstowe.getText(),0, null,0,
							0, 0, null), 3);
					
					//powieliæ
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tPoleTekstowe.setText("");
			}
		});
		fSerwer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fSerwer.setVisible(true);
	}

	public static void moznaPisac(Boolean x) {
		tPoleTekstowe.setEnabled(x);
	}
	
	public static void moznaRozpoczynac(Boolean x){
		bRozpocznijGre.setEnabled(x);
	}

	public static void ustawNapisGracza(int nr, String x) {
		lKlienci[nr].setText("klient nr " + nr + ": " + x);
	}

	public static void napisz(String x) {
		tObszarTekstowy.append(x);
		jScrollPane.getVerticalScrollBar().setValue(
				jScrollPane.getVerticalScrollBar().getMaximum());
	}

	public static void napiszInt(int x) {
		tObszarTekstowy.append(Integer.toString(x));
		jScrollPane.getVerticalScrollBar().setValue(
				jScrollPane.getVerticalScrollBar().getMaximum());
	}

	public static Karta[][] tasowanie(Karta karty[][]) {
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
		return karty;
		
	}

	public static int getWidth() {
		return sWidth;
	}

	public static int getHeight() {
		return sHeight;
	}
}
