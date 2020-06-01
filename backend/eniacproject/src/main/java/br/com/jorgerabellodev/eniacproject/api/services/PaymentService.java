package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Payment;
import br.com.jorgerabellodev.eniacproject.api.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public void save(Payment payment) {
        repository.save(payment);
    }
}
