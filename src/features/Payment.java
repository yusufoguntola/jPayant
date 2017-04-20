package features;

import base.Base;
import base.RequestManager;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JSONParser;
import features.objects.PaymentObject;
import utils.constants.FeatureTypes;

/**
 *
 * @author Yusuf Oguntola
 */
public class Payment extends Base {

    public Payment(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     * Saves a new payment object
     *
     * @param referenceCode
     * @param date
     * @param amount
     * @param channel
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public PaymentObject addPayment(String referenceCode, String date, String amount, String channel) throws JSONException, InvalidInputException, IOException {
        String data = new JSONParser(FeatureTypes.PAYMENT, referenceCode, date, amount, channel).getJSONString();
        return new PaymentObject(new RequestManager(getUrl("payments"), getAuthKey()).doPost(data));
    }

    /**
     * Returns the payment object matching the supplied reference code.
     *
     * @param referenceCode
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public PaymentObject getPayment(String referenceCode) throws JSONException, InvalidInputException, IOException {
        return new PaymentObject(new RequestManager(getUrl(String.format("payments/%s", referenceCode)), getAuthKey()).doGet());
    }

    /**
     * Returns a list of payment made within the range of the period
     * selected.<br>
     * Period should be gotten from HistoryPeriod. e.g: HistoryPeriod.TODAY
     *
     * @param period
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public List<PaymentObject> getPaymentHistory(String period) throws JSONException, InvalidInputException, IOException {
        JSONObject object = new JSONObject();
        object.put("period", period);
        JSONObject responseObject = new RequestManager(getUrl("payments/history"), getAuthKey()).doPost(object.toString()).getJSONObject();
        List<PaymentObject> result = new ArrayList<>();
        JSONArray array = responseObject.getJSONObject("data").getJSONArray("payments");
        for (int i = 0; i < array.length(); i++) {
            JSONObject data = new JSONObject();
            data.put("data", array.getJSONObject(i));
            result.add(new PaymentObject(data));
        }
        return result;
    }

    /**
     * Returns a list of payment made within the range of the dates
     * supplied.<br>
     * startDate and endDate should be in the format DD/MM/YYYY
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public List<PaymentObject> getPaymentHistory(String startDate, String endDate) throws JSONException, InvalidInputException, IOException {
        JSONObject object = new JSONObject();
        object.put("period", "custom");
        object.put("start", startDate);
        object.put("end", endDate);
        JSONObject responseObject = new RequestManager(getUrl("payments/history"), getAuthKey()).doPost(object.toString()).getJSONObject();
        List<PaymentObject> result = new ArrayList<>();
        JSONArray array = responseObject.getJSONObject("data").getJSONArray("payments");
        for (int i = 0; i < array.length(); i++) {
            JSONObject data = new JSONObject();
            data.put("data", array.getJSONObject(i));
            result.add(new PaymentObject(data));
        }
        return result;
    }

}
