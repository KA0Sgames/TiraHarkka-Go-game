package GoPeli.logiikka;

public class PelinHallinnointi {
    
    private byte[][] peli;
    private boolean mustanVuoro;
    private int mustanVangit;
    private int valkoisenVangit;
    private SiirronLaillisuudenTarkastaja tarkastaja;
    
    
    public PelinHallinnointi() {
        this.peli = new byte[9][9];
        this.mustanVuoro = true;
        this.mustanVangit = 0;
        this.valkoisenVangit = 0;
        this.tarkastaja = new SiirronLaillisuudenTarkastaja();
    }
    
    public byte[][] getPeli() {
        return this.peli;
    }
    
    public byte getKoordinaatinTila(Koordinaatti koordinaatti) {
        return this.peli[koordinaatti.getYKoordinaatti()][koordinaatti.getXKoordinaatti()];
    }
    
    public boolean getMustanVuoro() {
        return this.mustanVuoro;
    }
    
    public int getMustanVangit() {
        return this.mustanVangit;
    }
    
    public int getValkoisenVangit() {
        return this.valkoisenVangit;
    }
    
    public SiirronLaillisuudenTarkastaja getSiirronLaillisuudenTarkastaja() {
        return this.tarkastaja;
    }
    
    public String lisaaSiirto(Koordinaatti koordinaatti) {
        String onkoLaillinen = tarkastaja.onkoLaillinen(this.peli, mustanVuoro, koordinaatti);
        
        if (onkoLaillinen.equals("On")) {
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
}