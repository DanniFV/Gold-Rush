package vidmot;


import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenuController {
    @FXML
    private ToggleGroup Erfidleikastig;

    @FXML
    private void onErfidleikastig() {
        RadioMenuItem selected = (RadioMenuItem) Erfidleikastig.getSelectedToggle();
        int selectedDifficulty = Integer.parseInt(selected.getId());
        System.out.println("Valið erfiðleikastig: " + selectedDifficulty);

        // Call GoldController to pass the selected difficulty level
        GoldController.getInstance().setDifficultyLevel(selectedDifficulty);
    }
}
