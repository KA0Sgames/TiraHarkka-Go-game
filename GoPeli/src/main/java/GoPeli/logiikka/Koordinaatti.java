package GoPeli.logiikka;

import java.util.HashSet;

/**
 * Luokka kuvaa pelilaudan koordinaattia, sis‰lt‰en sen leveys- ja korkeussuuntaiset arvot.
 */
public class Koordinaatti {
    private final byte x;
    private final byte y;
    
    /**
     * Konstruktori jolla luodaan koordinaatti antaen parametriksi sen korkeus ja leveyssuuntaiset arvot.
     * @param y koordinaatin korkeussuuntainen arvo ylh‰‰lt‰ l‰htien.
     * @param x koordinaatin leveyssuuntainen arvo vasemmalta l‰htien.
     */
    public Koordinaatti(byte y, byte x) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Metodi palauttaa koordinaatin leveyssuuntaisen arvon
     * @return koordinaatin x arvo.
     */
    public byte getXKoordinaatti() {
        return this.x;
    }
    
    /**
     * Metodi palauttaa koordinaatin korkeussuuntaisen arvon ylh‰‰lt‰ l‰htien
     * @return y arvo.
     */
    public byte getYKoordinaatti() {
        return this.y;
    }
    
    /**
     * Metodi joka palauttaa koordinaatin pysty- ja vaakasuuntaan vieress‰ olevat koordinaatit, jos ne ovat laudan sis‰ll‰.
     * @return HashSet jossa vaaka-ja pystysuuntaan viereiset koordinaatit.
     */
    public HashSet<Koordinaatti> getNaapurit() {
        HashSet<Koordinaatti> naapurit = new HashSet<>();
        
        if (this.y < 8) {
            naapurit.add(new Koordinaatti((byte) (this.y + 1), this.x));
        }
        
        if (this.y > 0) {
            naapurit.add(new Koordinaatti((byte) (this.y - 1), this.x));
        }
        
        if (this.x < 8) {
            naapurit.add(new Koordinaatti(this.y,(byte) (this.x + 1)));
        }
        
        if (this.x > 0) {
            naapurit.add(new Koordinaatti(this.y,(byte) (this.x - 1)));
        }
        
        return naapurit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Koordinaatti other = (Koordinaatti) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
}
