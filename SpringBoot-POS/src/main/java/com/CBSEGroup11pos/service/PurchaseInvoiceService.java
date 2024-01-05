package com.CBSEGroup11pos.service;

import java.util.Map;

public interface PurchaseInvoiceService {
	Map<String, Object> genereteInvoice(Integer purchaseId);
	Map<String, Object> printInvoice(Integer purchaseId);
	Map<String, Object> emailInvoice(Integer purchaseId);
}
