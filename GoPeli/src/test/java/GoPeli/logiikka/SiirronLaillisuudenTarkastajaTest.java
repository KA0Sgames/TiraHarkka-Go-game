package GoPeli.logiikka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SiirronLaillisuudenTarkastajaTest {
    
    public SiirronLaillisuudenTarkastajaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void onkoLaillinenVastaaOikeinKivenPaallePelattaessa() {
        byte[][] peli = new byte[9][9];
        SiirronLaillisuudenTarkastaja tarkastaja = new SiirronLaillisuudenTarkastaja();
        
        peli[1][2] = 1;
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertEquals("On jo kivi", tarkastaja.onkoLaillinen(peli, true, koordinaatti));
    }
}
