package br.com.dev.delivery.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUser {

        @NotBlank(message = "Digite seu nome")
        @Size(min = 1, message = "Digite um nome válido!")
        private String name;

        @NotBlank(message = "Digite o seu e-mail")
        @Email(message = "Digite um e-mail válido")
        private String email;

        @NotBlank(message = "Digite a sua senha")
        @Size(min = 8, message = "Digite um senha maior que 8 caracteres!")
        private String password;
        private String pathImage;
        public CreateUser(String name, String email, String password, String pathImage) {
                this.name = name;
                this.email = email;
                this.password = password;
                this.pathImage = pathImage;
        }
}
