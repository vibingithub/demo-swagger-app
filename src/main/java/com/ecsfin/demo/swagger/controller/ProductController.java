package com.ecsfin.demo.swagger.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecsfin.demo.swagger.model.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product",description = "Product API")
@SecurityRequirement(name = "BearerAuth")
public class ProductController {

	ArrayList<Product> products = new ArrayList<Product>();
	
	@Operation(summary = "Get all Products",
			description = "All Products will be listed",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Sucess"
							),
					@ApiResponse(responseCode = "403",
					description = "Authorization Error"
					)
			}
			)
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@Operation(summary = "Get Specific product",
			description = "Get particular product using product name",
					responses = {
							@ApiResponse(responseCode = "200",
									description = "Sucess"
									),
							@ApiResponse(responseCode = "403",
							description = "Authorization Error"
							)
					}
			)
	@GetMapping("/{productName}")
	public ResponseEntity<Product> getProduct(@Parameter(name = "productName", 
														description = "Valid Product Name", 
														example = "Laptop", 
														required = true, allowEmptyValue = false)	
											@PathVariable(name = "productName")  String productName) throws Exception{
		Optional<Product> product = products.stream()
							.filter(p->p.getName().equals(productName))
							.findFirst();
		product.orElseThrow(()->new NoSuchElementException("Product Not Found"));
		return new ResponseEntity<Product>(product.get(),HttpStatus.OK);
	}
	
	@Operation(summary = "Add Product",
			description = "Add new product",
			responses = {
					@ApiResponse(responseCode = "200",
							description = "Sucess"
							),
					@ApiResponse(responseCode = "403",
					description = "Authorization Error"
					)}
			)
	@PostMapping
	public ResponseEntity<Product> upsertProduct(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
				description = "Product to add", required = true,
				content = @Content(schema = @Schema(implementation = Product.class))
			)
			@RequestBody Product product) throws Exception{
		Optional<Product> opsProduct = products.stream()
				.filter(p->p.getName().equals(product.getName()))
				.findFirst();
		
		if(opsProduct.isPresent())
			throw new Exception("Product Already Exists");
		
		products.add(product);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}
}
