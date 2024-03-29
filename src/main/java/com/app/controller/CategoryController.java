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

import com.app.dto.ResponseDto;
import com.app.pojos.Category;
import com.app.service.ICategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService catService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addNewCategory(@RequestBody Category category){
		return new ResponseEntity<>(new ResponseDto<Category>("success", catService.addOrEditCategory(category)),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCategories(){
		return new ResponseEntity<>(new ResponseDto<List<Category>>("success", catService.getAllCategories()),HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<?> editCategory(@RequestBody Category category){
		return new ResponseEntity<>(new ResponseDto<Category>("success", catService.addOrEditCategory(category)),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer cid){
		return new ResponseEntity<>(new ResponseDto<String>("success",catService.deleteCategoryById(cid)),HttpStatus.OK);
	}
}