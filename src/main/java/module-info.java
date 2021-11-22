module sample.test_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens sample.test_fx to javafx.fxml;
    exports sample.test_fx;
}