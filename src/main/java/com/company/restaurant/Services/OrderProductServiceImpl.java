package com.company.restaurant.Services;

import com.company.restaurant.Models.OrderFood;
import com.company.restaurant.Repositories.OrderFoodRepository;
import com.company.restaurant.Services.interfaces.OrderProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderFoodRepository orderProductRepository;

    public OrderProductServiceImpl(OrderFoodRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderFood create(OrderFood orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}