package com.granovskiy.service;

import com.granovskiy.model.Category;
import com.granovskiy.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private static List<Category> categories = new ArrayList<>();

    static {
        //  products1
        List<Product> products1 = new ArrayList<>();
        Product product1 = new Product("iPhone", "Apple product", 999.99);
        Product product2 = new Product("samsung", "korean product", 700.99);
        Product product3 = new Product("huawai", "korean product", 650.99);
        products1.add(product1);
        products1.add(product2);
        products1.add(product3);

        //  category1 and its products
        Category category1 = new Category(1L, "Mobile Phones", "Best ever phones");
        category1.setProducts(products1);
        categories.add(category1);

        //  category2 and its products
        categories.add(new Category(2L, "Shoes", "Excellent shoes"));

        //  category3 and its products
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
