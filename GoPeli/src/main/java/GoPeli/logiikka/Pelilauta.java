package GoPeli.logiikka;

import java.util.HashSet;

/**
 * Pelilautaa kuvaava luokka, joka sis‰lt‰‰ taulukon koordinaatti olioita, jotka kuvaavat laudan risteyspisteit‰.
 */
public class Pelilauta {
    private Koordinaatti[][] lauta;
    private HashSet<Ryhma> ryhmat;
    private HashSet<Koordinaatti> kaapatutKivet;
    
    /**
     * Konstruktori alustaa pelilaudan kooksi 9x9.
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
     * @param pelilauta parametrina kopioitava pelilauta.
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
    
    public Koordinaatti[][] getLauta() {
        return this.lauta;
    }
    
    public HashSet<Ryhma> getRyhmat() {
        return this.ryhmat;
    }
    
    public HashSet<Koordinaatti> getKaapatutKivet() {
        return this.kaapatutKivet;
    }
    
    public Ryhma etsiRyhma(Koordinaatti koordinaatti) {
        for (Ryhma ryhma : this.ryhmat) {
            if (ryhma.getKivet().contains(koordinaatti)) {
                return ryhma;
            }
        }
        return null;
    }
    
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