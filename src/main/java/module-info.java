module pl.network.httpclient.http_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens pl.network.httpclient.http_client to javafx.fxml;
    exports pl.network.httpclient.http_client;
}