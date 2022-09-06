package GoPeli.tekoaly;

import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.Random;

/**
 * Luokka jonka ilmentymi‰ k‰ytet‰‰n Monte Carlo Puuhaku puun solmuina.
 * S‰ilytt‰‰ tietoa pelitilanteesta, hakupuun vanhempana olevasta solmusta, sek‰ lapsisolmuista, sek‰ statistisista tiedoista kuten mustan ja valkoisen voitoista t‰m‰n ja kaikkien
 * lapsisolmujen kohdalla simuloiduista peleist‰, sek‰ tietoa viel‰ vierailemattomista jatkosiirroista.
 */
public class MCPHSolmu {
    private Pelitilanne pelitilanne;
    private MCPHSolmu vanhempi;
    private MCPHSolmu[] lapset;
    private Siirto viimeinenSiirto;
    private Random random;
    private int mustanVoitot;
    private int valkoisenVoitot;
    private int playoutit;
    private Siirto[] vierailemattomatSiirrot;
    private int vierailemattomiaSiirtoja;
    private int seuraavaTyhjaIndeksiLapsissa;
    
    /**
     * Konstruktori jolla luodaan uusi solmu Monte Carlo Puuhaku puuhun.
     * @param pelitilanne solmun ajankohtainen pelitilanne, sis‰lt‰en laudan tilan, sek‰ muun pelitilanteen informaation siirtoketjussa viimeisimp‰‰n siirtoon asti t‰ss‰ puun
     * vaiheessa.
     * @param vanhempi viite puun v‰littˆm‰sti ylemp‰‰n solmuun. Mik‰li t‰m‰ solmu on juurisolmu, niin viitteen tilalla on null-arvo.
     * @param viimeinenSiirto Siirto-olio joka kuvaa siirtoa, joka johti pelitilanteeseen, joka kuvaa t‰t‰ puun vaihetta.
     * @param random satunnaisgeneraattori, jota k‰ytet‰‰n kun halutaan lis‰t‰ sattumanvarainen lapsi puuhun t‰m‰n solmun lapseksi, eli halutaan valita sattumanvarainen siirto viel‰
     * vierailemattomien siirtojen joukosta.
     */
    public MCPHSolmu(Pelitilanne pelitilanne, MCPHSolmu vanhempi, Siirto viimeinenSiirto, Random random) {
        this.pelitilanne = pelitilanne;
        this.vanhempi = vanhempi;
        this.viimeinenSiirto = viimeinenSiirto;
        this.random = random;
        this.mustanVoitot = 0;
        this.valkoisenVoitot = 0;
        this.playoutit = 0;
        this.lapset = new MCPHSolmu[10];
        this.vierailemattomatSiirrot = pelitilanne.laillisetSiirrot();
        
        int siirtoja = 0;
        for (Siirto siirto : this.vierailemattomatSiirrot) {
            if (siirto != null) {
                siirtoja++;
            }
        }
        this.vierailemattomiaSiirtoja = siirtoja;
        this.seuraavaTyhjaIndeksiLapsissa = 0;
    }
    
    /**
     * Metodi joka palauttaa t‰h‰n solmuun liittyv‰n pelitilanteen.
     * @return Pelitilanne - pelin vaihetta kuvaava olio t‰m‰n solmun kohdalla.
     */
    public Pelitilanne getPelitilanne() {
        return this.pelitilanne;
    }
    
    /**
     * Metodi palauttaa Monte Carlo Puuhaku puussa suoraan t‰m‰n solmun yll‰ olevan solmun, eli t‰m‰n solmun vanhemman.
     * Huom. jos t‰m‰ solmu on puun juurisolmu, niin metodi palauttaa null-arvon.
     * @return MCPHSolmu/null - palauttaa t‰m‰n solmun vanhemman puussa tai null-arvon mik‰li t‰m‰ solmu on juurisolmu puussa.
     */
    public MCPHSolmu getVanhempi() {
        return this.vanhempi;
    }
    
    /**
     * Metodi palauttaa viimeisen siirron, joka johti t‰h‰n solmuun liittyv‰‰n pelitilanteeseen.
     * @return Siirto - palauttaa Siirto-olion, joka kuvaa t‰h‰n solmuun liittyv‰‰n pelitilanteeseen johtanutta siirtoa.
     */
    public Siirto getViimeinenSiirto() {
        return this.viimeinenSiirto;
    }
    
