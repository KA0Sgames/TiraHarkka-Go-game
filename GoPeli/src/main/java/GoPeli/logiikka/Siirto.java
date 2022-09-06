package GoPeli.logiikka;

import java.util.Objects;

/**
 * Luokka jonka ilmentym‰t kuvaavat yksitt‰ist‰ siirtoa.
 * T‰ss‰ siirrolla tarkoitetaan joko pelilaudalle pelattavan kiven lis‰‰mist‰, passausta tai luovutusta.
 */
public class Siirto {
    private Koordinaatti koordinaatti;
    private boolean passaus;
    private boolean luovutus;
    
    /**
     * Konstruktori jolla luodaan siirtoa kuvaava olio.
     * @param koordinaatti jos siirto on kiven lis‰‰minen pelilaudalle, niin haluttu piste annetaan Koordinaatti oliona, muissa siirtotyypeiss‰ annetaan null-arvo.
     * @param passaus jos siirto on passaus, niin annetaan parametrina true-arvo, muussa tilanteessa false.
     * @param luovutus jos siirto on luovutus, niin annetaan parametrina true-arvo, muussa tilanteessa false.
     */
    public Siirto(Koordinaatti koordinaatti, boolean passaus, boolean luovutus) {
        this.koordinaatti = koordinaatti;
        this.passaus = passaus;
        this.luovutus = luovutus;
    }
    
    /**
     * Luokkametodi jolla saa luotua Siirto-olion, joka kuvaa kiven pelaamista haluttuun pisteeseen.
     * Passaus ja luovutus saavat arvot false.
     * @param koordinaatti parametrina annetaan pisteen koordinaatti Koordinaatti-oliona, johon halutaan lis‰t‰ siirto.
     * @return Siirto - palauttaa uuden Siirto-olion, joka tiet‰‰ mihin koordinaattiin halutaan kivi pelata ja jonka passaus- ja luovutusarvot ovat false.
     */
    public static Siirto pelaa(Koordinaatti koordinaatti) {
        return new Siirto(koordinaatti, false, false);
    }
    
    /**
     * Luokkametodi jolla saa luotua Siirto-olion, joka kuvaa passaamista.
     * Siirron koordinaatti saa arvon null ja luovutusta kuvaava arvo false.
     * @return Siirto - palauttaa uuden Siirto-olion, jonka passausarvo on true, siirron koordinaatti on null ja luovutusarvo on false.
     */
    public static Siirto passaus() {
        return new Siirto(null, true, false);
    }
    
    /**
     * Luokkametodi jolla saa luotua Siirto-olion, joka kuvaa luovutusta.
     * Siirron koordinaatti saa arvon null ja passausta kuvaava arvo false.
     * @return Siirto - palauttaa uuden Siirto-olion, jonka luovutusarvo on true, siirron koordinaatti on null ja passausarvo on false.
     */
    public static Siirto luovutus() {
        return new Siirto(null, false, true);
    }
    
    /**
     * Metodi jolla saadaan selville koordinaatti, johon pelattava siirto kohdistuu.
     * Huom. jos siirto on passaus tai luovutus, niin palautetaan null-arvo.
     * @return Koordinaatti/null - kuvaus pisteest‰ mihin halutaan lis‰t‰ kivi Koordinaatti-oliona, tai null jos siirto on passaus tai luovutus.
     */
    public Koordinaatti getKoordinaatti() {
        return this.koordinaatti;
    }
    
    /**
     * Metodi jolla saadaan selville, oliko siirto passaus.
     * @return true/false - true jos siirto oli passaus, muuten false.
     */
    public boolean getPassaus() {
        return this.passaus;
    }
    
    /**
     * Metodi jolla saadaan selville, oliko siirto luovutus.
     * @return true/false - true jos siirto oli luovutus, muuten false.
     */
    public boolean getLuovutus() {
        return this.luovutus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.koordinaatti);
        hash = 47 * hash + (this.passaus ? 1 : 0);
        hash = 47 * hash + (this.luovutus ? 1 : 0);
        return hash;
    }

    /**
     * Equals jolla verrataan vastaavatko kaksi oliota toisiaan.
     * Palauttaa true jos verrataan oliota itseens‰.
     * Palauttaa false jos verrataan Siirto-oliota null arvoon tai toisesta luokasta konstruoituun olioon.
     * Palauttaa true jos verrattavilla Siirto-olioilla on samat passaus- ja luovutusarvot, sek‰ toisiaan vastaavat siirtoa kuvaavat koordinaatit.
     * @param obj parametrina annetaan olio, johon haluttua Siirto-oliota verrataan.
     * @return true/false - palauttaa true, mik‰li oliota verrataan itseens‰ tai verrattavilla Siirto-olioilla on samat passaus- ja luovutusarvot, sek‰ toisiaan vastaavat
     * koordinaatit kuvaamassa laudalle pelattavaa siirtoa.
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
        final Siirto other = (Siirto) obj;
        if (this.passaus != other.passaus) {
            return false;
        }
        if (this.luovutus != other.luovutus) {
            return false;
        }
        return Objects.equals(this.koordinaatti, other.koordinaatti);
    }

}
