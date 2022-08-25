package GoPeli.tekoaly;

import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.Random;

public class Tekoaly {
    private Random random;
    
    public Tekoaly() {
        this.random = new Random();
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
    
    private Vari simuloiSattumanvarainenPeli(Pelitilanne lahtotilanne) {
        // TODO
        return Vari.MUSTA;
    }
}
