package GoPeli.logiikka;

/**
 * Luokka pelin hallinnointiin, joka pit‰‰ kirjaa t‰m‰nhetkisest‰ pelitilanteesta, siit‰ kumman vuoro on, sek‰ molempien pelaajien kaapatuista vangeista.
 * Mahdollistaa siirtojen lis‰‰misen peliin, sek‰ tarkastamiseen onko tiettyyn pelin tietyss‰ koordinaatissa jo kivi.
 */
public class PelinHallinnointi {
    
    private byte[][] peli;
    private boolean mustanVuoro;
    private int mustanVangit;
    private int valkoisenVangit;
    private final SiirronLaillisuudenTarkastaja tarkastaja;
    
    /**
     * Konstruktori, joka luo peli-taulukon t‰ynn‰ tyhj‰‰ leikkauspistett‰ kuvaavia nollia, asettaa mustan vuoron, m‰‰ritt‰‰ mustan ja valkoisen vangit alussa
     * nollaan ja luo siirron laillisuuden tarkastukseen olion.
     */
    public PelinHallinnointi() {
        this.peli = new byte[9][9];
        this.mustanVuoro = true;
        this.mustanVangit = 0;
        this.valkoisenVangit = 0;
        this.tarkastaja = new SiirronLaillisuudenTarkastaja();
    }
    
    /**
     * Getteri jolta saa t‰m‰nhetkist‰ lautatilannetta vastaavan taulukon.
     * @return byte taulukko, joka kuvaa pelin tilannetta t‰ll‰ hetkell‰.
     */
    public byte[][] getPeli() {
        return this.peli;
    }
    
    /**
     * Metodi jolla saa t‰m‰nhetkisen lautatilanteen koordinaatin arvon selville.
     * @param koordinaatti, koordinaatti-olio joka sis‰lt‰‰ halutun laudan pisteen korkeus- ja leveyssuuntaiset koordinaatit.
     * @return palauttaa kysytyn pisteen tilan, 0 jos pisteeseen ei ole pelattu, 1 jos pisteess‰ on mustan kivi ja 2 jos pisteess‰ on valkean kivi.
     */
    public byte getKoordinaatinTila(Koordinaatti koordinaatti) {
        return this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()];
    }
    
    /**
     * Getteri joka kertoo kumman pelaajan vuoro peliss‰ on seuraavaksi.
     * @return palauttaa true jos on mustan vuoro ja false jos on valkoisen vuoro.
     */
    public boolean getMustanVuoro() {
        return this.mustanVuoro;
    }
    
    /**
     * Getteri mustan jo vangitsemien kivien m‰‰r‰‰ varten.
     * @return mustan jo vangitsemien kivien m‰‰r‰ int muodossa.
     */
    public int getMustanVangit() {
        return this.mustanVangit;
    }
    
    /**
     * Getteri valkoisen jo vangitsemien kivien m‰‰r‰‰ varten.
     * @return valkoisen jo vangitsemien kivien m‰‰r‰ int muodossa.
     */
    public int getValkoisenVangit() {
        return this.valkoisenVangit;
    }
    
    /**
     * Palauttaa pelinhallinnoijan k‰ytt‰m‰n siirron laillisuuden tarkistamiseen k‰ytett‰v‰n olion.
     * @return SiirronLaillisuudenTarkastaja-tyyppinen olio, jota pelin hallinnointi k‰ytt‰‰.
     */
    public SiirronLaillisuudenTarkastaja getSiirronLaillisuudenTarkastaja() {
        return this.tarkastaja;
    }
    
    /**
     * Metodi joka yritt‰‰ lis‰t‰ uuden siirron peliin, sille pelaajalle kumman vuoro on.
     * @param koordinaatti johon seuraava siirto halutaan Koordinaatti-tyyppisen‰ oliona joka sis‰lt‰‰ halutun pisteen korkeus- ja leveyssuuntaiset arvot.
     * @return palauttaa merkkijonon, joka kertoo, onnistuiko siirron lis‰‰minen. "Onnistui" jos siirto lis‰ttiin onnistuneesti, muussa tapauksessa palautetaan
     * SiirronLaillisuudenTarkastajan palauttama arvo.
     */
    public String lisaaSiirto(Koordinaatti koordinaatti) {
        String onkoLaillinen = tarkastaja.onkoLaillinen(this.peli, mustanVuoro, koordinaatti);
        
        if (onkoLaillinen.equals("On")) {
            if (this.mustanVuoro) {
                this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = 1;
                this.mustanVuoro = !this.mustanVuoro;
                return "Onnistui";
            } else {
                this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = 2;
                this.mustanVuoro = !this.mustanVuoro;
                return "Onnistui";
            }
        } else {
            return onkoLaillinen;
        }
    }
}