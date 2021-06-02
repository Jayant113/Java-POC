package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProductDto;
import com.app.dto.ResponseDto;
import com.app.pojos.Category;
import com.app.pojos.Product;
import com.app.service.ICategoryService;
import com.app.service.IProductService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService prodService;
	
	@Autowired
	private ICategoryService catService;
	
	// add new product with stock
	@PostMapping("/add")
	public ResponseEntity<?> addNewProduct(@RequestBody ProductDto input){
		return new ResponseEntity<>(new ResponseDto<>("success", prodService.addProduct(input)),HttpStatus.CREATED);
	}
	
	// show all by category
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable String categoryName){
		Category cat = catService.findByName(categoryName);
		return new ResponseEntity<>(new ResponseDto<List<Product>>("success", prodService.getProductsByCategory(cat.getId())),HttpStatus.OK);
	}
	
	// 	show stock with category
	@GetMapping("/stock/category-report/{categoryName}")
	public ResponseEntity<?> getStockReportByCategory(@PathVariable String categoryName ) {
		List<ProductDto> product_details = prodService.getStockReportByCategory(categoryName);
		return new ResponseEntity<>(new ResponseDto<List<ProductDto>>("success", product_details), HttpStatus.OK);
	}
		
	// show all product details with stock 
	@GetMapping("/detail/{pid}")
	public ResponseEntity<?> getProductDetail(@PathVariable Integer pid){
		ProductDto productDetail = prodService.getProductDetail(pid);
		return new ResponseEntity<>(new ResponseDto<>("success",productDetail), HttpStatus.OK);
	}

	// edit product
	@PutMapping("/edit")
	public ResponseEntity<?> editProduct(@RequestBody ProductDto input){
		ProductDto newProduct =prodService.editProduct(input);
		return new ResponseEntity<>(new ResponseDto<>("success", newProduct),HttpStatus.OK);
	}

	// delete product 
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer pid){
		return new ResponseEntity<>(new ResponseDto<String>("success", prodService.deleteProduct(pid)),HttpStatus.OK);
	}
}