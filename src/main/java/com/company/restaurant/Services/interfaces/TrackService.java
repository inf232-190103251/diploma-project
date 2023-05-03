package com.company.restaurant.Services.interfaces;

import com.company.restaurant.Models.DiningTableTrack;

import java.util.List;

public interface TrackService {
    List<DiningTableTrack> getReadyOrders();
}
