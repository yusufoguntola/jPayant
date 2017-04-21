package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.constants.FeatureTypes;

/**
 *
 * @author Yusuf Oguntola
 */
public class JSONParser {

    JSONObject object;
    String[] clientFieldNames = {"first_name", "last_name", "email", "phone",
        "settlement_bank", "account_number",
        "website", "address", "type", "company_name"};
    String[] invoiceFieldNames = {"client_id", "due_date", "fee_bearer", "items"};
    String[] paymentFields = {"reference_code", "date", "amount", "channel"};
    String[] productFields = {"name", "description", "unit_cost", "type"};

    public JSONParser(String feature, String... params) throws JSONException {
        object = new JSONObject();
        if (feature.equals(FeatureTypes.CLIENT)) {
            for (int i = 0; i < params.length; i++) {
                object.put(clientFieldNames[i], params[i]);
            }
        } else if (feature.equals(FeatureTypes.INVOICE)) {
            for (int i = 0; i < params.length; i++) {
                if (invoiceFieldNames[i].equals("items")) {
                    JSONArray array = new JSONArray(params[i]);
                    object.put(invoiceFieldNames[i], array);
                } else {
                    object.put(invoiceFieldNames[i], params[i]);
                }
            }
        } else if (feature.equals(FeatureTypes.PAYMENT)) {
            for (int i = 0; i < params.length; i++) {
                object.put(paymentFields[i], params[i]);
            }
        } else if (feature.equals(FeatureTypes.PRODUCTS)) {
            for (int i = 0; i < params.length; i++) {
                object.put(productFields[i], params[i]);
            }
        }
    }

    public static JSONObject invoiceParser(String clientId, String dueDate, String feeBearer, JSONArray items) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("client_id", clientId);
        object.put("due_date", dueDate);
        object.put("fee_bearer", feeBearer);
        object.put("items", items);
        return object;
    }

    public String getJSONString() {
        return object.toString();
    }

    public JSONObject getJSONObject() {
        return object;
    }

}
