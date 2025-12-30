package com.corpevent.corpevent.dto.request;


import com.corpevent.corpevent.enums.ClientStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestCreateDto {

    @NotNull(message = "Evenimentul este obligatoriu")
    private Long eventId;

    @NotBlank(message = "Numele solicitantului este obligatoriu")
    @Size(min = 2, message = "Numele trebuie să aibă minim 2 caractere")
    private String requesterName;

    @NotBlank(message = "Compania este obligatorie")
    private String company;

    @NotBlank(message = "Telefonul este obligatoriu")
    @Pattern(
            regexp = "^(\\+40|0)[0-9]{9}$",
            message = "Număr de telefon invalid. Exemplu valid: 07XXXXXXXX sau +407XXXXXXXX"
    )
    private String phone;
    @NotBlank(message = "Emailul este obligatoriu")
    @Email(message = "Email invalid")
    private String email;

    @NotNull(message =  "Statusul clientului este obligatoriu")
    private ClientStatus clientStatus;
}
