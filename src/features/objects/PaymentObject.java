package features.objects;

import base.ResponseParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class PaymentObject {

    JSONObject object;
    ResponseParser parser;

    public PaymentObject(ResponseParser parser) {
        object = parser.getJSONObject();
        this.parser = parser;
    }

    public PaymentObject(JSONObject object) {
        this.object = object;
        this.parser = null;
    }

    public ResponseParser getParser() {
        return parser;
    }

    public String getCompanyId() throws JSONException {
        return object.getJSONObject("data").getString("company_id");
    }

    public String getClientId() throws JSONException {
        return object.getJSONObject("data").getString("client_id");
    }

    public String getInvoiceId() throws JSONException {
        return object.getJSONObject("data").getString("invoice_id");
    }

    public String getExpenseId() throws JSONException {
        return object.getJSONObject("data").getString("expense_id");
    }

    public String getAmount() throws JSONException {
        return object.getJSONObject("data").getString("amount");
    }

    public String getCurrency() throws JSONException {
        return object.getJSONObject("data").getString("currency");
    }

    public String getTransactionDate() throws JSONException {
        return object.getJSONObject("data").getString("transaction_date");
    }

    public String getStatus() throws JSONException {
        return object.getJSONObject("data").getString("status");
    }

    public String getReferenceCode() throws JSONException {
        return object.getJSONObject("data").getString("reference_code");
    }

    public String getPaymentId() throws JSONException {
        return object.getJSONObject("data").getString("payment_id");
    }

    public String getReferrer() throws JSONException {
        return object.getJSONObject("data").getString("referrer");
    }

    public String getGatewayResponse() throws JSONException {
        return object.getJSONObject("data").getString("gateway_response");
    }

    public String getMessage() throws JSONException {
        return object.getJSONObject("data").getString("message");
    }

    public String getChannel() throws JSONException {
        return object.getJSONObject("data").getString("channel");
    }

    public String getType() throws JSONException {
        return object.getJSONObject("data").getString("type");
    }

    public InvoiceObject getInvoice() throws JSONException {
        return new InvoiceObject(object.getJSONObject("data").getJSONObject("invoice"));
    }

    public ClientObject getClient() throws JSONException {
        return new ClientObject(object.getJSONObject("data").getJSONObject("client"));
    }
}
