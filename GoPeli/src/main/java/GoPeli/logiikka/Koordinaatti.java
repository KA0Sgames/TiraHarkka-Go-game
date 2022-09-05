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
     * Kopiokonstruktori, jolla luodaan uusi koordinaatti, jolla alkuper‰ist‰ vastaavat arvot.
     * @param koordinaatti parametrina kopioitava koordinaatti.
     */
    public Koordinaatti(Koordinaatti koordinaatti) {
        this.x = koordinaatti.x;
        this.y = koordinaatti.y;
    }
    
    /**
     * Metodi palauttaa koordinaatin leveyssuuntaisen arvon vasemmalta l‰htien.
     * @return x - koordinaatin leveyssuuntainen arvo. Arvov‰li 0-8.
     */
    public byte getXKoordinaatti() {
        return this.x;
    }
    
    /**
     * Metodi palauttaa koordinaatin korkeussuuntaisen arvon ylh‰‰lt‰ l‰htien.
     * @return y - koordinaatin korkeussuuntainen arvo. Arvov‰li 0-8.
     */
    public byte getYKoordinaatti() {
        return this.y;
    }
    
    /**
     * Metodi joka palauttaa koordinaatin pysty- ja vaakasuuntaan vieress‰ olevat koordinaatit, jos ne ovat laudan sis‰ll‰.
     * @return HashSet - vaaka- ja pystysuuntaan viereiset koordinaatit, jotka ovat laudan sis‰puolella.
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

    /**
     * Equals jolla verrataan kahta oliota, ovatko ne samat.
     * Palauttaa true, jos oliota verrataan siihen itseens‰.
     * Palauttaa false, jos oliota verrataan null arvoon tai eli luokasta konstruoituun olioon.
     * Muuten palauttaa true, jos verrattavien Koordinaatti-olioiden y, sek‰ x arvot ovat samat.
     * @param obj parametrina annetaan olio, johon verrataan haluttua Koordinaatti-oliota.
     * @return true/false - jos verrattavat oliot ovat samat, tai niill‰ on samat y ja x arvot, niin true, muuten false.
     */
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
