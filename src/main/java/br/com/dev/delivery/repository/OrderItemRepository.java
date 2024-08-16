package br.com.dev.delivery.repository;

import br.com.dev.delivery.entites.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Item, Long> {
}
