package GoPeli.tekoaly;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelilauta;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.HashSet;
import java.util.Random;
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
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertNotNull(solmu);
    }
    
    @Test
    public void konstruktoriAsettaaPelitilanteen() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertNotNull(solmu.getPelitilanne());
    }
    
    @Test
    public void konstruktoriAsettaaOikeanPelitilanteen() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, new Random());
        
        assertEquals(new Koordinaatti((byte) 1, (byte) 2), solmu.getPelitilanne().getEdellinenSiirto().getKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaVanhemmanOikein() {
        MCPHSolmu vanhempi = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        MCPHSolmu lapsi = new MCPHSolmu(Pelitilanne.uusiPeli(), vanhempi, null, new Random());
        
        assertEquals(vanhempi, lapsi.getVanhempi());
    }
    
    @Test
    public void konstruktoriAsettaaEdellisenSiirronOikein() {
        Siirto siirto = Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2));
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, siirto, new Random());
        
        assertEquals(siirto, solmu.getViimeinenSiirto());
    }
    
    @Test
    public void konstruktoriAsettaaMustanVoitotOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertEquals(0, solmu.getMustanVoitot());
    }
    
    @Test
    public void konstruktoriAsettaaValkoisenVoitotOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertEquals(0, solmu.getValkoisenVoitot());
    }
    
    @Test
    public void konstruktoriAsettaaPlayouttienMaaranOikein() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertEquals(0, solmu.getPlayoutit());
    }
    
    @Test
    public void konstruktoriTekeeListanLapsille() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertNotNull(solmu.getLapset());
    }
    
    @Test
    public void konstruktorinTekemaListaLapsilleOnTyhja() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
        assertNull(solmu.getLapset()[0]);
    }
    
    @Test
    public void konstruktoriTekeeTaulukonVierailemattomilleSiirroille() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random());
        
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
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, new Random());
        
        Siirto[] vierailemattomat = solmu.getVierailemattomatSiirrot();
        int siirtoja = 0;
        
        for (Siirto siirto : vierailemattomat) {
            if (siirto != null) {
                siirtoja++;
            }
        }
        
        assertEquals(78, siirtoja);
    }
    
    @Test
    public void konstruktoriAsettaaVierailemattomienSiirtojenMaaranOikein() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertEquals(78, solmu.getVierailemattomiaSiirtoja());
    }
    
    @Test
    public void konstruktoriAsettaaLapsienSeuraavanTyhjanIndeksinNollaksi() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        assertEquals(0, solmu.getSeuraavaTyhjaIndeksiLapsissa());
    }
    
    @Test
    public void lisaaSattumanvarainenLapsiPuuhunPalauttaaOlion() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random(1337));
        
        assertNotNull(solmu.lisaaSattumanvarainenLapsiPuuhun());
    }
    
    @Test
    public void lisaaSattumanvarainenLapsiPuuhunLisaaLapsenAlkuperaiselle1() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random(1337));
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        
        assertEquals(1, solmu.getSeuraavaTyhjaIndeksiLapsissa());
    }
    
    @Test
    public void lisaaSattumanvarainenLapsiPuuhunLisaaLapsenAlkuperaiselle2() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, new Random(1337));
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        
        assertNotNull(solmu.getLapset()[0]);
    }
    
    @Test
    public void lisaaSattumanvarainenLapsiPuuhunPoistaaVierailemattomistaSiirron() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 0, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 0)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, new Random(1337));
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        
        assertEquals(77, solmu.getVierailemattomiaSiirtoja());
    }
    
    @Test
    public void lisaaSattumanvarainenLapsiPuuhunKasvattaaLapsetTaulukonKokoa() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, new Random(1337));
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        solmu.lisaaSattumanvarainenLapsiPuuhun();
        
        assertEquals(20, solmu.getLapset().length);
    }
    
    @Test
    public void tallennaVoittoTallentaaMustanVoiton() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.MUSTA);
        
        assertEquals(1, solmu.getMustanVoitot());
    }
    
    @Test
    public void tallennaVoittoTallentaaValkoisenVoiton() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.VALKOINEN);
        
        assertEquals(1, solmu.getValkoisenVoitot());
    }
    
    @Test
    public void tallennaVoittoTallentaaPlayoutin() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.MUSTA);
        
        assertEquals(1, solmu.getPlayoutit());
    }
    
    @Test
    public void voiLisataLapsenPalauttaaOikeinKunVoiLisataLapsen() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        assertTrue(solmu.voiLisataLapsen());
    }
    
    @Test
    public void voiLisataLapsenPalauttaaOikeinKunEiVoiLisataLasta() {
        Pelilauta lauta = new Pelilauta(new HashSet<>());
        
        for (int rivi = 0; rivi < 9; rivi++) {
            for (int sarake = 0; sarake < 9; sarake++) {
                if (rivi != sarake) {
                    lauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) rivi, (byte) sarake));
                }
            }
        }
        
        Pelitilanne tilanne = new Pelitilanne(lauta, Vari.VALKOINEN, null, null, null, null, 0, 0);
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertFalse(solmu.voiLisataLapsen());
    }
    
    @Test
    public void onLopputilannePalauttaaOikeinKunLuovutettu() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.luovutus());
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertTrue(solmu.onLopputilanne());
    }
    
    @Test
    public void onLopputilannePalauttaaOikeinKunPassattuKahdesti() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertTrue(solmu.onLopputilanne());
    }
    
    @Test
    public void onLopputilannePalauttaaOikeinKunSiirtojaEiPelattu() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertFalse(solmu.onLopputilanne());
    }
    
    @Test
    public void onLopputilannePalauttaaOikeinKunYksiSiirtoPelattu() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertFalse(solmu.onLopputilanne());
    }
    
    @Test
    public void onLopputilannePalauttaaOikeinKunUseampiSiirtoJaYksiPassausPelattu() {
        Pelitilanne tilanne = Pelitilanne.uusiPeli();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 1, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 2)));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) 2, (byte) 1)));
        tilanne = tilanne.lisaaSiirto();
        
        tilanne.setSiirto(Siirto.passaus());
        tilanne = tilanne.lisaaSiirto();
        
        MCPHSolmu solmu = new MCPHSolmu(tilanne, null, null, null);
        
        assertFalse(solmu.onLopputilanne());
    }
    
    @Test
    public void pelaajanVoittoprosenttiPalauttaaOikein1() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.MUSTA);
        solmu.tallennaVoitto(Vari.VALKOINEN);
        
        assertEquals(0.5, solmu.pelaajanVoittoprosentti(Vari.MUSTA));
    }
    
    @Test
    public void pelaajanVoittoprosenttiPalauttaaOikein2() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.MUSTA);
        solmu.tallennaVoitto(Vari.MUSTA);
        solmu.tallennaVoitto(Vari.VALKOINEN);
        
        assertEquals(0.333333, solmu.pelaajanVoittoprosentti(Vari.VALKOINEN), 0.000001d);
    }
    
    @Test
    public void pelaajanVoittoprosenttiPalauttaaOikein3() {
        MCPHSolmu solmu = new MCPHSolmu(Pelitilanne.uusiPeli(), null, null, null);
        
        solmu.tallennaVoitto(Vari.MUSTA);
        solmu.tallennaVoitto(Vari.MUSTA);
        solmu.tallennaVoitto(Vari.VALKOINEN);
        
        assertEquals(0.666666, solmu.pelaajanVoittoprosentti(Vari.MUSTA), 0.000001d);
    }
}
