package features;

import base.Base;
import base.RequestManager;
import base.ResponseParser;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import base.exceptions.NoDataException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import features.objects.ClientObject;
import utils.constants.FeatureTypes;
import features.objects.InvoiceObject;
import utils.Item;
import utils.JSONParser;

/**
 *
 * @author Yusuf Oguntola
 */
public class Invoice extends Base {

    /**
     * Constructor Class. <br>
     * The authentication key is used to validate requests. <br>
     * Implementation should either be Implementation.LIVE or
     * Implementation.DEMO
     *
     * @param authKey
     * @param implementation
     * @throws base.exceptions.AuthenticationException
     * @throws base.exceptions.InvalidInputException
     */
    public Invoice(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     * Add a new invoice of the supplied item for the specified client.<br>
     * The format of the dueDate is MM/DD/YYYY.<br>
     * The feeBearer should either be FeeBearer.ACCOUNT or FeeBearer.<br>
     *
     * @param clientObject
     * @param dueDate
     * @param feeBearer
     * @param item
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public InvoiceObject addInvoice(ClientObject clientObject, String dueDate, String feeBearer, Item item) throws JSONException, InvalidInputException, IOException {
        String requestData = new JSONParser(FeatureTypes.INVOICE, clientObject.getClientId(), dueDate, feeBearer, item.getJSONString()).getJSONString();
        return new InvoiceObject(new RequestManager(getUrl("invoices"), getAuthKey()).doPost(requestData));
    }

    /**
     * Returns an InvoiceObject depending on the reference code provided.
     *
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public InvoiceObject getInvoice(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new InvoiceObject(new RequestManager(getUrl(String.format("invoices/%s", referenceCode)), getAuthKey()).doGet());
    }

    /**
     * Sends the invoice specified by the reference code to the customer the
     * invoice is meant for.
     *
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public InvoiceObject sendInvoice(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new InvoiceObject(new RequestManager(getUrl(String.format("invoices/send/%s", referenceCode)), getAuthKey()).doGet());
    }

    /**
     * Deletes the invoice represented by the specified reference code
     *
     * @param referenceCode
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ResponseParser deleteInvoice(String referenceCode) throws InvalidInputException, IOException, JSONException {
        return new RequestManager(getUrl(String.format("invoices/%s", referenceCode)), getAuthKey()).doDelete();
    }

    /**
     * Retrieves invoices saved within the range of the specified period.<br>
     * Period should be obtained from HistoryPeriod e.g: HistoryPeriod.TODAY
     *
     * @param period
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     * @throws base.exceptions.NoDataException
     */
    public List<InvoiceObject> getInvoiceHistory(String period) throws InvalidInputException, IOException, JSONException, NoDataException {
        JSONObject object = new JSONObject();
        object.put("period", period);
        return processHistory(object.toString());
    }

    /**
     * Retrieves invoices saved within the specified date.<br>
     * Date should be written in the format DD/MM/YYYY e.g 23/05/2016
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     * @throws base.exceptions.NoDataException
     */
    public List<InvoiceObject> getInvoiceHistory(String startDate, String endDate) throws InvalidInputException, IOException, JSONException, NoDataException {
        JSONObject object = new JSONObject();
        object.put("period", "custom");
        object.put("start", startDate);
        object.put("end", endDate);
        return processHistory(object.toString());
    }

    private List<InvoiceObject> processHistory(String data) throws InvalidInputException, IOException, JSONException, NoDataException {
        ResponseParser response = new RequestManager(getUrl("invoices/history"), getAuthKey()).doPost(data);
        JSONArray array = response.getJSONObject().getJSONObject("data").getJSONArray("invoices");
        List<InvoiceObject> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(new InvoiceObject(array.getJSONObject(i)));
        }
        return list;
    }

}
