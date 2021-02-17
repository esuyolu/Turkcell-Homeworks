package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dataaccess.ProductRepository;
import com.example.demo.dataaccess.ShoppingCartRepository;
import com.example.demo.entities.concretes.Product;
import com.example.demo.entities.concretes.ShoppingCart;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCartController {
	@Autowired
	private  ShoppingCartRepository shoppingCartRepository;
	@Autowired
    private  ProductRepository productRepository;
	
	@GetMapping("carts")
	public ResponseEntity<List<ShoppingCart>> getShoppingCarts()
	{
		List<ShoppingCart> carts = shoppingCartRepository.findAll();
		
		if (carts.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
	}
	
	@GetMapping("carts/{shoppingCartId}")
	public ResponseEntity<ShoppingCart> readShoppingCartById(@PathVariable(value="shoppingCartId") int id)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(id);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		return new ResponseEntity<ShoppingCart>(cart.get(), HttpStatus.OK);
	}
	
	@GetMapping("cartsWithContactName/{contactName}")
	public ResponseEntity<List<ShoppingCart>> getShoppingCartsByContactName(@PathVariable(value="contactName") String name)
	{
		List<ShoppingCart> carts = shoppingCartRepository.findByContactName(name);
		
		if (carts.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
	}
	
	@PostMapping("carts")
	public ResponseEntity<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart)
	{
		ShoppingCart cart = shoppingCartRepository.save(shoppingCart);
		
		return new ResponseEntity<ShoppingCart>(cart, HttpStatus.CREATED);
	}
	
	@PostMapping("carts/{shoppingCartId}/products/{productId}")
	public ResponseEntity<ShoppingCart> addProduct(@PathVariable(value="shoppingCartId") int cartId, @PathVariable(value="productId") int productId)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		Optional<Product> product = productRepository.findById(productId);
		
		if (product.isEmpty())
			throw new RuntimeException("no product was found");
		
		cart.get().addProduct(product.get());
		cart.get().addProductQuantity(product.get());
		
		if (!product.get().removeStock())
			return new ResponseEntity<ShoppingCart>(cart.get(), HttpStatus.ACCEPTED);
		
		ShoppingCart updated = shoppingCartRepository.save(cart.get());
		productRepository.save(product.get());
		
		return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
	}
	
	@PostMapping("carts/{shoppingCartId}/products/{productId}//amount/{total}")
	public ResponseEntity<ShoppingCart> addProduct(@PathVariable(value="shoppingCartId") int cartId, @PathVariable(value="productId") int productId, @PathVariable(value="total") int total)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		Optional<Product> product = productRepository.findById(productId);
		
		if (product.isEmpty())
			throw new RuntimeException("no product was found");
		
		cart.get().addProduct(product.get());
		cart.get().addProductQuantity(product.get(), total);
		
		if (!product.get().removeStock(total))
			return new ResponseEntity<ShoppingCart>(cart.get(), HttpStatus.ACCEPTED);
		
		ShoppingCart updated = shoppingCartRepository.save(cart.get());
		productRepository.save(product.get());
		
		return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
	}
	
	@PutMapping("carts/{cartId}")
	public ResponseEntity<ShoppingCart> update(@RequestBody ShoppingCart shoppingCart, @PathVariable(value="cartId") int cartId)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		ShoppingCart updated = shoppingCartRepository.save(shoppingCart);
		
		return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("carts/{shoppingCartId}/products/{productId}")
	public ResponseEntity<ShoppingCart> removeProduct(@PathVariable(value="shoppingCartId") int cartId, @PathVariable(value="productId") int productId)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		Optional<Product> product = productRepository.findById(productId);
		
		if (product.isEmpty())
			throw new RuntimeException("no product was found");
		
		cart.get().removeProductQuantity(product.get());
		product.get().addStock();
		
		ShoppingCart updated = shoppingCartRepository.save(cart.get());
		productRepository.save(product.get());
		
		return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("carts/{shoppingCartId}/products/{productId}/amount/{total}")
	public ResponseEntity<ShoppingCart> removeProduct(@PathVariable(value="shoppingCartId") int cartId, @PathVariable(value="productId") int productId, @PathVariable(value="total") int total)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		Optional<Product> product = productRepository.findById(productId);
		
		if (product.isEmpty())
			throw new RuntimeException("no product was found");
		
		cart.get().removeProductQuantity(product.get(), total);
		product.get().addStock(total);
		
		ShoppingCart updated = shoppingCartRepository.save(cart.get());
		productRepository.save(product.get());
		
		return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
	}
	
	@DeleteMapping("carts/{shoppingCartId}")
	public ResponseEntity<ShoppingCart> delete(@PathVariable(value="shoppingCartId") int cartId)
	{
		Optional<ShoppingCart> cart = shoppingCartRepository.findById(cartId);
		
		if (cart.isEmpty())
			throw new RuntimeException("no shopping cart was found");
		
		if (!cart.get().getProductQuantities().isEmpty()) {
			List<Product> productList = new ArrayList<Product>();
			for (Map.Entry<Integer, Integer> entry : cart.get().getProductQuantities().entrySet()) {
				Product product = productRepository.getOne(entry.getKey());
				if (product != null) {
					product.addStock(entry.getValue());
					productList.add(product);
				}
			}
			
			if (!productList.isEmpty())
				productRepository.saveAll(productList);
		}
		
		shoppingCartRepository.deleteById(cartId);
		
		return new ResponseEntity<ShoppingCart>(HttpStatus.NO_CONTENT);
	}
}
