package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import vinnsla.Klukka;

import java.util.HashMap;


public class GoldController {

    private static MenuController menuController = new MenuController();

    private boolean iGangi = false;

    private int Stig;

    Klukka klukka = new Klukka(30);

    private HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();

    private static GoldController instance;

    private Leikbord leikbord;

    @FXML
    private Pane fxLeikbord;

    @FXML
    private Rectangle fxGrafari;

    @FXML
    private Label fxStefna;// sýnir stefnuna

    @FXML
    private Label fxTimiEftir;

    @FXML
    private Label fxStig;

    public void initialize(){
 /**       leikur = new Leikur();
        fxLeikbord.setLeikur(leikur);
        menuController.setController(this);
        fxStig.textProperty().bind(leikur.stiginProperty().asString());
        fxTimiEftir.textProperty().bind(klukka.tiiProperty().asString());

  */

        fxTimiEftir.textProperty().bind(klukka.timiProperty().asString());

        fxLeikbord.requestFocus();
        if (menuController != null) {
            menuController.setController(this);
        } else {
            System.err.println("menuController is null");
        }
    }


    public static GoldController getInstance() {
        if (instance == null) {
            instance = new GoldController();
        }
        return instance;
    }

    public void setStefna(int upp) {
        fxStefna.setText(upp+"");
    }

    public void setLeikbord(Leikbord fxLeikbord) {
        this.fxLeikbord = new Leikbord();
    }

    public void setjaLeikabord (Leikbord leikbord) {
        this.leikbord = new Leikbord();
    }

    public void setDifficultyLevel(int difficultyLevel) {
        // Implement logic to store the selected difficulty level
        // For example, you can store it in a variable or pass it to another method.
        // Here, let's assume you have a method setDifficultyLevel in your GoldController
        // that accepts the selected difficulty level as a parameter.
        System.out.println("Stilla erfiðleikastigið: " + difficultyLevel);
        // goldController.setDifficultyLevel(difficultyLevel);
        int tStig = 30;
        if (difficultyLevel == 1) {
            tStig = 30;
        } else if (difficultyLevel == 2) {
            tStig = 20;
        } else if (difficultyLevel == 3) {
            tStig = 10;
        }
        klukka = new Klukka(tStig);
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
                    grafaGull();
                });
    }


    public void getOrvatakkar(GoldController sc, Scene scene){
        orvatakkar(sc, scene);
    }

    public void onByrja() {
        System.out.println("Leikurinn er hafinn");
        iGangi = true;
        gullTimer();
        fxTimiEftir.textProperty().bind(klukka.timiProperty().asString());

    }

    public void raesaKlukku(){
        System.out.println("Klukka er ræst!");
        klukka.tic();

        klukka.timiProperty().addListener((ObservableValue<? extends Number> observable, Number oldvalue, Number newValue) -> {
            if (newValue.intValue() == 0) {
                iGangi = false;
                System.out.println("Times up assface!");
            }
        });

    }

    public void onHaetta() {
        System.out.println("Leikurinn er stoppp");
        iGangi = false;
        klukka.stopTime();

    }

    private void afram(){
        double X = fxGrafari.getX();
        double Y = fxGrafari.getY();
        double W = fxGrafari.getWidth();
        double H = fxGrafari.getHeight();

        String stefna = fxStefna.getText();

        if (iGangi) {
            if (stefna.equals("90")) {
                fxGrafari.setY(Y - H);
            } else if (stefna.equals("180")) {
                fxGrafari.setX(X - W);
            } else if (stefna.equals("270")) {
                fxGrafari.setY(Y + H);
            } else if (stefna.equals("360")) {
                fxGrafari.setX(X + W);
            }
        } else {
            System.out.println("haha leikur ekki í gangi");
        }
        System.out.println("grafari XY: " + fxGrafari.getX() + " " + fxGrafari.getY());
    }

    // Gull
    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();


    private void gullTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            if (iGangi) {
                virkjaGull();
            }
        }));


        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

    }


    private int randomHnit(int S, int s){
        return (int) (Math.random() * (S/s + 1)) * s;
    }


    private void virkjaGull(){
        gullListi.add(
                new Gull(randomHnit((int)fxLeikbord.getWidth(),(int)fxGrafari.getWidth()), randomHnit((int)fxLeikbord.getHeight(),(int)fxGrafari.getHeight()))
        );

        gullListi.addListener((ListChangeListener<Gull>) change -> {
            while (change.next() ){
                if (change.wasAdded()) {
                    for (Gull gull : change.getAddedSubList()) {
                        if (!fxLeikbord.getChildren().contains(gull)) {
                            fxLeikbord.getChildren().add(gull);
                        }
                    }
                }
            }
        });
    }

    private void grafaGull() {
        System.out.println("Bless");
        var iterator = gullListi.iterator();
        while (iterator.hasNext()) {
            Gull gull = iterator.next();
            if (gull.getX() == (int)fxGrafari.getX() && gull.getY() == (int)fxGrafari.getY()) {
                iterator.remove();
                System.out.println("Blessaður");
                gull.setVisible(false);

                fxStig.setText("Stig : " + String.valueOf(++Stig));

            }
        }
    }
}

