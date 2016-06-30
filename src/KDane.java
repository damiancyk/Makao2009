import java.io.Serializable;

public class KDane implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int nrGracza;
	private String komenda;
	private String imiona[];
	private String napis;
	private int ileKart;
	private int pozycjeKart[][];
	private int ilePolozonych;
	private int ileZakrytych;
	private int czyjaKolej;
	private Karta[][] karty;

	public KDane(int nrG, String cmd, String name[], String napis, int x,
			int posK[][], int ilePol, int ileZ, int cK, Karta[][] k) {
		this.nrGracza = nrG;
		this.komenda = cmd;
		this.imiona = name;
		this.napis = napis;
		this.ileKart = x;
		this.pozycjeKart = posK;
		this.ilePolozonych = ilePol;
		this.ileZakrytych = ileZ;
		this.czyjaKolej = cK;
		this.karty = k;
	}

	public int getNrGracza() {
		return nrGracza;
	}

	public String getKomenda() {
		return komenda;
	}

	public String getImie(int nr) {
		return imiona[nr];
	}

	public String getNapis() {
		return napis;
	}

	public int getIleKart() {
		return ileKart;
	}

	public int[][] getPozycjeKart() {
		return pozycjeKart;
	}

	public int getIlePolozonych() {
		return ilePolozonych;
	}

	public int getIleZakrytych() {
		return ileZakrytych;
	}

	public int getCzyjaKolej() {
		return czyjaKolej;
	}

	public Karta[][] getKarty() {
		return karty;
	}

	public String toString() {
		return "zwraca to";
	}

}

// wygranaDla , !!potasowane, !!rozdane <<-wszystko jednak na serwie bedzie
// sprawdzane, tasowane nowa gra i tym podobne. ++klasa SDane zapisujaca stan
// gry i aktualizujaca sie z pól sThread(lub static SThread)

// zrobione:://
// karty[i][j].pozycja bedzie przesylana (u kart tylko to)
// z klasy stó³ bedzie przesylane: ileZakrytych, ilePolozonych, czyjaKolej,
// z klasy gracz:: mojNr(nrGracza), imieGracza, ileKart
