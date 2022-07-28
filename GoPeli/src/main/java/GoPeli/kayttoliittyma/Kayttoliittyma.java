package GoPeli.kayttoliittyma;

import GoPeli.logiikka.Koordinaatti;
import GoPeli.logiikka.PelinHallinnointi;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kayttoliittyma extends Application {

    @Override
    public void start(Stage ikkuna) {
        PelinHallinnointi pelinHallinnointi = new PelinHallinnointi();
        
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        
        Image pelilauta = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Board.png");
        ImageView lauta = new ImageView(pelilauta);
        
        Image mustakivi = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\Black_stone.png");
        ImageView musta = new ImageView(mustakivi);
        
        Image valkoinenkivi = new Image("C:\\KA0S\\Programming\\Koulu\\TiraHarkka\\TiraHarkka-Go-game\\GoPeli\\src\\main\\kuvat\\White_stone.png");
        ImageView valkoinen = new ImageView(valkoinenkivi);
        
        lauta.setX(0.0);
        lauta.setY(0.0);
        
        root.getChildren().add(lauta);
        
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 1, (byte) 2));
        pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 2, (byte) 3));
        // pelinHallinnointi.lisaaSiirto(new Koordinaatti((byte) 4, (byte) 4));
        
        
        for (byte i = 0; i < 9; i++) {
            for (byte j = 0; j < 9; j++) {
                if (pelinHallinnointi.getKoordinaatinTila(new Koordinaatti(j, i)) == 1) {
                    musta.setX(100 * j + 50);
                    musta.setY(100 * i + 50);
                    root.getChildren().add(musta);
                } else if (pelinHallinnointi.getKoordinaatinTila(new Koordinaatti(j, i)) == 2) {
                    valkoinen.setX(100 * j + 50);
                    valkoinen.setY(100 * i + 50);
                    root.getChildren().add(valkoinen);
                }
            }
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
