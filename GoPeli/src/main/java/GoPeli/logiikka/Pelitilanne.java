package GoPeli.logiikka;

import java.util.HashSet;

public class Pelitilanne {
    
    private Pelilauta lauta;
    private Vari pelaaja;
    private Pelitilanne edellinenTilanne;
    private Siirto siirto;
    private Siirto edellinenSiirto;
    private String laitonSiirto;
    
    public Pelitilanne(Pelilauta pelilauta, Vari pelaaja, Pelitilanne edellinenTilanne, Siirto siirto, Siirto edellinen, String laitonSiirto) {
        this.lauta = pelilauta;
        this.pelaaja = pelaaja;
        this.edellinenTilanne = edellinenTilanne;
        this.siirto = siirto;
        this.edellinenSiirto = edellinen;
        this.laitonSiirto = laitonSiirto;
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
            String onkoJoKivi = seuraavaLauta.lisaaSiirto(this.pelaaja, this.siirto.getKoordinaatti());
            
            String eiVapauksia = null;
            if (onkoItseatari(seuraavaLauta, this.siirto.getKoordinaatti())) {
                eiVapauksia = "Siirron j�lkeen ei ole vapauksia";
            }
                
            String koSaanto = null;
            if (rikkookoSiirtoKoSaantoa(seuraavaLauta)) {
                koSaanto = "Siirto rikkoo Ko-s��nt��";
            }
            
            if (onkoJoKivi.equals("Onnistui") && eiVapauksia == null && koSaanto == null) {
                Vari seuraavaPelaaja = seuraavaPelaaja();
                return new Pelitilanne(seuraavaLauta, seuraavaPelaaja, this, null, this.siirto, null);
            } else {
                String virhe = null;
                if (onkoJoKivi.equals("On jo kivi")) {
                    virhe = "On jo kivi";
                } else if (eiVapauksia != null) {
                    virhe = eiVapauksia;
                } else {
                    virhe = koSaanto;
                }
                return new Pelitilanne(this.lauta, this.pelaaja, this.edellinenTilanne, null, this.edellinenSiirto, virhe);
            }
        }
        
        Vari seuraavaPelaaja = seuraavaPelaaja();
        
        return new Pelitilanne(seuraavaLauta, seuraavaPelaaja, this, null, this.siirto, null);
    }
    
    /**
     * Uuden pelin luonti, luo uuden pelilaudan, asettaa seuraavan pelaajan mustaksi ja edellisen tilanteen, sek�
     * siirron null:iksi.
     * @return palauttaa uuden pelitilanteen, joka toimii l�ht�tilanteena uudelle pelille.
     */
    public static Pelitilanne uusiPeli() {
        return new Pelitilanne(new Pelilauta(new HashSet<>()), Vari.MUSTA, null, null, null, null);
    }
    
    public Pelilauta getPelilauta() {
        return this.lauta;
    }
    
    public Vari getPelaaja() {
        return this.pelaaja;
    }
    
    public Pelitilanne getEdellinenTilanne() {
        return this.edellinenTilanne;
    }
    
    public Siirto getSiirto() {
        return this.siirto;
    }
    
    public Siirto getEdellinenSiirto() {
        return this.edellinenSiirto;
    }
    
    public String getLaitonSiirto() {
        return this.laitonSiirto;
    }
    
    public boolean peliOhi() {
        if (this.edellinenSiirto == null) {
            return false;
        }
        
        if (this.edellinenSiirto.getLuovutus()) {
            return true;
        }
        
        Siirto toiseksiViimeinen = this.edellinenTilanne.getEdellinenSiirto();
        
        if (toiseksiViimeinen == null) {
            return false;
        }
        
        return (this.edellinenSiirto.getPassaus() && toiseksiViimeinen.getPassaus());
    }
    
    public void setSiirto(Siirto siirto) {
        this.siirto = siirto;
    }
    
    private boolean onkoItseatari(Pelilauta lauta, Koordinaatti siirto) {
        return lauta.etsiRyhma(siirto).vapauksienMaara() == 0;
    }
    
    private boolean rikkookoSiirtoKoSaantoa(Pelilauta lauta) {
        Pelitilanne edellinenTilanne = this.edellinenTilanne;
        
        while (edellinenTilanne != null) {
            if (edellinenTilanne.getPelilauta().getRyhmat().equals(lauta.getRyhmat())) {
                return true;
            }
            
            edellinenTilanne = edellinenTilanne.getEdellinenTilanne();
        }
        
        return false;
    }
    
    private Vari seuraavaPelaaja() {
        if (this.getPelaaja() == Vari.MUSTA) {
            return Vari.VALKOINEN;
        }
        
        return Vari.MUSTA;
    }
}
