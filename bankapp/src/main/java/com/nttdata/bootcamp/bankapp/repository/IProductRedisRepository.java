package com.nttdata.bootcamp.bankapp.repository;

import com.nttdata.bootcamp.bankapp.model.Product;
import java.util.Map;

public interface IProductRedisRepository {

    Map<String, Product> findAll();

    Product findById(String id);

    void save(Product student);

    void delete(String id);
}
