package GoPeli.logiikka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SiirtoTest {
    
    public SiirtoTest() {
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
    public void konstruktoriLuoSiirtoOlion() {
        Siirto siirto = new Siirto(null, false, false);
        
        assertNotNull(siirto);
    }
    
    @Test
    public void konstruktorinAsettamaKoordinaattiOnOlio() {
        Siirto siirto = new Siirto(new Koordinaatti((byte) 1, (byte) 2), false, false);
        
        assertNotNull(siirto.getKoordinaatti());
    }
    
    public void konstruktoriAsettaaOikeanKoordinaatinJaGetKoordinaattiToimii1() {
        Siirto siirto = new Siirto(new Koordinaatti((byte) 1, (byte) 2), false, false);
        
        assertEquals(1, siirto.getKoordinaatti().getYKoordinaatti());
    }
    
    public void konstruktoriAsettaaOikeanKoordinaatinJaGetKoordinaattiToimii2() {
        Siirto siirto = new Siirto(new Koordinaatti((byte) 1, (byte) 2), false, false);
        
        assertEquals(2, siirto.getKoordinaatti().getXKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaPassaukselleArvonOikeinJaGetPassausToimii1() {
        Siirto siirto = new Siirto(null, true, false);
        
        assertTrue(siirto.getPassaus());
    }
    
    @Test
    public void konstruktoriAsettaaPassaukselleArvonOikeinJaGetPassausToimii2() {
        Siirto siirto = new Siirto(null, false, false);
        
        assertFalse(siirto.getPassaus());
    }
    
    @Test
    public void konstruktoriAsettaaLuovutukselleArvonOikeinJaGetLuovutusToimii1() {
        Siirto siirto = new Siirto(null, false, true);
        
        assertTrue(siirto.getLuovutus());
    }
    
    @Test
    public void konstruktoriAsettaaLuovutukselleArvonOikeinJaGetLuovutusToimii2() {
        Siirto siirto = new Siirto(null, false, false);
        
        assertFalse(siirto.getLuovutus());
    }
    
    @Test
    public void pelaaMetodiLuoOikeanOlion1() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(1, siirto.getKoordinaatti().getYKoordinaatti());
    }
    
    @Test
    public void pelaaMetodiLuoOikeanOlion2() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(2, siirto.getKoordinaatti().getXKoordinaatti());
    }
    
    @Test
    public void pelaaMetodiLuoOikeanOlion3() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        
        assertFalse(siirto.getPassaus());
    }
    
    @Test
    public void pelaaMetodiLuoOikeanOlion4() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        
        assertFalse(siirto.getLuovutus());
    }
    
    @Test
    public void passausMetodiLuoOikeanOlion1() {
        Siirto siirto = Siirto.passaus();
        
        assertNull(siirto.getKoordinaatti());
    }
    
    @Test
    public void passausMetodiLuoOikeanOlion2() {
        Siirto siirto = Siirto.passaus();
        
        assertTrue(siirto.getPassaus());
    }
    
    @Test
    public void passausMetodiLuoOikeanOlion3() {
        Siirto siirto = Siirto.passaus();
        
        assertFalse(siirto.getLuovutus());
    }
    
    @Test
    public void luovutusMetodiLuoOikeanOlion1() {
        Siirto siirto = Siirto.luovutus();
        
        assertNull(siirto.getKoordinaatti());
    }
    
    @Test
    public void luovutusMetodiLuoOikeanOlion2() {
        Siirto siirto = Siirto.luovutus();
        
        assertFalse(siirto.getPassaus());
    }
    
    @Test
    public void luovutusMetodiLuoOikeanOlion3() {
        Siirto siirto = Siirto.luovutus();
        
        assertTrue(siirto.getLuovutus());
    }
}
