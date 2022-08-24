package GoPeli.tekoaly;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MCPHSolmuTest {
    
    public MCPHSolmuTest() {
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
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertNotNull(solmu);
    }
    
    @Test
    public void konstruktoriAsettaaPelitilanteen() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertNotNull(solmu.getPelitilanne());
    }
    
    @Test
    public void konstruktoriAsettaaOikeanPelitilanteen() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null);
        
        assertEquals(new Koordinaatti((byte) 1, (byte) 2), solmu.getPelitilanne().getEdellinenSiirto().getKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaVanhemmanOikein() {
        MCPHSolmu vanhempi = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        MCPHSolmu lapsi = new MCPHSolmu(Pelitilanne.uusiPeli(), vanhempi, null);
        
        assertEquals(vanhempi, lapsi.getVanhempi());
    }
    
    @Test
    public void konstruktoriAsettaaEdellisenSiirronOikein() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, siirto);
        
        assertEquals(siirto, solmu.getViimeinenSiirto());
    }
    
    @Test
    public void konstruktoriAsettaaMustanVoitotOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertEquals(0, solmu.getMustanVoitot());
    }
    
    @Test
    public void konstruktoriAsettaaValkoisenVoitotOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertEquals(0, solmu.getValkoisenVoitot());
    }
    
    @Test
    public void konstruktoriAsettaaPlayouttienMaaranOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertEquals(0, solmu.getPlayoutit());
    }
    
    @Test
    public void konstruktoriTekeeListanLapsille() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertNotNull(solmu.getLapset());
    }
    
    @Test
    public void konstruktorinTekemaListaLapsilleOnTyhja() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertNull(solmu.getLapset()[0]);
    }
    
    @Test
    public void konstruktoriTekeeTaulukonVierailemattomilleSiirroille() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null);
        
        assertNotNull(solmu.getVierailemattomatSiirrot());
    }
    
    @Test
    public void konstruktoriTekeeOikeanTaulukonVierailemattomilleSiirroille() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null);
        
        Siirto[] vierailemattomat = solmu.getVierailemattomatSiirrot();
        int siirtoja = 0;
        
        for (Siirto siirto : vierailemattomat) {
            if (siirto != null) {
                siirtoja++;
            }
        }
        
        assertEquals(78, siirtoja);
    }
}
