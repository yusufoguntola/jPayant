package features;

import base.Base;
import base.RequestManager;
import base.ResponseParser;
import base.exceptions.AuthenticationException;
import base.exceptions.InvalidInputException;
import features.objects.ProductObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import utils.JSONParser;
import utils.constants.FeatureTypes;

/**
 *
 * @author Yusuf Oguntola
 */
public class Product extends Base {

    public Product(String authKey, String implementation) throws AuthenticationException, InvalidInputException {
        super(authKey, implementation);
    }

    /**
     *
     * Adds a new product.<br>
     * Product type should be gotten from ProductType class e.g:<br>
     * ProductType.PRODUCT
     *
     * @param name
     * @param description
     * @param unitCost
     * @param type
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public ProductObject addProduct(String name, String description, String unitCost, String type) throws JSONException, InvalidInputException, IOException {
        String data = new JSONParser(FeatureTypes.PRODUCTS, name, description, unitCost, type).getJSONString();
        return new ProductObject(new RequestManager(getUrl("products"), getAuthKey()).doPost(data).getJSONObject().getJSONObject("data"));
    }

    /**
     * @param productId
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ProductObject getProduct(String productId) throws InvalidInputException, IOException, JSONException {
        return new ProductObject(new RequestManager(getUrl(String.format("products/%s", productId)), getAuthKey()).doGet().getJSONObject().getJSONObject("data"));
    }

    /**
     * Modifies the product with the supplied product id with the new parameters
     * supplied.<br>
     * Product type should be gotten from ProductType class. e.g:<br>
     * ProductType.PRODUCT
     *
     * @param productId
     * @param name
     * @param description
     * @param unitCost
     * @param type
     * @return
     * @throws org.json.JSONException
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     */
    public ProductObject editProduct(String productId, String name, String description, String unitCost, String type) throws JSONException, InvalidInputException, IOException {
        String data = new JSONParser(FeatureTypes.PRODUCTS, name, description, unitCost, type).getJSONString();
        return new ProductObject(new RequestManager(getUrl(String.format("products/%s", productId)), getAuthKey()).doPut(data).getJSONObject().getJSONObject("data"));
    }

    /**
     * Returns a list of all products you have added previously
     *
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public List<ProductObject> getProducts() throws InvalidInputException, IOException, JSONException {
        JSONArray array = new RequestManager(getUrl("products"), getAuthKey()).doGet().getJSONObject().getJSONArray("data");
        List<ProductObject> result = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            result.add(new ProductObject(array.getJSONObject(i)));
        }
        return result;
    }

    /**
     * @param productId
     * @return
     * @throws base.exceptions.InvalidInputException
     * @throws java.io.IOException
     * @throws org.json.JSONException
     */
    public ResponseParser deleteProduct(String productId) throws InvalidInputException, IOException, JSONException {
        return new RequestManager(getUrl(String.format("products/%s", productId)), getAuthKey()).doPut(productId);
    }

}
