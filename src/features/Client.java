package features;

import base.Base;
import base.RequestManager;
import base.ResponseParser;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import features.objects.ClientObject;
import java.io.IOException;
import org.json.JSONException;
import utils.JSONParser;
import utils.constants.FeatureTypes;

/**
 *
 * @author Yusuf Oguntola
 */
public class Client extends Base {

    /**
     * Constructor Class. The authentication key is used to validate requests.
     * Implementation should either be Implementation.LIVE or
     * Implementation.DEMO
     *
     * @param authKey
     * @param implementation
     * @throws base.exceptions.AuthenticationException
     * @throws base.exceptions.InvalidInputException
     */
    public Client(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     * Creates a new client. A client can be charged for a particular service
     * after creation.
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ClientObject addClient(String firstName, String lastName,
            String email, String phone) throws InvalidInputException,
            IOException, JSONException {

        String requestData = new JSONParser(FeatureTypes.CLIENT, firstName,
                lastName, email, phone).getJSONString();
        return new ClientObject(new RequestManager(getUrl("clients"), getAuthKey()).doPost(requestData));
    }

    /**
     * Creates a new client. A client can be charged for a particular service
     * after creation.<br>
     * Client Type should be obtained from ClientType e.g: ClientType.CUSTOMER
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param website
     * @param address
     * @param type
     * @param settlementBank
     * @param accountNumber
     * @param companyName
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ClientObject addClient(String firstName, String lastName,
            String email, String phone, String settlementBank, String accountNumber,
            String website, String address, String type,
            String companyName) throws InvalidInputException, IOException, JSONException {
        String requestData = new JSONParser(FeatureTypes.CLIENT, firstName,
                lastName, email, phone, settlementBank, accountNumber, website,
                address, type, companyName).getJSONString();
        return new ClientObject(new RequestManager(getUrl("clients"), getAuthKey()).doPost(requestData));
    }

    /**
     * Gets details of a client with the supplied clientId
     *
     * @param clientId
     * @return ResponseParser
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ClientObject getClient(String clientId) throws InvalidInputException, IOException, JSONException {
        return new ClientObject(new RequestManager(getUrl(String.format("clients/%s", clientId)), getAuthKey()).doGet());
    }

    /**
     * Edit a previously added client. Supply the client id of the client to
     * edit and the new values of each of the parameters
     *
     * @param clientId
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public ClientObject editClient(String clientId, String firstName, String lastName,
            String email, String phone) throws JSONException, InvalidInputException, IOException {
        String requestData = new JSONParser(FeatureTypes.CLIENT, firstName,
                lastName, email, phone).getJSONString();
        return new ClientObject(new RequestManager(getUrl(String.format("clients/%s", clientId)), getAuthKey()).doPut(requestData));
    }

    /**
     * Edit a previously added client. Supply the client id of the client to
     * edit and the new values of each of the parameters.<br>
     * Client Type should be obtained from ClientType e.g: ClientType.CUSTOMER
     *
     * @param clientId
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param website
     * @param address
     * @param type
     * @param settlementBank
     * @param accountNumber
     * @param companyName
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public ClientObject editClient(String clientId, String firstName, String lastName,
            String email, String phone, String settlementBank, String accountNumber,
            String website, String address, String type,
            String companyName) throws JSONException, InvalidInputException, IOException {
        String requestData = new JSONParser(FeatureTypes.CLIENT, firstName,
                lastName, email, phone, settlementBank, accountNumber, website, address, type,
                companyName).getJSONString();
        return new ClientObject(new RequestManager(getUrl(String.format("clients/%s", clientId)), getAuthKey()).doPut(requestData));
    }

    /**
     * Delete a previously created parameter. Supply the clientId of the client
     * to delete.
     *
     * @param clientId
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ResponseParser deleteClient(String clientId) throws InvalidInputException, IOException, JSONException {
        return new RequestManager(getUrl(String.format("clients/%s", clientId)), getAuthKey()).doDelete();
    }

}
