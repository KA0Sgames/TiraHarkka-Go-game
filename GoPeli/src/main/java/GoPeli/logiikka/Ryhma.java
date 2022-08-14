package GoPeli.logiikka;

import java.util.HashSet;
import java.util.Objects;

public class Ryhma {
    private final Vari vari;
    private HashSet<Koordinaatti> kivet;
    private HashSet<Koordinaatti> vapaudet;
    
    public Ryhma(Vari vari, HashSet<Koordinaatti> kivet, HashSet<Koordinaatti> vapaudet) {
        this.vari = vari;
        this.kivet = kivet;
        this.vapaudet = vapaudet;
    }
    
    /**
     * Kopiokonstruktori, jolla luodaan uusi ryhm�, jolla alkuper�ist� vastaavat arvot ja setit, mutta oliot
     * seteiss� uusia, mutta alkuper�ist� vastaavia, eli niiden muuttaminen ei vaikuta alkuper�isen ryhm�n olioihin.
     * @param ryhma parametrina kopioitava ryhm�.
     */
    public Ryhma(Ryhma ryhma) {
        this.vari = ryhma.vari;
        this.vapaudet = new HashSet<>();
        this.kivet = new HashSet<>();
        
        for (Koordinaatti koordinaatti : ryhma.vapaudet) {
            this.vapaudet.add(new Koordinaatti(koordinaatti));
        }
        
        for (Koordinaatti koordinaatti : ryhma.kivet) {
            this.kivet.add(new Koordinaatti(koordinaatti));
        }
    }
    
    public void poistaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.remove(koordinaatti);
    }
    
    public void lisaaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.add(koordinaatti);
    }
    
    public Ryhma yhdista(Ryhma ryhma) {
        if (this.vari == ryhma.vari) {
            this.kivet.addAll(ryhma.getKivet());
            this.vapaudet.addAll(ryhma.getVapaudet());
        
            for (Koordinaatti koordinaatti : this.kivet) {
                if (this.vapaudet.contains(koordinaatti)) {
                    this.vapaudet.remove(koordinaatti);
                }
            }
        }
        
        return new Ryhma(this.vari, this.kivet, this.vapaudet);
    }
    
    public Vari getVari() {
        return this.vari;
    }
    
    public HashSet<Koordinaatti> getKivet() {
        return this.kivet;
    }
    
    public HashSet<Koordinaatti> getVapaudet() {
        return this.vapaudet;
    }
    
    public int vapauksienMaara() {
        return this.vapaudet.size();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.vari);
        hash = 67 * hash + Objects.hashCode(this.kivet);
        hash = 67 * hash + Objects.hashCode(this.vapaudet);
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
        final Ryhma other = (Ryhma) obj;
        if (this.vari != other.vari) {
            return false;
        }
        if (!Objects.equals(this.kivet, other.kivet)) {
            return false;
        }
        return Objects.equals(this.vapaudet, other.vapaudet);
    }
}
