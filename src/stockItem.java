public class stockItem {
    String itemName;
    float itemPrice;
    long itemBarcode;

    public stockItem(String stockItemName, float stockItemPrice, long stockItemBarcode) {
        itemName = stockItemName;
        itemPrice = stockItemPrice;
        itemBarcode = stockItemBarcode;
    }

    public String GetItemName() {
        return itemName;
    }

    public float GetItemPrice() {
        return itemPrice;
    }

    public long GetItemBarcode(){
        return itemBarcode;
    }

    public void SetItemName(String newItemName) {
        itemName = newItemName;
    }

    public void SetItemPrice(float newItemPrice) {
        itemPrice = newItemPrice;
    }

    public void SetItemBarcode(long newItemBarcode){
        itemBarcode = newItemBarcode;
    }
}