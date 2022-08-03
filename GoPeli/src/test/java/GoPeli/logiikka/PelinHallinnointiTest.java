/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GoPeli.logiikka;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KA0S
 */
public class PelinHallinnointiTest {
    
    public PelinHallinnointiTest() {
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
    public void KonstruktoriLuoOikeanlevyisenPelimatriisin() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        byte[][] taulukko = pelinHallinnointi.getPeli();
        
        assertEquals(9, taulukko[0].length);
    }
    
    @Test
    public void KonstruktoriLuoOikeankorkuisenPelimatriisin() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        byte[][] taulukko = pelinHallinnointi.getPeli();
        
        assertEquals(9, taulukko.length);
    }
    
    @Test
    public void KonstruktorinLuomassaPelimatriisissaNollia() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        byte[][] taulukko = pelinHallinnointi.getPeli();
        boolean vainNollia = true;
        
        for (int rivi = 0; rivi < 9; rivi++) {
            for (int sarake = 0; sarake < 9; sarake++) {
                if (taulukko[rivi][sarake] != 0) {
                    vainNollia = false;
                }
            }
        }
        
        assertTrue(vainNollia);
    }
    
    @Test
    public void KonstruktoriAsettaaMustanVuoronTrue() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        assertTrue(pelinHallinnointi.getMustanVuoro());
    }
    
    @Test
    public void KonstruktoriAsettaaMustanVangitNollaan() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        assertEquals(0, pelinHallinnointi.getMustanVangit());
    }
    
    @Test
    public void KonstruktoriAsettaaValkoisenVangitNollaan() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        assertEquals(0, pelinHallinnointi.getValkoisenVangit());
    }
    
    @Test
    public void KonstruktoriLuoSiirronLaillisuudenTarkastajan() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        assertNotNull(pelinHallinnointi.getSiirronLaillisuudenTarkastaja());
    }
    
    @Test
    public void GetPeliPalauttaaPelin() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        byte[][] peli = pelinHallinnointi.getPeli();
        
        assertNotNull(peli);
    }
    
    @Test
    public void getPeliPalauttaaOikeanPelin1() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelinHallinnointi.lisaaSiirto(koordinaatti);
        
        byte[][] peli = pelinHallinnointi.getPeli();
        assertEquals(1, peli[1][2]);
    }
    
    @Test
    public void getPeliPalauttaaOikeanPelin2() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti toinenKoordinaatti = new Koordinaatti((byte) 3, (byte) 5);
        
        pelinHallinnointi.lisaaSiirto(koordinaatti);
        pelinHallinnointi.lisaaSiirto(toinenKoordinaatti);
        
        byte[][] peli = pelinHallinnointi.getPeli();
        assertEquals(2, peli[3][5]);
    }
    
    @Test
    public void getKoordinaatinTilaPalauttaaOikeanArvon1() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertEquals(0, pelinHallinnointi.getKoordinaatinTila(koordinaatti));
    }
    
    @Test
    public void getKoordinaatinTilaPalauttaaOikeanArvon2() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        pelinHallinnointi.lisaaSiirto(koordinaatti);
        
        assertEquals(1, pelinHallinnointi.getKoordinaatinTila(koordinaatti));
    }
    
    @Test
    public void getKoordinaatinTilaPalauttaaOikeanArvon3() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 3, (byte) 2);
        
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 1, (byte) 2));
        pelinHallinnointi.lisaaSiirto(koordinaatti);        
        
        assertEquals(2, pelinHallinnointi.getKoordinaatinTila(koordinaatti));
    }
    
    @Test
    public void getKoordinaatinTilaPalauttaaOikeanArvon4() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 3, (byte) 2);
        
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 1, (byte) 2));
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 2, (byte) 1));
        pelinHallinnointi.lisaaSiirto(koordinaatti);        
        
        assertEquals(1, pelinHallinnointi.getKoordinaatinTila(koordinaatti));
    }
    
    @Test
    public void lisaaSiirtoOnnistuuLisaamaanMustanSiirron() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        assertEquals("Onnistui", pelinHallinnointi.lisaaSiirto(koordinaatti));
    }
    
    @Test
    public void lisaaSiirtoOnnistuuLisaamaanValkoisenSiirron() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 2, (byte) 1));
        
        
        assertEquals("Onnistui", pelinHallinnointi.lisaaSiirto(koordinaatti));
    }
    
    @Test
    public void lisaaSiirtoPalauttaaOnJoKiviKunKoitetaanLisataSiirtoKohtaanJossaJoKivi() {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelinHallinnointi.lisaaSiirto(koordinaatti);
        
        assertEquals("On jo kivi", pelinHallinnointi.lisaaSiirto(koordinaatti));
    }
}
