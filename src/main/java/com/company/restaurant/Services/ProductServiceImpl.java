package com.company.restaurant.Services;

import com.company.restaurant.Models.Food;
import com.company.restaurant.Repositories.FoodRepository;
import com.company.restaurant.Services.interfaces.ProductService;
import com.company.restaurant.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final FoodRepository productRepository;

    public ProductServiceImpl(FoodRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Food> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Food getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Food save(Food product) {
        return productRepository.save(product);
    }

    @Override
    public Food getProduct(String name) {
        return productRepository.findFoodByName(name);
    }
}