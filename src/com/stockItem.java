package com;

public class stockItem {
    String itemName;
    float itemPrice;
    long itemBarcode;
    int itemNum;

    public stockItem(String stockItemName, float stockItemPrice, long stockItemBarcode, int stockItemNum) {
        itemName = stockItemName;
        itemPrice = stockItemPrice;
        itemBarcode = stockItemBarcode;
        itemNum = stockItemNum;
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

    public int GetItemNum()
    {
        return itemNum;
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

    public void SetItemNum(int newItemNum)
    {
        itemNum = newItemNum;
    }
}