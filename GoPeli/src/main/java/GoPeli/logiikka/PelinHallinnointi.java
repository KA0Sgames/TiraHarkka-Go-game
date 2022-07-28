package GoPeli.logiikka;

public class PelinHallinnointi {
    
    private byte[][] peli;
    private boolean mustanVuoro;
    
    
    public PelinHallinnointi() {
        this.peli = new byte[9][9];
        this.mustanVuoro = true;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                peli[i][j] = 0;
            }
        }
    }
    
    public byte getKoordinaatinTila(Koordinaatti koordinaatti) {
        return this.peli[koordinaatti.getXKoordinaatti()][koordinaatti.getYKoordinaatti()];
    }
    
    public boolean getMustanVuoro() {
        return this.mustanVuoro;
    }
    
    public String lisaaSiirto(Koordinaatti koordinaatti) {
        String onkoLaillinen = tarkistaSiirronLaillisuus(koordinaatti);
        
        if (onkoLaillinen == "On") {
            if (this.mustanVuoro) {
                this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = 1;
                this.mustanVuoro = !this.mustanVuoro;
                return "Onnistui";
            } else {
                this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] = 2;
                this.mustanVuoro = !this.mustanVuoro;
                return "Onnistui";
            }
        } else {
            return onkoLaillinen;
        }
    }
    
    private String tarkistaSiirronLaillisuus(Koordinaatti koordinaatti) {
        if (this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()] != 0) {
            return "On jo kivi";
        }
        
        return "On";
    }
    
    
    
    
    
}