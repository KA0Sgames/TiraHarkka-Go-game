package GoPeli.tekoaly;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Ryhma;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.HashSet;
import java.util.Random;

public class Tekoaly {
    private Random random;
    
    public Tekoaly() {
        this.random = new Random();
    }
    
    public Tekoaly(Random random) {
        this.random = random;
    }
    
    public Siirto valitseSiirto(Pelitilanne tilanne){
        MCPHSolmu juuri = new MCPHSolmu(tilanne, null, tilanne.getEdellinenSiirto(), this.random);
        
        long alku = System.currentTimeMillis();
        long loppu = alku + 30 * 1000;
        
        while (System.currentTimeMillis() < loppu) {
            MCPHSolmu solmu = juuri;
            
            while (!solmu.voiLisataLapsen() && !solmu.onLopputilanne()) {
                solmu = valitseLapsi(solmu);
            }
            
            if (solmu.voiLisataLapsen()) {
                solmu = solmu.lisaaSattumanvarainenLapsiPuuhun();
            }
            
            // TODO simuloiSattumanvarainenPeli
            Vari voittaja = simuloiSattumanvarainenPeli(solmu.getPelitilanne());
            
            while (solmu != null) {
                solmu.tallennaVoitto(voittaja);
                solmu = solmu.getVanhempi();
            }
        }
        
        Siirto parasSiirto = null;
        double parhaanVoittotodennakoisyys = -1.0;
        
        for (MCPHSolmu lapsi : juuri.getLapset()) {
            double lapsenVoittotodennakoisyys = lapsi.pelaajanVoittoprosentti(tilanne.getPelaaja());
            
            if (lapsenVoittotodennakoisyys > parhaanVoittotodennakoisyys) {
                parhaanVoittotodennakoisyys = lapsenVoittotodennakoisyys;
                parasSiirto = lapsi.getViimeinenSiirto();
            }
        }
        return parasSiirto;
    }
    
    private double uctTulos(int vanhemmanPlayoutit, int lapsenPlayoutit, double voittotodennakoisyys, double lampotila) {
        double etsinta = Math.sqrt(Math.log(vanhemmanPlayoutit) / lapsenPlayoutit);
        
        return voittotodennakoisyys + lampotila * etsinta;
    }
    
    private MCPHSolmu valitseLapsi(MCPHSolmu vanhempi) {
        int playoutitYhteensa = 0;
        
        for (MCPHSolmu lapsi : vanhempi.getLapset()) {
            playoutitYhteensa += lapsi.getPlayoutit();
        }
        
        double parasTulos = -1.0;
        MCPHSolmu parasLapsi = null;
        
        for (MCPHSolmu lapsi : vanhempi.getLapset()) {
            double uctTulos = uctTulos(playoutitYhteensa, lapsi.getPlayoutit(), lapsi.pelaajanVoittoprosentti(vanhempi.getPelitilanne().getPelaaja()), 1.5);
            if (uctTulos > parasTulos) {
                parasTulos = uctTulos;
                parasLapsi = lapsi;
            }
        }
        return parasLapsi;
    }
    
