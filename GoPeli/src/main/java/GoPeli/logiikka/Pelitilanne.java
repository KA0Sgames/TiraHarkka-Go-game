package GoPeli.logiikka;

import java.util.HashSet;

public class Pelitilanne {
    
    private Pelilauta lauta;
    private Vari pelaaja;
    private Pelitilanne edellinenTilanne;
    private Siirto siirto;
    private Siirto edellinenSiirto;
    private String laitonSiirto;
    private int kaapatutMustat;
    private int kaapatutValkoiset;
    
    public Pelitilanne(Pelilauta pelilauta, Vari pelaaja, Pelitilanne edellinenTilanne, Siirto siirto, Siirto edellinen, String laitonSiirto, int kaapatutMustat, int kaapatutValkoiset) {
        this.lauta = pelilauta;
        this.pelaaja = pelaaja;
        this.edellinenTilanne = edellinenTilanne;
        this.siirto = siirto;
        this.edellinenSiirto = edellinen;
        this.laitonSiirto = laitonSiirto;
        this.kaapatutMustat = kaapatutMustat;
        this.kaapatutValkoiset = kaapatutValkoiset;
    }
    
    /**
     * Siirron lisääminen pelitilanteeseen. Täällä tapahtuu tarkastukset, että siirto on laillinen, sekä seuraavan
     * pelaajan värin valinta.
     * @return palauttaa uuden pelitilanteen, kun siirto on lisätty.
     */
    public Pelitilanne lisaaSiirto() {
        Pelilauta seuraavaLauta = this.lauta;
        
        if (this.siirto.getKoordinaatti() != null) {
            seuraavaLauta = new Pelilauta(this.lauta);
            String onkoJoKivi = seuraavaLauta.lisaaSiirto(this.pelaaja, this.siirto.getKoordinaatti());
            
            String eiVapauksia = null;
            if (onkoItseatari(seuraavaLauta, this.siirto.getKoordinaatti())) {
                eiVapauksia = "Siirron jälkeen ei ole vapauksia";
            }
                
            String koSaanto = null;
            if (rikkookoSiirtoKoSaantoa(seuraavaLauta)) {
                koSaanto = "Siirto rikkoo Ko-sääntöä";
            }
            
            if (onkoJoKivi.equals("Onnistui") && eiVapauksia == null && koSaanto == null) {
                if (this.pelaaja == Vari.MUSTA) {
                    kaapatutValkoiset += seuraavaLauta.getKaapatutKivet().size();
                } else {
                    kaapatutMustat += seuraavaLauta.getKaapatutKivet().size();
                }
                
                Vari seuraavaPelaaja = seuraavaPelaaja();
                
                return new Pelitilanne(seuraavaLauta, seuraavaPelaaja, this, null, this.siirto, null, this.kaapatutMustat, this.kaapatutValkoiset);
                
            } else {
                String virhe = null;
                if (onkoJoKivi.equals("On jo kivi")) {
                    virhe = "On jo kivi";
                } else if (eiVapauksia != null) {
                    virhe = eiVapauksia;
                } else {
                    virhe = koSaanto;
                }
                
                return new Pelitilanne(this.lauta, this.pelaaja, this.edellinenTilanne, null, this.edellinenSiirto, virhe, this.kaapatutMustat, this.kaapatutValkoiset);
            }
        }
        
        Vari seuraavaPelaaja = seuraavaPelaaja();
        
        return new Pelitilanne(seuraavaLauta, seuraavaPelaaja, this, null, this.siirto, null, this.kaapatutMustat, this.kaapatutValkoiset);
    }
    
