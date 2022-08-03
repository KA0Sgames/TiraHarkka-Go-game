package GoPeli.logiikka;

public class SiirronLaillisuudenTarkastaja {
    
    public SiirronLaillisuudenTarkastaja() {
        
    }
    
    public String onkoLaillinen(byte[][] peli, boolean mustanVuoro, Koordinaatti koordinaatti) {
        if (onkoJoKivi(peli, koordinaatti)) {
            return "On jo kivi";
        }
        
        return "On";
    }
    
    private boolean onkoJoKivi(byte[][] peli, Koordinaatti koordinaatti){
        return peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] != 0;
    }
}
