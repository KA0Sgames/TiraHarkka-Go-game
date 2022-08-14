package GoPeli.logiikka;

public class Siirto {
    private Koordinaatti koordinaatti;
    private boolean passaus;
    private boolean luovutus;
    
    public Siirto(Koordinaatti koordinaatti, boolean passaus, boolean luovutus) {
        this.koordinaatti = koordinaatti;
        this.passaus = passaus;
        this.luovutus = luovutus;
    }
    
    public static Siirto pelaa(Koordinaatti koordinaatti) {
        return new Siirto(koordinaatti, false, false);
    }
    
    public static Siirto passaus() {
        return new Siirto(null, true, false);
    }
    
    public static Siirto luovutus() {
        return new Siirto(null, false, true);
    }
    
    public Koordinaatti getKoordinaatti() {
        return this.koordinaatti;
    }
    
    public boolean getPassaus() {
        return this.passaus;
    }
    
    public boolean getLuovutus() {
        return this.luovutus;
    }
}
