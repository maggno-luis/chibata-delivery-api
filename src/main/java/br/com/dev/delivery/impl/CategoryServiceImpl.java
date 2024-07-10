package br.com.dev.delivery.impl;

import br.com.dev.delivery.entites.Category;
import br.com.dev.delivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;
    public Category listCategoriesAndProducts(Long category_id){
        return categoryRepository.findCategoriesWithProducts(category_id);
    }
}
