package br.com.dev.delivery.dto;

import br.com.dev.delivery.entites.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private String userId;
    private List<ItemDto> items;
    @Column(name = "payment_method")
    private String paymentMethod;
    private Address address;
}
