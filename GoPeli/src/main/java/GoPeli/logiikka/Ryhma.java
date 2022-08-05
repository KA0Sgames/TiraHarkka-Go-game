package GoPeli.logiikka;

import java.util.HashSet;

public class Ryhma {
    final Vari vari;
    HashSet<Koordinaatti> kivet;
    HashSet<Koordinaatti> vapaudet;
    
    public Ryhma(Vari vari, HashSet<Koordinaatti> kivet, HashSet<Koordinaatti> vapaudet) {
        this.vari = vari;
        this.kivet = kivet;
        this.vapaudet = vapaudet;
    }
    
    public void poistaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.remove(koordinaatti);
    }
    
    public void lisaaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.add(koordinaatti);
    }
    
    public Ryhma yhdista(Ryhma ryhma) {
        this.kivet.addAll(ryhma.getKivet());
        
        for (Koordinaatti koordinaatti : this.kivet) {
            if (this.vapaudet.contains(koordinaatti)) {
                this.vapaudet.remove(koordinaatti);
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
}
