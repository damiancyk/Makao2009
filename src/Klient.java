import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Klient {

	private Stol stol;
	private Gracz[] gracze;
	private InetAddress addr;
	private Socket socketClient;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private KThread kThread;
	private KDane dane;

	// przesy³ane:://
	private int nrGracza;
	private int pozycjeKart[][] = new int[6][4];
	private String komenda = "";
	private String imiona[] = new String[4];
	private String napis = "zg³asza siê nowy klient!";
	int ileKart = 0;
	private Karta[][] karty;

	public Klient(Stol stol, Gracz[] gracze) {
		this.stol = stol;
		this.gracze=gracze;
	}

	public void uruchom(String nazwaKlienta) throws IOException {
		addr = InetAddress.getByName("localhost");
		socketClient = new Socket(addr, Serwer.PORT);
		try {
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			out.writeObject(dane);
			out.flush();
		} catch (IOException e) {
			socketClient.close();
		}
		kThread = new KThread(in, out, stol, gracze);
		kThread.setDaemon(true);
		kThread.start();
		System.out.println("uruchomiono klienta");
	}

	public void uaktualnijNrGracza(int x) {
		nrGracza = x;
	}

	public void uaktualnijKomende(String x) {
		komenda = x;
	}

	public void uaktualnijNapis(String x) {
		napis = x;
	}

	public void uaktualnijImie(int nr, String x) {
		imiona[nr] = x;
	}

	public void uakatualnijIlekart(int x) {
		ileKart = x;
	}

	public void uaktualnijKarty(Karta[][] x) {
		karty = x;
	}

	public void uaktualnijPozycjeKart(int i, int j, int w) {
		pozycjeKart[i][j] = w;
	}

	public void wyslijDane() throws IOException {
		out.writeObject(new KDane(nrGracza, komenda, imiona, napis, ileKart,
				pozycjeKart, stol.getIlePolozonych(), stol.getIleZakrytych(),
				stol.getCzyjaKolej(), karty));
		out.flush();
	}

	public Gracz[] getGracze() {
		return gracze;
	}

}