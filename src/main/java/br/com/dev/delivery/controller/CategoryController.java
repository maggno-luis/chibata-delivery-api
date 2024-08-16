package br.com.dev.delivery.controller;

import br.com.dev.delivery.entites.Category;
import br.com.dev.delivery.impl.CategoryServiceImpl;
import br.com.dev.delivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Category create(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping
    public List<Category> listAll(){
        return categoryRepository.findAll();
    }
    @GetMapping("/{category_id}")
    public Category listCategoriesAndProducts(@PathVariable("category_id") Long category_id){
        return categoryServiceImpl.listCategoriesAndProducts(category_id);
    }

}
