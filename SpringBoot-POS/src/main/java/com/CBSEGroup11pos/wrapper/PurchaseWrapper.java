package com.CBSEGroup11pos.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseWrapper {
    Integer id;
    String date;
    String time;
    Integer cashierId;
    String barcode;
    String quantity;
    String totalAmount;
}
