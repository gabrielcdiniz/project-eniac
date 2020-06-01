package br.com.jorgerabellodev.eniacproject.api.services;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Address;
import br.com.jorgerabellodev.eniacproject.api.repositories.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public void saveAll(List<Address> adresses) {
        repository.saveAll(adresses);
    }
}
