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
            
            this.lauta[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = null;
        }
        this.ryhmat.remove(ryhma);
    }
}