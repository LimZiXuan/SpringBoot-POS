package com.CBSEGroup11pos.serviceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.dao.PurchaseDao;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.service.PurchaseInvoiceService;

@RequiredArgsConstructor
@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {

    private final JavaMailSender mailSender;

	@Autowired
	private PurchaseDao purchaseDao;

	@Autowired
	private ProductItemDao productItemDao;

    @Override
	public Map<String, Object> genereteInvoice(Integer purchaseId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
        	Purchase purchase = purchaseDao.findById(purchaseId).orElse(null);
        	
        	if (purchase == null) {
                response.put("message", "Purcahse Does Not Exists!");
                response.put("success", false);
                return response;
            }
        	
            String[] barcodes = purchase.getBarcode().split(",");
            String[] quantities = purchase.getQuantity().split(",");
            List<String> invoiceItems = new ArrayList<>();

            for (int i = 0; i < barcodes.length; i++) {
                String barcode = barcodes[i].trim();
                String quantity = quantities[i].trim();

                ProductItems productItem = productItemDao.findByBarcode(barcode);

                if (productItem != null) {
                    String itemName = productItem.getName();
                    String itemPrice = productItem.getPrice();
                    String itemTotalPrice = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(quantity));

                    String invoiceLine = "Item: " + itemName + ", Price: " + itemPrice + 
                    		", Quantity: " + quantity + ", Total Price: " + itemTotalPrice;
                    invoiceItems.add(invoiceLine);
                }
            }

            response.put("Date of Purchase", purchase.getDate());
            response.put("Total Amount", purchase.getTotalamount());
            response.put("Cashier ID", purchase.getCashierid().toString());
            response.put("Invoice Items", invoiceItems);
            response.put("message", "Generating Purchase Successful");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Generating Purchase Failed");
            response.put("success", false);
            return response;
        }
	}
	
	@Override
	public Map<String, Object> printInvoice(Integer purchaseId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
        	Purchase purchase = purchaseDao.findById(purchaseId).orElse(null);
        	
        	if (purchase == null) {
                response.put("message", "Purcahse Does Not Exists!");
                response.put("success", false);
                return response;
            }
            
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    
                    contentStream.newLineAtOffset(40, 750);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                    contentStream.showText(purchase.getDate());
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                    contentStream.newLineAtOffset(400, 0);
                    contentStream.showText("Cashier ID: " + purchase.getCashierid().toString());
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 20);
                    contentStream.newLineAtOffset(-240, -50);
                    contentStream.showText("Total Amount " + String.format("%.0f", Double.parseDouble(purchase.getTotalamount())));
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 18);
                    contentStream.newLineAtOffset(-160, -50);
                    contentStream.showText("Invoice Items");
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 17);
            		contentStream.newLineAtOffset(10, -30);
            		
                    String[] barcodes = purchase.getBarcode().split(",");
                    String[] quantities = purchase.getQuantity().split(",");

                    for (int i = 0; i < barcodes.length; i++) {
                        String barcode = barcodes[i].trim();
                        String quantity = quantities[i].trim();

                        ProductItems productItem = productItemDao.findByBarcode(barcode);

                        if (productItem != null) {
                            String itemName = productItem.getName();
                            String itemPrice = productItem.getPrice();
                            String itemTotalPrice = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(quantity));
                        
                            String invoiceItem = "-> " + itemName + ", " + itemPrice + 
                            		"$, " + quantity + "PCS, Total " + itemTotalPrice + "$";

                            contentStream.showText(invoiceItem);
                            contentStream.newLineAtOffset(0, -30);
                        }
                    }
                    
                    contentStream.endText();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                document.save(byteArrayOutputStream);

                response.put("pdfContent", byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                System.err.println("Printing PDF file Failed" + e);
            }

            response.put("message", "Generating Purchase Successful");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Generating Purchase Failed");
            response.put("success", false);
            return response;
        }
	}

	@Override
	public Map<String, Object> emailInvoice(Integer purchaseId) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
        	Purchase purchase = purchaseDao.findById(purchaseId).orElse(null);
        	
        	if (purchase == null) {
                response.put("message", "Purcahse Does Not Exists!");
                response.put("success", false);
                return response;
            }
            
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.beginText();
                    
                    contentStream.newLineAtOffset(40, 750);
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                    contentStream.showText(purchase.getDate());
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                    contentStream.newLineAtOffset(400, 0);
                    contentStream.showText("Cashier ID: " + purchase.getCashierid().toString());
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 20);
                    contentStream.newLineAtOffset(-240, -50);
                    contentStream.showText("Total Amount " + String.format("%.0f", Double.parseDouble(purchase.getTotalamount())));
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 18);
                    contentStream.newLineAtOffset(-160, -50);
                    contentStream.showText("Invoice Items");
                    
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 17);
            		contentStream.newLineAtOffset(10, -30);
            		
                    String[] barcodes = purchase.getBarcode().split(",");
                    String[] quantities = purchase.getQuantity().split(",");

                    for (int i = 0; i < barcodes.length; i++) {
                        String barcode = barcodes[i].trim();
                        String quantity = quantities[i].trim();

                        ProductItems productItem = productItemDao.findByBarcode(barcode);

                        if (productItem != null) {
                            String itemName = productItem.getName();
                            String itemPrice = productItem.getPrice();
                            String itemTotalPrice = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(quantity));
                        
                            String invoiceItem = "-> " + itemName + ", " + itemPrice + 
                            		"$, " + quantity + "PCS, Total " + itemTotalPrice + "$";

                            contentStream.showText(invoiceItem);
                            contentStream.newLineAtOffset(0, -30);
                        }
                    }
                    
                    contentStream.endText();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                document.save(byteArrayOutputStream);

                sendEmail(byteArrayOutputStream.toByteArray());
            }

            response.put("message", "Emailing Invoice Successful");
            response.put("success", true);
            return response;
        } catch (Exception e) {
            response.put("message", "Emailing Invoice Failed");
            response.put("success", false);
            return response;
        }
	}

	public void sendEmail(byte[] pdf) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom("stargazing0610@gmail.com", "Invoice Service");
            messageHelper.setTo("17215717@siswa.um.edu.my");
            messageHelper.setSubject("Invoice");
            messageHelper.setText("Invoice PDF File can found attached to this email.", true);
            messageHelper.addAttachment("Invoice.pdf", new ByteArrayResource(pdf),"application/pdf");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
