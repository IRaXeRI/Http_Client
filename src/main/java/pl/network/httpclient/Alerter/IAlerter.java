package pl.network.httpclient.Alerter;

import javafx.scene.control.Alert;

public interface IAlerter {
    public static IAlerter getInstance() { return new Alerter(); }
    public void showAlert(Alert.AlertType type, String title, String message);
}
