package main;

import base.ResponseParser;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import base.exceptions.NoDataException;
import base.exceptions.NoMessageException;
import features.Client;
import features.Invoice;
import features.Miscellaneous;
import features.objects.ClientObject;
import features.objects.InvoiceObject;
import features.objects.PaymentObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import utils.AccountDetails;
import utils.Item;
import utils.constants.ClientType;
import utils.constants.FeeBearer;
import utils.constants.HistoryPeriod;
import utils.constants.Implementation;

/**
 *
 * @author Yusuf Oguntola
 */
public class Usage {

    //This is a full sample of how to use the client features
    private void usingClientFeatures() {
        try {
            //Initialize client. Use Implementation.LIVE when going Live
            Client client = new Client("YOUR_PRIVATE_KEY", Implementation.DEMO);

            //Add a new Client using very few information
            ClientObject newClient = client.addClient("First Name", "Last Name", "Email", "Phone");
            //OR add all the details using an empty string for information that's not available
            newClient = client.addClient("First Name", "Last Name", "Email", "Phone", "Bank Id", "Account Number", "Website", "Address", ClientType.CUSTOMER, "Company Name");
            //Use the ClientObject object to get details of the newly registered client.
            String clientId = newClient.getClientId();
            String accountNumber = newClient.getAccountNumber();

            // Retrieve a client
            ClientObject oldClient = client.getClient(clientId);

            //Edit a client. The two variants of addClient also exist for editClient
            ClientObject editedClient = client.editClient(clientId, "First Name", "Last Name", "Email", "Phone");

            //Delete Client
            ResponseParser parser = client.deleteClient(clientId);

            //You can view the details of the request by checking the parser object. e.g:
            String message = parser.getMessage();
            String status = parser.getStatus();
            String data = parser.getData(); //This would throw a NoDataException if there's no data in the response.

            //A client object also has a parser in it to view the response details.
            //You can obtain it by doing:
            ResponseParser parser1 = newClient.getParser();

        } catch (AuthenticationException | InvalidInputException | IOException | JSONException | NoMessageException | NoDataException ex) {

        }
    }

    //This is a full example of how to use the invoice features
    private void usingInvoiceFeatures() {

        try {
            //Initialize the invoice class
            Invoice invoice = new Invoice("YOUR_PRIVATE_KEY", Implementation.DEMO);

            //Add a new invoice
            /*
            For an invoice, you'd need a client to add the invoice for.
            Either create a new client or add a new client to get a clientObject to use.
            e.g ClientObject client Object = client.getClient("client id");
            You'd also need to create a list of items to add to the invoice.
            The list may however contain just one item.
             */
            ClientObject client = new Client("YOUR_PRIVATE_KEY", Implementation.DEMO).getClient("47");
            List<Item> items = new ArrayList<>();
            items.add(new Item("Another", "Another Item", "20000", 1));
            items.add(new Item("Second", "Another Second Item", "25000", 5));
            items.add(new Item("Third", "Another Third Item", "10000", 3));
            InvoiceObject newInvoice = invoice.addInvoice(client, "12/30/2016", FeeBearer.ACCOUNT, items);

            //Retrieve an invoice
            // Reference code may be obtained from an invoice object.
            InvoiceObject oldInvoice = invoice.getInvoice("Reference Code");

            //Delete an invoice
            ResponseParser parser = invoice.deleteInvoice("Reference Code");

            //Send an invoice
            InvoiceObject sentInvoice = invoice.sendInvoice("Reference Code");

            //Invoice History
            /*
            //Retrieving between a preset history period.
            Make use of the several period options in HistoryPeriod class.
            e.g: HistoryPeriod.TODAY, HistoryPeriod.ONE_WEEK etc.
             */
            List<InvoiceObject> invoices = invoice.getInvoiceHistory(HistoryPeriod.LAST_NINETY_DAYS);

            //Or use a custom date range
            invoices = invoice.getInvoiceHistory("05/12/2016", "05/12/2017");

            //Get details of an invoice from an invoice object. e.g:
            ClientObject receivingClient = sentInvoice.getClient();
            List<Item> attachedItems = sentInvoice.getItems();
            String dueDate = sentInvoice.getDueDate();
            String feeBearer = sentInvoice.getFeeBearer();
        } catch (AuthenticationException | InvalidInputException | NoDataException | IOException | JSONException e) {

        }
    }

    private void Miscellaneous() {
        try {
            Miscellaneous miscellaneous = new Miscellaneous("YOUR_PRIVATE_KEY", Implementation.DEMO);

            //Retrieve a payment
            PaymentObject payment = miscellaneous.getPayment("Reference Code");
            //The payment object has all the details of the payment in it

            //Retrieve all banks
            //This would return a map of bank codes to bank names
            Map<String, String> banks = miscellaneous.getBanks();

            //Resolve an account
            AccountDetails accountDetails = miscellaneous.resolveAccount("Bank Id", "Account Number");
            String accountName = accountDetails.getAccountName();
            String accountNumber = accountDetails.getAccountNumber();
        } catch (AuthenticationException | InvalidInputException | IOException | JSONException e) {
        }
    }
}
