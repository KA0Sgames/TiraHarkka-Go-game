package GoPeli.logiikka;

import java.util.HashSet;
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
        Pelitilanne pelitilanne = new Pelitilanne(null, null, null, null, siirto, null, 0, 0);
        
        assertTrue(pelitilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunEdellisenPelitilanteenEdellistaSiirtoaEiOle() {
        Siirto siirto = Siirto.passaus();
        
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, null, null, 0, 0);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, siirto, null, 0, 0);
        
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunPassattuKahdesti() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.passaus(), null, 0, 0);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.passaus(), null, 0, 0);
        
        assertTrue(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunToiseksiviimeinenSiirtoOn() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)), null, 0, 0);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.passaus(), null, 0, 0);
        
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void peliOhiPalauttaaOikeinKunViimeinenSiiroOn() {
        Pelitilanne edellinenTilanne = new Pelitilanne(null, null, null, null, Siirto.passaus(), null, 0, 0);
        Pelitilanne nykyinenTilanne = new Pelitilanne(null, null, edellinenTilanne, null, Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)), null, 0, 0);
    
        assertFalse(nykyinenTilanne.peliOhi());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPelataanToisenKivenPaalle1() {
        Pelitilanne ensimmainen = Pelitilanne.uusiPeli();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ensimmainen.setSiirto(Siirto.pelaa(koordinaatti));
        Pelitilanne toinen = ensimmainen.lisaaSiirto();
        
        toinen.setSiirto(Siirto.pelaa(koordinaatti));
        Pelitilanne kolmas = toinen.lisaaSiirto();
        
        assertEquals("On jo kivi", kolmas.getLaitonSiirto());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPelataanToisenKivenPaalle2() {
        Pelitilanne ensimmainen = Pelitilanne.uusiPeli();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ensimmainen.setSiirto(Siirto.pelaa(koordinaatti));
        Pelitilanne toinen = ensimmainen.lisaaSiirto();
        
        toinen.setSiirto(Siirto.pelaa(koordinaatti));
        Pelitilanne kolmas = toinen.lisaaSiirto();
        
        assertTrue(toinen.getPelilauta().equals(kolmas.getPelilauta()));
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunSiirronJalkeenEiVapauksia1() {
        Koordinaatti eka = new Koordinaatti((byte) 0, (byte) 1);
        Koordinaatti toka = new Koordinaatti((byte) 1, (byte) 0);
        
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, eka);
        lauta.lisaaSiirto(Vari.MUSTA, toka);
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, null, null, null, 0, 0);
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 0)));
        
        assertEquals("Siirron jälkeen ei ole vapauksia", tilanne.lisaaSiirto().getLaitonSiirto());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunSiirronJalkeenEiVapauksia2() {
        Koordinaatti eka = new Koordinaatti((byte) 0, (byte) 1);
        Koordinaatti toka = new Koordinaatti((byte) 1, (byte) 0);
        
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, eka);
        lauta.lisaaSiirto(Vari.MUSTA, toka);
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, null, null, null, 0, 0);
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 0)));
        
        tilanne = tilanne.lisaaSiirto();
        
        assertTrue(lauta.equals(tilanne.getPelilauta()));
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunSiirronJalkeenEiVapauksia3() {
        Koordinaatti eka = new Koordinaatti((byte) 0, (byte) 1);
        Koordinaatti toka = new Koordinaatti((byte) 1, (byte) 1);
        Koordinaatti kolmas = new Koordinaatti((byte) 2, (byte) 0);
        Koordinaatti valkoinen = new Koordinaatti((byte) 0, (byte) 0);
        
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, eka);
        lauta.lisaaSiirto(Vari.MUSTA, toka);
        lauta.lisaaSiirto(Vari.VALKOINEN, valkoinen);
        lauta.lisaaSiirto(Vari.MUSTA, kolmas);
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, null, null, null, 0, 0);
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 0)));
        
        assertEquals("Siirron jälkeen ei ole vapauksia", tilanne.lisaaSiirto().getLaitonSiirto());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunSiirtoYrittaaOttaaKonTakaisin1() {
        Koordinaatti musta1 = new Koordinaatti((byte) 0, (byte) 1);
        Koordinaatti musta2 = new Koordinaatti((byte) 1, (byte) 0);
        Koordinaatti musta3 = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti musta4 = new Koordinaatti((byte) 2, (byte) 1);
        
        Koordinaatti valkoinen1 = new Koordinaatti((byte) 0, (byte) 2);
        Koordinaatti valkoinen2 = new Koordinaatti((byte) 1, (byte) 3);
        Koordinaatti valkoinen3 = new Koordinaatti((byte) 2, (byte) 2);
        
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, musta1);
        lauta.lisaaSiirto(Vari.VALKOINEN, valkoinen1);
        lauta.lisaaSiirto(Vari.MUSTA, musta2);
        lauta.lisaaSiirto(Vari.VALKOINEN, valkoinen2);
        lauta.lisaaSiirto(Vari.MUSTA, musta3);
        lauta.lisaaSiirto(Vari.VALKOINEN, valkoinen3);
        lauta.lisaaSiirto(Vari.MUSTA, musta4);
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, null, null, null, 0, 0);
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        
        assertEquals("Siirto rikkoo Ko-sääntöä", tilanne.lisaaSiirto().getLaitonSiirto());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPassataan1() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, Siirto.passaus(), null, null, 0, 0);
        
        tilanne = tilanne.lisaaSiirto();
        
        assertTrue(lauta.equals(tilanne.getPelilauta()));
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanOlionKunPassataan2() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        tilanne.setSiirto(Siirto.pelaa(koordinaatti));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        
        assertTrue(tilanne.getEdellinenSiirto().equals(new Siirto(null, true, false)));
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOlionJollaOikeatKaapatutKivet1() {
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        lauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.MUSTA, null, Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 1)), null, null, 0, 0);
        tilanne = tilanne.lisaaSiirto();
        
        assertEquals(1, tilanne.getKaapatutValkoiset());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOlionJollaOikeatKaapatutKivet2() {
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 1));
        lauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 0)), null, null, 0, 0);
        tilanne = tilanne.lisaaSiirto();
        
        assertEquals(2, tilanne.getKaapatutMustat());
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOlionJollaOikeatKaapatutKivet3() {
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        lauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 1));
        lauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 0)), null, null, 0, 0);
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        
        assertEquals(2, tilanne.getKaapatutMustat());
    }
    
    @Test
    public void laillisetSiirrotPalauttaaTaulukon() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertNotNull(tilanne.laillisetSiirrot());
    }
    
    @Test
    public void laillisetSiirrotPalauttaaOikeanKokoisenTaulukon() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        
        assertEquals(80, tilanne.laillisetSiirrot().length);
    }
    
    @Test
    public void laillisetSiirrotPalauttaaOikeanTaulukon1() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        
        assertTrue(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 4)).equals(tilanne.laillisetSiirrot()[2]));
    }
}
