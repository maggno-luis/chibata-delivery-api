package br.com.dev.delivery.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String houseNumber;
    private String neighborhood;
    private String complement;
}
