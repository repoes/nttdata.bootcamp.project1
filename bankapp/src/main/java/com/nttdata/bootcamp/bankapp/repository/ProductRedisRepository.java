package com.nttdata.bootcamp.bankapp.repository;

import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import com.nttdata.bootcamp.bankapp.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRedisRepository implements IProductRedisRepository {
        private static final String KEY = "Product";
    
    private RedisTemplate<String, Product> redisTemplate;
    private HashOperations hashOperations;

    public ProductRedisRepository(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, Product> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public Product findById(String id) {
        return (Product) hashOperations.get(KEY, id);
    }

    @Override
    public void save(Product product) {
        hashOperations.put(KEY, String.valueOf(product.getId()), product);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }
}
