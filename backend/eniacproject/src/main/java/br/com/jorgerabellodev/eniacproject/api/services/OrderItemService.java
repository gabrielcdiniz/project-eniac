package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.models.entities.OrderItem;
import br.com.jorgerabellodev.eniacproject.api.repositories.OrderItemRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public void save(Set<OrderItem> items) {
        repository.saveAll(items);
    }
}
