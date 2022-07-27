package GoPeli.kayttoliittyma;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kayttoliittyma extends Application {

    @Override
    public void start(Stage ikkuna) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        
        Image pelilauta = new Image("src\\main\\kuvat\\Board.png");
        ImageView imageView = new ImageView(pelilauta);
        
        imageView.setX(0.0);
        imageView.setY(0.0);
        
        root.getChildren().add(imageView);
        
        ikkuna.setScene(scene);
        ikkuna.show();
    }
    
    public static void main(String[] args) {
        launch(Kayttoliittyma.class);
    }
}
