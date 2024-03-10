module com.example.goldrush {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens vidmot to javafx.fxml;
    exports vidmot;
}
