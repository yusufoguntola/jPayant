# jPayant
> Java wrapper for <a href="https://payant.ng/">Payant</a> payment

## Background
Payant makes receiving money on your website or from your app very easy.<br>
You can accept payments, set invoices make transfer from one bank account to another and many more.

Visit <a href="https://developers.payant.ng/">Payant developer's page</a> for a comprehensive documentation.

## Installation
To use this wrapper in your app, copy the jar files in the lib folder into your app directory.
> Luckily, this wrapper is well documented.

## Usage.
To start with, visit <a href="https://payant.ng/get-started">Payant startup page</a> to register and obtain your ```Authorization Keys.```
You'd get both a `PUBLIC KEY` and a `PRIVATE KEY`.
The PRIVATE KEY is expected for all authentication.

### There are five major features in the api, including: Client, Invoice, Miscellaneous, Transfer, Product, Payment
#### Client
```shell
import features.objects.ClientObject;
import features.Client;

// Initialize a client object
//Change to Implementation.LIVE when you are going live with your app.
Client client = new Client(PRIVATE_KEY, Implementation.DEMO);

//Create a new client

Client client = new Client(YOUR_PRIVATE_KEY, Implementation.DEMO);
ClientObject obj = client.addClient(firstName, lastName, email, phone);
//OR
ClientObject obj = client.addClient(firstName, lastName, email, phone, settlementBank, accountNumber, website, address, type, companyName);
//Get type from ClientType e.g ClientType.CUSTOMER
```
