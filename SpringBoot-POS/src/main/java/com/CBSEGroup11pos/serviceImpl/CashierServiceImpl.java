package com.CBSEGroup11pos.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.CashierDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.dao.PurchaseDao;
import com.CBSEGroup11pos.dto.SalesHistoryItem;
import com.CBSEGroup11pos.entity.Cashier;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.service.CashierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CashierServiceImpl implements CashierService {

	@Autowired
	CashierDao cashierDao;

	@Autowired
	PurchaseDao purchaseDao;

	@Autowired
	ProductItemDao productItemDao;

	@Override
	public Map<String, Object> getAllCashier() {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Business Logic here
			List<Cashier> entityCards = cashierDao.findAll();
			response.put("message", "Cashiers is retrieved successfully.");
			response.put("success", true);
			response.put("data", entityCards);
			return response;
		} catch (Exception e) {
			response.put("message", "Cashiers has failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> getCashier(String cashierId) {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Business Logic here
			Optional<Cashier> optional = cashierDao.findById(cashierId);

			if (optional.isPresent()) {
				Cashier fetchedCashier = optional.get();
				response.put("message", "Cashier with ID : " + cashierId + " has been retrieved successfully.");
				response.put("success", true);
				response.put("data", fetchedCashier);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}

			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " is failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> addCashier(Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Extracting values from the requestMap
			String name = requestMap.get("name");
			Integer age = Integer.parseInt(requestMap.get("age"));
			String gender = requestMap.get("gender");
			String address = requestMap.get("address");
			String phone = requestMap.get("phone");
			String email = requestMap.get("email");
			String password = requestMap.get("password");
			String dateCreated = requestMap.get("datecreated");

			// Creating a Cashier object
			Cashier cashier = new Cashier();
			cashier.setName(name);
			cashier.setAge(age);
			cashier.setGender(gender);
			cashier.setAddress(address);
			cashier.setPhone(phone);
			cashier.setEmail(email);
			cashier.setPassword(password);
			cashier.setDatecreated(dateCreated);

			// Adding the cashier using the service
			Cashier newCashier = cashierDao.save(cashier);
			response.put("message", "New cashier has been created successfully.");
			response.put("success", true);
			response.put("data", newCashier);
			return response;
		} catch (Exception e) {
			response.put("message", "New cashier creation is failed.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> updateCashier(String cashierId, Map<String, String> requestMap) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			Optional<Cashier> optional = cashierDao.findById(cashierId);

			if (optional.isPresent()) {
				Cashier existingCashier = optional.get();

				// Extracting values from the requestMap
				String name = requestMap.get("name");
				Integer age = Integer.parseInt(requestMap.get("age"));
				String gender = requestMap.get("gender");
				String address = requestMap.get("address");
				String phone = requestMap.get("phone");
				String email = requestMap.get("email");
				String password = requestMap.get("password");
				String dateCreated = requestMap.get("datecreated");

				// Update properties of the existing Cashier entity with values from
				// updatedCashier
				existingCashier.setName(name);
				existingCashier.setAge(age);
				existingCashier.setGender(gender);
				existingCashier.setAddress(address);
				existingCashier.setPhone(phone);
				existingCashier.setEmail(email);
				existingCashier.setPassword(password);
				existingCashier.setDatecreated(dateCreated);

				Cashier updatedCashier = cashierDao.save(existingCashier);

				response.put("message", "Cashier with ID : " + cashierId + " has been updated successfully.");
				response.put("success", true);
				response.put("data", updatedCashier);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " has been failed to update.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> deleteCashier(String cashierId) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {
			if (cashierDao.existsById(cashierId)) {
				cashierDao.deleteById(cashierId);
				response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
				response.put("success", true);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			response.put("message", "Cashier with ID : " + cashierId + " deletion is failed.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> viewSalesHistory(String cashierId) {

		Map<String, Object> response = new LinkedHashMap<>();

		try {

			if (cashierDao.existsById(cashierId)) {
				// Fetch purchase details
				List<Purchase> purchaseDetails = purchaseDao.findByCashierId(Integer.valueOf(cashierId));

				// Create a list to store SalesHistoryItem objects
				List<SalesHistoryItem> salesHistoryItems = new ArrayList<>();

				// Iterate through each Purchase object
				for (Purchase purchase : purchaseDetails) {
					// Split the barcode string into individual barcodes
					String[] barcodes = purchase.getBarcode().split(",");
					String[] quantities = purchase.getQuantity().split(",");

					// Iterate through each barcode and quantity
					for (int i = 0; i < barcodes.length; i++) {
						String barcode = barcodes[i].trim();
						String quantity = quantities[i].trim();

						ProductItems pi = productItemDao.findByBarcode(barcode);

						// Convert String values to BigDecimal
						BigDecimal price = new BigDecimal(pi.getPrice());
						BigDecimal quantityDecimal = new BigDecimal(quantity);

						// Multiply the values
						BigDecimal resultDecimal = price.multiply(quantityDecimal);

						// Convert the result back to String
						String totalAmount = resultDecimal.toString();

						SalesHistoryItem salesHistoryItem = new SalesHistoryItem(Integer.toString(purchase.getId()),
								barcode, pi.getName(), purchase.getDate(), quantity, totalAmount);

						// Add the SalesHistoryItem to the list
						salesHistoryItems.add(salesHistoryItem);
					}
				}

				// Create a custom comparator based on the date field
				Comparator<SalesHistoryItem> dateComparator = Comparator.comparing(SalesHistoryItem::getDate);

				// Sort the list in descending order using the custom comparator
				Collections.sort(salesHistoryItems, dateComparator.reversed());

				response.put("message", "Sales History for Cashier with ID : " + cashierId + " has been retrieved.");
				response.put("success", true);
				response.put("data", salesHistoryItems);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}

			return response;
		} catch (Exception e) {
			response.put("message", "Sales History for Cashier with ID : " + cashierId + " is failed to retrieve.");
			response.put("success", false);
			return response;
		}
	}

	@Override
	public Map<String, Object> viewTransactionGraph(String cashierId) {
		Map<String, Object> response = new LinkedHashMap<>();

		try {
			// Check if the cashier exists (this part may need modification based on your
			// logic)
			if (cashierDao.existsById(cashierId)) {

				List<Purchase> purchaseDetails = purchaseDao.findByCashierId(Integer.valueOf(cashierId));

				// Create a Map to store aggregated total amounts for each date
				Map<String, Double> aggregatedTotalAmounts = new LinkedHashMap<>();

				// Iterate over the purchase details and aggregate total amounts by date
				for (Purchase purchase : purchaseDetails) {
					String date = purchase.getDate();
					Double totalAmount = Double.valueOf(purchase.getTotalamount());

					// Update the aggregated total amount for the date
					aggregatedTotalAmounts.merge(date, totalAmount, Double::sum);
				}

				// Sort the dates in ascending order
				List<String> sortedDates = aggregatedTotalAmounts.keySet().stream().sorted(Comparator.naturalOrder())
						.collect(Collectors.toList());

				// Select the five latest dates
				List<String> latestDates = sortedDates.stream().skip(Math.max(0, sortedDates.size() - 5))
						.collect(Collectors.toList());

				DefaultCategoryDataset dataset = new DefaultCategoryDataset();

				// Add the latest 5 aggregated total amounts to the dataset
				for (String date : latestDates) {
					Double totalAmount = aggregatedTotalAmounts.get(date);

					// Add to the dataset
					dataset.addValue(totalAmount, "Transactions", date);
				}

				// Generate the line chart image
				BufferedImage chartImage = generateLineChart(dataset);

				// Convert the image to Base64 for easier inclusion in the response
				byte[] base64Image = convertImageToBase64(chartImage);

				// Include the base64 image in the response
				response.put("chartImage", base64Image);
				response.put("message", "Cashier with ID : " + cashierId + " has been deleted.");
				response.put("success", true);
			} else {
				response.put("message", "Cashier with ID : " + cashierId + " is not exist.");
				response.put("success", false);
			}
			return response;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			response.put("message", "Cashier with ID : " + cashierId + " deletion is failed.");
			response.put("success", false);
			return response;
		}
	}

	private BufferedImage generateLineChart(DefaultCategoryDataset dataset) {

		// Create a line chart
		JFreeChart chart = ChartFactory.createLineChart("Amount of Transaction Graph", "Date", "Amount", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		// Convert the chart to a BufferedImage
		BufferedImage image = chart.createBufferedImage(800, 600);
		return image;
	}

	private byte[] convertImageToBase64(BufferedImage image) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		byte[] imageBytes = baos.toByteArray();
		return imageBytes;
	}
}
