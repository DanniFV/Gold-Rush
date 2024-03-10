package vidmot;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;


public class GoldController {


    private static GoldController instance;
    @FXML
    private Pane fxLeikbord;
    private HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();
    @FXML
    private Rectangle fxGrafari;
    @FXML
    private Label fxStefna;     // sýnir stefnuna






    public static GoldController getInstance() {
        if (instance == null) {
            instance = new GoldController();
        }
        return instance;
    }

    public void setStefna(int upp) {
        fxStefna.setText(upp+"");
    }


    public void setDifficultyLevel(int difficultyLevel) {
        // Implement logic to store the selected difficulty level
        // For example, you can store it in a variable or pass it to another method.
        // Here, let's assume you have a method setDifficultyLevel in your GoldController
        // that accepts the selected difficulty level as a parameter.
        System.out.println("Stilla erfiðleikastigið: " + difficultyLevel);
        // goldController.setDifficultyLevel(difficultyLevel);
    }

    private void orvatakkar(GoldController sc, Scene scene) {
        // setjum upp beina aðganginn frá örvatökkunum og í hornið
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);

        stefna(sc, scene);
    }

    private void stefna(GoldController sc, Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED,      //KeyEvents eru sendar á Scene
                event -> {      // lambda fall - event er parameter
                    // flettum upp horninu fyrir KeyCode í map
                    sc.setStefna(map.get(event.getCode()).getGradur());
                    System.out.println(fxStefna.getText());
                    afram();
                });
    }


    public void getOrvatakkar(GoldController sc, Scene scene){
        orvatakkar(sc, scene);
    }

    private void afram(){
        double X = fxGrafari.getX();
        double Y = fxGrafari.getY();
        double W = fxGrafari.getWidth();
        double H = fxGrafari.getHeight();

        String stefna = fxStefna.getText();
        if (stefna.equals("90")) {
            fxGrafari.setY(Y - H);
        } else if (stefna.equals("180")) {
            fxGrafari.setX(X - W);
        } else if (stefna.equals("270")) {
            fxGrafari.setY(Y + H);
        } else if (stefna.equals("360")) {
            fxGrafari.setX(X + W);
        }

    }






}
