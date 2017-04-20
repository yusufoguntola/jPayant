package base;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;

/**
 *
 * @author Yusuf Oguntola
 */
public class RequestManager {

    OkHttpClient client;
    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String authKey, url;
    private final Request.Builder builder;

    public RequestManager(String url, String authKey) {
        client = new OkHttpClient();
        this.authKey = authKey;
        this.url = url;
        builder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", authKey))
                .addHeader("user-agent", "jPayant-1.0");
    }

    public ResponseParser doGet() throws IOException, JSONException {
        Request request = builder.build();
        return executeRequest(request);
    }

    public ResponseParser doPost(String data) throws IOException, JSONException {
        RequestBody body = RequestBody.create(JSON, data);
        Request request = builder
                .post(body)
                .build();
        return executeRequest(request);
    }

    public ResponseParser doPut(String data) throws IOException, JSONException {
        RequestBody body = RequestBody.create(JSON, data);
        Request request = builder
                .put(body)
                .build();
        return executeRequest(request);
    }

    public ResponseParser doDelete() throws IOException, JSONException {
        Request request = builder.delete().build();
        return executeRequest(request);
    }

    private String getStatusMessage(int statusCode) {
        if (statusCode == 404) {
            return "Object request cannot be found";
        } else {
            return String.format("%s", statusCode);
        }
    }

    public ResponseParser executeRequest(Request request) throws IOException, JSONException {
        Response response = client.newCall(request).execute();
        int code = response.code();
        return new ResponseParser(response.body().string(), getStatusMessage(code));
    }
}
