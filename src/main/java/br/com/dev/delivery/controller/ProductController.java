package br.com.dev.delivery.controller;

import br.com.dev.delivery.dto.CreateProdutct;
import br.com.dev.delivery.entites.Category;
import br.com.dev.delivery.entites.Product;
import br.com.dev.delivery.exception.UserFoundException;
import br.com.dev.delivery.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> create(@RequestParam("name") String name,
                                    @RequestParam("category_id") Category category,
                                    @RequestParam("file")MultipartFile file,
                                    @RequestParam("price") Double price){
        try{
            CreateProdutct dto = new CreateProdutct();
            dto.setName(name);
            dto.setCategory(category);
            dto.setImage(file);
            dto.setPrice(price);
            return ResponseEntity.status(HttpStatus.CREATED).body(productServiceImpl.create(dto));
        }catch (UserFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @GetMapping
    public Page<Product> listAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size){
        return productServiceImpl.listAll(page, size);
    }

    @GetMapping("/search")
    public Page<Product> listByName(@RequestParam String name,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size){
        return productServiceImpl.listByName(name, page, size);
    }

}
