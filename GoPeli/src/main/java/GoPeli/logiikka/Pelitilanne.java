package GoPeli.logiikka;

import java.util.HashSet;

/**
 * Pelitilannetta kuvaava luokka.
 * Sis‰lt‰‰ pelilaudan kivineen ja ryhmineen Pelilauta-oliona, tiedon t‰m‰nhetkisen pelaajan v‰rist‰, viitteen edelliseen pelitilanteeseen, seuraavan ja edellisen siirron
 * Siirto-olioina, tiedon onko edellinen yritetty siirto laiton, sek‰ tiedon kuinka monta mustaa ja valkoista kive‰ pelin aikana on yhteens‰ kaapattu.
 */
public class Pelitilanne {
    
    private Pelilauta lauta;
    private Vari pelaaja;
    private Pelitilanne edellinenTilanne;
    private Siirto siirto;
    private Siirto edellinenSiirto;
    private String laitonSiirto;
    private int kaapatutMustat;
    private int kaapatutValkoiset;
    
    /**
     * Luo uuden t‰m‰nhetkist‰ pelin tilannetta kuvaavan olion.
     * @param pelilauta t‰m‰nhetkinen pelin lautatilanne Pelilauta-oliona.
     * @param pelaaja t‰ll‰ hetkell‰ vuorossa olevan pelaajan v‰ri, Enum Vari muodossa.
     * @param edellinenTilanne viite t‰m‰nhetkist‰ pelitilannetta edelt‰v‰‰n pelitilanteeseen.
     * @param siirto seuraavaksi pelitilanteeseen lis‰tt‰v‰ siirto, Siirto-oliona.
     * @param edellinen siirto, Siirto-oliona, joka johti edellisess‰ pelitilanteessa t‰h‰n pelitilanteeseen.
     * @param laitonSiirto tieto, oliko t‰h‰n pelitilanteeseen johtanut siirto laiton.
     * @param kaapatutMustat tieto int muodossa pelin t‰h‰n asti kaapattujen mustien kivien m‰‰r‰st‰.
     * @param kaapatutValkoiset tieto int muodossa pelin t‰h‰n asti kaapattujen valkoisten kivien m‰‰r‰st‰.
     */
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
     * Halutun siirron lis‰‰minen pelitilanteeseen.
     * T‰‰ll‰ tapahtuu tarkastukset, ett‰ siirto on laillinen, sek‰ seuraavan pelaajan v‰rin valinta.
     * Mik‰li siirto on laiton, niin palautetaan Pelitilanne-olio, jossa alkuper‰inen lautatilanne, mutta myˆs tieto laittoman siirron tyypist‰ String muodossa laitonSiirto kohdassa.
     * @return palauttaa uuden pelitilanteen, kun siirto on lis‰tty.
     */
    public Pelitilanne lisaaSiirto() {
        Pelilauta seuraavaLauta = this.lauta;
        
        if (this.siirto.getKoordinaatti() != null) {
            seuraavaLauta = new Pelilauta(this.lauta);
            String onkoJoKivi = seuraavaLauta.lisaaSiirto(this.pelaaja, this.siirto.getKoordinaatti());
            
            String eiVapauksia = null;
            if (onkoItseatari(seuraavaLauta, this.siirto.getKoordinaatti())) {
                eiVapauksia = "Siirron j‰lkeen ei ole vapauksia";
            }
                
            String koSaanto = null;
            if (rikkookoSiirtoKoSaantoa(seuraavaLauta)) {
                koSaanto = "Siirto rikkoo Ko-s‰‰ntˆ‰";
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
     * Uuden pelin luonti, luo uuden pelilaudan, asettaa seuraavan pelaajan mustaksi ja edellisen tilanteen, sek‰
     * siirron null arvoisiksi.
     * @return palauttaa uuden pelitilanteen, joka toimii l‰htˆtilanteena uudelle pelille.
     */
    public static Pelitilanne uusiPeli() {
        return new Pelitilanne(new Pelilauta(new HashSet<>()), Vari.MUSTA, null, null, null, null, 0, 0);
    }
    
    /**
     * Metodi palauttaa t‰m‰n pelitilanteen t‰m‰nhetkisen pelilaudan Pelilauta-oliona.
     * @return Pelilauta - t‰m‰nhetkinen pelilaudan tila, Pelilauta-oliona, joka sis‰lt‰‰ tiedon miss‰ pelilaudan pisteiss‰ on kivet, sek‰ tiedon pelilaudan ryhmist‰.
     */
    public Pelilauta getPelilauta() {
        return this.lauta;
    }
    
    /**
     * Metodi palauttaa tiedon pelaajan v‰rist‰, jonka vuoro on t‰ss‰ pelitilanteessa.
     * @return Vari - t‰ll‰ hetkell‰ vuorossa olevan pelaajan v‰ri Enum Vari muodossa.
     */
    public Vari getPelaaja() {
        return this.pelaaja;
    }
    
    /**
     * Viite pelitilanteeseen, joka johti t‰h‰n tilanteeseen viimeisen siirron lis‰yksen seurauksena.
     * @return Pelitilanne/null - t‰h‰n pelitilanteeseen viimeisen siirron seurauksena johtanut pelitilanne. Jos t‰m‰ pelitilanne on alkutilanne, niin arvona null.
     */
    public Pelitilanne getEdellinenTilanne() {
        return this.edellinenTilanne;
    }
    
    /**
     * Metodi palauttaa t‰ss‰ pelitilanteessa seuraavaksi siirroksi suunnitellun siirron, Siirto-oliona.
     * Null jos seuraavaa siirtoa ei ole viel‰ p‰‰tetty.
     * @return Siirto/null - seuraavaksi siirroksi suunniteltu siirto, joka lis‰‰m‰ll‰ saadaan uusi pelitilanne. Jos siirtoa ei ole viel‰ p‰‰tetty, niin arvona on null.
     */
    public Siirto getSiirto() {
        return this.siirto;
    }
    
    /**
     * Palauttaa siirron, Siirto-oliona, joka johti siirtym‰‰n edellisest‰ pelitilanteesta t‰h‰n pelitilanteeseen.
     * @return Siirto/null - edellisest‰ pelitilanteesta t‰h‰n pelitilanteeseen johtanut siirto, Siirto-oliona. Jos t‰m‰ pelitilanne on alkutilanne, niin arvona null.
     */
    public Siirto getEdellinenSiirto() {
        return this.edellinenSiirto;
    }
    
    /**
     * Metodi palauttaa tiedon, oliko t‰h‰n pelitilanteeseen johtanut siirto laiton, String muodossa.
     * Mik‰li siirto oli laiton, laittoman siirron tyyppi k‰y ilmi String arvosta seuraavasti:
     * "On jo kivi" - mik‰li pisteess‰, johon siirtoa yritettiin lis‰t‰, oli jo jommankumman pelaajan kivi.
     * "Siirron j‰lkeen ei ole vapauksia" - mik‰li yritettiin lis‰t‰ siirto, jonka j‰lkeen ryhm‰ll‰ johon kivi lis‰t‰‰n, ei j‰‰ vapauksia siirron suorittamisen j‰lkeen (eli edes
     * vastustajan kivien poiston j‰lkeen).
     * "Siirto rikkoo Ko-s‰‰ntˆ‰" - mik‰li yritettiin lis‰t‰ siirto, joka palauttaa jo aikaisemmin pelin aikana olleen lautatilanteen.
     * null - jos siirto oli laillinen.
     * @return String/null - yll‰ mainittu String-arvo jos t‰h‰n pelitilanteeseen johtanut siirto oli laiton, tai null arvo jos kyseess‰ oleva siirto oli laillinen.
     */
    public String getLaitonSiirto() {
        return this.laitonSiirto;
    }
    
    /**
     * Pelin aikana t‰h‰n tilanteeseen menness‰ kaapattujen mustan kivien m‰‰r‰.
     * @return int - kaapattujen mustan kivien m‰‰r‰ kokonaislukuna.
     */
    public int getKaapatutMustat() {
        return this.kaapatutMustat;
    }
    
    /**
     * Pelin aikana t‰h‰n tilanteeseen menness‰ kaapattujen valkoisen kivien m‰‰r‰.
     * @return int - kaapattujen valkoisen kivien m‰‰r‰ kokonaislukuna.
     */
    public int getKaapatutValkoiset() {
        return this.kaapatutValkoiset;
    }
    
    /**
     * Palauttaa totuusarvoisena, onko t‰m‰ pelitilanne lopputilanne.
     * @return true/false - palauttaa false, jos t‰m‰ on alkutilanne, palauttaa true jos viimeisin siirto oli luovutus tai jos kaksi viimeisint‰ siirtoa olivat passauksia, muuten false.
     */
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
    
    /**
     * Metodi jolla asetetaan siirto, joka halutaan seuraavaksi lis‰t‰ peliin.
     * @param siirto asettaa seuraavaksi lis‰tt‰v‰n siirron Siirto-oliona, mutta ei lis‰‰ sit‰ viel‰ peliin.
     */
    public void setSiirto(Siirto siirto) {
        this.siirto = siirto;
    }
    
    /**
     * Pelauttaa Siirto-taulukon, joka sis‰lt‰‰ t‰ss‰ pelitilanteessa lailliset siirrot Siirto-olioina.
     * Huom. taulukko voi olla kooltaan isompi kuin mit‰ laillisia siirtoja on.
     * @return Siirto[] - taulukko, joka sis‰lt‰‰ kaikki t‰ss‰ pelitilanteessa lailliset siirrot Siirto-olioina.
     */
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
    
    /**
     * Metodi joka selvitt‰‰ kumpi pelaaja voitti pelin t‰ss‰ pelitilanteessa.
     * Huom. Toimii oikein ainoastaan kun pelitilanne on lopputilanne kaikkein naiivimmassa mieless‰, eli pelilauta on t‰ytetty niin, ett‰ ryhmien sis‰‰n tai v‰leihin j‰‰v‰t alueet
     * ovat korkeintaan yhden pisteen kokoisia, eiv‰tk‰ muodosta isompia yhten‰isi‰ tyhji‰ alueita.
     * @return Vari - palauttaa lopputilanteessa voittavan pelaajan Enum Vari tyyppisen‰ arvona.
     */
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
        
        /*  Hyvin naaivi pisteidenlaskuj‰rjestelm‰. Olettaa, ett‰ tyhj‰t alueet ovat yhden pisteen
            kokoisia, eli ett‰ alueet on t‰ytetty silmi‰ lukuunottamatta. Ottaa huomioon sekin, eli
            jos pisteen vieress‰ on sek‰ mustan, ett‰ valkean kivi, tosin Monte Carlo algoritmin
            naiivi botti t‰t‰ ei ota huomioon, eli se saattaa t‰ytt‰‰ toiseksiviimeisen vapauden
            sekiss‰ olevalta ryhm‰lt‰.
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
