package com.CBSEGroup11pos.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CBSEGroup11pos.dao.ProductCategoryDao;
import com.CBSEGroup11pos.dao.ProductItemDao;
import com.CBSEGroup11pos.entity.ProductItems;
import com.CBSEGroup11pos.service.ProductService;
import com.CBSEGroup11pos.util.ProductItemTransformer;
import com.CBSEGroup11pos.wrapper.ProductByCategoryWrapper;
import com.CBSEGroup11pos.wrapper.ProductItemWrapper;

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

}
