package GoPeli.tekoaly;

import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.Random;

public class MCPHSolmu {
    private Pelitilanne pelitilanne;
    private MCPHSolmu vanhempi;
    private Siirto viimeinenSiirto;
    private MCPHSolmu[] lapset;
    private Random random;
    private int mustanVoitot;
    private int valkoisenVoitot;
    private int playoutit;
    private Siirto[] vierailemattomatSiirrot;
    private int vierailemattomiaSiirtoja;
    private int seuraavaTyhjaIndeksiLapsissa;
    
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
    
    public Pelitilanne getPelitilanne() {
        return this.pelitilanne;
    }
    
    public MCPHSolmu getVanhempi() {
        return this.vanhempi;
    }
    
    public Siirto getViimeinenSiirto() {
        return this.viimeinenSiirto;
    }
    
    public int getMustanVoitot() {
        return this.mustanVoitot;
    }
    
    public int getValkoisenVoitot() {
        return this.valkoisenVoitot;
    }
    
    public int getPlayoutit() {
        return this.playoutit;
    }
    
    public MCPHSolmu[] getLapset() {
        return this.lapset;
    }
    
    public Siirto[] getVierailemattomatSiirrot() {
        return this.vierailemattomatSiirrot;
    }
    
    public int getVierailemattomiaSiirtoja() {
        return this.vierailemattomiaSiirtoja;
    }
    
    public int getSeuraavaTyhjaIndeksiLapsissa() {
        return this.seuraavaTyhjaIndeksiLapsissa;
    }
    
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
    
    public void tallennaVoitto(Vari voittaja) {
        if (voittaja == Vari.MUSTA) {
            this.mustanVoitot++;
        } else {
            this.valkoisenVoitot++;
        }
        
        this.playoutit++;
    }
    
    public boolean voiLisataLapsen() {
        return this.vierailemattomatSiirrot[0] != null;
    }
    
    public boolean onLopputilanne() {
        return this.pelitilanne.peliOhi();
    }
    
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
