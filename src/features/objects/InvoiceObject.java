package features.objects;

import base.ResponseParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Item;

/**
 *
 * @author Yusuf Oguntola
 */
public class InvoiceObject {

    JSONObject object;
    ResponseParser parser;

    public InvoiceObject(ResponseParser parser) throws JSONException {
        object = parser.getJSONObject().getJSONObject("data");
        this.parser = parser;
    }

    public InvoiceObject(JSONObject object) {
        this.object = object;
        this.parser = null;
    }

    public ResponseParser getParser() {
        return parser;
    }

    public String getDueDate() throws JSONException {
        if (object.has("due_date")) {
            return object.getString("due_date");
        }
        return "";
    }

    public ClientObject getClient() throws JSONException {
        if (object.has("client")) {
            return new ClientObject(object.getJSONObject("client"));
        }
        return null;
    }

    public String getReferenceCode() throws JSONException {
        if (object.has("reference_code")) {
            return object.getString("reference_code");
        }
        return "";
    }

    public String getFeeBearer() throws JSONException {
        if (object.has("fee_bearer")) {
            return object.getString("fee_bearer");
        }
        return "";
    }

    public List<Item> getItems() throws JSONException {
        if (object.has("items")) {
            JSONArray array = object.getJSONArray("items");
            List<Item> result = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                result.add(new Item(array.getJSONObject(i)));
            }
            return result;
        }
        return null;
    }
}
