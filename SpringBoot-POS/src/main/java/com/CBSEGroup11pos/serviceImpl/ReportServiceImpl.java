package com.CBSEGroup11pos.serviceImpl;

import com.CBSEGroup11pos.auth.config.securityToken.JwtAuthFilter;
import com.CBSEGroup11pos.constants.PosConstants;
import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.PurchaseDao;
import com.CBSEGroup11pos.dao.ReportDao;
import com.CBSEGroup11pos.dao.TransactionDao;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.entity.Report;
import com.CBSEGroup11pos.service.ReportService;
import com.CBSEGroup11pos.util.PosUtils;
import com.CBSEGroup11pos.wrapper.ProductInfoWrapper;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final JwtAuthFilter jwtAuthFilter;
    private final ReportDao reportDao;
    private final PurchaseDao purchaseDao;
    private final TransactionDao transactionDao;
    private final ProductCategoryDao productCategoryDao;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String fileName;
            double totalDailySale = 0;
            int totalDailySaleCount, totalDailyPurchaseCount, totalDailyTransaction, totalCashUser = 0;

            if (validateReportRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");
                } else {
                    fileName = PosUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                String currentDate = dateFormat.format(date);
                String currentTime = dateFormat.format(date);


                String data = "Name: " + requestMap.get("name")
                        + "\nEmail: " + requestMap.get("email")
                        + "\nDate: " + currentDate
                        + "\nTime: " + currentTime;

                String reportDescription = "Today's sales report highlights a comprehensive overview of our daily transactions. " +
                        "The total sale amount represents the overall income generated throughout the day. In tandem, the total sale " +
                        "item count enumerates the quantity of products successfully sold, categorized based on their respective product types. " +
                        "Noteworthy distinctions are made between card-paid and cash-paid customers, with the former indicating those who made " +
                        "purchases using cards and the latter reflecting those who opted for cash transactions.\n\n" +
                        "The initial sale amount encapsulates the sum derived from the calculated product prices, forming the basis for our " +
                        "financial overview. Moving forward, the net sale amount stands as the actual revenue received, post-adjustments for " +
                        "promotions, which encompass both item-specific and card-related discounts. The promotion value signifies the amount allocated " +
                        "by the store to facilitate customer promotions or offer discounts, thereby influencing the net sale amount. This detailed " +
                        "report serves as a valuable tool for analyzing daily sales performance, providing insights into the success of promotional " +
                        "strategies and overall financial health.";

                List<Double> dailyAmountList = purchaseDao.getTotalDailySale(currentDate);
                totalDailySale = dailyAmountList.stream().mapToDouble(Double::doubleValue).sum();

                List<Integer> dailySaleCountList = purchaseDao.getTotalDailySaleItemCount(currentDate);
                totalDailySaleCount = dailySaleCountList.stream().mapToInt(Integer::intValue).sum();

                totalDailyPurchaseCount = purchaseDao.getTotalDailyPurchase(currentDate);
                totalDailyTransaction = transactionDao.getTotalDailyTransaction(currentDate);
                totalCashUser = totalDailyPurchaseCount - totalDailyTransaction;

                String generalData = "Total Sale Amount: " + String.valueOf(totalDailySale)
                        + "\nTotal Sale Item Count: " + String.valueOf(totalDailySaleCount)
                        + "\nTotal Card Paid Customer" + String.valueOf(totalDailyTransaction)
                        + "\nTotal Cash Paid Customer" + String.valueOf(totalCashUser);

                List<String> categoryNameList = productCategoryDao.getCategoryNameList();

                ArrayList<String> categoryName = new ArrayList<>();
                ArrayList<Integer> categoryCount = new ArrayList<>();
                ArrayList<Double> categoryTotalSaleAmount = new ArrayList<>();

                for (int i = 0; i < categoryNameList.size(); i++) {
                    categoryName.add(categoryNameList.get(i));
                    categoryCount.add(0);
                    categoryTotalSaleAmount.add(0.0);
                }

                List<PurchaseWrapper> purchaseBarcodeQuantity = purchaseDao.getPurchaseBarcodeCategoryName(currentDate);
                ArrayList<String> barcodeList = new ArrayList<>();
                ArrayList<Integer> quantityList = new ArrayList<>();

                for (int i = 0; i < purchaseBarcodeQuantity.size(); i++) {
                    barcodeList.add(purchaseBarcodeQuantity.get(i).getBarcode());
                    quantityList.add(Integer.valueOf(purchaseBarcodeQuantity.get(i).getQuantity()));
                }

                List<Object[]> productCategoryPrice = new ArrayList<>();
                for (int i = 0; i < barcodeList.size(); i++) {
                    productCategoryPrice.add(productCategoryDao.getProductCategoryPrice(barcodeList.get(i)));
                }
                log.info(productCategoryPrice.toString());

                if (requestMap.get("reportType").equals("DailySaleReport")) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(PosConstants.REPORT_STORE_LOCATION + "\\" + fileName + ".pdf"));

                    document.open();
                    setRectangleInPdf(document);

                    Paragraph chunk = new Paragraph("G-7/11 POS Daily Sale report", getFont("Header"));
                    chunk.setAlignment(Element.ALIGN_CENTER);
                    document.add(chunk);

                    Paragraph paragraph = new Paragraph(data + "\n\n", getFont("Data"));
                    document.add(paragraph);

                    Paragraph description = new Paragraph(reportDescription, getFont("Description"));
                    document.add(description);

                    Paragraph generalData1 = new Paragraph(generalData + "\n\n", getFont("Description"));
                    document.add(generalData1);

                    PdfPTable table = new PdfPTable(3);
                    PdfPCell categoryCell = new PdfPCell(new Paragraph("Category"));
                    categoryCell.setColspan(3);
                    table.addCell(categoryCell);
                    table.setWidthPercentage(100);
                    addSalesReportCategoryTableHeader(table);

                    JSONArray jsonArray = PosUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        addRows(table, PosUtils.getMapFromJson(jsonArray.getString(i)));
                    }
                    document.add(table);

                    Paragraph footer = new Paragraph("Total: " + requestMap.get("total")
                            + "\nThank you for vising. Please visit again!!", getFont("Data"));
                    document.add(footer);
                    document.close();
                    return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
                } else if (requestMap.get("reportType").equals("MonthlySaleReport")) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(PosConstants.REPORT_STORE_LOCATION + "\\" + fileName + ".pdf"));

                    document.open();
                    setRectangleInPdf(document);

                    Paragraph chunk = new Paragraph("G11 Point-of-Sale Daily Sale report", getFont("Header"));
                    chunk.setAlignment(Element.ALIGN_CENTER);
                    document.add(chunk);

                    Paragraph paragraph = new Paragraph(data + "\n\n", getFont("Data"));
                    document.add(paragraph);

                    PdfPTable table = new PdfPTable(5);
                    table.setWidthPercentage(100);
                    addSalesReportCategoryTableHeader(table);

                    JSONArray jsonArray = PosUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        addRows(table, PosUtils.getMapFromJson(jsonArray.getString(i)));
                    }
                    document.add(table);

                    Paragraph footer = new Paragraph("Total: " + requestMap.get("total")
                            + "\nThank you for vising. Please visit again!!", getFont("Data"));
                    document.add(footer);
                    document.close();
                    return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
                } else if (requestMap.get("reportType").equals("MonthlySaleReport")) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(PosConstants.REPORT_STORE_LOCATION + "\\" + fileName + ".pdf"));

                    document.open();
                    setRectangleInPdf(document);

                    Paragraph chunk = new Paragraph("G11 Point-of-Sale Daily Sale report", getFont("Header"));
                    chunk.setAlignment(Element.ALIGN_CENTER);
                    document.add(chunk);

                    Paragraph paragraph = new Paragraph(data + "\n\n", getFont("Data"));
                    document.add(paragraph);

                    PdfPTable table = new PdfPTable(5);



                    JSONArray jsonArray = PosUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        addRows(table, PosUtils.getMapFromJson(jsonArray.getString(i)));
                    }
                    document.add(table);

                    Paragraph footer = new Paragraph("Total: " + requestMap.get("total")
                            + "\nThank you for vising. Please visit again!!", getFont("Data"));
                    document.add(footer);
                    document.close();
                    return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
                } else {
                    return PosUtils.getResponseEntity("Invalid choice input!", HttpStatus.BAD_REQUEST);
                }
            }
            return PosUtils.getResponseEntity("Required date not found.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PosUtils.getResponseEntity(PosConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell((String) data.get("quantity"));
        table.addCell(Double.toString((Double) data.get("price")));
        table.addCell(Double.toString((Double) data.get("total")));
    }

    private void addSalesReportCategoryTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Category Name", "Sale Count", "Total Amount")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String fontType) {
        log.info("Inside getFont");
        switch (fontType) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            case "Description":
                Font descriptionFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                descriptionFont.setStyle(Font.NORMAL);
                return descriptionFont;
            default:
                return new Font();
        }
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangleInPdf");
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(Map<String, Object> requestMap) {
        log.info("Inside insertBill");
        try {
            Report report = new Report();
            report.setUuid((String) requestMap.get("uuid"));
            report.setName((String) requestMap.get("name"));
            report.setEmail((String) requestMap.get("email"));
            report.setCreatedBy(jwtAuthFilter.getCurrentUser());
            reportDao.save(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateReportRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("email")&&
                requestMap.containsKey("reportType");
    }

    @Override
    public ResponseEntity<List<Report>> getReports() {
        List<Report> reports = new ArrayList<>();
        try {
            if (jwtAuthFilter.isCashier()) {
                reports = reportDao.getAllReports();
            } else {
                reports = reportDao.getReportByUserName(jwtAuthFilter.getCurrentUser());
            }
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("Inside getPdf : requestMap{}", requestMap);
        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && validateReportRequestMap(requestMap)) {
                return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
            }
            String filePath = PosConstants.REPORT_STORE_LOCATION + "\\" + requestMap.get("uuid") + ".pdf";
            if (PosUtils.isFileExist(filePath)) {
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                byteArray = getByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getByteArray(String filePath) throws Exception {
        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    @Override
    public ResponseEntity<String> deleteReport(Integer id) {
        try {
            Optional optional = reportDao.findById(id);
            if (!optional.isEmpty()) {
                reportDao.deleteById(id);
                return PosUtils.getResponseEntity("Report Deleted Successfully", HttpStatus.OK);
            }
            return PosUtils.getResponseEntity("Report ID does not exist", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PosUtils.getResponseEntity(PosConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
