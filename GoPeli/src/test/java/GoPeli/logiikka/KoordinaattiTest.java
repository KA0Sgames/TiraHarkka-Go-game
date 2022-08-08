package GoPeli.logiikka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KoordinaattiTest {
    
//    public KoordinaattiTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    @Test
    public void konstruktoriLuoKoordinaattiOlion() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertNotNull(koordinaatti);
    }
    
    @Test
    public void konstruktoriAsettaaYArvonOikeinJaGetYKoordinaattiToimii() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertEquals(1, koordinaatti.getYKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaXArvonOikeinJaGetXKoordinaattiToimii() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertEquals(2, koordinaatti.getXKoordinaatti());
    }
    
    @Test
    public void getNaapuritPalauttaaOikeanKokoisenSetin1() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 0);
        
        assertEquals(2, koordinaatti.getNaapurit().size());
    }
    
    @Test
    public void getNaapuritPalauttaaOikeanKokoisenSetin2() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 1);
        
        assertEquals(3, koordinaatti.getNaapurit().size());
    }
    
    @Test
    public void getNaapuritPalauttaaOikeanKokoisenSetin3() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 3, (byte) 4);
        
        assertEquals(4, koordinaatti.getNaapurit().size());
    }
    
    @Test
    public void getNaapuritPalauttaaOikeatNaapurit1() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 2, (byte) 2);
        
        assertTrue(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));        
    }
    
    @Test
    public void getNaapuritPalauttaaOikeatNaapurit2() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 0, (byte) 2);
        
        assertTrue(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));     
    }
    
    @Test
    public void getNaapuritPalauttaaOikeatNaapurit3() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 1, (byte) 1);
        
        assertTrue(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));     
    }
    
    @Test
    public void getNaapuritPalauttaaOikeatNaapurit4() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 1, (byte) 3);
        
        assertTrue(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));     
    }
    
    @Test
    public void getNaapuritEiPalautaLaudanUlkopuolisiaKoordinaatteja1 () {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 0);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) -1, (byte) 0);
        
        assertFalse(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));  
    }
    
    @Test
    public void getNaapuritEiPalautaLaudanUlkopuolisiaKoordinaatteja2 () {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 0);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 0, (byte) -1);
        
        assertFalse(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));  
    }
    
    @Test
    public void getNaapuritEiPalautaLaudanUlkopuolisiaKoordinaatteja3 () {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 8, (byte) 8);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 9, (byte) 8);
        
        assertFalse(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));  
    }
    
    @Test
    public void getNaapuritEiPalautaLaudanUlkopuolisiaKoordinaatteja4 () {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 8, (byte) 8);
        Koordinaatti naapuriKoordinaatti = new Koordinaatti((byte) 8, (byte) 9);
        
        assertFalse(koordinaatti.getNaapurit().contains(naapuriKoordinaatti));  
    }
}
