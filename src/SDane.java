public class SDane {

	private int nrGracza;
	private String komenda;
	private String imiona[];
	private String napis;
	private int ileKart;
	private int ilePolozonych;
	private int ileZakrytych;
	private int czyjaKolej;
	private Karta[][] karty;

	public SDane() {
		nrGracza = 0;
		komenda = "";
		imiona = new String[4];
		napis = "__";
		ileKart = 0;
		ilePolozonych = 0;
		ileZakrytych = 0;
		czyjaKolej = 10;
		karty = new Karta[6][4];
	}

	public int getNrGracza() {
		return nrGracza;
	}

	public void uaktualnijNrGracz(int x) {
		nrGracza = x;
	}

	public String getKomenda() {
		return komenda;
	}

	public void uaktualnijKomende(String cmd) {
		komenda = cmd;
	}

	public String[] getImie() {
		return imiona;
	}

	public void uaktualnijImie(int nr, String x) {
		imiona[nr] = x;
	}

	public String getNapis() {
		return napis;
	}

	public void uaktualnijNapis(String x) {
		napis = x;
	}

	public int getIleKart() {
		return ileKart;
	}

	public void uaktualnijIleKart(int x) {
		ileKart = x;
	}

	public int getIlePolozonych() {
		return ilePolozonych;
	}

	public void uaktualnijIleP(int x) {
		ilePolozonych = x;
	}

	public int getIleZakrytych() {
		return ileZakrytych;
	}

	public void uaktualnijIleZ(int x) {
		ileZakrytych = x;
	}

	public int getCzyjaKolej() {
		return czyjaKolej;
	}

	public void uaktualnijCzyjaKolej(int x) {
		czyjaKolej = x;
	}

	public Karta[][] getKarty() {
		return karty;
	}

	public void uaktualnijKarty(Karta[][] x) {
		karty = x;
	}

	public String toString() {
		return "zwraca to";
	}

}