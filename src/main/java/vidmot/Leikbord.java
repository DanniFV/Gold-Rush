package vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Leikbord extends Pane {
    @FXML
    private Pane rootPane;
    @FXML
    private Grafari fxGrafari;

    public Leikbord() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));
        loader.setController(this);


        try {
            rootPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public Grafari getFxGrafari() {
        return fxGrafari;
    }



}
