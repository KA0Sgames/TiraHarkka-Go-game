package GoPeli.tekoaly;

import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;

public class MCPHSolmu {
    private Pelitilanne pelitilanne;
    private MCPHSolmu vanhempi;
    private Siirto viimeinenSiirto;
    private MCPHSolmu[] lapset;
    private int mustanVoitot;
    private int valkoisenVoitot;
    private int playoutit;
    private Siirto[] vierailemattomatSiirrot;
    
    public MCPHSolmu(Pelitilanne pelitilanne, MCPHSolmu vanhempi, Siirto viimeinenSiirto) {
        this.pelitilanne = pelitilanne;
        this.vanhempi = vanhempi;
        this.viimeinenSiirto = viimeinenSiirto;
        this.mustanVoitot = 0;
        this.valkoisenVoitot = 0;
        this.playoutit = 0;
        this.lapset = new MCPHSolmu[10];
        this.vierailemattomatSiirrot = pelitilanne.laillisetSiirrot();
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
    
    public void lisaaSattumanvarainenLapsiPuuhun() {
        // TODO
    }
}
