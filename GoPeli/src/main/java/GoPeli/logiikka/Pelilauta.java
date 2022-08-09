package GoPeli.logiikka;

import java.util.HashSet;

/**
 * Pelilautaa kuvaava luokka, joka sis‰lt‰‰ taulukon koordinaatti olioita, jotka kuvaavat laudan risteyspisteit‰.
 */
public class Pelilauta {
    private Koordinaatti[][] lauta;
    private HashSet<Ryhma> ryhmat;
    
    /**
     * Konstruktori alustaa pelilaudan kooksi 9x9.
     */
    public Pelilauta(HashSet<Ryhma> ryhmat) {
        this.lauta = new Koordinaatti[9][9];
        this.ryhmat = ryhmat;
        
        for (Ryhma ryhma : ryhmat) {
            for (Koordinaatti koordinaatti : ryhma.getKivet()) {
                this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = koordinaatti;
            }
        }
    }
    
    public Koordinaatti[][] getLauta() {
        return this.lauta;
    }
    
    public HashSet<Ryhma> getRyhmat() {
        return this.ryhmat;
    }
    
    public String lisaaSiirto(Vari pelaaja, Koordinaatti koordinaatti) {
        
        if (this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] != null) {
            return "On jo kivi";
        }
        
        HashSet<Ryhma> samanvarisetNaapurit = new HashSet<>();
        HashSet<Ryhma> erivarisetNaapurit = new HashSet<>();
        HashSet<Koordinaatti> vapaudet = new HashSet<>();
        
        for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
            Ryhma naapuriRyhma = etsiNaapuriRyhma(naapuri);
            
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
        }
        
        this.ryhmat.add(ryhma);
        
        for (Ryhma poistettavaRyhma : this.ryhmat) {
            if (poistettavaRyhma.getKivet().isEmpty()) {
                this.ryhmat.remove(poistettavaRyhma);
            }
        }
        
        for (Ryhma erivarinenNaapuri : erivarisetNaapurit) {
            erivarinenNaapuri.poistaVapaus(koordinaatti);
            if (erivarinenNaapuri.getVapaudet().isEmpty()) {
                poistaRyhma(erivarinenNaapuri);
                this.ryhmat.remove(erivarinenNaapuri);
            }
        }
        return "Onnistui";
    }
    
    private Ryhma etsiNaapuriRyhma(Koordinaatti koordinaatti) {
        for (Ryhma ryhma : this.ryhmat) {
                if (ryhma.getKivet().contains(koordinaatti)) {
                    return ryhma;
                }
            }
        return null;
    }
    
    private void poistaRyhma(Ryhma ryhma) {
        for (Koordinaatti koordinaatti : ryhma.getKivet()) {
            for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
                Ryhma naapuriRyhma = etsiNaapuriRyhma(naapuri);
                
                if (!ryhma.equals(naapuriRyhma)) {
                    naapuriRyhma.lisaaVapaus(koordinaatti);
                }
            }
            
            this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = null;
        }
    }
}