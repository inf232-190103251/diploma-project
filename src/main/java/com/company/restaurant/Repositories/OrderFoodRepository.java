package com.company.restaurant.Repositories;

import com.company.restaurant.Models.OrderFood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderFoodRepository extends CrudRepository<OrderFood, Long> {
    List<OrderFood> findAllByOrderId(Long order_ID);
}
