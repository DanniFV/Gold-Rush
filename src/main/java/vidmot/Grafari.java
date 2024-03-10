package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Grafari extends Rectangle {
    @FXML
    private Grafari fxGrafari;

    public Grafari() {
        super(100, 50); // Breidd og hæð eftir þörfum
        setStyle("-fx-fill: blue;"); // Stílar fyrir gerð
    }

    public Grafari(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
