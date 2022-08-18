package GoPeli.kayttoliittyma;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.Pelitilanne;
import GoPeli.logiikka.Siirto;
import GoPeli.logiikka.Vari;
import java.util.HashMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
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

public class Kayttoliittyma extends Application {
    private HashMap<Integer, ImageView> mustanKivet;
    private HashMap<Integer, ImageView> valkoisenKivet;
    private Pelitilanne pelitilanne;
    
    @Override
    public void init() {
        this.mustanKivet = new HashMap<>();
        this.valkoisenKivet = new HashMap<>();
        this.pelitilanne = Pelitilanne.uusiPeli();
    }
    
    @Override
    public void start(Stage ikkuna) {
        
        Group pelialusta = new Group();
        
        Label mustanVangit = new Label("Mustan vangit");
        mustanVangit.setMinWidth(100);
        mustanVangit.setMaxWidth(100);
        
        Label valkoisenVangit = new Label("Valkoisen vangit");
        valkoisenVangit.setMinWidth(100);
        valkoisenVangit.setMaxWidth(100);
        
        Label mustanVankienMaara = new Label("" + pelitilanne.getKaapatutValkoiset());
        mustanVankienMaara.setMinWidth(100);
        mustanVankienMaara.setMaxWidth(100);
        
        Label valkoisenVankienMaara = new Label("" + pelitilanne.getKaapatutMustat());
        valkoisenVankienMaara.setMinWidth(100);
        valkoisenVankienMaara.setMaxWidth(100);
        
        HBox vankitekstit = new HBox(mustanVangit, valkoisenVangit);
        HBox vankimaarat = new HBox(mustanVankienMaara, valkoisenVankienMaara);
        
        
        
        VBox oikeaMenu = new VBox(vankitekstit, vankimaarat);
        oikeaMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        HBox asettelu = new HBox(pelialusta, oikeaMenu);
        
        Scene scene = new Scene(asettelu);
        
        Image pelilauta = new Image("D:\\KA0S\\Koulu\\TiraHarkkatyo\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Board.png");
        ImageView lauta = new ImageView(pelilauta);
        
        lauta.setX(0.0);
        lauta.setY(0.0);
        
        pelialusta.getChildren().add(lauta);

        pelialusta.setOnMouseClicked(event -> {
            hiirtaPainettu(event);
            pelialusta.getChildren().remove(lauta);
            pelialusta.getChildren().removeAll(this.mustanKivet.values());
            pelialusta.getChildren().removeAll(this.valkoisenKivet.values());
            
            pelialusta.getChildren().add(lauta);
            pelialusta.getChildren().addAll(this.mustanKivet.values());
            pelialusta.getChildren().addAll(this.valkoisenKivet.values());
        });
        
        ikkuna.setScene(scene);
        ikkuna.show();    
    }
    
    private void hiirtaPainettu(MouseEvent e) {
        if (e.getY() > 25 && e.getY() < 475 && e.getX() > 25 && e.getX() < 475) {
            int yKoordinaatti = ((int) e.getY() - 25) / 50;
            int xKoordinaatti = ((int) e.getX() - 25) / 50;

            kasitteleKoordinaatit(yKoordinaatti, xKoordinaatti);
        }
    }
    
    private void kasitteleKoordinaatit(int y, int x) {
        Vari pelaaja = this.pelitilanne.getPelaaja();
        
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
        }
    }
    
    public static void main(String[] args) {
        launch(Kayttoliittyma.class);
    }
}
