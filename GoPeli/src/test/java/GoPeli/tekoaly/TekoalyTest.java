package GoPeli.tekoaly;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TekoalyTest {
    
    public TekoalyTest() {
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
    public void konstruktoriLuoOlion() {
        Tekoaly tekoaly = new Tekoaly();
        
        assertNotNull(tekoaly); 
    }
    
    @Test
    public void konstruktoriAsettaaRandomOlion() {
        Tekoaly tekoaly = new Tekoaly();
        
        assertNotNull(tekoaly.getRandom());
    }
    
    @Test
    public void testikonstruktoriLuoOlion() {
        Tekoaly tekoaly = new Tekoaly(new Random(1337));
        
        assertNotNull(tekoaly);
    }
    
    @Test
    public void testikonstruktoriAsettaaRandomOlion() {
        Tekoaly tekoaly = new Tekoaly(new Random(1337));
        
        assertNotNull(tekoaly.getRandom());
    }
    
    @Test
    public void testikonstruktoriAsettaaOikeanRandomOlion() {
        Tekoaly tekoaly = new Tekoaly(new Random(1337));
        
        assertEquals(1, tekoaly.getRandom().nextInt(10));
    }
    
    @Test
    public void valitseSiirtoPalauttaaSiirtoOlion() {
        Tekoaly tekoaly = new Tekoaly(new Random(666));
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        assertTrue(tekoaly.valitseSiirto(tilanne) instanceof Siirto);
    }
    
    @Test
    public void valitseSiirtoPalauttaaOikeanSiirron() {
        Tekoaly tekoaly = new Tekoaly(new Random(1337));
        
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 8)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 8, (byte)0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 7)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 8, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 8, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 8, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 8)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 7)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 3)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 8)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 8)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 7)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 5)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, (byte) 7)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 7)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, (byte) 6)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, (byte) 4)));
        tilanne = tilanne.lisaaSiirto();
        

        Siirto siirto = tekoaly.valitseSiirto(tilanne);
        
        assertEquals(new Koordinaatti((byte) 0, (byte) 3), siirto.getKoordinaatti());
    }
    
    @Test
    public void valitseSiirtoLuovuttaaOikein() {
        Tekoaly tekoaly = new Tekoaly(new Random(1337));
        
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        for (byte indeksi = 0; indeksi < 9; indeksi += 2) {
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 8, indeksi)));
            tilanne = tilanne.lisaaSiirto();
        }
        
        for (byte indeksi = 0; indeksi < 9; indeksi++) {
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 7, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 6, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 3, indeksi)));
            tilanne = tilanne.lisaaSiirto();
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 5, indeksi)));
            tilanne = tilanne.lisaaSiirto();
        }
        
        for (byte indeksi = 0; indeksi < 8; indeksi++) {
            tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 4, indeksi)));
            tilanne = tilanne.lisaaSiirto();
        }
        
        assertEquals(Siirto.luovutus(), tekoaly.valitseSiirto(tilanne));
    }
}
