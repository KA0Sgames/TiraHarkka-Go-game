package GoPeli.logiikka;

import java.util.HashSet;

/**
 * Pelilautaa kuvaava luokka, joka sis‰lt‰‰ taulukon koordinaatti olioita, jotka kuvaavat laudan risteyspisteit‰.
 * Null arvo kuvaa pistett‰, jossa ei ole kive‰.
 * Sis‰lt‰‰ HashSetin Ryhma-olioita, jotka kuvaavat pelilaudalla olevia yhten‰isi‰ kiviryhmi‰, sis‰lt‰en niiden v‰rin.
 * Lis‰ksi sis‰lt‰‰ HashSetin Koordinaatti-olioita, johon tallennetaan siirron lis‰yksen yhteydess‰ kaapattujen kivien koordinaatit.
 */
public class Pelilauta {
    private Koordinaatti[][] lauta;
    private HashSet<Ryhma> ryhmat;
    private HashSet<Koordinaatti> kaapatutKivet;
    
    /**
     * Konstruktori alustaa pelilaudan kooksi 9x9.
     * Lis‰ksi aikaisemmat ryhm‰t lis‰t‰‰n t‰lle pelilaudalle, jos ryhmi‰ on jo.
     * Kaapatuille kiville luodaan uusi HashSet.
     * @param HashSet<Ryhma> sis‰lt‰‰ aikaisemmat ryhm‰t, jotka halutaan lis‰t‰ t‰lle pelilaudalle, jos halutaan alustaa tyhj‰ pelilauta, niin parametrina annetaan tyhj‰ HashSet<Ryhma>.
     */
    public Pelilauta(HashSet<Ryhma> ryhmat) {
        this.lauta = new Koordinaatti[9][9];
        this.ryhmat = ryhmat;
        this.kaapatutKivet = new HashSet<>();
        
        for (Ryhma ryhma : ryhmat) {
            for (Koordinaatti koordinaatti : ryhma.getKivet()) {
                this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = koordinaatti;
            }
        }
    }
    
    /**
     * Kopiokonstruktori, jolla saa laudasta syv‰kopion.
     * @param pelilauta parametrina Pelilauta-olio, josta syv‰kopio tehd‰‰n.
     */
    public Pelilauta(Pelilauta pelilauta) {
        this.kaapatutKivet = new HashSet<>();
        this.ryhmat = new HashSet<>();

        for (Ryhma ryhma : pelilauta.getRyhmat()) {
            this.ryhmat.add(new Ryhma(ryhma));
        }
        
        this.lauta = new Koordinaatti[9][9];
        
        for (int rivi = 0; rivi < 9; rivi++) {
            for (int sarake = 0; sarake < 9; sarake++) {
                if (pelilauta.getLauta()[rivi][sarake] == null) {
                    this.lauta[rivi][sarake] = null;
                } else {
                    this.lauta[rivi][sarake] = new Koordinaatti(pelilauta.lauta[rivi][sarake]);
                }
            }
        }
    }
    
    /**
     * Metodi palauttaa pelilautaa kuvaavan taulukon Koordinaatti-olioita, jotka kuvaavat, ett‰ kyseisess‰ risteyspisteess‰ on kivi ja null arvot vastaavat tilannetta, ett‰ risteyspisteess‰ ei ole kive‰.
     * @return Koordinaatti[][] - taulukko Koordinaatti-olioita, jotka kuvaavat onko risteyspisteess‰ kive‰.
     */
    public Koordinaatti[][] getLauta() {
        return this.lauta;
    }
    
    /**
     * Metodi palauttaa HashSetin, jossa on laudalla olevia kiviryhmi‰ vastaavat Ryhma-oliot.
     * @return HashSet<Ryhma> - sis‰lt‰‰ laudalla olevia kiviryhmi‰ vastaavat Ryhma-oliot, jotka kuvaavat yhten‰isi‰ kiviketjuja, sis‰lt‰en niiden v‰rin.
     */
    public HashSet<Ryhma> getRyhmat() {
        return this.ryhmat;
    }
    
    /**
     * Metodi palauttaa HashSetin Koordinaatti-olioita, jotka kuvaavat siirron lis‰‰misen yhteydess‰ kaapattuja kivi‰.
     * @return HashSet<Koordinaatti> - palauttaa kaapatut kivet, kun t‰lle laudalle lis‰t‰‰n siirto lisaaSiirto-metodilla.
     */
    public HashSet<Koordinaatti> getKaapatutKivet() {
        return this.kaapatutKivet;
    }
    
