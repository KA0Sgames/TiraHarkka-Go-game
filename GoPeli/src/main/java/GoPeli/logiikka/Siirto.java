package GoPeli.logiikka;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.koordinaatti);
        hash = 47 * hash + (this.passaus ? 1 : 0);
        hash = 47 * hash + (this.luovutus ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Siirto other = (Siirto) obj;
        if (this.passaus != other.passaus) {
            return false;
        }
        if (this.luovutus != other.luovutus) {
            return false;
        }
        return Objects.equals(this.koordinaatti, other.koordinaatti);
    }
    
    
}
