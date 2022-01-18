package com.project.ChocoWorld.repositories;

import com.project.ChocoWorld.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Integer> {
    Product findFirstByName(String name);
}
