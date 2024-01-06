package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.dao.PurchaseDao;
import com.CBSEGroup11pos.dao.SupplierDao;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.service.SellService;
import com.CBSEGroup11pos.util.ProductItemTransformer;
import com.CBSEGroup11pos.util.PurchaseTransformer;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

@Service
public class SellServiceImpl implements SellService {
    @Autowired
    ProductItemDao productDao;
    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    ProductItemTransformer productTransformer;

    @Autowired
    PurchaseTransformer purchaseTransformer;

    @Override
    public ProductItemWrapper sellProduct(String barcode, int quantity) {
        ProductItems product = productDao.findByBarcode(barcode);
        if (product != null) {
            String countAsString = product.getStockAmount();

            try {
                int currentCount = Integer.parseInt(countAsString);
                if (currentCount >= quantity) {
                    product.setStockAmount(Integer.toString(currentCount - quantity));
                    productDao.save(product);
                    System.out.println("Product sold");

                    // Assuming productTransformer.transform() is used to convert ProductItems to
                    // ProductItemWrapper
                    return productTransformer.transformEntityToObj(product);
                } else {
                    System.out.println("Product not available");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing count as integer: " + e.getMessage());
            }
        }

        return null;
    }

    @Override
    public double getPriceByBarcode(String barcode) {
        // Replace this with your actual logic to fetch the price from the database
        productDao.findByBarcode(barcode);

        Map<String, Double> prices = new HashMap<>();
        prices.put(productDao.findByBarcode(barcode).getBarcode(),
                Double.parseDouble(productDao.findByBarcode(barcode).getPrice()));
        return prices.getOrDefault(barcode, 0.0);
    }

    @Override
    public PurchaseWrapper addPurchase(PurchaseWrapper newPurchase) {
        List<ProductItemWrapper> purchasedItems = new ArrayList<>();

        // Assuming barcode and quantity are provided in the PurchaseWrapper class
        String[] barcodes = newPurchase.getBarcode().split(",");
        String[] quantities = newPurchase.getQuantity().split(",");
        System.out.println("barcodes: " + Arrays.toString(barcodes));
        System.out.println("quantities: " + Arrays.toString(quantities));
        if (quantities.length > 0 && quantities[quantities.length - 1].isEmpty()) {
            quantities = Arrays.copyOf(quantities, quantities.length - 1);
        }
        for (int i = 0; i < barcodes.length && i < quantities.length; i++) {
            String barcode = barcodes[i].trim();
            int quantity = Integer.parseInt(quantities[i].trim());

            ProductItems product = productDao.findByBarcode(barcode);

            if (product != null) {
                String countAsString = product.getStockAmount();

                try {
                    int currentCount = Integer.parseInt(countAsString);

                    if (currentCount >= quantity) {
                        product.setStockAmount(Integer.toString(currentCount - quantity));
                        productDao.save(product);

                        System.out.println("Product sold");
                        purchasedItems.add(productTransformer.transformEntityToObj(product));
                    } else {
                        System.out.println("Product not available");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing count as integer: " + e.getMessage());
                }
            }
        }

        // Update the total amount based on the purchased items and quantity from
        // newPurchase
        double totalAmount = calculateTotalAmount(purchasedItems, newPurchase);
        newPurchase.setTotalamount(String.valueOf(totalAmount));

        Purchase entity = purchaseTransformer.transformObjToEntity(newPurchase);
        entity.setTotalamount(newPurchase.getTotalamount());
        purchaseDao.save(entity);

        return newPurchase;
    }

    private double calculateTotalAmount(List<ProductItemWrapper> purchasedItems, PurchaseWrapper newPurchase) {
        double totalAmount = 0.0;

        String[] quantities = newPurchase.getQuantity().split(",");

        for (int i = 0; i < purchasedItems.size() && i < quantities.length; i++) {
            ProductItemWrapper item = purchasedItems.get(i);
            int quantity = Integer.parseInt(quantities[i].trim());

            totalAmount += Double.parseDouble(item.getPrice()) * quantity;
        }

        return totalAmount;
    }
    // Helper method to calculate the total amount based on purchased items and
    // newPurchase quantity
    // private double calculateTotalAmount(List<ProductItemWrapper> purchasedItems,
    // PurchaseWrapper newPurchase) {
    // double totalAmount = 0.0;

    // for (ProductItemWrapper item : purchasedItems) {
    // totalAmount += Double.parseDouble(item.getPrice()) *
    // Double.parseDouble(newPurchase.getQuantity());
    // }
    // System.out.println("Total amount calculated" + totalAmount);
    // return totalAmount;
    // }
}
// @Override
// public PurchaseWrapper addPurchase(PurchaseWrapper newPurchase) {
// Purchase entity = new Purchase();
// entity = purchaseTransformer.transformObjToEntity(newPurchase);
// entity.setTotalamount(newPurchase.calculateTotalAmount(this));
// // newPurchase.calculateTotalAmount(this);
// purchaseDao.save(entity);
// return newPurchase;
// }

// @Override
// public PurchaseWrapper addPurchase(PurchaseWrapper newPurchase) {
// List<ProductItemWrapper> purchasedItems = new ArrayList<>();

// // Assuming barcode and quantity are provided in the PurchaseWrapper class
// String[] barcodes = newPurchase.getBarcode().split(",");
// String[] quantities = newPurchase.getQuantity().split(",");

// for (int i = 0; i < barcodes.length && i < quantities.length; i++) {
// String barcode = barcodes[i].trim();
// int quantity = Integer.parseInt(quantities[i].trim());

// ProductItems product = productDao.findByBarcode(barcode);

// if (product != null) {
// String countAsString = product.getStockAmount();

// try {
// int currentCount = Integer.parseInt(countAsString);

// if (currentCount >= quantity) {
// product.setStockAmount(Integer.toString(currentCount - quantity));
// productDao.save(product);

// System.out.println("Product sold");
// purchasedItems.add(productTransformer.transformEntityToObj(product));
// } else {
// System.out.println("Product not available");
// }
// } catch (NumberFormatException e) {
// System.out.println("Error parsing count as integer: " + e.getMessage());
// }
// }
// }

// // Update the total amount based on the purchased items
// double totalAmount = calculateTotalAmount(purchasedItems);
// newPurchase.setTotalamount(String.valueOf(totalAmount));
// System.out.println("Total amount calculated" + totalAmount);

// Purchase entity = purchaseTransformer.transformObjToEntity(newPurchase);
// entity.setTotalamount(newPurchase.getTotalamount());
// purchaseDao.save(entity);

// return newPurchase;
// }

// // Helper method to calculate the total amount based on purchased items
// private double calculateTotalAmount(List<ProductItemWrapper> purchasedItems)
// {
// double totalAmount = 0.0;

// for (ProductItemWrapper item : purchasedItems) {
// totalAmount += Double.parseDouble(item.getPrice()); // Assuming getPrice
// method exists in ProductItemWrapper
// }

// return totalAmount;
// }
// }
