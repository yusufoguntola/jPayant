package features;

import base.Base;
import base.RequestManager;
import base.ResponseParser;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import features.objects.TransferObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.JSONParser;
import utils.constants.FeatureTypes;

/**
 *
 * @author Yusuf Oguntola
 */
public class Transfer extends Base {

    public Transfer(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param settlementBank
     * @param accountNumber
     * @param amount
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public TransferObject addTransfer(String firstName, String lastName, String email, String phone, String settlementBank, String accountNumber, String amount) throws JSONException, InvalidInputException, IOException {
        JSONObject clientObject = new JSONParser(FeatureTypes.CLIENT, firstName, lastName, email, phone, settlementBank, accountNumber).getJSONObject();
        JSONObject dataObject = new JSONObject();
        dataObject.put("client", clientObject);
        dataObject.put("amount", amount);
        return new TransferObject(new RequestManager(getUrl("transfers"), getAuthKey()).doPost(dataObject.toString()).getJSONObject().getJSONObject("data"));
    }

    /**
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public TransferObject getTransfer(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new TransferObject(new RequestManager(getUrl(String.format("transfers/%s", referenceCode)), getAuthKey()).doGet().getJSONObject().getJSONObject("data"));
    }

    /**
     * Retrieves transfers made within the range of the specified period.<br>
     * Period should be obtained from HistoryPeriod e.g: HistoryPeriod.TODAY
     *
     * @param period
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public List<TransferObject> getTransferHistory(String period) throws JSONException, InvalidInputException, IOException {
        JSONObject dataObject = new JSONObject();
        dataObject.put("period", period);
        return processHistory(dataObject.toString());
    }

    /**
     * Retrieves transfers made within the specified date.<br>
     * Date should be written in the format DD/MM/YYYY e.g 23/05/2016
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public List<TransferObject> getTransferHistory(String startDate, String endDate) throws JSONException, InvalidInputException, IOException {
        JSONObject dataObject = new JSONObject();
        dataObject.put("period", "custom");
        dataObject.put("start", startDate);
        dataObject.put("end", endDate);
        return processHistory(dataObject.toString());
    }

    private List<TransferObject> processHistory(String data) throws InvalidInputException, IOException, JSONException {
        JSONObject responseObject = new RequestManager(getUrl("transfers/history"), getAuthKey()).doPost(data).getJSONObject();
        JSONArray invoices = responseObject.getJSONObject("data").getJSONArray("expenses");
        List<TransferObject> result = new ArrayList<>();
        for (int i = 0; i < invoices.length(); i++) {
            result.add(new TransferObject(invoices.getJSONObject(i)));
        }
        return result;
    }

    /**
     * Deletes the transfer with the specified reference code.
     *
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ResponseParser deleteTransfer(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new RequestManager(getUrl(String.format("transfers/%s", referenceCode)), getAuthKey()).doDelete();
    }

}
