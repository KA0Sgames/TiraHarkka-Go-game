package GoPeli.tekoaly;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Ryhma;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.HashSet;
import java.util.Random;

/**
 * Monte Carlo Puuhakuun perustuvan teko‰lyn m‰‰ritt‰v‰ luokka.
 */
public class Tekoaly {
    private final Random random;
    private double viimeisenSiirronVoittotodennakoisyys;
    
    /**
     * Konstruktori Tekoaly-olion luontiin ilman parametreja.
     * Ohjelman teko‰lyn p‰‰konstruktori.
     */
    public Tekoaly() {
        this.random = new Random();
    }
    
    /**
     * Konstruktori jolla luodaan Tekoaly-olio annetun satunnaislukugeneraattorin kanssa.
     * P‰‰k‰yttˆtarkoitus ohjelman testaamisessa, kun testien pit‰‰ tuottaa sama tulos joka suorituskerralla.
     * @param random Javan Random-luokan satunnaisgeneraattori, jonka voi luoda seedill‰, jotta generaattori tuottaa aina saman tuloksen samassa tilanteessa.
     */
    public Tekoaly(Random random) {
        this.random = random;
    }
    
    /**
     * Metodi jolla saa viitteen teko‰lyn satunnaisgeneraattoriin.
     * P‰‰k‰yttˆ testeiss‰, kun tarkistetaan, ett‰ satunnaisgeneraattori on asetettu oikein.
     * @return Random - palauttaa teko‰lyn k‰ytt‰m‰n satunnaisgeneraattori-olion.
     */
    public Random getRandom() {
        return this.random;
    }
    
    /**
     * Metodi jolla teko‰ly muodostaa parametrina annettavaan pelitilanteeseen sopivan siirron.
     * 
     * Huom. metodia ei ole viel‰ t‰ysin testattu ja se ei toimi t‰ysin oikein.
     * Tilanteessa jossa lauta on l‰hes t‰ysi (l‰hell‰ lopputilannetta) ja palautettavan siirron tulisi olla passaus (mahdollisesti jo edelt‰v‰ss‰ tilanteessa jossa on vain yksi
     * laillinen paikka pelattavana, joka ei t‰yt‰ omaa silm‰‰) metodi tuottaa NullPointerExceptionin rivill‰ 60. T‰t‰ en ole kerennyt tutkimaan ja korjaamaan, enk‰ kurssin
     * aikarajoissa ehdik‰‰n en‰‰ korjaamaan.
     * 
     * Lis‰ksi t‰m‰nhetkinen toteutus on liian hidas, jotta pelien simulointeja teht‰isiin tarpeeksi j‰rkevi‰ tuloksia varten. K‰yt‰nnˆss‰ kun metodin s‰‰t‰‰ k‰ytt‰m‰‰n 30 sekuntia
     * Monte Carlo hakupuun generointiin, se toimii l‰hestulkoon naiivin botin tasoisesti.
     * 
     * Yrit‰n j‰rjest‰‰ itselleni aikaa tehd‰ parannetun version ohjelmasta kurssin j‰lkeen harrasteprojektina ja korjata yll‰ mainitun bugin uudessa versiossa, sek‰ tehostaa
     * k‰ytett‰vi‰ toteutuksia. Eritoten jos saan Pelitilanne-luokan tarkastamaan ko-s‰‰nnˆn Zobrist hajautusta k‰ytt‰en, sen sijaan ett‰ se tarkistaa kaikki aikaisemmat
     * pelitilanteet linkitetyn rakenteen l‰pik‰yden ja verraten tarkistettavaa lautatilannetta aikaisempiin lautatilanteisiin.
     * 
     * @param tilanne parametrina annettava pelitilanne Pelitilanne-oliona, johon halutaan teko‰lyn valitsevan sen mielest‰ parhaan siirron.
     * @return Siirto - palauttaa valitsemansa siirron Siirto-oliona, joka sis‰lt‰‰ joko pelattavan koordinaatin Koordinaatti-oliona, tai sen passaus- tai luovutusarvo on true.
     */
    public Siirto valitseSiirto(Pelitilanne tilanne){
        MCPHSolmu juuri = new MCPHSolmu(tilanne, null, tilanne.getEdellinenSiirto(), this.random);
        
        long alku = System.currentTimeMillis();
        long loppu = alku + 10 * 1000;
        
        while (System.currentTimeMillis() < loppu) {
            MCPHSolmu solmu = juuri;
            
            while (!solmu.voiLisataLapsen() && !solmu.onLopputilanne()) {
                solmu = valitseLapsi(solmu);
            }
            
            if (solmu.voiLisataLapsen()) {
                solmu = solmu.lisaaSattumanvarainenLapsiPuuhun();
            }
            
            Vari voittaja = simuloiSattumanvarainenPeli(solmu.getPelitilanne());
            
            while (solmu != null) {
                solmu.tallennaVoitto(voittaja);
                solmu = solmu.getVanhempi();
            }
        }
        
        Siirto parasSiirto = null;
        double parhaanVoittotodennakoisyys = -1.0;
        
        int lapsia = 0;
        MCPHSolmu[] lapset = juuri.getLapset();
        
        for (int indeksi = 0; indeksi < lapset.length; indeksi++) {
            if (lapset[indeksi] != null) {
                lapsia++;
            }
        }
        for (int indeksi = 0; indeksi < lapsia; indeksi++) {
            MCPHSolmu lapsi = lapset[indeksi];
            double lapsenVoittotodennakoisyys = lapsi.pelaajanVoittoprosentti(tilanne.getPelaaja());
            
            if (lapsenVoittotodennakoisyys > parhaanVoittotodennakoisyys) {
                parhaanVoittotodennakoisyys = lapsenVoittotodennakoisyys;
                parasSiirto = lapsi.getViimeinenSiirto();
            }
        }
        if (parhaanVoittotodennakoisyys < 0.1) {
            return Siirto.luovutus();
        }
        
        if (parhaanVoittotodennakoisyys - this.viimeisenSiirronVoittotodennakoisyys < -0.3) {
            return Siirto.passaus();
        }
        
        this.viimeisenSiirronVoittotodennakoisyys = parhaanVoittotodennakoisyys;
        return parasSiirto;
    }
    
