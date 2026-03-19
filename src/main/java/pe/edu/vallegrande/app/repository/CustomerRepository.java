package pe.edu.vallegrande.app.repository;

import pe.edu.vallegrande.app.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    
    // Agrega esta línea para que el Service deje de marcar error
    Flux<Customer> findByState(String state);
}