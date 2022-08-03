package GoPeli.logiikka;

/**
 * Luokka kuvaa pelilaudan koordinaattia, sis‰lt‰en sen leveys- ja korkeussuuntaiset arvot
 */
public class Koordinaatti {
    private final byte x;
    private final byte y;
    
    /**
     * Konstruktori jolla luodaan koordinaatti-olio kahdella parametrilla
     * @param y koordinaatin korkeussuuntainen arvo ylh‰‰lt‰ l‰htien
     * @param x koordinaatin leveyssuuntainen arvo vasemmalta l‰htien
     */
    public Koordinaatti(byte y, byte x) {
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
     * Metodi palauttaa koordinaatin korkeussuuntaisen arvon ylh‰‰lt‰ l‰htien
     * 
     * @return koordinaatin y arvo
     */
    public byte getYKoordinaatti() {
        return this.y;
    }
}
