package GoPeli.logiikka;

/**
 * Luokka kuvaa pelilaudan koordinaattia, sis‰lt‰en sen leveys- ja korkeussuuntaiset arvot
 */
public class Koordinaatti {
    private final byte x;
    private final byte y;
    private Vari vari;
    
    /**
     * Konstruktori jolla luodaan koordinaatti-olio kolmella parametrilla
     * @param y koordinaatin korkeussuuntainen arvo ylh‰‰lt‰ l‰htien
     * @param x koordinaatin leveyssuuntainen arvo vasemmalta l‰htien
     * @param vari koordinaatin v‰ri, eli onko piste tyhj‰, vai onko siin‰ musta tai valkea kivi
     */
    public Koordinaatti(byte y, byte x, Vari vari) {
        this.x = x;
        this.y = y;
        this.vari = vari;
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
    
    public Vari getVari() {
        return this.vari;
    }
}
