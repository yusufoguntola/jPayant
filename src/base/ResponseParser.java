package base;

import base.exceptions.NoDataException;
import base.exceptions.NoMessageException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class ResponseParser {

    JSONObject object;
    String statusMessage;

    public ResponseParser(String response, String statusMessage) throws JSONException {
        object = new JSONObject(response);
        this.statusMessage = statusMessage;
    }

    /**
     * Returns the message object of the response. If the response has no
     * message, this will throw a NoMessageException
     *
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.NoMessageException
     */
    public String getMessage() throws JSONException, NoMessageException {
        if (!object.has("message")) {
            throw new NoMessageException("Response has no message");
        }
        return object.getString("message");
    }

    /**
     * Returns the status for the request response.
     *
     * @return
     * @throws org.json.JSONException
     */
    public String getStatus() throws JSONException {
        return object.getString("status");
    }

    /**
     * Returns the data associated with the response object. Since not all
     * requests returns a data, this will throw a NoDataException if the
     * response has no data.
     *
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.NoDataException
     */
    public String getData() throws JSONException, NoDataException {

        if (!object.has("data")) {
            throw new NoDataException("Response has no data");
        }
        return object.getString("data");
    }

    /**
     * Returns the status message or status code for the response.
     *
     * @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Returns the JSON object containing the information.
     *
     * @return
     */
    public JSONObject getJSONObject() {
        return object;
    }
}
