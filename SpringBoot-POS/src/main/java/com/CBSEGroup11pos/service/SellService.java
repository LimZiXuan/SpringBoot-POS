package com.CBSEGroup11pos.service;

import java.util.List;

import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

public interface SellService {
    ProductItemWrapper sellProduct(String barcode, int quantity);

    PurchaseWrapper addPurchase(PurchaseWrapper newPurchase);

    double getPriceByBarcode(String barcode);
}
