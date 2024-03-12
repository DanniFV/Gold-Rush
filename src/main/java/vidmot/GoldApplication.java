package vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GoldApplication extends Application {
    private final HashMap<KeyCode, Stefna> map= new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("gold-rush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        GoldController sc = fxmlLoader.getController();
        sc.getOrvatakkar(sc, scene);
        stage.setTitle("Gold Rush Game");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
