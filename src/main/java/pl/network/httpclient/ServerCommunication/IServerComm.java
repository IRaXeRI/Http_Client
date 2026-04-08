package pl.network.httpclient.ServerCommunication;

import java.net.http.HttpResponse;
import org.json.JSONArray;

public interface IServerComm {
    public static IServerComm getConnection() throws ExServerComm { return new ServerComm(); }
    public HttpResponse<String> sendRequest(EnREST command,String endpoint, JSONArray headers, JSONArray params, String body) throws ExServerComm;
}
