package com.project.ChocoWorld.repositories;

import com.project.ChocolateWorld.entities.Order;
import com.project.ChocolateWorld.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