    private double uctTulos(int vanhemmanPlayoutit, int lapsenPlayoutit, double voittotodennakoisyys, double lampotila) {
        double etsinta = Math.sqrt(Math.log(vanhemmanPlayoutit) / lapsenPlayoutit);
        
        return voittotodennakoisyys + lampotila * etsinta;
    }
    
    private MCPHSolmu valitseLapsi(MCPHSolmu vanhempi) {
        int playoutitYhteensa = 0;
        
        int lapsia = 0;
        MCPHSolmu[] lapset = vanhempi.getLapset();
        
        for (MCPHSolmu lapsi : lapset) {
            if (lapsi != null) {
                lapsia++;
            }
        }
        
        for (int indeksi = 0; indeksi < lapsia; indeksi++) {
            MCPHSolmu lapsi = lapset[indeksi];
            playoutitYhteensa += lapsi.getPlayoutit();
        }
        
        double parasTulos = -1.0;
        MCPHSolmu parasLapsi = null;
        
        for (int indeksi = 0; indeksi < lapsia; indeksi++) {
            MCPHSolmu lapsi = lapset[indeksi];
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
            Siirto[] lailliset = tilanne.laillisetSiirrot();
            Siirto[] kandidaatit = new Siirto[lailliset.length];
            int seuraavaTyhja = 0;
            int laillisia = 0;
            
            for (Siirto laillinen : lailliset) {
                if (laillinen != null) {
                    laillisia++;
                }
            }
            
            for (int laillistenIndeksi = 0; laillistenIndeksi < laillisia; laillistenIndeksi++) {
                Siirto kandidaatti = lailliset[laillistenIndeksi];
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
                boolean lisattiin = false;
                for (Koordinaatti naapuri : naapurit) {
                    Ryhma ryhma = tilanne.getPelilauta().etsiRyhma(naapuri);
                    

                    if (ryhma == null) {
                        kandidaatit[seuraavaTyhja] = kandidaatti;
                        seuraavaTyhja++;
                        lisattiin = true;
                        break;
                    } else if (ryhma.getVari() != tilanne.getPelaaja()) {
                        kandidaatit[seuraavaTyhja] = kandidaatti;
                        seuraavaTyhja++;
                        lisattiin = true;
                        break;
                    }
                }
                
                /*
                    Jos edellisess‰ vaiheessa ei lis‰tty kandidaattia niin tarkastellaan
                    kulmapisteit‰.
                */
                if (!lisattiin) {
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
                            Jos jokin nurkka on laudan ulkopuolella, niin laudan ulkopuolisten ja
                            omien nurkkien summan tulee olla 4, jotta piste on silm‰. Jos kaikki
                            nurkat ovat laudalla, niin riitt‰‰, ett‰ v‰hint‰‰n kolme on omia.
                            Jos piste ei ole silm‰, niin lis‰t‰‰n kandidaatti.
                        */
                    if (laudanUlkopuolisetNurkat > 0) {
                        if (laudanUlkopuolisetNurkat + omatNurkat != 4) {
                            kandidaatit[seuraavaTyhja] = kandidaatti;
                            seuraavaTyhja++;
                        }
                    } else if (omatNurkat < 3) {
                        kandidaatit[seuraavaTyhja] = kandidaatti;
                        seuraavaTyhja++;
                    }
                }
            }

            if (kandidaatit[0] == null) {
                tilanne.setSiirto(Siirto.passaus());
            } else {
                Siirto siirto = kandidaatit[this.random.nextInt(seuraavaTyhja)];
                tilanne.setSiirto(siirto);
            }
            tilanne = tilanne.lisaaSiirto();
        }
        return tilanne.laskeVoittaja();
    }
}
