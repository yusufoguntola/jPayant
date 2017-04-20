package features.objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class ProductObject {

    private String name, description, unitCost, type, productId;
    private final JSONObject object;

    public ProductObject(JSONObject object) throws JSONException {
        this.object = object;
        setParameters();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private void setParameters() throws JSONException {
        setName(object.getString("name"));
        setDescription(object.getString("description"));
        setType(object.getString("type"));
        setUnitCost(object.getString("unit_cost"));
        setProductId(object.getString("id"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
