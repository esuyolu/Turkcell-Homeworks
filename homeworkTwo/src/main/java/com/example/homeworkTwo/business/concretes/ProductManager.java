package com.example.homeworkTwo.business.concretes;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.homeworkTwo.business.abstracts.IProductService;
import com.example.homeworkTwo.dataaccess.abstracts.ProductRepository;
import com.example.homeworkTwo.entities.concretes.Product;

@Service
public class ProductManager implements IProductService {
	@Autowired
	private ProductRepository productRepository;
	
	private boolean addable(Product product)
	{
		return (productRepository.countOfProductsByCategory(product) <= 10 && product.getProductName().length() >= 2);
	}
	
	@Override
	public List<Product> getAll()
	{
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getById(int id)
	{
		return productRepository.findById(id);
	}

	@Override
	public Product add(Product product) 
	{
		if (addable(product)) {
			return productRepository.save(product);
		}
		else {
			Product dumyProduct = new Product();
			dumyProduct.setProductName("Hata!!! bir kategoride en fazla 10 urun olabilir yada urunun ismi en az 2 karakterden olusabilir");
			return dumyProduct;
		}	
	}

	@Override
	public void delete(Product product) 
	{
		productRepository.delete(product);
	}
}
