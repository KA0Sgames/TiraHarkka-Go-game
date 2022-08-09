package GoPeli.logiikka;

import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PelilautaTest {
    
    public PelilautaTest() {
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
    public void konstruktoriLuoPelilautaOlion() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertNotNull(pelilauta);
    }
    
    @Test
    public void konstruktoriLuoTaulukonPelilaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertNotNull(pelilauta.getLauta());
    }
    
    @Test
    public void konstruktoriLuoSetinRyhmille() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertNotNull(pelilauta.getRyhmat());
    }
    
    @Test
    public void konstruktorinParametrinaAnnettuSettiOnTyhjaKunLuodaanUusi() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertEquals(0, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void konstruktoriLuoOikeanKorkuisenTaulukonPelilaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertEquals(9, pelilauta.getLauta().length);
    }
    
    @Test
    public void konstruktoriLuoOikeanLevyisenTaulukonPelilaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertEquals(9, pelilauta.getLauta()[0].length);
    }
    
    @Test
    public void konstruktoriLuoTyhjanTaulukonKunRyhmiaEiOle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti[][] lauta = pelilauta.getLauta();
        boolean tyhja = true;
        
        for (int rivi = 0; rivi < 9; rivi++) {
            for (int sarake = 0; sarake < 9; sarake++) {
                if (lauta[rivi][sarake] != null) {
                    tyhja = false;
                }
            }
        }
        
        assertTrue(tyhja);
    }
    
    @Test
    public void konstruktoriLuoTaulukonJossaOikeaKoordinaattiKunOnRyhma() {
        Koordinaatti kivi = new Koordinaatti((byte) 1, (byte) 2);
        HashSet<Koordinaatti> kivet = new HashSet<>();
        kivet.add(kivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, kivet, new HashSet<>());
        HashSet<Ryhma> ryhmat = new HashSet<>();
        ryhmat.add(ryhma);
        
        Pelilauta pelilauta = new Pelilauta(ryhmat);
        
        assertNotNull(pelilauta.getLauta()[1][2]);
    }
    
    @Test
    public void lisaaSiirtoEiAnnaLisataSiirtoaKoordinaattiinJossaOnJoKivi() {
        Koordinaatti kivi = new Koordinaatti((byte) 1, (byte) 2);
        HashSet<Koordinaatti> kivet = new HashSet<>();
        kivet.add(kivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, kivet, new HashSet<>());
        HashSet<Ryhma> ryhmat = new HashSet<>();
        ryhmat.add(ryhma);
        
        Pelilauta pelilauta = new Pelilauta(ryhmat);
        
        assertEquals("On jo kivi", pelilauta.lisaaSiirto(Vari.VALKOINEN, kivi));
    }
    
    @Test
    public void lisaaSiirtoLisaaSiirronLaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        assertNotNull(pelilauta.getLauta()[1][2]);
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOikeanTulosteenOnnistuessa() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        assertEquals("Onnistui", pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2)));
    }
    
    @Test
    public void lisaaSiirtoLisaaRyhmanTyhjalleLaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(1, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaOikeanVarisenRyhmanTyhjalleLaudalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        HashSet<Ryhma> ryhmat = pelilauta.getRyhmat();
        
        Ryhma ryhma = new Ryhma(Vari.VALKOINEN, new HashSet<>(), new HashSet<>());
        
        for (Ryhma setissa : ryhmat) {
            ryhma = setissa;
        }
        
        assertEquals(Vari.MUSTA, ryhma.getVari());
    }
}
