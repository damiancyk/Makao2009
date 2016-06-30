import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class KThread extends Thread {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Stol stol;
	Karta[][] karty;
	Start start;
	Gracz gracze[];

	public KThread(ObjectInputStream in, ObjectOutputStream out, Stol stol,
			Gracz gr[]) {
		this.in = in;
		this.out = out;
		this.stol = stol;
		this.gracze = gr;
	}

	public void run() {
		try {
			while (true) {
				KDane dane = (KDane) in.readObject();
				while (dane != null) {

					if ((dane.getKomenda()).equals("cPotasowalem")) {
						// Start.uaktualnijKarty(dane.getKarty());
						stol.dopiszZdarzenie("@@serv>> " + dane.getNapis());
						stol.dodajLinie();
						stol.refreshAll();
					}

					if ((dane.getKomenda()).equals("cRozpoczynasz")) {
						Start.rozpoczynasz(true);
						stol.refreshAll();
					}

					if ((dane.getKomenda()).equals("cPisze")) {
						stol.dopiszZdarzenie("@@serv>> " + dane.getNapis());
						stol.dodajLinie();
					}

					if ((dane.getKomenda()).equals("cCzat")) {
						 stol.dopiszZdarzenie(dane.getImie(dane.getNrGracza())+">> " +
						 dane.getNapis());
						stol.dodajLinie();
					}

					if ((dane.getKomenda()).equals("cImie")) {
						gracze[dane.getNrGracza()].ustawImieGracza(dane
								.getImie(dane.getNrGracza()));
					}

					if ((dane.getKomenda()).equals("cKladzie")) {
						
						//stol.dopiszZdarzenie(">>"+dane.getImie(dane.getNrGracza())+" polozyl karte");
						Start.uaktualnijKarty(dane.getKarty());
						stol.zmienCzyjaKolej(dane.getCzyjaKolej());
						stol.zmienIlePolozonych(dane.getIlePolozonych());
						stol.zmienIleZakrytych(dane.getIleZakrytych());
						Start.zmienIleKartGracz(dane.getNrGracza(), dane
								.getIleKart());
						if(dane.getIleKart()==0){
							//wygrana
							stol.dopiszZdarzenie("wygral gracz nr: "+dane.getNrGracza());
							Stol.setWygrDla(dane.getNrGracza());
						}
						stol.refreshAll();
					}

					dane = null;
				}
				sleep(100);
			}
		} catch (Exception e) {
		}
	}

}
