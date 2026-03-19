package pe.edu.vallegrande.app.service.impl;

import pe.edu.vallegrande.app.model.Customer;
import pe.edu.vallegrande.app.repository.CustomerRepository;
import pe.edu.vallegrande.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Flux<Customer> findAll() {
        log.info("Buscando todos los clientes sin filtro");
        return customerRepository.findAll();
    }

    @Override
    public Flux<Customer> findAllActive() {
        log.info("Listando solo clientes con estado Activo (A)");
        // Asumiendo que crearás este método en el Repository
        return customerRepository.findByState("A");
    }

    @Override
    public Mono<Customer> findById(String id) {
        log.info("Buscando cliente con ID: {}", id);
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        log.info("Insertando nuevo cliente: {}", customer.getDni());
        customer.setState("A"); // Estado Activo por defecto
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        log.info("Iniciando actualización para ID: {}", customer.getId());
        return customerRepository.findById(customer.getId())
                .flatMap(existingCustomer -> {
                    // Mantener el estado original o forzar Activo
                    customer.setState(existingCustomer.getState());
                    return customerRepository.save(customer);
                })
                .doOnSuccess(c -> log.info("Cliente actualizado correctamente"));
    }

    @Override
    public Mono<Customer> deleteById(String id) {
        log.warn("Solicitud de eliminación lógica para ID: {}", id);
        return customerRepository.findById(id)
                .flatMap(customer -> {
                    customer.setState("I"); // "I" de Inactivo
                    return customerRepository.save(customer);
                })
                .doOnSuccess(c -> log.info("Cliente marcado como Inactivo exitosamente"));
    }
}