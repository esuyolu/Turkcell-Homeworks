package homeworkOne.business.concretes;

import java.util.List;

import homeworkOne.dataAccess.abstracts.IProductDao;
import homeworkOne.entities.concretes.Product;

public class ProductService {
	private IProductDao productDao;

	public ProductService(IProductDao productDao) 
	{
		this.productDao = productDao;
	}
	
	public void add(Product product)
	{
		productDao.add(product);
	}
	
	public void update(Product product)
	{
		productDao.update(product);
	}
	
	public void delete(Product product)
	{
		productDao.delete(product);
	}
	
	public List<Product> getAll() 
	{
		return productDao.getAll();
	}
}
