package GoPeli.logiikka;

import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RyhmaTest {
    
    public RyhmaTest() {
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
    public void KonstruktoriLuoRyhman() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma);
    }
    
    @Test
    public void KonstruktoriVarmistaaArvonVarille() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getVari());
    }
    
    
}
