package GoPeli.kayttoliittyma;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.PelinHallinnointi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Kayttoliittyma extends Application {

    @Override
    public void start(Stage ikkuna) {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        Group pelialusta = new Group();
        
        Label mustanVangit = new Label("Mustan vangit");
        mustanVangit.setMinWidth(100);
        mustanVangit.setMaxWidth(100);
        
        Label valkoisenVangit = new Label("Valkoisen vangit");
        valkoisenVangit.setMinWidth(100);
        valkoisenVangit.setMaxWidth(100);
        
        Label mustanVankienMaara = new Label("" + pelinHallinnointi.getMustanVangit());
        mustanVankienMaara.setMinWidth(100);
        mustanVankienMaara.setMaxWidth(100);
        
        Label valkoisenVankienMaara = new Label("" + pelinHallinnointi.getValkoisenVangit());
        valkoisenVankienMaara.setMinWidth(100);
        valkoisenVankienMaara.setMaxWidth(100);
        
        HBox vankitekstit = new HBox(mustanVangit, valkoisenVangit);
        HBox vankimaarat = new HBox(mustanVankienMaara, valkoisenVankienMaara);
        
        
        
        VBox oikeaMenu = new VBox(vankitekstit, vankimaarat);
        oikeaMenu.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        
        HBox asettelu = new HBox(pelialusta, oikeaMenu);
        
        Scene scene = new Scene(asettelu);
        
        Image pelilauta = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Board.png");
        ImageView lauta = new ImageView(pelilauta);
        
        Image mustakivi = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Black_stone.png");
        ImageView musta = new ImageView(mustakivi);
        
        Image valkoinenkivi = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\White_stone.png");
        ImageView valkoinen = new ImageView(valkoinenkivi);
        
        lauta.setX(0.0);
        lauta.setY(0.0);
        
        pelialusta.getChildren().add(lauta);
        
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 1, (byte) 2));
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 2, (byte) 3));
        // pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 4, (byte) 4));
        
        
        while (true) {
            break;
        }
        
        ikkuna.setScene(scene);
        ikkuna.show();
        
        while (true) {
            for (byte i = 0; i < 9; i++) {
                for (byte j = 0; j < 9; j++) {
                       System.out.print("[" + pelinHallinnointi.getKoordinaatinTila(new Koordinaatti(j, i)) + "]");
                }
                System.out.println("");
            }
            break;
        }
    }
    
    public static void main(String[] args) {
        launch(Kayttoliittyma.class);
    }
}
