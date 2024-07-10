package br.com.dev.delivery.service;

import br.com.dev.delivery.dto.CreateProdutct;
import br.com.dev.delivery.entites.Product;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ProductService {

    Product create(CreateProdutct createProdutct) throws IOException;
    Page<Product> listAll(int page, int size);

    Page<Product> listByName(String name, int page, int size);

}
