package com.company.restaurant.Services.interfaces;

import com.company.restaurant.Models.OrderFood;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderProductService {

    OrderFood create(@NotNull(message = "The products for order cannot be null.") @Valid OrderFood orderProduct);
}