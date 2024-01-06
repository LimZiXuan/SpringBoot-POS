package com.CBSEGroup11pos.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

@RequestMapping(path = "/sell")
public interface SellRest {
    @GetMapping(path = "/displayAllProducts")
    ResponseEntity<List<ProductItemWrapper>> displayAllProducts();

    @GetMapping(path = "/get/{barcode}")
    ResponseEntity<ProductItemWrapper> getProductByBarcode(@PathVariable String barcode);

    @PostMapping(path = "/{barcode}/{quantity}")
    ResponseEntity<ProductItemWrapper> sellProduct(@RequestBody String barcode, @RequestBody int quantity);

    @PostMapping(path = "/addPurchase")
    ResponseEntity<PurchaseWrapper> addPurchase(@RequestBody(required = true) PurchaseWrapper newPurchase);

    // @PostMapping(path = "/{barcode}/{quantity}")
    // ResponseEntity<ProductItemWrapper> makeTransaction(@PathVariable String
    // barcode, @PathVariable int quantity);

    // @PostMapping(path = "/sellProducts")
    // ResponseEntity<List<ProductItemWrapper>> sellProducts(@RequestBody
    // List<SellRequest> sellRequests);

}
