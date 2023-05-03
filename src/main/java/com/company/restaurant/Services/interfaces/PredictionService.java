package com.company.restaurant.Services.interfaces;

import com.company.restaurant.Models.Order;

public interface PredictionService {
    Order setPredictedDate(Order order, Integer numOfOrdersBefore) throws Exception;
}
