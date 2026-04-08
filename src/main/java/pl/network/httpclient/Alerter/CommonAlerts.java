package pl.network.httpclient.Alerter;

import javafx.scene.control.Alert;

public class CommonAlerts {
    public static void errorConnection() {
        IAlerter.getInstance().showAlert(Alert.AlertType.ERROR, "Error", "There is an error with connection to server - check internet connection");
    }

    public static void errorAuthorizationToken() {
        IAlerter.getInstance().showAlert(Alert.AlertType.ERROR, "Error", "There is an error with connection to server - try to refresh the login credentials");
    }
}
