package pe.edu.vallegrande.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate; 
@Data
@Document(collection = "customer")
public class Customer {

    @Id
    private String id;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;        
    private String phoneNumber;  
    private LocalDate birthDate;
    private String state;
    
}