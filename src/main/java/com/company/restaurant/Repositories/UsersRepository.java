package com.company.restaurant.Repositories;

import com.company.restaurant.Models.Usr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Usr,Long> {
    Usr findUsersByUsername(String username);
    Usr findByUsername(String username);

}
