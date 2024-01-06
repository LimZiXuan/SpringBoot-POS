package com.CBSEGroup11pos.wrapper;

import com.CBSEGroup11pos.service.SellService;

public class PurchaseWrapper {
    private Integer id;
    private String date;
    private String time;
    private Integer cashierId;
    private String barcode;
    private String quantity;
    private String totalAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCashierid() {
        return cashierId;
    }

    public void setCashierid(Integer cashierid) {
        this.cashierId = cashierid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalamount() {
        return totalAmount;
    }

    public void setTotalamount(String totalamount) {
        this.totalAmount = totalamount;
    }

    // public String calculateTotalAmount(SellService sellService) {
    // try {
    // int quantityValue = Integer.parseInt(quantity);
    // // Perform the calculation (replace this with your actual calculation logic)
    // double calculatedTotalAmount = quantityValue *
    // sellService.getPriceByBarcode(barcode);
    // // Convert the calculated total amount back to String if needed
    // this.totalAmount = String.valueOf(calculatedTotalAmount);
    // return totalAmount;
    // // System.out.println("Total amount calculated" + totalAmount);
    // } catch (NumberFormatException e) {
    // return "Error parsing quantity as integer: " + e.getMessage();
    // // e.printStackTrace();
    // }
    // }

}
