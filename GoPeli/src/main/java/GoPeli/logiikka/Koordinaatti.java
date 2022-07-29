package GoPeli.logiikka;

/**
 * Luokka kuvaa pelilaudan koordinaattia, sisältäen sen leveys- ja korkeussuuntaiset arvot
 */
public class Koordinaatti {
    private final byte x;
    private final byte y;
    
    public Koordinaatti(byte x, byte y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Metodi palauttaa koordinaatin leveyssuuntaisen arvon
     * 
     * @return koordinaatin x arvo
     */
    public byte getXKoordinaatti() {
        return this.x;
    }
    
    /**
     * Metodi palauttaa koordinaatin korkeussuuntaisen arvon
     * 
     * @return koordinaatin y arvo
     */
    public byte getYKoordinaatti() {
        return this.y;
    }
}
