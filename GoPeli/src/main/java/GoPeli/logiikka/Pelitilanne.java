package GoPeli.logiikka;

import java.util.HashSet;

public class Pelitilanne {
    
    private Pelilauta lauta;
    private Vari pelaaja;
    private Pelitilanne edellinenTilanne;
    private Siirto siirto;
    
    public Pelitilanne(Pelilauta pelilauta, Vari pelaaja, Pelitilanne edellinenTilanne, Siirto siirto) {
        this.lauta = pelilauta;
        this.pelaaja = pelaaja;
        this.edellinenTilanne = edellinenTilanne;
        this.siirto = siirto;
    }
    
    /**
     * Siirron lis��minen pelitilanteeseen. T��ll� tapahtuu tarkastukset, ett� siirto on laillinen, sek� seuraavan
     * pelaajan v�rin valinta.
     * @return palauttaa uuden pelitilanteen, kun siirto on lis�tty.
     */
    public Pelitilanne lisaaSiirto() {
        Pelilauta seuraavaLauta = this.lauta;
        
        if (this.siirto.getKoordinaatti() != null) {
            seuraavaLauta = new Pelilauta(this.lauta);
            seuraavaLauta.lisaaSiirto(this.pelaaja, this.siirto.getKoordinaatti());
        }
        
        Vari seuraavaPelaaja = null;
        if (this.pelaaja == Vari.MUSTA) {
            seuraavaPelaaja = Vari.VALKOINEN;
        } else {
            seuraavaPelaaja = Vari.MUSTA;
        }
        
        return new Pelitilanne(seuraavaLauta, seuraavaPelaaja, this, null);
    }
    
    /**
     * Uuden pelin luonti, luo uuden pelilaudan, asettaa seuraavan pelaajan mustaksi ja edellisen tilanteen, sek�
     * siirron null:iksi.
     * @return palauttaa uuden pelitilanteen, joka toimii l�ht�tilanteena uudelle pelille.
     */
    public static Pelitilanne uusiPeli() {
        return new Pelitilanne(new Pelilauta(new HashSet<>()), Vari.MUSTA, null, null);
    }
}
