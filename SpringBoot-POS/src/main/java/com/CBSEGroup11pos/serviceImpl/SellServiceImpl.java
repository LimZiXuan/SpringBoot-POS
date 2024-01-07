package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.CBSEGroup11pos.dao.CardDao;
import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.dao.PurchaseDao;
import com.CBSEGroup11pos.dao.SupplierDao;
import com.CBSEGroup11pos.entity.Card;
import com.CBSEGroup11pos.entity.Customer;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.entity.Supplier;
import com.CBSEGroup11pos.service.SellService;
import com.CBSEGroup11pos.util.ProductItemTransformer;
import com.CBSEGroup11pos.util.PurchaseTransformer;
import com.CBSEGroup11pos.wrapper.CardWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

@Service
public class SellServiceImpl implements SellService {
    @Autowired
    ProductItemDao productDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private CardDao cardDao;

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

        double totalAmount = calculateTotalAmount(purchasedItems, newPurchase);
        newPurchase.setTotalAmount(String.valueOf(totalAmount));
        // Set the purchase date and time to the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);

        newPurchase.setDate(formattedDate);
        newPurchase.setTime(formattedTime);
        Purchase entity = purchaseTransformer.transformObjToEntity(newPurchase);
        entity.setTotalamount(newPurchase.getTotalAmount());
        entity.setDate(formattedDate);
        entity.setTime(formattedTime);
        purchaseDao.save(entity);

        return newPurchase;
    }

    private double calculateTotalAmount(List<ProductItemWrapper> purchasedItems,
            PurchaseWrapper newPurchase) {
        double totalAmount = 0.0;

        String[] quantities = newPurchase.getQuantity().split(",");
        double subtotal = 0.0;

        for (int i = 0; i < purchasedItems.size() && i < quantities.length; i++) {
            ProductItemWrapper item = purchasedItems.get(i);
            int quantity = Integer.parseInt(quantities[i].trim());

            subtotal += Double.parseDouble(item.getPrice()) * quantity;
        }
        // Apply optional discount (if any)
        double discount = newPurchase.getDiscount();
        double discountAmount = subtotal * (discount / 100);
        subtotal -= discountAmount;

        // Apply optional taxes (if any)
        double taxRate = newPurchase.getTaxRate();
        double taxAmount = subtotal * (taxRate / 100);

        // Calculate the total amount after applying taxes and discounts
        // totalAmount = subtotal - discountAmount + taxAmount;
        totalAmount = subtotal + taxAmount;

        return totalAmount;
    }

    // private double calculateTotalAmount(List<ProductItemWrapper> purchasedItems,
    // PurchaseWrapper newPurchase) {
    // double totalAmount = 0.0;

    // String[] quantities = newPurchase.getQuantity().split(",");

    // for (int i = 0; i < purchasedItems.size() && i < quantities.length; i++) {
    // ProductItemWrapper item = purchasedItems.get(i);
    // int quantity = Integer.parseInt(quantities[i].trim());

    // totalAmount += Double.parseDouble(item.getPrice()) * quantity;
    // }

    // return totalAmount;
    // }

    @Override
    public CardWrapper payByCard(String cardNumber, CardWrapper newCard) {
        Card existingEntity = cardDao.findById(cardNumber).orElse(null);
        // System.out.println("cardAmount" + existingEntity);
        if (existingEntity != null) {
            double cardAmount = Double.parseDouble(newCard.getAmount());
            double currentCardAmount = Double.parseDouble(existingEntity.getAmount());
            System.out.println("cardAmount" + cardAmount);
            System.out.println("currentCardAmount" + currentCardAmount);

            // Check if the card has sufficient balance
            if (currentCardAmount >= cardAmount) {
                existingEntity.setAmount(String.valueOf(currentCardAmount - cardAmount));
                cardDao.save(existingEntity);
                newCard.setAmount(String.valueOf(currentCardAmount - cardAmount));
                // double totalAmount = calculateTotalAmount(newCard);
                // newCard.setAmount(String.valueOf(totalAmount));

                return newCard;
            } else {
                System.out.println("Insufficient balance on the card.");
            }
        }

        return null;
    }

}
