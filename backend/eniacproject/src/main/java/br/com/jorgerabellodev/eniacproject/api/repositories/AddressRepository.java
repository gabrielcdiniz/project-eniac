package br.com.jorgerabellodev.eniacproject.api.repositories;

import br.com.jorgerabellodev.eniacproject.api.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByPostalCode(String postalCode);
}
