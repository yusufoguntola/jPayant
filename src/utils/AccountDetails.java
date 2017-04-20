package utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class AccountDetails {

    JSONObject object;

    public AccountDetails(JSONObject object) {
        this.object = object;
    }

    public String getBankId() throws JSONException {
        return object.getString("settlement_bank");
    }

    public String getAccountNumber() throws JSONException {
        return object.getString("account_number");
    }

    public String getAccountName() throws JSONException {
        return object.getString("account_name");
    }

    @Override
    public String toString() {
        try {
            return getAccountNumber() + "\n" + getAccountName();
        } catch (JSONException ex) {
            return "";
        }
    }
}
