package com.company.restaurant.Services;

import com.company.restaurant.Models.DiningTableTrack;
import com.company.restaurant.Models.Order;
import com.company.restaurant.Models.OrderFood;
import com.company.restaurant.Models.enums.OrderStatus;
import com.company.restaurant.Repositories.DiningTableTrackRepository;
import com.company.restaurant.Repositories.OrderFoodRepository;
import com.company.restaurant.Repositories.OrderRepository;
import com.company.restaurant.Services.interfaces.OrderService;
import com.company.restaurant.Services.interfaces.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    private PredictionService predictionService;
    @Autowired
    private OrderFoodRepository orderFoodRepository;
    @Autowired
    private DiningTableTrackRepository diningTableTrackRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
//        sender.send(order.getUser().getUsername(), "ORDER", "You have ordered products for " + order.getTotalOrderPrice());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> getMyOrder(String username) {
        return this.orderRepository.findByUserUsername(username);
    }

    @Override
    public Order findById(Long order_id) {
        return orderRepository.findById(order_id).get();
    }

    @Override
    public Order saveWithPredictions(Order paid) throws Exception {
        Integer ordersInQueue = getOrdersInQueue();
        paid = predictionService.setPredictedDate(paid, ordersInQueue);
        return create(paid);
    }

    @Override
    @Transactional
    public Order payTheOrder(Order paid) throws Exception {
        ZonedDateTime date = ZonedDateTime.now();
        paid.setStatus(OrderStatus.PAID.name());
        paid.setPurchaseDate(date);
        return saveWithPredictions(paid);
    }

    @Override
    public Integer getOrdersInQueue() {
        Integer countAllByStatus = orderRepository.countAllByStatus(OrderStatus.PAID.name().toUpperCase());
        System.out.println("Cooking orders : " + countAllByStatus);
        return countAllByStatus;
    }

    @Override
    public void setDone(Integer order_id) {
        Order order = orderRepository.findById(Long.valueOf(order_id)).get();
        ZonedDateTime date = ZonedDateTime.now();
        System.out.println("Zoned : " + date);
        order.setStatus(OrderStatus.DONE.name());
        order.setDoneDate(date);
        orderRepository.save(order);
    }

    @Override
    public void deleteTheOrder(Long id, String name) {
        Order deletable = orderRepository.findByIdAndUserUsername(id, name);

        if (deletable.getStatus().equals("WAITING") || deletable.getStatus().equals("WITHCASH") || deletable.getStatus().equals("WITHWAITER")) {
            List<OrderFood> orderFoods = orderFoodRepository.findAllByOrderId(id);

            DiningTableTrack diningTableTrack = diningTableTrackRepository.findByOrderid(deletable);
            if (diningTableTrack != null) {
                diningTableTrackRepository.delete(diningTableTrack);
            }
            orderFoodRepository.deleteAll(orderFoods);
            orderRepository.delete(deletable);
        }
    }

}