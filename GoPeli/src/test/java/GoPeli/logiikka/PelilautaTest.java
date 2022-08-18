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
    public void kopioKonstruktoriLuoOlion() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        assertNotNull(kopio);
    }
    
    @Test
    public void kopioKonstruktoriKopioTyhjanPelilaudanOikein() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        Koordinaatti[][] lauta = kopio.getLauta();
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
    public void kopioKonstruktoriKopioTyhjanRyhmatArvonOikein() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        assertTrue(kopio.getRyhmat().isEmpty());
    }
    
    @Test
    public void kopioKonstruktoriKopioiOikeinLaudanJollaKivi1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        assertEquals(1, kopio.getRyhmat().size());
    }
    
    @Test
    public void kopioKonstruktoriKopioiOikeinLaudanJollaKivi2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        assertNotNull(kopio.getLauta()[1][2]);
    }
    
    @Test
    public void kopioKonstruktoriKopioiUuteenOlioonLaudanKoordinaatin() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        Koordinaatti alkuperaisenKoordinaatti = pelilauta.getLauta()[1][2];
        Koordinaatti kopionKoordinaatti = kopio.getLauta()[1][2];
        
        assertFalse(alkuperaisenKoordinaatti == kopionKoordinaatti);
    }
    
    @Test
    public void kopioKonstruktoriKopioiAlkuperaistaVastaavanKoordinaatin() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        Koordinaatti alkuperaisenKoordinaatti = pelilauta.getLauta()[1][2];
        Koordinaatti kopionKoordinaatti = kopio.getLauta()[1][2];
        
        assertTrue(alkuperaisenKoordinaatti.equals(kopionKoordinaatti));
    }
    
    @Test
    public void kopioKonstruktoriKopioiUuteenOlioonAlkuperaisenRyhman() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        Ryhma alkuperaisenRyhma = null;
        
        for (Ryhma ryhma : pelilauta.getRyhmat()) {
            alkuperaisenRyhma = ryhma;
        }
        
        Ryhma kopionRyhma = null;
        
        for (Ryhma ryhma : kopio.getRyhmat()) {
            kopionRyhma = ryhma;
        }
        
        assertFalse(alkuperaisenRyhma == kopionRyhma);
    }
    
    public void kopioKonstruktoriKopioiAlkuperaistaVastaavanRyhman() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        Pelilauta kopio = new Pelilauta(pelilauta);
        
        Ryhma alkuperaisenRyhma = null;
        
        for (Ryhma ryhma : pelilauta.getRyhmat()) {
            alkuperaisenRyhma = ryhma;
        }
        
        Ryhma kopionRyhma = null;
        
        for (Ryhma ryhma : kopio.getRyhmat()) {
            kopionRyhma = ryhma;
        }
        
        assertFalse(alkuperaisenRyhma.equals(kopionRyhma));
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
        
        Ryhma ryhma = pelilauta.etsiRyhma(new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(Vari.MUSTA, ryhma.getVari());
    }
    
    @Test
    public void lisaaSiirtoLisaaOikeanMaaranVapauksiaLisatessaRyhmanTyhjalleLaudalle1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 0);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        assertEquals(2, pelilauta.etsiRyhma(koordinaatti).getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaOikeanMaaranVapauksiaLisatessaRyhmanTyhjalleLaudalle2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        assertEquals(3, pelilauta.etsiRyhma(koordinaatti).getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaOikeanMaaranVapauksiaLisatessaRyhmanTyhjalleLaudalle3() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        Koordinaatti koordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        
        assertEquals(4, pelilauta.etsiRyhma(koordinaatti).getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoLaskeeOikeanMaaranRyhmiaKahdelleVierekkaiselleRyhmalle() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 2);
        Koordinaatti toinenKoordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        pelilauta.lisaaSiirto(Vari.VALKOINEN, toinenKoordinaatti);
        
        assertEquals(2, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoLaskeeOikeanMaaranVapauksiaKahdelleVierekkaiselleRyhmalle1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 2);
        Koordinaatti toinenKoordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        pelilauta.lisaaSiirto(Vari.VALKOINEN, toinenKoordinaatti);
        
        assertEquals(2, pelilauta.etsiRyhma(koordinaatti).getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoLaskeeOikeanMaaranVapauksiaKahdelleVierekkaiselleRyhmalle2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        Koordinaatti koordinaatti = new Koordinaatti((byte) 0, (byte) 2);
        Koordinaatti toinenKoordinaatti = new Koordinaatti((byte) 1, (byte) 2);
        
        pelilauta.lisaaSiirto(Vari.MUSTA, koordinaatti);
        pelilauta.lisaaSiirto(Vari.VALKOINEN, toinenKoordinaatti);
        
        assertEquals(3, pelilauta.etsiRyhma(toinenKoordinaatti).getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoLaskeeOikeanMaaranRyhmiaYhdistetylleRyhmalle1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        
        assertEquals(1, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoLaskeeOikeanMaaranRyhmiaYhdistetylleRyhmalle2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        
        assertEquals(1, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoPoistaaRyhmanKunRyhmaSyodaan() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        assertEquals(4, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoEiRikoRyhmiaJoilleLisataanVapaus1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        Ryhma ryhma = pelilauta.etsiRyhma(new Koordinaatti((byte) 0, (byte) 1));
        
        assertEquals(3, ryhma.vapauksienMaara());
    }
    
    @Test
    public void lisaaSiirtoEiRikoRyhmiaJoilleLisataanVapaus2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        
        Ryhma ryhma = pelilauta.etsiRyhma(new Koordinaatti((byte) 0, (byte) 1));
        
        assertTrue(pelilauta.getRyhmat().contains(ryhma));
    }
    
    @Test
    public void lisaaSiirtoVarmistaaEttaSyodynKivenKoordinaatissaNull() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));

        assertNull(pelilauta.getLauta()[1][1]);
    }
    
    @Test
    public void suurempiTestiRyhmienMaarasta() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 2));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 5));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 3));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 2, (byte) 2));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 2, (byte) 5));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 3, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 3, (byte) 5));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 3));
        
        assertEquals(5, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoYhdistaaRyhmanOikeinKunKaksiSamaanNaapuriRyhmaanKuuluvaa() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        
        assertEquals(1, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoPoistaaVapaudenOikeinKunPoistettavaKoskeeKahteenNaapuriKiveen() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        
        Ryhma ryhma = pelilauta.etsiRyhma(new Koordinaatti((byte) 1, (byte) 1));
        
        assertEquals(3, ryhma.getVapaudet().size());
    }
    
    @Test
    public void lisaaSiirtoPoistaaRyhmanOikeinKunKahdenSamanRyhmanKivenVapausViedaanViimeiseksi1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        
        assertNull(pelilauta.etsiRyhma(new Koordinaatti((byte) 1, (byte) 1)));
    }
    
    @Test
    public void lisaaSiirtoPoistaaRyhmanOikeinKunKahdenSamanRyhmanKivenVapausViedaanViimeiseksi2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 2, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        
        assertEquals(3, pelilauta.getRyhmat().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaPoistetutKivetOikein1() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        
        assertEquals(1, pelilauta.getKaapatutKivet().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaPoistetutKivetOikein2() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 2));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 1));
        
        Koordinaatti kaapattu = null;
        
        for (Koordinaatti koordinaatti : pelilauta.getKaapatutKivet()) {
            kaapattu = koordinaatti;
        }
        
        assertTrue(kaapattu.equals(new Koordinaatti((byte) 1, (byte) 1)));
    }
    
    @Test
    public void lisaaSiirtoLisaaPoistetutKivetOikein3() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 0));
        
        assertEquals(2, pelilauta.getKaapatutKivet().size());
    }
    
    @Test
    public void lisaaSiirtoLisaaPoistetutKivetOikein4() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 0));
        
        assertTrue(pelilauta.getKaapatutKivet().contains(new Koordinaatti((byte) 0, (byte) 0)));
    }
    
    @Test
    public void lisaaSiirtoLisaaPoistetutKivetOikein5() {
        Pelilauta pelilauta = new Pelilauta(new HashSet<>());
        
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 0, (byte) 1));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 1, (byte) 1));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 0, (byte) 0));
        pelilauta.lisaaSiirto(Vari.VALKOINEN, new Koordinaatti((byte) 1, (byte) 0));
        pelilauta.lisaaSiirto(Vari.MUSTA, new Koordinaatti((byte) 2, (byte) 0));
        
        assertTrue(pelilauta.getKaapatutKivet().contains(new Koordinaatti((byte) 1, (byte) 0)));
    }
}
