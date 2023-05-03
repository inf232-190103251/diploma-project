package com.company.restaurant.Services;

import com.company.restaurant.Models.DiningTableTrack;
import com.company.restaurant.Models.enums.OrderStatus;
import com.company.restaurant.Repositories.DiningTableTrackRepository;
import com.company.restaurant.Services.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private DiningTableTrackRepository repository;

    @Override
    public List<DiningTableTrack> getReadyOrders() {
        return repository.findAllByOrderidStatus(OrderStatus.DONE.name());
    }
}
