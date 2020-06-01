package br.com.jorgerabellodev.eniacproject.api.repositories;

import br.com.jorgerabellodev.eniacproject.api.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
