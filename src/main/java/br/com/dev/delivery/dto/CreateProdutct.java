package br.com.dev.delivery.dto;

import br.com.dev.delivery.entites.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class CreateProdutct {

        @NotBlank(message = "Digite seu nome")
        @Size(min = 1, message = "Digite um nome válido!")
        private String name;
        private MultipartFile image;
        private Category category;
        private Double price;
        public CreateProdutct(String name, Category category, MultipartFile image, Double price) {
                this.name = name;
                this.category = category;
                this.image = image;
                this.price = price;
        }
}
