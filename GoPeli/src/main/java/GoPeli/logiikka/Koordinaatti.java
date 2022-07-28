package GoPeli.logiikka;

public class Koordinaatti {
    private final byte x;
    private final byte y;
    
    public Koordinaatti(byte x, byte y) {
        this.x = x;
        this.y = y;
    }
    
    public byte getXKoordinaatti() {
        return this.x;
    }
    
    public byte getYKoordinaatti() {
        return this.y;
    }
}