    private Vari simuloiSattumanvarainenPeli(Pelitilanne tilanne) {
        while(!tilanne.peliOhi()) {
            Siirto[] kandidaatit = tilanne.laillisetSiirrot();
            
            int tyhjaIndeksi = kandidaatit.length;
            
            for (int indeksi = 0; indeksi < kandidaatit.length; indeksi++) {
                if (kandidaatit[indeksi] == null) {
                    tyhjaIndeksi = indeksi;
                    break;
                }
            }
            
            while (tyhjaIndeksi > 0) {
                tilanne.setSiirto(null);
            
                int sattumanvarainenIndeksi = this.random.nextInt(tyhjaIndeksi);
                Siirto kandidaatti = kandidaatit[sattumanvarainenIndeksi];
            
                /*
                    Siirto ei saa t‰ytt‰‰ omaa silm‰‰. Piste on silm‰, jos sen jokaisessa naapurissa
                    vaaka ja pystysuunnalla on samanv‰rinen kivi, sek‰ jos piste ei ole laudan
                    reunalla niin kolmessa nelj‰st‰ kulmittain viereisess‰ pisteess‰ on samanv‰rinen
                    kivi, tai jos siirto on laudan reunalla, niin kaikissa kulmittain viereisiss‰
                    pisteiss‰ on samanv‰rinen kivi.
                */
                HashSet<Koordinaatti> naapurit = kandidaatti.getKoordinaatti().getNaapurit();
            
                /*
                    Tarkastetaan pysty ja vaakasuunnan naapurit, jos jokin n‰ist‰ on tyhj‰ tai siin‰
                    on vastustajan kivi, niin siirto ei t‰yt‰ omaa silm‰‰ ja voimme hyv‰ksy‰
                    kandidaatin.
                */
                for (Koordinaatti naapuri : naapurit) {
                    Ryhma ryhma = tilanne.getPelilauta().etsiRyhma(naapuri);

                    if (ryhma == null) {
                        tilanne.setSiirto(kandidaatti);
                        break;
                    } else if (ryhma.getVari() != tilanne.getPelaaja()) {
                        tilanne.setSiirto(kandidaatti);
                        break;
                    }
                }
                if (tilanne.getSiirto() != null) {
                    break;
                } else {
                    System.out.println("thsiu");
                }
            
                /*
                    Jos edellisess‰ vaiheessa ei ole asetettu seuraavaa siirtoa (hyv‰ksytty
                    kandidaattia) niin tarkastellaan kulmapisteit‰.
                */
                Koordinaatti[] kulmaKoordinaatit = new Koordinaatti[4];
                
                kulmaKoordinaatit[0] = new Koordinaatti(
                        (byte) (kandidaatti.getKoordinaatti().getYKoordinaatti() - 1),
                        (byte) (kandidaatti.getKoordinaatti().getXKoordinaatti() - 1));
                
                kulmaKoordinaatit[1] = new Koordinaatti(
                        (byte) (kandidaatti.getKoordinaatti().getYKoordinaatti() + 1),
                        (byte) (kandidaatti.getKoordinaatti().getXKoordinaatti() - 1));
                
                kulmaKoordinaatit[2] = new Koordinaatti(
                        (byte) (kandidaatti.getKoordinaatti().getYKoordinaatti() - 1),
                        (byte) (kandidaatti.getKoordinaatti().getXKoordinaatti() + 1));
                
                kulmaKoordinaatit[3] = new Koordinaatti(
                        (byte) (kandidaatti.getKoordinaatti().getYKoordinaatti() + 1),
                        (byte) (kandidaatti.getKoordinaatti().getXKoordinaatti() + 1));
                
                int laudanUlkopuolisetNurkat = 0;
                int omatNurkat = 0;
                
                for (int indeksi = 0; indeksi < 4; indeksi++) {
                    // Jos piste on laudan ulkopuolella :
                    if (
                            kulmaKoordinaatit[indeksi].getYKoordinaatti() < 0 ||
                            kulmaKoordinaatit[indeksi].getYKoordinaatti() > 8 ||
                            kulmaKoordinaatit[indeksi].getXKoordinaatti() < 0 ||
                            kulmaKoordinaatit[indeksi].getXKoordinaatti() > 8) {
                        laudanUlkopuolisetNurkat++;
                        
                    // Piste on laudan sis‰puolella :
                    } else {
                        Ryhma ryhma = tilanne.getPelilauta().etsiRyhma(kulmaKoordinaatit[indeksi]);
                        
                        if (ryhma == null) {
                            continue;
                        }
                        if (ryhma.getVari() == tilanne.getPelaaja()) {
                            omatNurkat++;
                        }
                    }
                }
                
                /*
                    Jos jokin nurkka on laudan ulkopuolella, niin laudan ulkopuolisten ja omien
                    nurkkien summan tulee olla 4, jotta piste on silm‰. Jos kaikki nurkat ovat
                    laudalla, niin riitt‰‰, ett‰ v‰hint‰‰n kolme on omia.
                    Jos piste ei ole silm‰, niin lis‰t‰‰n kandidaatti.
                */
                if (laudanUlkopuolisetNurkat > 0) {
                    if (laudanUlkopuolisetNurkat + omatNurkat != 4) {
                        tilanne.setSiirto(kandidaatti);
                        System.out.println("Laudan ulkopuoliset + omat != 4");
                        break;
                    }
                } else if (omatNurkat < 3) {
                    tilanne.setSiirto(kandidaatti);
                    break;
                }
                
                /*
                    Jos kandidaattia ei ole lis‰tty, niin se tarkoittaa, ett‰ se t‰ytt‰isi oman
                    silm‰n ja se pit‰‰ poistaa kandidaattien joukosta.
                */
                Siirto[] uusiTaulukko = new Siirto[kandidaatit.length];
                
                for (int indeksi = 0; indeksi < sattumanvarainenIndeksi; indeksi++) {
                    uusiTaulukko[indeksi] = kandidaatit[indeksi];
                }
                
                for (int indeksi = sattumanvarainenIndeksi + 1; indeksi < kandidaatit.length; indeksi++) {
                    uusiTaulukko[indeksi - 1] = kandidaatit[indeksi];
                }
                kandidaatit = uusiTaulukko;
                tyhjaIndeksi--;
            }
            
            System.out.println(tilanne.getSiirto() == null);
            if (tilanne.getSiirto() == null) {
                tilanne.setSiirto(Siirto.passaus());
            }
            
            tilanne = tilanne.lisaaSiirto();
        }
        return tilanne.laskeVoittaja();
    }
}
