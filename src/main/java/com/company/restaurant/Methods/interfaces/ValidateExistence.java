package com.company.restaurant.Methods.interfaces;

import com.company.restaurant.DTO.OrderProductDto;
import com.company.restaurant.Services.interfaces.ProductService;

import java.util.List;

public interface ValidateExistence {
     void validateExistence(List<OrderProductDto> orderProducts, ProductService productService);
}
