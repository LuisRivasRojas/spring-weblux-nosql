package pe.edu.vallegrande.app.service;

import pe.edu.vallegrande.app.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService { // Cambiado de InterfazCustomerService a CustomerService

    Flux<Customer> findAll();

    Flux<Customer> findAllActive();

    Mono<Customer> findById(String id);

    Mono<Customer> save(Customer customer);

    Mono<Customer> update(Customer customer);

    Mono<Customer> deleteById(String id); 
}