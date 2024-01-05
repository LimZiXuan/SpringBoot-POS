package com.CBSEGroup11pos.restImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.CBSEGroup11pos.rest.PurchaseInvoiceRest;
import com.CBSEGroup11pos.service.PurchaseInvoiceService;

@RestController
public class PurchaseInvoiceRestImpl implements PurchaseInvoiceRest{
	
	@Autowired
	private PurchaseInvoiceService purchaseInvoiceService;
	
	@Override
	public ResponseEntity<Map<String, Object>> genereteInvoice(Integer purchaseId) {
		Map<String, Object> response = purchaseInvoiceService.genereteInvoice(purchaseId);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
	}
	
	@Override
    public ResponseEntity<byte[]> printInvoice(Integer purchaseId) {
        Map<String, Object> response = purchaseInvoiceService.printInvoice(purchaseId);
        byte[] pdfContent = (byte[]) response.get("pdfContent");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice.pdf");
        return ResponseEntity.ok().headers(headers).body(pdfContent);
    }
    
	@Override
	public ResponseEntity<Map<String, Object>> emailInvoice(Integer purchaseId) {
		Map<String, Object> response = purchaseInvoiceService.emailInvoice(purchaseId);
        HttpStatus status = (boolean) response.get("success") ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
	}
	
}