    /**
     * Metodi palauttaa t‰m‰n pelilaudan yhten‰ist‰ kiviryhm‰‰ kuvaavan Ryhma-olion, mik‰li parametrina annettu risteyspisteess‰ olevaa kive‰ kuvaava Koordinaatti-olio kuuluu ryhm‰‰n.
     * Palautetaan null, mik‰li annettu Koordinaatti-olio ei vastaa mink‰‰n t‰m‰n lautatilanteen ryhm‰n mit‰‰n kive‰.
     * @param koordinaatti haluttua risteyspisteess‰ olevaa kive‰ kuvaava Koordinaatti-olio.
     * @return Ryhma - palautetaan Ryhma-olio, johon kysytty koordinaatti kuuluu. Mik‰li kysytty koordinaatti ei kuulu mihink‰‰n ryhm‰‰n, palautetaan null.
     */
    public Ryhma etsiRyhma(Koordinaatti koordinaatti) {
        for (Ryhma ryhma : this.ryhmat) {
            if (ryhma.getKivet().contains(koordinaatti)) {
                return ryhma;
            }
        }
        return null;
    }
    
    /**
     * Metodi jolla lis‰t‰‰n uusi siirto t‰lle pelilaudalle.
     * Metodi tarkastaa onko halutussa pisteess‰ jo kivi, mik‰li ei ole, niin se luo uuden ryhm‰n laudalle, yhdist‰‰ t‰m‰n kaikkiin suoraan (vaaka- tai pystysuunnassa) kytkˆksiss‰
     * oleviin samanv‰risiin ryhmiin, kuin annetun pelaajan v‰ri ja p‰ivitt‰‰ ryhmien vapaudet, jonka j‰lkeen se poistaa vastustajan ryhm‰t laudalta, joille ei j‰‰nyt vapauksia.
     * @param pelaaja pelaajan v‰ri Enum Vari arvona, jolle halutaan siirto lis‰t‰.
     * @param koordinaatti haluttua pelilaudan pistett‰ kuvaava arvo Koordinaatti-oliona.
     * @return String - palauttaa String-olion jonka arvona "On jo kivi", mik‰li halutussa pisteess‰ on jo jommankumman pelaajan kivi ja muuten arvo "Onnistui" siirron lis‰‰misoperaatioiden j‰lkeen.
     */
    public String lisaaSiirto(Vari pelaaja, Koordinaatti koordinaatti) {
        
        if (this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] != null) {
            return "On jo kivi";
        }
        
        HashSet<Ryhma> samanvarisetNaapurit = new HashSet<>();
        HashSet<Ryhma> erivarisetNaapurit = new HashSet<>();
        HashSet<Koordinaatti> vapaudet = new HashSet<>();
        
        for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
            Ryhma naapuriRyhma = etsiRyhma(naapuri);
            
            if (naapuriRyhma == null) {
                vapaudet.add(naapuri);
            } else if (pelaaja == naapuriRyhma.getVari()) {
                if (!samanvarisetNaapurit.contains(naapuriRyhma)) {
                    samanvarisetNaapurit.add(naapuriRyhma);
                }
            } else {
                if (!erivarisetNaapurit.contains(naapuriRyhma)) {
                    erivarisetNaapurit.add(naapuriRyhma);
                }
            }
        }
        
        
        HashSet<Koordinaatti> kivi = new HashSet<>();
        kivi.add(koordinaatti);
        this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = koordinaatti;
        Ryhma ryhma = new Ryhma(pelaaja, kivi, vapaudet);
        
        for (Ryhma naapuri : samanvarisetNaapurit) {
            ryhma = ryhma.yhdista(naapuri);
            this.ryhmat.remove(naapuri);
        }
        
        this.ryhmat.add(ryhma);

        for (Ryhma erivarinenNaapuri : erivarisetNaapurit) {
            this.ryhmat.remove(erivarinenNaapuri);
            erivarinenNaapuri.poistaVapaus(koordinaatti);
            this.ryhmat.add(erivarinenNaapuri);

            if (erivarinenNaapuri.getVapaudet().isEmpty()) {
                poistaRyhma(erivarinenNaapuri);
            }
        }
        return "Onnistui";
    }
    
    private void poistaRyhma(Ryhma ryhma) {
        for (Koordinaatti koordinaatti : ryhma.getKivet()) {
            HashSet<Ryhma> naapuriRyhmat = new HashSet<>();
            
            for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
                Ryhma naapuriRyhma = etsiRyhma(naapuri);
                
                if (!naapuriRyhmat.contains(naapuriRyhma)) {
                    naapuriRyhmat.add(naapuriRyhma);
                }

            }
            
            for (Ryhma naapuriRyhma : naapuriRyhmat) {
                if (!(ryhma.equals(naapuriRyhma))) {
                    this.ryhmat.remove(naapuriRyhma);
                    naapuriRyhma.lisaaVapaus(koordinaatti);
                    this.ryhmat.add(naapuriRyhma);
                }
            }
            
            this.kaapatutKivet.add(koordinaatti);
            this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = null;
        }
        this.ryhmat.remove(ryhma);
    }
}