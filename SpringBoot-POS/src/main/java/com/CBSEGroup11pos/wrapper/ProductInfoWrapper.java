package com.CBSEGroup11pos.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoWrapper {

    String productName;
    String categoryName;
    String price;

    public ProductInfoWrapper(String categoryName, String price) {
        this.categoryName = categoryName;
        this.price = price;
    }
}