    /**
     * Metodi joka palauttaa statistiikkatietona t‰st‰ solmusta tai sen lapsista l‰hteneist‰ simulaatioista jotka p‰‰tyv‰t mustan pelaajan voittoon.
     * @return int - kokonaisluku joka kertoo t‰st‰ solmusta ja kaikista sen lapsista l‰hteneiden simulaatioiden m‰‰r‰n, jotka p‰‰tyv‰t mustan voittoon.
     */
    public int getMustanVoitot() {
        return this.mustanVoitot;
    }
    
    /**
     * Metodi joka palauttaa statistiikkatietona t‰st‰ solmusta tai sen lapsista l‰hteneist‰ simulaatioista jotka p‰‰tyv‰t valkoisen pelaajan voittoon.
     * @return int - kokonaisluku joka kertoo t‰st‰ solmusta ja kaikista sen lapsista l‰hteneiden simulaatioiden m‰‰r‰n, jotka p‰‰tyv‰t valkoisen voittoon.
     */
    public int getValkoisenVoitot() {
        return this.valkoisenVoitot;
    }
    
    /**
     * Metodi joka palauttaa statistiikkatietona t‰st‰ solmusta tai sen lapsista l‰hteneiden simulaatioiden m‰‰r‰st‰.
     * @return int - kokonaisluku joka kertoo t‰st‰ solmusta ja kaikista sen lapsista l‰hteneiden simulaatioiden m‰‰r‰n, jotka p‰‰tyv‰t valkoisen voittoon.
     */
    public int getPlayoutit() {
        return this.playoutit;
    }
    
    /**
     * Metodi joka palauttaa t‰m‰n solmun t‰h‰nastiset lapsisolmut taulukkona.
     * Huom. taulukon koko ei v‰ltt‰m‰tt‰ vastaa tasan lapsien m‰‰r‰‰, eli taulukon lopussa voi olla null-arvoja.
     * @return MCPHSolmu[] - taulukko joka sis‰lt‰‰ t‰lle solmulle lis‰tyt lapset.
     */
    public MCPHSolmu[] getLapset() {
        return this.lapset;
    }
    
    /**
     * Metodi joka palauttaa taulukon siirroista, jotka johtavat pelitilanteisiin, joista ei ole tehty viel‰ solmuja puuhun.
     * Huom. taulukon koko ei v‰ltt‰m‰tt‰ vastaa tasan vierailemattomien siirtojen m‰‰r‰‰, eli taulukon lopussa voi olla null-arvoja.
     * @return Siirto[] - taulukko joka sis‰lt‰‰ siirrot Siirto-oliolla kuvattuna, jotka voi lis‰t‰ t‰m‰n solmun pelitilanteeseen, luoden pelitilanteen, josta ei ole viel‰ solmua
     * puussa.
     */
    public Siirto[] getVierailemattomatSiirrot() {
        return this.vierailemattomatSiirrot;
    }
    
    /**
     * Metodi joka palauttaa siirtojen m‰‰r‰n, joista ei t‰ss‰ pelitilanteessa ole tehty seuraavaa pelitilannetta kuvaavaa solmua hakupuuhun.
     * @return int - siirtojen m‰‰r‰, joista t‰h‰n pelitilanteeseen lis‰‰m‰ll‰ saadaan uusi pelitilanne, jota kuvaavaa solmua ei ole viel‰ hakupuussa.
     */
    public int getVierailemattomiaSiirtoja() {
        return this.vierailemattomiaSiirtoja;
    }
    
    /**
     * Metodi jolla saa indeksinumeron, jossa on ensimm‰inen null-arvo taulukosta, joka sis‰lt‰‰ t‰m‰n solmun lapset.
     * @return int - indeksinumero lapset-taulukossa, jossa on ensimm‰inen null arvo.
     */
    public int getSeuraavaTyhjaIndeksiLapsissa() {
        return this.seuraavaTyhjaIndeksiLapsissa;
    }
    
