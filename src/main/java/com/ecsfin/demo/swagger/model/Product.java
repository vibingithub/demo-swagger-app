package com.ecsfin.demo.swagger.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

	private String name;
	private String description;
	private BigDecimal price;
	private String type;
}
