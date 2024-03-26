module tic.tactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens tic.tactoe to javafx.fxml;
    exports tic.tactoe;
}
