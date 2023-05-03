package com.company.restaurant.Services.interfaces;

import com.company.restaurant.Models.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

    List<Order> getMyOrder(String username);

    Order findById(Long order_id);

    Order saveWithPredictions(Order paid) throws Exception;

    Order payTheOrder(Order order) throws Exception;

    Integer getOrdersInQueue();

    void setDone(Integer order_id);

    void deleteTheOrder(Long id, String name);
}