import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SThread extends Thread {
	private Socket socket;
	private ObjectInputStream in[];
	private ObjectOutputStream out[];
	private int nrWatku;
	SDane sDane;

	public SThread(int nrWatku, ObjectInputStream[] in,
			ObjectOutputStream[] out, SDane sD) throws IOException {
		this.nrWatku = nrWatku;
		this.in = in;
		this.out = out;
		this.sDane = sD;

	}

	public void run() {
		try {
			while (true) {
				KDane dane = (KDane) in[getNrWatku()].readObject();
				while (dane != null) {

					if ((dane.getKomenda()).equals("cRozdaje")) {
						sDane.uaktualnijKarty(dane.getKarty());
						sDane.uaktualnijCzyjaKolej(nrWatku + 1);
						sDane.uaktualnijIleP(dane.getIlePolozonych());
						sDane.uaktualnijIleZ(dane.getIleZakrytych());
						Karta[][] xx = dane.getKarty();

						Serwer.napisz("pozycje kart: ");
						for (int i = 0; i < 6; i++) {
							for (int j = 0; j < 4; j++) {
								Serwer.napiszInt(xx[i][j].getPozycja());
								Serwer.napisz("|");
							}
						}

						if (getNrWatku() == 0) {
							wyslijDane(new KDane(0, "cRozdaje", null, "", 0,
									null, 0, 0, 1, xx), 1);
						}

						// powielic dla pozostalych
						xx = null;
					}

					if ((dane.getKomenda()).equals("cKladzie")) {

						sDane.uaktualnijKarty(dane.getKarty());
						sDane.uaktualnijCzyjaKolej(nrWatku + 1);
						sDane.uaktualnijIleP(dane.getIlePolozonych());
						sDane.uaktualnijIleZ(dane.getIleZakrytych());
						// sDane.uaktualnijIleKart(dane.getIleKart());

						Serwer.napisz("//dane przesy³a gracz nr: ");
						Serwer.napiszInt(dane.getNrGracza());
						Serwer.napisz("\n");

						Serwer.napisz("posiada kart: ");
						Serwer.napiszInt(dane.getIleKart());
						Serwer.napisz("\n");

						Karta[][] k = dane.getKarty();
						Serwer.napisz("pozycje kart: ");
						for (int i = 0; i < 6; i++) {
							for (int j = 0; j < 4; j++) {
								Serwer.napiszInt(k[i][j].getPozycja());
								Serwer.napisz("|");
							}
						}
						if (getNrWatku() == 0) {
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 1);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 2);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 3);
						}
						if (getNrWatku() == 1) {
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 0);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 2);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 3);
						}

						if (getNrWatku() == 2) {
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 0);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 1);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 3);
							
						}
						
						if (getNrWatku() == 3){
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 0);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 1);
							wyslijDane(new KDane(getNrWatku(), "cKladzie",
									null, "", dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), k), 2);	
							
						}

						// powielic dla pozostalych

						Serwer.napisz("\n");

						Serwer.napisz("polozonych: ");
						Serwer.napiszInt(sDane.getIlePolozonych());
						Serwer.napisz("\n");

						Serwer.napisz("zakrytych: ");
						Serwer.napiszInt(sDane.getIleZakrytych());
						Serwer.napisz("\n");

						Serwer.napisz("kolej na: ");
						Serwer.napiszInt(dane.getCzyjaKolej());
						Serwer.napisz("\n");

						Serwer.napisz("----------------");
						Serwer.napisz("\n");

						k = null;
					}

					if ((dane.getKomenda()).equals("cImie")) {
						Serwer.ustawNapisGracza(dane.getNrGracza(), dane
								.getImie(getNrWatku()));
						Serwer.napisz("!!wszed³ klient ("
								+ dane.getImie(getNrWatku()) + ")\n\n");
						sDane.uaktualnijKarty(dane.getKarty());
						sDane.uaktualnijImie(nrWatku, dane
								.getImie(getNrWatku()));

						if (getNrWatku() == 0) {
							wyslijDane(new KDane(getNrWatku(), "cImie", sDane
									.getImie(), "", dane.getIleKart(), null,
									dane.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 1);
						}

						if (getNrWatku() == 1) {

							wyslijDane(new KDane(getNrWatku(), "cImie", sDane
									.getImie(), "", dane.getIleKart(), null,
									dane.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 0);
						}

						// powielic dla pozostalych
					}

					if ((dane.getKomenda()).equals("cPisze")) {
						Serwer.napisz(dane.getImie(dane.getNrGracza()) + ">> "
								+ dane.getNapis() + "\n");

						if (getNrWatku() == 0) {
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 1);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 2);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 3);
						}
						if (getNrWatku() == 1) {
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 0);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 2);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 3);
						}

						if (getNrWatku() == 2) {
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 0);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 1);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 3);
							
						}
						
						if (getNrWatku() == 3){
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 0);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 1);
							wyslijDane(new KDane(getNrWatku(), "cCzat",
									null, dane.getNapis(), dane.getIleKart(), null, dane
											.getIlePolozonych(), dane
											.getIleZakrytych(), dane
											.getCzyjaKolej(), null), 2);	
							
						}
						// powielic dla pozostalych
					}
					dane = null;
				}
				sleep(100);
			}
		} catch (Exception e) {
		} finally {

			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				Serwer.napisz("jakis klient wyszedl");
			}

		}
	}

	public void wyslijDane(KDane x, int nr) throws IOException {
		out[nr].writeObject(x);
		out[nr].flush();
	}

	public int getNrWatku() {
		return nrWatku;
	}
}