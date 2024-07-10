package br.com.dev.delivery.impl;

import br.com.dev.delivery.dto.ItemDto;
import br.com.dev.delivery.dto.OrderDto;
import br.com.dev.delivery.dto.OrderResponseDto;
import br.com.dev.delivery.entites.Item;
import br.com.dev.delivery.entites.Order;
import br.com.dev.delivery.repository.OrderRepository;
import br.com.dev.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDto processOrder(OrderDto orderDto){

        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setAddress(orderDto.getAddress());
        order.setPaymentMethod(orderDto.getPaymentMethod());

        for (ItemDto itemDto: orderDto.getItems()){
            Item item = new Item();
            item.setName(itemDto.getName());
            item.setPrice(itemDto.getPrice());
            item.setQuantity(itemDto.getQuantity());

            order.addItem(item);

        }

        orderRepository.save(order);
        return new OrderResponseDto(true, "Pedido realizado com sucesso!");
    }
}
