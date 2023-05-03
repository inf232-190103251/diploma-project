package com.company.restaurant.Methods;

import com.company.restaurant.DTO.OrderProductDto;
import com.company.restaurant.Exceptions.ResourceNotFoundException;
import com.company.restaurant.Methods.interfaces.ValidateExistence;
import com.company.restaurant.Services.interfaces.ProductService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ValidationExistence implements ValidateExistence {

    @Override
    public void validateExistence(List<OrderProductDto> orderProducts, ProductService productService) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getName()))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(list)) {
            throw new ResourceNotFoundException("Product not found");
        }
    }
}
