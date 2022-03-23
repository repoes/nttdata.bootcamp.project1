package com.nttdata.bootcamp.bankapp;

import com.nttdata.bootcamp.bankapp.model.Product;
import com.nttdata.bootcamp.bankapp.repository.ProductRedisRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableCaching
public class BankappApplication implements CommandLineRunner {

    private final ProductRedisRepository productRedisRepository;

    @Autowired
    public BankappApplication(ProductRedisRepository productRedisRepository) {
        this.productRedisRepository = productRedisRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        Product[] array = new Product[8];
        array[0] = new Product(1, "PASIVO_CUENTA_AHORROS", "PASIVO", "AHORROS", new BigDecimal(1.5), 10);
        array[1] = new Product(2, "PASIVO_CUENTA_CORRIENTE", "PASIVO", "CORRIENTE", new BigDecimal(2.5), 5);
        array[2] = new Product(3, "PASIVO_CUENTA_PLAZOFIJO", "PASIVO", "PLAZOFIJO", new BigDecimal(10), 2);
        array[3] = new Product(4, "ACTIVO_CREDITO_PERSONAL", "CREDITO", "PERSONAL", new BigDecimal(0), 0);
        array[4] = new Product(5, "ACTIVO_CREDITO_EMPRESARIAL", "CREDITO", "TARJETA", new BigDecimal(9), 1);
        array[5] = new Product(6, "ACTIVO_CREDITO_TARJETA", "CREDITO", "AHORROS", new BigDecimal(1.5), 10);
        array[6] = new Product(7, "CUENTA_AHORROS_VIP", "PASIVO", "AHORROS", new BigDecimal(10), 5);
        array[7] = new Product(8, "MONEDERO BOOTCOIN", "PASIVO", "MONEDERO", new BigDecimal(20), 5);
        
        for(int i = 0 ; i < 8 ; i++){
            productRedisRepository.save(array[i]);
        }
    }

}
