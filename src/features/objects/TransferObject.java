package features.objects;

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
public class TransferObject {

    JSONObject object;

    public TransferObject(JSONObject object) {
        this.object = object;
    }

    public String getClientId() throws JSONException {
        return object.getString("client_id");
    }

    public String getReferenceCode() throws JSONException {
        return object.getString("reference_code");
    }

    public String getPaymentId() throws JSONException {
        return object.getString("payment_id");
    }

    public String getSettlementBank() throws JSONException {
        return object.getString("settlement_bank");
    }

    public String getAccountNumber() throws JSONException {
        return object.getString("account_number");
    }

    public String getAccountName() throws JSONException {
        return object.getString("account_name");
    }

    public ClientObject getClient() throws JSONException {
        return new ClientObject(object.getJSONObject("client"));
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
