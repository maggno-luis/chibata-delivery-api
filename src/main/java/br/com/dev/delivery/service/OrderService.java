package br.com.dev.delivery.service;

import br.com.dev.delivery.dto.OrderDto;
import br.com.dev.delivery.dto.OrderResponseDto;

public interface OrderService {

    OrderResponseDto processOrder(OrderDto orderDto);
}
