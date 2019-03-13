package com.granovskiy.service;

import com.granovskiy.model.Category;
import com.granovskiy.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private static List<Category> categories = new ArrayList<>();

    static {
        Product product = new Product("iPhone", "Apple product", 999.99);
        Product product2 = new Product("samsung", "korean product", 700.99);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        Category category = new Category(1L, "Mobile Phones", "Best ever phones");
        category.setProducts(products);
        categories.add(category);
        categories.add(new Category(2L, "Shoes", "Italian shoes"));
        categories.add(new Category(3L, "TVs", "Chines TVs"));
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Optional<Category> getById(Long id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
