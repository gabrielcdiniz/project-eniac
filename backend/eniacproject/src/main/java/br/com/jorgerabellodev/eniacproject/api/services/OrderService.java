package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.errors.exceptions.ResourceNotFoundException;
import br.com.jorgerabellodev.eniacproject.api.models.entities.ExchangePayment;
import br.com.jorgerabellodev.eniacproject.api.models.entities.Order;
import br.com.jorgerabellodev.eniacproject.api.models.entities.OrderItem;
import br.com.jorgerabellodev.eniacproject.api.models.entities.enums.PaymentStatus;
import br.com.jorgerabellodev.eniacproject.api.repositories.OrderRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ExchangeService exchangeService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final CustomerService customerService;

    @Autowired
    public OrderService(
            OrderRepository repository,
            ExchangeService exchangeService,
            PaymentService paymentService,
            ProductService productService,
            OrderItemService orderItemService,
            CustomerService customerService) {
        this.repository = repository;
        this.exchangeService = exchangeService;
        this.paymentService = paymentService;
        this.productService = productService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> order = repository.findById(id);
        return order.orElseThrow(() -> new ResourceNotFoundException(
                "Resource not found ! Id: " + id + ", Type: " + Order.class.getName()));
    }

    @Transactional
    public Order save(Order order) {
        customerService.findById(order.getCustomer().getId());
        order.setId(null);
        order.setOrderDate(new Date());
        order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
        order.getPayment().setOrder(order);
        if (order.getPayment() instanceof ExchangePayment) {
            ExchangePayment exchangePayment = (ExchangePayment) order.getPayment();
            exchangeService.fillExchangePayment(exchangePayment, order.getOrderDate());
        }
        Order savedOrder = repository.save(order);
        paymentService.save(savedOrder.getPayment());

        for (OrderItem orderItem : order.getItems()) {
            orderItem.setDiscount(BigDecimal.ZERO);
            orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
            orderItem.setPrice(orderItem.getProduct().getPrice());
            orderItem.setOrder(order);
        }
        orderItemService.save(order.getItems());
        return order;
    }
}
