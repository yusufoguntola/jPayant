package utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yusuf Oguntola
 */
public class Item {

    public String itemName;
    public String description;
    public String unitCost;
    public String quantity;

    /**
     * This represents a single item object.<br>
     * Each item should have a name, description, unit cost (Amount a single
     * unit of the item would cost) and quantity (The total unit to be added to
     * an invoice)
     *
     * @param itemName
     * @param description
     * @param unitCost
     * @param quantity
     */
    public Item(String itemName, String description, String unitCost, int quantity) {
        setItem(itemName, description, unitCost, quantity);
    }

    private void setItem(String itemName, String description, String unitCost, int quantity) {
        this.itemName = itemName;
        this.description = description;
        this.unitCost = unitCost;
        this.quantity = String.valueOf(quantity);
    }

    /**
     * This represents a single item object.<br>
     * This constructor expects the item object to be formed as a JSONObject
     * with the object containing fields: <br>
     * item<br>
     * description<br>
     * unit_cost<br>
     * quantity<br>
     * whose values must all be String<br>
     * Each item should have a name, description, unit cost (Amount a single
     * unit of the item would cost) and quantity (The total unit to be added to
     * an invoice)
     *
     * @param object
     * @throws org.json.JSONException
     */
    public Item(JSONObject object) throws JSONException {
        setItem(object.getString("item"), object.getString("description"), object.getString("unit_cost"), Integer.parseInt(object.getString("quantity")));
    }

    public String getJSONString() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("item", getItemName());
        object.put("description", getDescription());
        object.put("unit_cost", getUnitCost());
        object.put("quantity", getQuantity());
        String string = object.toString();
        return "[" + string + "]";
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
