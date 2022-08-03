package GoPeli.logiikka;

/**
 * Luokka jonka ilmentym‰‰ voidaan k‰ytt‰‰ tarkastamaan onko haluttu siirto laillinen halutussa pelitilanteessa.
 */
public class SiirronLaillisuudenTarkastaja {
    
    public SiirronLaillisuudenTarkastaja() {
        
    }
    
    /**
     * Metodi jolla tarkistetaan siirtojen laillisuus.
     * @param peli haluttu pelitilanne, jossa siirron laillisuus tarkastetaan byte taulukkona.
     * @param mustanVuoro kertoo kumman pelaajan vuoro halutulla siirrolla on, true jos on mustan vuoro ja false jos on valkoisen vuoro.
     * @param koordinaatti haluttu siirto Koordinaatti-oliona, joka kertoo pelattavan pisteen korkeus- ja leveyssuuntaiset arvot.
     * @return String merkkijono joka kertoo onko haluttu siirto laillinen, merkkijono "On", jos siirto on laillinen, "On jo kivi" mik‰li
     * halutussa paikassa on jo jommankumman pelaajan kivi.
     */
    public String onkoLaillinen(byte[][] peli, boolean mustanVuoro, Koordinaatti koordinaatti) {
        if (onkoJoKivi(peli, koordinaatti)) {
            return "On jo kivi";
        }
        
        return "On";
    }
    
    private boolean onkoJoKivi(byte[][] peli, Koordinaatti koordinaatti){
        return peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] != 0;
    }
}