    /**
     * Uuden pelin luonti, luo uuden pelilaudan, asettaa seuraavan pelaajan mustaksi ja edellisen tilanteen, sekä
     * siirron null:iksi.
     * @return palauttaa uuden pelitilanteen, joka toimii lähtötilanteena uudelle pelille.
     */
    public static Pelitilanne uusiPeli() {
        return new Pelitilanne(new Pelilauta(new HashSet<>()), Vari.MUSTA, null, null, null, null, 0, 0);
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
    
    public int getKaapatutMustat() {
        return this.kaapatutMustat;
    }
    
    public int getKaapatutValkoiset() {
        return this.kaapatutValkoiset;
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
    
    public Siirto[] laillisetSiirrot() {
        Siirto[] lailliset = new Siirto[10];
        int tyhjaIndeksi = 0;
        
        for (byte rivi = 0; rivi < 9; rivi++) {
            for (byte sarake = 0; sarake < 9; sarake++) {
                Siirto siirto = Siirto.pelaa(new Koordinaatti(rivi, sarake));
                
                this.setSiirto(siirto);
                Pelitilanne uusiTilanne = this.lisaaSiirto();
                
                if (uusiTilanne.getLaitonSiirto() == null) {
                    if (tyhjaIndeksi == lailliset.length) {
                        lailliset = uusiTaulukko(lailliset);
                    }
                    lailliset[tyhjaIndeksi] = siirto;
                    tyhjaIndeksi++;
                }
            }
        }
        
        return lailliset;
    }
    
    public Vari laskeVoittaja() {
        double mustanPisteet = 0;
        double valkeanPisteet = 7.5;
        
        for (Ryhma ryhma : this.lauta.getRyhmat()) {
            if (ryhma.getVari() == Vari.MUSTA) {
                mustanPisteet += ryhma.getKivet().size();
            } else {
                valkeanPisteet += ryhma.getKivet().size();
            }
        }
        
        /*  Hyvin naaivi pisteidenlaskujärjestelmä. Olettaa, että tyhjät alueet ovat yhden pisteen
            kokoisia, eli että alueet on täytetty silmiä lukuunottamatta. Ottaa huomioon sekin, eli
            jos pisteen vieressä on sekä mustan, että valkean kivi, tosin Monte Carlo algoritmin
            naiivi botti tätä ei ota huomioon, eli se saattaa täyttää toiseksiviimeisen vapauden
            sekissä olevalta ryhmältä.
        */
        for (byte rivi = 0; rivi < 9; rivi++) {
            for (byte sarake = 0; sarake < 9; sarake++) {
                boolean mustanKiviVieressa = false;
                boolean valkeanKiviVieressa = false;
                
                for (Ryhma ryhma : this.lauta.getRyhmat()) {
                    if (rivi > 0) {
                        if (ryhma.getKivet().contains(new Koordinaatti((byte) (rivi - 1), sarake))) {
                            if (ryhma.getVari() == Vari.MUSTA) {
                                mustanKiviVieressa = true;
                            } else {
                                valkeanKiviVieressa = true;
                            }
                        }
                    }
                    if (rivi < 8) {
                        if (ryhma.getKivet().contains(new Koordinaatti((byte) (rivi + 1), sarake))) {
                            if (ryhma.getVari() == Vari.MUSTA) {
                                mustanKiviVieressa = true;
                            } else {
                                valkeanKiviVieressa = true;
                            }
                        }
                    }
                    if (sarake > 0) {
                        if (ryhma.getKivet().contains(new Koordinaatti(rivi, (byte) (sarake - 1)))) {
                            if (ryhma.getVari() == Vari.MUSTA) {
                                mustanKiviVieressa = true;
                            } else {
                                valkeanKiviVieressa = true;
                            }
                        }
                    }
                    if (sarake < 8) {
                        if (ryhma.getKivet().contains(new Koordinaatti(rivi, (byte) (sarake + 1)))) {
                            if (ryhma.getVari() == Vari.MUSTA) {
                                mustanKiviVieressa = true;
                            } else {
                                valkeanKiviVieressa = true;
                            }
                        }
                    }
                }
                if (mustanKiviVieressa && valkeanKiviVieressa) {
                    continue;
                } else if (mustanKiviVieressa) {
                    mustanPisteet++;
                } else {
                    valkeanPisteet++;
                }
            }
        }
        
        if (mustanPisteet > valkeanPisteet) {
            return Vari.MUSTA;
        } else {
            return Vari.VALKOINEN;
        }
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
    
    private Siirto[] uusiTaulukko(Siirto[] taulukko) {
        Siirto[] uusiTaulukko = new Siirto[taulukko.length * 2];
        
        for (int i = 0; i < taulukko.length; i++) {
            uusiTaulukko[i] = taulukko[i];
        }
        
        return uusiTaulukko;
    }
}
