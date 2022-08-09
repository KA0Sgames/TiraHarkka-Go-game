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
    public void konstruktoriLuoRyhman() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma);
    }
    
    @Test
    public void konstruktoriVarmistaaArvonVarille() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getVari());
    }
    
    @Test
    public void konstruktoriVarmistaaEttaKivilleOnSetti() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getKivet());
    }
    
    @Test
    public void konstruktoriVarmistaaEttaVapauksilleOnSetti() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getVapaudet());
    }
    
    @Test
    public void lisaaVapausLisaaVapauden() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        ryhma.lisaaVapaus(new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(1, ryhma.getVapaudet().size());
    }
    
    @Test
    public void lisaaVapausLisaaOikeanVapauden() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ryhma.lisaaVapaus(koordinaatti);
        
        assertTrue(ryhma.getVapaudet().contains(koordinaatti));
    }
    
    @Test
    public void poistaVapausPoistaaVapauden() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        ryhma.lisaaVapaus(koordinaatti);
        
        ryhma.poistaVapaus(koordinaatti);
        
        assertEquals(0, ryhma.getVapaudet().size());
    }
    
    @Test
    public void poistaVapausPoistaaOikeanVapauden() {        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        Koordinaatti toinenKoordinaatti = new Koordinaatti((byte) 2, (byte) 2);
        
        ryhma.lisaaVapaus(koordinaatti);
        ryhma.lisaaVapaus(toinenKoordinaatti);
        
        ryhma.poistaVapaus(koordinaatti);
        
        assertFalse(ryhma.getVapaudet().contains(koordinaatti));
    }
    
    @Test
    public void yhdistaRyhmaPalauttaaRyhman() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        ekanRyhmanKivi.add(new Koordinaatti((byte) 1, (byte) 2));
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        tokanRyhmanKivi.add(new Koordinaatti((byte) 2, (byte) 2));
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, tokanRyhmanKivi, new HashSet<>());
        
        assertNotNull(ryhma.yhdista(toinenRyhma));
    }
    
    @Test
    public void yhdistettyRyhmaSisaltaaMolempienKivet1() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        Koordinaatti tokaKivi = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanKivi.add(tokaKivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, tokanRyhmanKivi, new HashSet<>());
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertTrue(yhdistettyRyhma.getKivet().contains(ekaKivi));
    }
    
    @Test
    public void yhdistettyRyhmaSisaltaaMolempienKivet2() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        Koordinaatti tokaKivi = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanKivi.add(tokaKivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, tokanRyhmanKivi, new HashSet<>());
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertTrue(yhdistettyRyhma.getKivet().contains(tokaKivi));
    }
    
    @Test
    public void yhdistettyRyhmaSisaltaaMolempienVapaudet1() {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertTrue(yhdistettyRyhma.getVapaudet().contains(ekaVapaus));
    }
    
    @Test
    public void yhdistettyRyhmaSisaltaaMolempienVapaudet2() {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertTrue(yhdistettyRyhma.getVapaudet().contains(tokaVapaus));
    }
    
    @Test
    public void yhdistettyRyhmaEiSisallaToisenRyhmanKiveaVapautena() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, ekanRyhmanVapaus);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ekaRyhma.yhdista(tokaRyhma);
        
        assertFalse(yhdistettyRyhma.getVapaudet().contains(tokaVapaus));
    }
    
    @Test
    public void yhdistaRyhmaEiYhdistaErivarisia1() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        Koordinaatti tokaKivi = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanKivi.add(tokaKivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        Ryhma toinenRyhma = new Ryhma(Vari.VALKOINEN, tokanRyhmanKivi, new HashSet<>());
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertFalse(yhdistettyRyhma.getKivet().contains(tokaKivi));
    }
    
    @Test
    public void yhdistaRyhmaEiYhdistaErivarisia2 () {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        Ryhma toinenRyhma = new Ryhma(Vari.VALKOINEN, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertFalse(yhdistettyRyhma.getVapaudet().contains(tokaVapaus));
    }
    
    @Test
    public void getVariPalauttaaOikeanVarin() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertEquals(Vari.MUSTA, ryhma.getVari());
    }
    
    @Test
    public void getKivetPalauttaaSetin() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getKivet());
    }
    
    @Test
    public void getKivetPalauttaaOikeanKokoisenSetin() {
        HashSet<Koordinaatti> kivet = new HashSet<>();
        Koordinaatti kivi = new Koordinaatti((byte) 1, (byte) 2);
        kivet.add(kivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, kivet, new HashSet<>());
        
        assertEquals(1, ryhma.getKivet().size());
    }
    
    @Test
    public void getKivetPalauttaaOikeanSetin() {
        HashSet<Koordinaatti> kivet = new HashSet<>();
        Koordinaatti kivi = new Koordinaatti((byte) 1, (byte) 2);
        kivet.add(kivi);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, kivet, new HashSet<>());
        
        assertTrue(ryhma.getKivet().contains(new Koordinaatti((byte) 1, (byte) 2)));
    }
    
    @Test
    public void getVapaudetPalauttaaSetin() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertNotNull(ryhma.getVapaudet());
    }
    
    @Test
    public void getVapaudetPalauttaaOikeanKokoisenSetin() {
        HashSet<Koordinaatti> vapaudet = new HashSet<>();
        Koordinaatti vapaus = new Koordinaatti((byte) 1, (byte) 2);
        vapaudet.add(vapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), vapaudet);
        
        assertEquals(1, ryhma.getVapaudet().size());
    }
    
    @Test
    public void getVapaudetPalauttaaOikeanSetin() {
        HashSet<Koordinaatti> vapaudet = new HashSet<>();
        Koordinaatti vapaus = new Koordinaatti((byte) 1, (byte) 2);
        vapaudet.add(vapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), vapaudet);
        
        assertTrue(ryhma.getVapaudet().contains(new Koordinaatti((byte) 1, (byte) 2)));
    }
    
    @Test
    public void vapauksienMaaraPalauttaaOikein0() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertEquals(0, ryhma.vapauksienMaara());
    }
    
    @Test
    public void vapauksienMaaraPalauttaaOikein1() {
        HashSet<Koordinaatti> vapaudet = new HashSet<>();
        Koordinaatti vapaus = new Koordinaatti((byte) 1, (byte) 2);
        vapaudet.add(vapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), vapaudet);
        
        assertEquals(1, ryhma.vapauksienMaara());
    }
    
    @Test
    public void vapauksienMaaraPalauttaaYhdistetynRyhmanVapaudetOikein1() {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        Ryhma toinenRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ryhma.yhdista(toinenRyhma);
        
        assertEquals(2, yhdistettyRyhma.vapauksienMaara());
    }
    
    @Test
    public void vapauksienMaaraPalauttaaYhdistetynRyhmanVapaudetOikein2() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, ekanRyhmanVapaus);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        Ryhma yhdistettyRyhma = ekaRyhma.yhdista(tokaRyhma);
        
        assertEquals(1, yhdistettyRyhma.vapauksienMaara());
    }
    
    @Test
    public void equalsTunnistaaSamatRyhmat() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertTrue(ryhma.equals(ryhma));
    }
    
    @Test
    public void equalsTunnistaaEriRyhmatJoillaTyhjatKivetJaVapaudet() {
        Ryhma ryhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        
        assertTrue(ryhma.equals(tokaRyhma));
    }
    
    @Test
    public void equalsTunnistaaRyhmatJoissaSamaKivi() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        Koordinaatti tokaKivi = new Koordinaatti((byte) 1, (byte) 2);
        tokanRyhmanKivi.add(tokaKivi);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, tokanRyhmanKivi, new HashSet<>());
        
        assertTrue(ekaRyhma.equals(tokaRyhma));
    }
    
    @Test
    public void equalsErottaaRyhmatJoissaEriKivi() {
        HashSet<Koordinaatti> ekanRyhmanKivi = new HashSet<>();
        Koordinaatti ekaKivi = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanKivi.add(ekaKivi);
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, ekanRyhmanKivi, new HashSet<>());
        
        HashSet<Koordinaatti> tokanRyhmanKivi = new HashSet<>();
        Koordinaatti tokaKivi = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanKivi.add(tokaKivi);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, tokanRyhmanKivi, new HashSet<>());
        
        assertFalse(ekaRyhma.equals(tokaRyhma));
    }
    
    @Test
    public void equalsTunnistaaRyhmatJoissaSamaVapaus() {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        assertTrue(ekaRyhma.equals(tokaRyhma));
    }
    
    @Test
    public void equalsErottaaRyhmatJoissaEriVapaus() {
        HashSet<Koordinaatti> ekanRyhmanVapaus = new HashSet<>();
        Koordinaatti ekaVapaus = new Koordinaatti((byte) 1, (byte) 2);
        ekanRyhmanVapaus.add(ekaVapaus);
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), ekanRyhmanVapaus);
        
        HashSet<Koordinaatti> tokanRyhmanVapaus = new HashSet<>();
        Koordinaatti tokaVapaus = new Koordinaatti((byte) 2, (byte) 2);
        tokanRyhmanVapaus.add(tokaVapaus);
        Ryhma tokaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), tokanRyhmanVapaus);
        
        assertFalse(ekaRyhma.equals(tokaRyhma));
    }
    
    @Test
    public void equalsErottaaErivarisetRyhmat() {
        Ryhma ekaRyhma = new Ryhma(Vari.MUSTA, new HashSet<>(), new HashSet<>());
        Ryhma tokaRyhma = new Ryhma(Vari.VALKOINEN, new HashSet<>(), new HashSet<>());
        
        assertFalse(ekaRyhma.equals(tokaRyhma));
    }
}
