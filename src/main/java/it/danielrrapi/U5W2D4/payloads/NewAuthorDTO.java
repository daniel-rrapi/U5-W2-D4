package it.danielrrapi.U5W2D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 3, max = 20, message = "Il nome deve essere compreso tra 3 e 20 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 20, message = "Il cognome deve essere compreso tra 3 e 20 caratteri")
        String cognome,
        @NotEmpty(message = "L'email è obbligaria")
        @Email(message = "Non hai inserito una email valida")
        String email,
        LocalDate dob
        ) {
}
