package GoPeli.logiikka;

import java.util.HashSet;
import java.util.Objects;

/**
 * Luokka kuvaamaan yhten‰isi‰ kiviryhmi‰.
 * Sis‰lt‰‰ ryhm‰n kivet ja vapaudet HashSeteiss‰ Koordinaatti-olioina, sek‰ kiviryhm‰n v‰rin Enum Vari muodossa.
 */
public class Ryhma {
    private final Vari vari;
    private HashSet<Koordinaatti> kivet;
    private HashSet<Koordinaatti> vapaudet;
    
    /**
     * Konstruktori jolla luodaan uusi ryhm‰.
     * @param vari ryhm‰n kivien v‰ri Enum Vari muodossa.
     * @param kivet HashSet<Koordinaatti> joka sis‰lt‰‰ ryhm‰‰n kuuluvat kivet ryhm‰n muodostamishetkell‰ Koordinaatti-olioina.
     * @param vapaudet HashSet<Koordinaatti> joka sis‰lt‰‰ ryhm‰n vapaudet ryhm‰n muodostamishetkell‰ Koordinaatti-olioina.
     */
    public Ryhma(Vari vari, HashSet<Koordinaatti> kivet, HashSet<Koordinaatti> vapaudet) {
        this.vari = vari;
        this.kivet = kivet;
        this.vapaudet = vapaudet;
    }
    
    /**
     * Kopiokonstruktori, jolla luodaan uusi ryhm‰ alkuper‰ist‰ vastaavilla arvoilla. 
     * Uudella ryhm‰ll‰ on alkuper‰ist‰ vastaavat arvot ja setit. Oliot seteiss‰ ovat uusia, mutta alkuper‰ist‰ vastaavia, eli niiden muuttaminen ei vaikuta alkuper‰isen
     * ryhm‰n olioihin.
     * @param ryhma parametrina alkuper‰inen ryhm‰, jota vastaava kopio halutaan tehd‰.
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
    
    /**
     * Metodi jolla voi v‰hent‰‰ halutun vapauden ryhm‰lt‰.
     * K‰yttˆtarkoituksena, kun ryhm‰n kiven viereen pelataan kivi, niin kyseisest‰ pisteest‰ poistuu vapaus.
     * @param koordinaatti piste josta vapaus v‰henee Koordinaatti-oliolla kuvattuna.
     */
    public void poistaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.remove(koordinaatti);
    }
    
    /**
     * Metodi jolla voi lis‰t‰ vapauden ryhm‰lle haluttuun pisteeseen.
     * K‰yttˆtarkoitus, kun uusi siirto syˆ kivi‰, jotka ovat ryhm‰n kivien vieress‰, niin syˆtyjen kivien tilalle syntyy vapauksia.
     * @param koordinaatti piste johon vapaus halutaan lis‰t‰ Koordinaatti-oliolla kuvattuna.
     */
    public void lisaaVapaus(Koordinaatti koordinaatti) {
        this.vapaudet.add(koordinaatti);
    }
    
    /**
     * Metodi jolla yhdistet‰‰n yhdeksi suuremmaksi yhten‰iseksi ryhm‰ksi.
     * K‰yttˆtarkoitus, kun ryhm‰n viereen lis‰t‰‰n samanv‰rinen kivi, t‰m‰ kivi liitet‰‰n ryhm‰‰n, ja palautetaan uusi ryhm‰, joka koostuu kaikista kivist‰.
     * Jos lis‰tt‰v‰ kivi koskee useampaan erilliseen samanv‰riseen ryhm‰‰n, niin kivi liitet‰‰n ensin yhteen ryhm‰‰n ja paluuarvona saatava ryhm‰ voidaan liitt‰‰ t‰ll‰
     * samalla metodilla seuraavaan ryhm‰‰n, sill‰ metodi ei vaadi, ett‰ k‰sitelt‰v‰ss‰ ryhm‰ss‰ olisi vain yksi kivi.
     * @param ryhma parametriksi annetaan ryhm‰ Ryhma-oliona, johon k‰sitelt‰v‰ ryhm‰ halutaan liitt‰‰.
     * @return Ryhma - metodi palauttaa uuden ryhm‰n, jossa on molempien yhdistett‰vien ryhmien kivet ja vapaudet, poislukien vapaus, joka on poistunut uuden kiven lis‰‰misen
     * seurauksena.
     */
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
    
    /**
     * Metodi palauttaa ryhm‰n kivien v‰rin.
     * @return Vari - ryhm‰n kivien (pelaajan kelle ryhm‰ kuuluu) v‰ri Enum Vari arvoisena.
     */
    public Vari getVari() {
        return this.vari;
    }
    
    /**
     * Metodi palauttaa HashSetin, joka sis‰lt‰‰ ryhm‰n kivet.
     * @return HashSet<Koordinaatti> - kokoelma ryhm‰n kivist‰ kuvattuna Koordinaatti-olioina.
     */
    public HashSet<Koordinaatti> getKivet() {
        return this.kivet;
    }
    
    /**
     * Metodi palauttaa HashSetin, joka sis‰lt‰‰ ryhm‰n vapaudet.
     * @return HashSet<Koordinaatti> - kokoelma ryhm‰n vapauksista kuvattuna Koordinaatti-olioina.
     */
    public HashSet<Koordinaatti> getVapaudet() {
        return this.vapaudet;
    }
    
    /**
     * Metodi palauttaa ryhm‰n vapauksien m‰‰r‰n kokonaislukuna.
     * K‰yttˆtarkoituksena nopea tarkistus, onko ryhm‰ll‰ vapauksia j‰ljell‰. Jos ei ole niin ryhm‰ tulee poistaa laudalta.
     * @return int - ryhm‰n vapauksien m‰‰r‰, eli kokoelman vapauksista koko.
     */
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

/**
 * Equals jolla verrataan vastaavatko kaksi oliota toisiaan.
 * Palauttaa true, jos verrataan ryhm‰‰ itseens‰.
 * Palauttaa false, jos verrataan ryhm‰‰ null-arvoon tai toisesta luokasta konstruoituun olioon.
 * Palauttaa true, jos verrattavien Ryhma-olioiden v‰ri on sama ja ne sis‰lt‰v‰t toisiaan vastaavat kivet ja vapaudet, muuten false.
 * @param obj parametrina annetaan olio, johon verrataan haluttua Ryhma-oliota.
 * @return true/false - true jos verrattavat oliot ovat samat, tai jos molemmat ovat Ryhma-olioita joilla on sama v‰ri, sek‰ toisiaan vastaavat kivet ja vapaudet, muuten false.
 */
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
