package com.company.restaurant.Services.interfaces;

import com.company.restaurant.DTO.AjaxResponseBody;
import com.company.restaurant.DTO.OrderForm;

public interface PurchaseService {
    AjaxResponseBody purchaseOrder(OrderForm form);
}
