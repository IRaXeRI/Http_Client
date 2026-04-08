package pl.network.httpclient.ServerCommunication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import org.json.JSONArray;

public class ServerComm implements IServerComm {
    private final String serverAddress;
    private final HttpClient client = HttpClient.newHttpClient();

    public ServerComm() throws ExServerComm {
        try (InputStream input = ServerComm.class.getResourceAsStream("/pl/lodz/uni/labmate_ui/serverAddress.properties")) {
            if (input == null) {
                throw new ExServerComm("Configuration file serverAddress.properties not found");
            }
            Properties prop = new Properties();
            prop.load(input);
            String host = prop.getProperty("SERVER_HOST");
            String port = prop.getProperty("SERVER_PORT");
            this.serverAddress = "http://" + host + ":" + port;

        } catch (IOException e) {
            throw new ExServerComm("There was an error reading configuration file - the connection to server could not be created");
        }
    }

    @Override
    public HttpResponse<String> sendRequest(EnREST command, String endpoint, JSONArray headers, JSONArray params, String body) throws ExServerComm {
        try {
            String paramsStr = "";
            if (params!=null) {
                if (!params.isEmpty()) {
                    paramsStr = "?";
                    for (int i = 0; i < params.length(); i++) {
                        paramsStr += params.getJSONObject(i).getString("name") + "=" + params.getJSONObject(i).getString("value") + "&";
                    }
                    paramsStr = paramsStr.substring(0, params.length()-1);
                }
            }
            HttpRequest.Builder request = HttpRequest.newBuilder(URI.create(serverAddress + endpoint + paramsStr));
            for (int i=0; i<headers.length(); i++) {
                request.header(headers.getJSONObject(i).getString("name"), headers.getJSONObject(i).getString("value"));
            }
            switch (command) {
                case GET:
                    request.GET();
                    break;
                case POST:
                    request.header("Content-Type", "text/plain").POST(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                    break;
                case PUT:
                    request.header("Content-Type", "text/plain").PUT(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                    break;
                case DELETE:
                    request.DELETE();
                    break;
                case PATCH:
                    request.header("Content-Type", "text/plain").method("PATCH", HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                    break;
            }
            return client.send(request.build(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ExServerComm("There was an error with Sending the Request");
        }
    }
}
