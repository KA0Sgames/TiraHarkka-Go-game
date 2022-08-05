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

//     TODO add test methods here.
//     The methods must be annotated with annotation @Test. For example:
//    
//     @Test
//     public void hello() {}
     
    @Test
    public void konstruktoriAsettaaYkoordinaatinOikein() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 2, (byte) 1, Vari.MUSTA);
        
        assertEquals(2, koordinaatti.getYKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaXkoordinaatinOikein() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 2, (byte) 1, Vari.MUSTA);
        
        assertEquals(1, koordinaatti.getXKoordinaatti());
    }
    
    @Test
    public void konstruktoriAsettaaVarinOikein() {
        Koordinaatti koordinaatti = new Koordinaatti((byte) 2, (byte) 1, Vari.MUSTA);
        
        assertEquals(Vari.MUSTA, koordinaatti.getVari());
    }
}
