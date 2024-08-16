package br.com.dev.delivery.impl;

import br.com.dev.delivery.dto.CreateProdutct;
import br.com.dev.delivery.entites.Product;
import br.com.dev.delivery.repository.ProductRepository;
import br.com.dev.delivery.service.ProductService;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AmazonS3 amazonS3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Override
    public Product create(CreateProdutct dto) throws IOException {
        String imageUrl = null;
        if (dto.getImage() != null){
            imageUrl = uploadImage(dto.getImage());
        }
        var product = new Product();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPathImage(imageUrl);
        product.setPrice(dto.getPrice());
        productRepository.save(product);
        return product;
    }

    private String uploadImage(MultipartFile multipartFile){
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartToFile(multipartFile);
            amazonS3Client.putObject(bucketName, fileName, file);
            file.delete();
            return amazonS3Client.getUrl(bucketName, fileName).toString();
        }catch (Exception e){
            System.out.println("error=================================================");
            return "";
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convertFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream outputStream = new FileOutputStream(convertFile);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return convertFile;
    }
    @Override
    public Page<Product> listAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> listByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