    /**
     * Metodi joka lis‰‰ hakupuuhun t‰lle solmulle lapsen, joka seuraa sattumanvaraisesta siirrosta, josta seuraavasta pelitilanteesta kuvaavaa solmua ei viel‰ ole.
     * @return MCPHSolmu - palauttaa viitteen t‰ll‰ metodilla lis‰ttyyn t‰m‰n solmun lapsisolmuun.
     */
    public MCPHSolmu lisaaSattumanvarainenLapsiPuuhun() {
        int sattumanvarainenIndeksi = this.random.nextInt(this.vierailemattomiaSiirtoja);
        Siirto sattumanvarainenSiirto = this.vierailemattomatSiirrot[sattumanvarainenIndeksi];
        
        this.pelitilanne.setSiirto(sattumanvarainenSiirto);
        Pelitilanne uusiTilanne = this.pelitilanne.lisaaSiirto();
        
        if (this.seuraavaTyhjaIndeksiLapsissa == this.lapset.length) {
            kasvataLapsienTaulukko();
        }
        
        MCPHSolmu uusiSolmu = new MCPHSolmu(uusiTilanne, this, sattumanvarainenSiirto, this.random);
        this.lapset[this.seuraavaTyhjaIndeksiLapsissa] = uusiSolmu;
        this.seuraavaTyhjaIndeksiLapsissa++;
        poistaKaytettySiirtoVierailemattomista(sattumanvarainenIndeksi);
        
        return uusiSolmu;
    }
    
    /**
     * Metodi joka tallentaa statistiikkatiedon t‰h‰n solmuun simuloidun pelin voittajasta ja kasvattaa tehtyjen simulointien m‰‰r‰‰.
     * @param voittaja simuloinnin lopputuloksena saatu pelin voittajan v‰ri Enum Vari arvoisena.
     */
    public void tallennaVoitto(Vari voittaja) {
        if (voittaja == Vari.MUSTA) {
            this.mustanVoitot++;
        } else {
            this.valkoisenVoitot++;
        }
        
        this.playoutit++;
    }
    
    /**
     * Metodi joka palauttaa totuusarvon onko t‰lle solmulle mahdollista viel‰ lis‰t‰ lapsisolmuja vai onko kaikista laillisista siirroista seuraavista pelitilanteista jo tehty
     * lapsisolmu.
     * @return true/false - true jos t‰lle solmulle on viel‰ mahdollista lis‰t‰ laillisesta siirrosta seuraava lapsisolmu, muuten false.
     */
    public boolean voiLisataLapsen() {
        return this.vierailemattomatSiirrot[0] != null;
    }
    
    /**
     * Metodi palauttaa totuusarvon onko t‰m‰n solmun kuvaama pelitilanne sellainen, ett‰ peli on ohi.
     * @return true/false - tarkastaa ja palauttaa arvon, onko t‰m‰n solmun kuvaama pelitilanne lopputilanne.
     */
    public boolean onLopputilanne() {
        return this.pelitilanne.peliOhi();
    }
    
    /**
     * Metodi palauttaa tiedon mik‰ on halutunv‰risen pelaajan voittojen suhde simulaatioiden m‰‰r‰‰n jotka vaikuttavat t‰h‰n solmuun.
     * @param pelaaja pelaajan v‰ri Enum Vari muodossa kumman voittojen suhde simulaatioiden m‰‰r‰‰n halutaan tarkastaa.
     * @return double - parametrina annetun v‰risen pelaajan voittojen suhde simuloitujen pelien m‰‰r‰‰n, arvov‰li 0.0 - 1.0.
     */
    public double pelaajanVoittoprosentti(Vari pelaaja) {
        if (pelaaja == Vari.MUSTA) {
            return (double) this.mustanVoitot / this.playoutit;
        } else {
            return (double) this.valkoisenVoitot / this.playoutit;
        }
    }
    
    private void kasvataLapsienTaulukko() {
        MCPHSolmu[] uusiTaulukko = new MCPHSolmu[this.lapset.length * 2];
        
        for (int indeksi = 0; indeksi < this.lapset.length; indeksi++) {
            uusiTaulukko[indeksi] = this.lapset[indeksi];
        }
        
        this.lapset = uusiTaulukko;
    }
    
    private void poistaKaytettySiirtoVierailemattomista(int indeksi) {
        Siirto[] uusiTaulukko = new Siirto[this.vierailemattomatSiirrot.length];
        
        for (int i = 0; i < indeksi; i++) {
            uusiTaulukko[i] = this.vierailemattomatSiirrot[i];
        }
        
        for (int j = indeksi + 1; j < this.vierailemattomatSiirrot.length; j++) {
            uusiTaulukko[j - 1] = this.vierailemattomatSiirrot[j];
        }
        
        this.vierailemattomatSiirrot = uusiTaulukko;
        this.vierailemattomiaSiirtoja--;
    }
}
