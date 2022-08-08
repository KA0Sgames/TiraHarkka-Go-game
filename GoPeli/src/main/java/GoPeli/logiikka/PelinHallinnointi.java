//package GoPeli.logiikka;
//
//import java.util.HashSet;
//
///**
// * Luokka pelin hallinnointiin, joka pit‰‰ kirjaa t‰m‰nhetkisest‰ pelitilanteesta, siit‰ kumman vuoro on, sek‰ molempien pelaajien kaapatuista vangeista.
// * Mahdollistaa siirtojen lis‰‰misen peliin, sek‰ tarkastamiseen onko tiettyyn pelin tietyss‰ koordinaatissa jo kivi.
// */
//public class PelinHallinnointi {
//    
//    private Koordinaatti[][] peli;
//    private Vari kummanVuoro;
//    private int mustanVangit;
//    private int valkoisenVangit;
//    private final SiirronLaillisuudenTarkastaja tarkastaja;
//    private HashSet<Ryhma> ryhmat;
//    
//    /**
//     * Konstruktori, joka luo peli-taulukon t‰ynn‰ Koordinaatti-olioita, jotka on asetettu sis‰lt‰m‰‰n tyhj‰n
//     * leikkauspisteen, asettaa mustan vuoron, m‰‰ritt‰‰ mustan ja valkoisen vangit alussa nollaan ja luo
//     * siirron laillisuuden tarkastukseen olion.
//     */
//    public PelinHallinnointi() {
//        this.peli = new Koordinaatti[9][9];
//        this.kummanVuoro = Vari.MUSTA;
//        this.mustanVangit = 0;
//        this.valkoisenVangit = 0;
//        this.tarkastaja = new SiirronLaillisuudenTarkastaja();
//        
//        for (byte rivi = 0; rivi < 9; rivi++) {
//            for (byte sarake = 0; sarake < 9; sarake++) {
//                this.peli[rivi][sarake] = new Koordinaatti(rivi, sarake, Vari.TYHJA);
//            }
//        }
//        
//        for (byte rivi = 0; rivi < 9; rivi++) {
//            for (byte sarake = 0; sarake < 9; sarake++) {
//                Koordinaatti koordinaatti = this.peli[rivi][sarake];
//                
//                if (rivi < 8) {
//                    Koordinaatti naapuri = this.peli[rivi + 1][sarake];
//                    koordinaatti.asetaNaapuri(naapuri);
//                }
//                
//                if (rivi > 0) {
//                    Koordinaatti naapuri = this.peli[rivi -1][sarake];
//                    koordinaatti.asetaNaapuri(naapuri);
//                }
//                if (sarake < 8) {
//                    Koordinaatti naapuri = this.peli[rivi][sarake + 1];
//                    koordinaatti.asetaNaapuri(naapuri);
//                }
//                
//                if (sarake > 0) {
//                    Koordinaatti naapuri = this.peli[rivi][sarake - 1];
//                    koordinaatti.asetaNaapuri(naapuri);
//                }
//            }
//        }
//    }
//    
//    /**
//     * Getteri jolta saa t‰m‰nhetkist‰ lautatilannetta vastaavan taulukon.
//     * @return Koordinaatti taulukko, joka kuvaa pelin tilannetta t‰ll‰ hetkell‰.
//     */
//    public Koordinaatti[][] getPeli() {
//        return this.peli;
//    }
//    
//    /**
//     * Metodi jolla saa t‰m‰nhetkisen lautatilanteen tietyn pisteen koordinaatin.
//     * @param y Koordinaatin korkeussuuntainen arvo ylh‰‰lt‰ p‰in.
//     * @param x koordinaatin leveyssuuntainen arvo vasemmalta l‰htien.
//     * @return palauttaa Koordinaatti-olion joka vastaa haluttua pistett‰.
//     */
//    public Koordinaatti getKoordinaatti(int y, int x) {
//        return this.peli[y][x];
//    }
//    
//    /**
//     * Getteri joka kertoo kumman pelaajan vuoro peliss‰ on seuraavaksi.
//     * @return palauttaa true jos on mustan vuoro ja false jos on valkoisen vuoro.
//     */
//    public Vari getVuoro() {
//        return this.kummanVuoro;
//    }
//    
//    /**
//     * Getteri mustan jo vangitsemien kivien m‰‰r‰‰ varten.
//     * @return mustan jo vangitsemien kivien m‰‰r‰ int muodossa.
//     */
//    public int getMustanVangit() {
//        return this.mustanVangit;
//    }
//    
//    /**
//     * Getteri valkoisen jo vangitsemien kivien m‰‰r‰‰ varten.
//     * @return valkoisen jo vangitsemien kivien m‰‰r‰ int muodossa.
//     */
//    public int getValkoisenVangit() {
//        return this.valkoisenVangit;
//    }
//    
//    /**
//     * Palauttaa pelinhallinnoijan k‰ytt‰m‰n siirron laillisuuden tarkistamiseen k‰ytett‰v‰n olion.
//     * @return SiirronLaillisuudenTarkastaja-tyyppinen olio, jota pelin hallinnointi k‰ytt‰‰.
//     */
//    public SiirronLaillisuudenTarkastaja getSiirronLaillisuudenTarkastaja() {
//        return this.tarkastaja;
//    }
//    
//    /**
//     * Metodi joka yritt‰‰ lis‰t‰ uuden siirron peliin, sille pelaajalle kumman vuoro on.
//     * @param koordinaatti johon seuraava siirto halutaan Koordinaatti-tyyppisen‰ oliona joka sis‰lt‰‰ halutun pisteen korkeus- ja leveyssuuntaiset arvot.
//     * @return palauttaa merkkijonon, joka kertoo, onnistuiko siirron lis‰‰minen. "Onnistui" jos siirto lis‰ttiin onnistuneesti, muussa tapauksessa palautetaan
//     * SiirronLaillisuudenTarkastajan palauttama arvo.
//     */
//    public String lisaaSiirto(int y, int x) {
//        Koordinaatti koordinaatti = getKoordinaatti(y, x);
//        
//        if (koordinaatti.getVari() != Vari.TYHJA) {
//            return "On jo kivi";
//        }
//        
//        koordinaatti.setVari(kummanVuoro);
//        
//        HashSet<Ryhma> samanvarisetNaapurit = new HashSet<>();
//        HashSet<Ryhma> erivarisetNaapurit = new HashSet<>();
//        HashSet<Koordinaatti> vapaudet = new HashSet<>();
//        
//        for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
//            if (naapuri.getVari() == Vari.TYHJA) {
//                vapaudet.add(naapuri);
//            } else if (this.kummanVuoro == naapuri.getVari()) {
//                samanvarisetNaapurit.add(naapuri.getRyhma());
//            } else {
//                erivarisetNaapurit.add(naapuri.getRyhma());
//            }
//        }
//        
//        
//        HashSet<Koordinaatti> kivi = new HashSet<>();
//        kivi.add(koordinaatti);
//        Ryhma ryhma = new Ryhma(this.kummanVuoro, kivi, vapaudet);
//        koordinaatti.asetaRyhma(ryhma);
//        
//        for (Ryhma naapuri : samanvarisetNaapurit) {
//            Ryhma yhdistettyRyhma = koordinaatti.getRyhma().yhdista(naapuri);
//            for (Koordinaatti ryhmanKivi : yhdistettyRyhma.getKivet()) {
//                ryhmanKivi.asetaRyhma(yhdistettyRyhma);
//            }
//        }
//        
//        for (Ryhma poistettavaRyhma : this.ryhmat) {
//            if (poistettavaRyhma.kivet.isEmpty()) {
//                this.ryhmat.remove(poistettavaRyhma);
//            }
//        }
//        
//        for (Ryhma erivarinenNaapuri : erivarisetNaapurit) {
//            erivarinenNaapuri.poistaVapaus(koordinaatti);
//            if (erivarinenNaapuri.getVapaudet().isEmpty()) {
//                poistaRyhma(erivarinenNaapuri);
//                this.ryhmat.remove(erivarinenNaapuri);
//            }
//        }
//        return "Onnistui";
//    }
//    
//    private void poistaRyhma(Ryhma ryhma) {
//        for (Koordinaatti koordinaatti : ryhma.getKivet()) {
//            for (Koordinaatti naapuri : koordinaatti.getNaapurit()) {
//                if (naapuri.getVari() == Vari.TYHJA) {
//                    continue;
//                } else {
//                    Ryhma naapuriryhma = naapuri.getRyhma();
//                    if (naapuriryhma.getVari() != ryhma.getVari()) {
//                        naapuriryhma.lisaaVapaus(koordinaatti);
//                        koordinaatti.setVari(Vari.TYHJA);
//                    }
//                }
//            }
//        }
//    }
//} 