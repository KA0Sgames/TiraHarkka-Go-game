package GoPeli.kayttoliittyma;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import GoPeli.tekoaly.Tekoaly;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Ohjelman käyttöliittymästä vastaava luokka, joka käynnistetään ohjelman käynnistyessä pääohjelman toimesta.
 */
public class Kayttoliittyma extends Application {
    private HashMap<Integer, ImageView> mustanKivet;
    private HashMap<Integer, ImageView> valkoisenKivet;
    private Pelitilanne pelitilanne;
    private Tekoaly tekoaly;
    private ImageView merkki;
    
    /**
     * init-metodi joka alustaa käyttöliittymän käyttämät tietorakenteet.
     */
    @Override
    public void init() {
        this.mustanKivet = new HashMap<>();
        this.valkoisenKivet = new HashMap<>();
        this.pelitilanne = Pelitilanne.uusiPeli();
        this.tekoaly = new Tekoaly();
        this.merkki = null;
    }
    
    /**
     * Varsinainen käyttöliittymän toteutus.
     * @param ikkuna Stage-olio, joka toimii JavaFX korkeimman tason säiliönä johon visuaaliset elementit sisällytetään.
     */
    @Override
    public void start(Stage ikkuna) {
        
        Group pelialusta = new Group();
        
        Label mustanVangit = new Label("Mustan vangit");
        mustanVangit.setMinWidth(100);
        mustanVangit.setMaxWidth(100);
        mustanVangit.setAlignment(Pos.CENTER);
        
        Label valkoisenVangit = new Label("Valkoisen vangit");
        valkoisenVangit.setMinWidth(100);
        valkoisenVangit.setMaxWidth(100);
        valkoisenVangit.setAlignment(Pos.CENTER);
        
        Label mustanVankienMaara = new Label("" + pelitilanne.getKaapatutValkoiset());
        mustanVankienMaara.setMinWidth(100);
        mustanVankienMaara.setMaxWidth(100);
        mustanVankienMaara.setAlignment(Pos.CENTER);
        
        Label valkoisenVankienMaara = new Label("" + pelitilanne.getKaapatutMustat());
        valkoisenVankienMaara.setMinWidth(100);
        valkoisenVankienMaara.setMaxWidth(100);
        valkoisenVankienMaara.setAlignment(Pos.CENTER);
        
        Button passaus = new Button("Passaa");
        Button luovutus = new Button("Luovuta");
        
        HBox vankitekstit = new HBox(mustanVangit, valkoisenVangit);
        HBox vankimaarat = new HBox(mustanVankienMaara, valkoisenVankienMaara);
        HBox napit = new HBox(passaus, luovutus);
        
        napit.setAlignment(Pos.CENTER);
        napit.setSpacing(30);
        
        Label viesti = new Label("");
        viesti.setMinWidth(200);
        viesti.setMaxWidth(200);
        viesti.setAlignment(Pos.CENTER);
        
        Label tulos = new Label("");
        tulos.setMinWidth(200);
        tulos.setMaxWidth(200);
        tulos.setAlignment(Pos.CENTER);        
        
        VBox oikeaMenu = new VBox(vankitekstit, vankimaarat, napit, viesti, tulos);
        oikeaMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        oikeaMenu.setSpacing(10);
        oikeaMenu.setPadding(new Insets(10, 0, 0, 0));
        
        HBox asettelu = new HBox(pelialusta, oikeaMenu);
        
        Scene scene = new Scene(asettelu);
        
        Image pelilauta = new Image("D:\\KA0S\\Koulu\\TiraHarkkatyo\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Board.png");
        ImageView lauta = new ImageView(pelilauta);
        
        lauta.setX(0.0);
        lauta.setY(0.0);
        
        
        
        pelialusta.getChildren().add(lauta);

        pelialusta.setOnMouseClicked(event -> {
            if (this.pelitilanne.getPelaaja() == Vari.MUSTA) {
                hiirtaPainettu(event, viesti);
                paivitaPelialusta(pelialusta, lauta);
                paivitaVangit(mustanVankienMaara, valkoisenVankienMaara);
                
                if (this.pelitilanne.peliOhi()) {
                    viesti.setText("Peli ohi!");
                    Vari voittaja = this.pelitilanne.laskeVoittaja();
                        
                    if (voittaja == Vari.MUSTA) {
                        tulos.setText("Musta voitti!");
                    } else {
                        tulos.setText("Valkoinen voitti!");
                    }
                    
                }
            } else {
                Siirto tekoalynSiirto = this.tekoaly.valitseSiirto(this.pelitilanne);

                if (tekoalynSiirto.equals(Siirto.passaus())) {
                    this.pelitilanne.setSiirto(tekoalynSiirto);
                    this.pelitilanne = this.pelitilanne.lisaaSiirto();
                    viesti.setText("Tekoäly passasi.");
                } else if (tekoalynSiirto.equals(Siirto.luovutus())) {
                    this.pelitilanne.setSiirto(tekoalynSiirto);
                    this.pelitilanne = this.pelitilanne.lisaaSiirto();
                } else {
                    kasitteleKoordinaatit(
                        tekoalynSiirto.getKoordinaatti().getYKoordinaatti(),
                        tekoalynSiirto.getKoordinaatti().getXKoordinaatti(),
                        viesti);
                
                    paivitaPelialusta(pelialusta, lauta);
                    paivitaVangit(mustanVankienMaara, valkoisenVankienMaara);
                }

                if (this.pelitilanne.peliOhi()) {
                    viesti.setText("Peli ohi!");
                    
                    if (this.pelitilanne.getEdellinenSiirto().equals(Siirto.luovutus())) {
                        if (this.pelitilanne.getPelaaja() == Vari.MUSTA) {
                            tulos.setText("Musta voitti luovutuksella!");
                        } else {
                            tulos.setText("Valkoinen voitti luovutuksella!");
                        }
                    } else {
                        Vari voittaja = this.pelitilanne.laskeVoittaja();
                    
                    
                        if (voittaja == Vari.MUSTA) {
                            tulos.setText("Musta voitti!");
                        } else {
                            tulos.setText("Valkoinen voitti!");
                        }
                    }
                }
            }
        });
        
        passaus.setOnMouseClicked(tapahtuma -> {
            this.pelitilanne.setSiirto(Siirto.passaus());
            this.pelitilanne = this.pelitilanne.lisaaSiirto();
            
            if (this.pelitilanne.peliOhi()) {
                viesti.setText("Peli ohi!");
                Vari voittaja = this.pelitilanne.laskeVoittaja();
                if (voittaja == Vari.MUSTA) {
                    tulos.setText("Musta voitti!");
                } else {
                    tulos.setText("Valkoinen voitti!");
                }
            }
        });
        
        luovutus.setOnMouseClicked(tapahtuma -> {
            this.pelitilanne.setSiirto(Siirto.luovutus());
            this.pelitilanne = this.pelitilanne.lisaaSiirto();
            viesti.setText("Peli ohi!");
            tulos.setText("Valkoinen voitti luovutuksella.");
        });
        
        ikkuna.setScene(scene);
        ikkuna.show();    
    }
    
