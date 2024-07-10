package br.com.dev.delivery.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Column(name = "payment_method")
    private String paymentMethod;
    private String status;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        item.setOrder(this);
        this.items.add(item);
    }

}
