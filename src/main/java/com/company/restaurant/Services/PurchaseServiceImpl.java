package com.company.restaurant.Services;

import com.company.restaurant.DTO.AjaxResponseBody;
import com.company.restaurant.DTO.OrderForm;
import com.company.restaurant.Repositories.*;
import com.company.restaurant.Services.interfaces.ProductService;
import com.company.restaurant.Services.interfaces.PurchaseService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final OrderProductServiceImpl orderProductService;
    private final ProductService productService;
    private final UsersRepository usersRepository;
    private final OrderServiceImpl orderService;
    private final OrderFoodRepository orderFoodRepository;
    private final OrderRepository orderRepository;
    private final DiningTablesRepository diningTablesRepository;
    private final DiningTableTrackRepository diningTableTrackRepository;

    public PurchaseServiceImpl(OrderProductServiceImpl orderProductService, ProductService productService, UsersRepository usersRepository, OrderServiceImpl orderService, OrderFoodRepository orderFoodRepository, OrderRepository orderRepository, DiningTablesRepository diningTablesRepository, DiningTableTrackRepository diningTableTrackRepository) {
        this.orderProductService = orderProductService;
        this.productService = productService;
        this.usersRepository = usersRepository;
        this.orderService = orderService;
        this.orderFoodRepository = orderFoodRepository;
        this.orderRepository = orderRepository;
        this.diningTablesRepository = diningTablesRepository;
        this.diningTableTrackRepository = diningTableTrackRepository;
    }

    @Override
    public AjaxResponseBody purchaseOrder(OrderForm form) {
        return null;
    }
}
