package com.company.restaurant.Repositories;

import com.company.restaurant.Models.DiningTables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTablesRepository extends CrudRepository<DiningTables, Long> {
    DiningTables findByID(Long ID);
    @Query("select d.section from DiningTables d where d.ID=?1")
    String findSectionbyTable_id(Long id);
}