    private void hiirtaPainettu(MouseEvent e, Label viesti) {
        if (e.getY() > 25 && e.getY() < 475 && e.getX() > 25 && e.getX() < 475) {
            int yKoordinaatti = ((int) e.getY() - 25) / 50;
            int xKoordinaatti = ((int) e.getX() - 25) / 50;

            kasitteleKoordinaatit(yKoordinaatti, xKoordinaatti, viesti);
        }
    }
    
    private void kasitteleKoordinaatit(int y, int x, Label viesti) {
        Vari pelaaja = this.pelitilanne.getPelaaja();
        viesti.setText("");
        
        this.pelitilanne.setSiirto(Siirto.pelaa(new Koordinaatti((byte) y, (byte) x)));
        this.pelitilanne = this.pelitilanne.lisaaSiirto();
        
        if (this.pelitilanne.getLaitonSiirto() == null) {
            if (pelaaja == Vari.MUSTA) {
                Image mustaKivi = new Image("D:\\KA0S\\Koulu\\TiraHarkkatyo\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Black_stone.png");
                ImageView kivi = new ImageView(mustaKivi);
                
                kivi.setY(y * 50 + 25);
                kivi.setX(x * 50 + 25);
                
                this.mustanKivet.put(y * 10 + x, kivi);
                
                for (Koordinaatti koordinaatti : this.pelitilanne.getPelilauta().getKaapatutKivet()) {
                    this.valkoisenKivet.remove(koordinaatti.getYKoordinaatti() * 10 + koordinaatti.getXKoordinaatti());
                }
            } else {
                Image valkeanKivi = new Image("D:\\KA0S\\Koulu\\TiraHarkkatyo\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\White_stone.png");
                ImageView kivi = new ImageView(valkeanKivi);
                
                kivi.setY(y * 50 + 25);
                kivi.setX(x * 50 + 25);
                
                this.valkoisenKivet.put(y * 10 + x, kivi);
                
                for (Koordinaatti koordinaatti : this.pelitilanne.getPelilauta().getKaapatutKivet()) {
                    this.mustanKivet.remove(koordinaatti.getYKoordinaatti() * 10 + koordinaatti.getXKoordinaatti());
                }
            }
        } else {
            viesti.setText(this.pelitilanne.getLaitonSiirto());
        }
        lisaaViimeisenSiirronMerkki(y, x);
    }
    
    private void paivitaPelialusta(Group pelialusta, ImageView lauta) {
        pelialusta.getChildren().remove(lauta);
            pelialusta.getChildren().removeAll(this.mustanKivet.values());
            pelialusta.getChildren().removeAll(this.valkoisenKivet.values());
            pelialusta.getChildren().remove(this.merkki);
            
            pelialusta.getChildren().add(lauta);
            pelialusta.getChildren().addAll(this.mustanKivet.values());
            pelialusta.getChildren().addAll(this.valkoisenKivet.values());
            pelialusta.getChildren().add(this.merkki);
    }
    
    private void paivitaVangit(Label mustanVankienMaara, Label valkoisenVankienMaara) {
        mustanVankienMaara.setText("" + this.pelitilanne.getKaapatutValkoiset());
        valkoisenVankienMaara.setText("" + this.pelitilanne.getKaapatutMustat());
    }
    
    private void lisaaViimeisenSiirronMerkki(int y, int x) {
        Image siirronMerkki = new Image("D:\\KA0S\\Koulu\\TiraHarkkatyo\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\LastMoveMarker.png");
        ImageView merkki = new ImageView(siirronMerkki);
        
        merkki.setY(y * 50 + 25);
        merkki.setX(x * 50 + 25);
        
        this.merkki = merkki;
    }
    
    /**
     * Käyttöliittymän käynnistävä käyttöliittymän main-metodi.
     * @param args 
     */
    public static void main(String[] args) {
        launch(Kayttoliittyma.class);
    }
}
