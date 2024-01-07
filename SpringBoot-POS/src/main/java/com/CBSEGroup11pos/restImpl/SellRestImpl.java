package com.CBSEGroup11pos.restImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.rest.SellRest;
import com.CBSEGroup11pos.service.CardService;
import com.CBSEGroup11pos.service.ProductService;
import com.CBSEGroup11pos.service.SellService;
import com.CBSEGroup11pos.wrapper.CardWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

@RestController
public class SellRestImpl implements SellRest {
    @Autowired
    private SellService sellService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CardService cardService;

    @Override
    public ResponseEntity<List<ProductItemWrapper>> displayAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @Override
    public ResponseEntity<ProductItemWrapper> getProductByBarcode(String barcode) {
        return ResponseEntity.ok(productService.getProduct(barcode));
    }

    @Override
    public ResponseEntity<ProductItemWrapper> sellProduct(String barcode, int quantity) {
        return ResponseEntity.ok(sellService.sellProduct(barcode, quantity));
    }

    @Override
    public ResponseEntity<PurchaseWrapper> addPurchase(PurchaseWrapper newPurchase) {
        return ResponseEntity.ok(sellService.addPurchase(newPurchase));
    }

    @Override
    public ResponseEntity<CardWrapper> payByCard(String cardNumber, CardWrapper newCard) {
        return ResponseEntity.ok(sellService.payByCard(cardNumber, newCard));
    }

}
