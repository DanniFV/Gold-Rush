package vidmot;


import javafx.event.ActionEvent;
import javafx.scene.control.RadioMenuItem;

public class MenuController {
    private static GoldController goldController = new GoldController();

    public static GoldController getGoldController() {
        if (goldController == null) {
            goldController = new GoldController();
        }
        return goldController;
    }

    public void setController(GoldController goldController){
        this.goldController = goldController;
    }

    public void onErfidleikastig(ActionEvent actionEvent){
        goldController.setDifficultyLevel
                (Integer.parseInt(((RadioMenuItem)actionEvent.getSource()).getId()));
    }



    public void onByrjaLeik(ActionEvent actionEvent) {
            goldController.onByrja();
            goldController.raesaKlukku();


    }

    public void onHaettaLeik(ActionEvent actionEvent) {
        goldController.onHaetta();
    }

}
