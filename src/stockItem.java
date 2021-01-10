public class stockItem {
    String itemName;
    float itemPrice;

    public stockItem(String stockItemName, float stockItemPrice) {
        itemName = stockItemName;
        itemPrice = stockItemPrice;
    }

    public String GetItemName() {
        return itemName;
    }

    public float GetItemPrice() {
        return itemPrice;
    }

    public void SetItemName(String newItemName) {
        itemName = newItemName;
    }

    public void SetItemPrice(float newItemPrice) {
        itemPrice = newItemPrice;
    }
}