package com.CBSEGroup11pos.service;

import java.util.List;
import java.util.Map;

import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.wrapper.CardWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

public interface SellService {
    ProductItemWrapper sellProduct(String barcode, int quantity);

    PurchaseWrapper addPurchase(PurchaseWrapper newPurchase);

    double getPriceByBarcode(String barcode);

    CardWrapper payByCard(String cardNumber, CardWrapper newCard);

    double convertCurrency(double amount, String fromCurrency, String toCurrency);

}
