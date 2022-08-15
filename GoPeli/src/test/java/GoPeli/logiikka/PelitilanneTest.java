package GoPeli.logiikka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PelitilanneTest {
    
    public PelitilanneTest() {
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
    public void uusiPeliPalauttaaOlion() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertNotNull(tilanne);
    }
    
    @Test
    public void uusiPeliPalauttaaOlionJollaEiOleRyhmia() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertTrue(tilanne.getPelilauta().getRyhmat().isEmpty());
    }
    
    @Test
    public void uusiPeliPalauttaaOlionJonkaSeuraavaPelaajaOnMusta() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertEquals(Vari.MUSTA, tilanne.getPelaaja());
    }
    
    @Test
    public void uusiPeliPalauttaaOlionJonkaEdellinenTilanneOnNull() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertNull(tilanne.getEdellinenTilanne());
    }
    
    @Test
    public void uusiPeliPalauttaaOlionJonkaSiirtoOnNull() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertNull(tilanne.getSiirto());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunEdellistaSiirtoaEiOle() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertFalse(tilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunEdellinenSiirtoOnLuovutus() {
        Siirto siirto = Siirto.luovutus();
        Pelitilanne pelitilanne = new Pelitilanne(null, null, null, null, siirto, null);
        
        assertTrue(pelitilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunEdellisenPelitilanteenEdellistaSiirtoaEiOle() {
        Siirto siirto = Siirto.passaus();
        
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, null, null);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, siirto, null);
        
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunPassattuKahdesti() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.passaus(), null);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.passaus(), null);
        
        assertTrue(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunToiseksiviimeinenSiirtoOn() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)), null);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.passaus(), null);
        
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunViimeinenSiiroOn() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.passaus(), null);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)), null);
    
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPelataanToisenKivenPaalle1() {
        Pelitilanne ensimmainen = Pelitilanne.uusiPeli();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ensimmainen.setSiirto(koordinaatti);
        Pelitilanne toinen = ensimmainen.lisaaSiirto();
        
        toinen.setSiirto(koordinaatti);
        Pelitilanne kolmas = toinen.lisaaSiirto();
        
        assertEquals("On jo kivi", kolmas.getLaitonSiirto());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPelataanToisenKivenPaalle2() {
        Pelitilanne ensimmainen = Pelitilanne.uusiPeli();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ensimmainen.setSiirto(koordinaatti);
        Pelitilanne toinen = ensimmainen.lisaaSiirto();
        
        toinen.setSiirto(koordinaatti);
        Pelitilanne kolmas = toinen.lisaaSiirto();
        
        assertTrue(toinen.getPelilauta().equals(kolmas.getPelilauta()));
    }
}
