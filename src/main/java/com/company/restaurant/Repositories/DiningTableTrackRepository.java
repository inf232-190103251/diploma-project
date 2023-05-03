package com.company.restaurant.Repositories;

import com.company.restaurant.Models.DiningTableTrack;
import com.company.restaurant.Models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiningTableTrackRepository extends CrudRepository<DiningTableTrack,Long> {
    @Query("select count(u.orderid) from DiningTableTrack u where u.diningTablesid=?1")
    int CountofOrderByTableID(Long id);
    void deleteByOrderid(Order orderid);
    DiningTableTrack findByOrderid(Order orderid);
    List<DiningTableTrack> findAllByOrderidStatus(String orderid_status);
}