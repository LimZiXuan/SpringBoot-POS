package com.CBSEGroup11pos.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.service.ProductService;
import com.CBSEGroup11pos.util.ProductItemTransformer;
import com.CBSEGroup11pos.wrapper.ProductByCategoryWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductItemDao productDao;

	@Autowired
	ProductCategoryDao productCategoryDao;

	@Autowired
	ProductItemTransformer productTransformer;

	@Override
	public List<ProductItemWrapper> getAllProduct() {
		List<ProductItemWrapper> allProducts = new ArrayList<ProductItemWrapper>();
		List<ProductItems> entityProducts = productDao.findAll();
		for (ProductItems data : entityProducts) {
			allProducts.add(productTransformer.transformEntityToObj(data));
		}
		return allProducts;
	}

	@Override
	public ProductItemWrapper getProduct(String barcode) {
		ProductItemWrapper product = new ProductItemWrapper();
		ProductItems entityProduct = productDao.findById(barcode).get();
		product = productTransformer.transformEntityToObj(entityProduct);
		return product;
	}

	@Override
	public ProductItemWrapper addProduct(ProductItemWrapper newProduct) {
		ProductItems entityProduct = new ProductItems();
		entityProduct = productTransformer.transformObjToEntity(newProduct);
		productDao.save(entityProduct);
		return newProduct;
	}

	@Override
	public ProductItemWrapper updateProduct(String barcode, ProductItemWrapper existingProduct) {
		ProductItems existingEntity = productDao.findById(barcode).get();
		if (existingEntity != null) {
			existingEntity.setCategoryId(existingProduct.getCategoryId());
			existingEntity.setCount(existingProduct.getCount());
			existingEntity.setExpiredDate(existingProduct.getExpiredDate());
			existingEntity.setName(existingProduct.getName());
			existingEntity.setPrice(existingProduct.getPrice());
			existingEntity.setStockAmount(existingProduct.getStockAmount());
			existingEntity.setSupplierId(existingProduct.getSupplierId());
			productDao.save(existingEntity);
			return existingProduct;
		}
		return null;
	}

	@Override
	public String deleteProduct(String barcode) {
		productDao.deleteById(barcode);
		return "Deleted Successfully";

	}

	@Override
	public List<ProductByCategoryWrapper> findProductByCategory(String categoryName) {
		List<ProductByCategoryWrapper> searchResult = new ArrayList<ProductByCategoryWrapper>();
		try {
			Integer categoryId = productCategoryDao.findByName(categoryName).get(0).getId();
			if (categoryId != null) {
				List<Object[]> result = productDao.findProductByCategory(categoryId);

				for (Object[] obj : result) {
					ProductByCategoryWrapper newObj = new ProductByCategoryWrapper();
					newObj.setBarcode((String) obj[7]);
					newObj.setCompanyName((String) obj[4]);
					newObj.setCount((Integer) obj[6]);
					newObj.setDateAdded((String) obj[1]);
					newObj.setExpiredDate((String) obj[2]);
					newObj.setName((String) obj[0]);
					newObj.setPrice((String) obj[3]);
					newObj.setStockAmount((String) obj[5]);
					searchResult.add(newObj);
				}
			}
			return searchResult;
		} catch (Exception e) {
			return searchResult;
		}
	}

	@Override
	public Map<String, Object> getProductQRCode(String barcode) {
		Map<String, Object> response = new LinkedHashMap<>();
		ProductItemWrapper product = new ProductItemWrapper();
		ProductItems entityProduct = productDao.findById(barcode).get();
		product = productTransformer.transformEntityToObj(entityProduct);
		String textToEncode = "Product ID: " + product.getBarcode() +
                "\nPrice: " + product.getPrice() +
                "\nName: " + product.getName() + "\nDate Added: " + product.getDateAdded()
                + "\nExpired Date: " + product.getExpiredDate() + "\nStock Amount: " + product.getStockAmount()
                + "\nCategory Id: " + product.getCategoryId() + "\nCount: " + product.getCount()
                + "\nSupplier Id: " + product.getSupplierId();
		try {
			byte[] base64Image = getQRCodeImage(textToEncode);
			response.put("QRImage", base64Image);
			response.put("success", true);
			return response;
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.put("QRImage", new byte[0]);
			response.put("success", false);
			return response;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.put("QRImage", new byte[0]);
			response.put("success", false);
			return response;
		}
	}

	private byte[] getQRCodeImage(String textToEncode) throws WriterException, IOException {
		int width = 300;
		int height = 300;
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(textToEncode, BarcodeFormat.QR_CODE, width, height, hints);

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

		return byteArrayOutputStream.toByteArray();
	}

}
