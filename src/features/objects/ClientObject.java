package features.objects;

import utils.constants.FeatureTypes;
import base.ResponseParser;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JSONParser;

/**
 *
 * @author Yusuf Oguntola
 */
public class ClientObject {

    JSONObject object;
    ResponseParser parser;

    public ClientObject(ResponseParser parser) throws JSONException {
        object = parser.getJSONObject().getJSONObject("data");
        this.parser = parser;
    }

    public ClientObject(JSONObject object) {
        this.object = object;
        this.parser = null;
    }

    public String getJSONString() {
        return object.toString();
    }

    public String getShortClientJSONString() throws JSONException {
        return new JSONParser(FeatureTypes.CLIENT, getFirstName(), getLastName(), getEmail(), getPhone()).getJSONString();
    }

    public ResponseParser getParser() {
        return parser;
    }

    public String getClientId() throws JSONException {
        if (object.has("id")) {
            return object.getString("id");
        }
        return "";
    }

    public String getFirstName() throws JSONException {
        if (object.has("first_name")) {
            return object.getString("first_name");
        }
        return "";
    }

    public String getLastName() throws JSONException {
        if (object.has("last_name")) {
            return object.getString("last_name");
        }
        return "";
    }

    public String getEmail() throws JSONException {
        if (object.has("email")) {
            return object.getString("email");
        }
        return "";
    }

    public String getPhone() throws JSONException {
        if (object.has("phone")) {
            return object.getString("phone");
        }
        return "";
    }

    public String getWebsite() throws JSONException {
        if (object.has("website")) {
            return object.getString("website");
        }
        return "";
    }

    public String getAddress() throws JSONException {
        if (object.has("address")) {
            return object.getString("address");
        }
        return "";
    }

    public String getType() throws JSONException {
        if (object.has("type")) {
            return object.getString("type");
        }
        return "";
    }

    public String getSettlementBank() throws JSONException {
        if (object.has("settlement_bank")) {
            return object.getString("settlement_bank");
        }
        return "";
    }

    public String getAccountNumber() throws JSONException {
        if (object.has("account_number")) {
            return object.getString("account_number");
        }
        return "";
    }

    public String getCompanyName() throws JSONException {
        if (object.has("company_name")) {
            return object.getString("company_name");
        }
        return "";
    }
}
