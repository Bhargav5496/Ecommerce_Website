package com.sheryians.major.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.dto.productDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	CategoryService categoryService;
	
	
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories",categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
		
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("categories") Category category){
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String getCatDelete(@PathVariable  int id) {
		 categoryService.deleteCategoryById(id);
		 return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String getCatUpdate(@PathVariable int id,  Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent()){
			model.addAttribute("category",category.get());
			System.out.println(category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	
//	------------------------------------------------------------------------------------------------------
	
//	product section
	
//	------------------------------------------------------------------------------------------------------
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin/products")
	public String getProd(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getProdAdd(Model model) {
		model.addAttribute("productDTO", new productDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}	
	
	
	@PostMapping("/admin/products/add")
	public String postProdAdd(@ModelAttribute("productDTO") productDTO productDTO) {
		
	}
	
	
	
}