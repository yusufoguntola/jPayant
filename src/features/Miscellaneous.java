package features;

import base.Base;
import base.RequestManager;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import utils.AccountDetails;
import features.objects.PaymentObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class Miscellaneous extends Base {

    public Miscellaneous(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     * Returns details of a particular payment.
     *
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public PaymentObject getPayment(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new PaymentObject(new RequestManager(getUrl(String.format("payments/%s", referenceCode)), getAuthKey()).doGet());
    }

    /**
     * Retrieves all banks as a Map of bank code to bank name.
     *
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public Map<String, String> getBanks() throws InvalidInputException, IOException, JSONException {
        JSONObject dataObject = new RequestManager(getUrl("banks"), getAuthKey()).doGet().getJSONObject();
        Iterator keys = dataObject.keys();
        Map<String, String> result = new HashMap<>();
        while (keys.hasNext()) {
            Object next = keys.next();
            result.put(next.toString(), dataObject.getString(next.toString()));
        }
        return result;
    }

    /**
     * Returns the details of an accountNumber. The bankId, AccountName and
     * AccountNumber;<br>
     * The settlementBank parameter should be the <b>code</b> for a particular
     * bank e.g: 011 for First Bank PLC.
     *
     * @param settlementBank
     * @param accountNumber
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public AccountDetails resolveAccount(String settlementBank, String accountNumber) throws JSONException, InvalidInputException, IOException {
        JSONObject object = new JSONObject();
        object.put("settlement_bank", settlementBank);
        object.put("account_number", accountNumber);
        JSONObject responseObject = new RequestManager(getUrl("resolve-account"), getAuthKey()).doPost(object.toString()).getJSONObject();
        return new AccountDetails(responseObject.getJSONObject("data"));
    }

}
