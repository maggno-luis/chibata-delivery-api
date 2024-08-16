package br.com.dev.delivery.dto;

import br.com.dev.delivery.entites.Address;
import br.com.dev.delivery.entites.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequestDto {

    private String userid;
    private List<Order> items;
    private Address address;
    private String paymentMethod;
}
