package pl.network.httpclient.Alerter;

import javafx.scene.control.Alert;

public class Alerter implements IAlerter {
    @Override
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
