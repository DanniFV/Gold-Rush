package vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import vidmot.GoldController;

public class Klukka {
    private SimpleIntegerProperty timi;
    private Timeline timeline;
    private static GoldController goldController = new GoldController();

    public Klukka(int startTime) {
        this.timi = new SimpleIntegerProperty(startTime);
    }

    public void tic() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (timi.get() > 0) {
                timi.set(timi.get() - 1);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void stopTime(){
        if (timeline != null) {
            timeline.stop();
        }
    }

    public int getTimi() {
        return timi.get();
    }

    public SimpleIntegerProperty timiProperty() {
        return timi;
    }

    // Aðferð sem stillir tímann á ákveðinn gildi
    public void setTimi(int nyrTimi) {
        timi.set(nyrTimi);
    }
}
